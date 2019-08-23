package testdemo.socket.third.server;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author liuhai
 * @date 2019/8/14 17:39
 */
public class Server implements Runnable {

    private Selector selector;
    private ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

    public Server(int port) {
        try {
            //打开多路复用器
            selector = Selector.open();
            //打开服务器通道
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            //设置服务器通道为非阻塞方式
            serverSocketChannel.configureBlocking(false);
            //绑定地址
            serverSocketChannel.socket().bind(new InetSocketAddress(port));
            //吧服务器通道注册到多路复用器上，并监听阻塞状态
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("服务端起来了===" + port);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Override
    public void run() {

        while (true) {
            try {
                //必须让多路复用器开始监听
                selector.select();
                //返回所有已经注册到多路复用器上的通道的selectorkey
                Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
                //遍历所有的key
                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();
                    keyIterator.remove();
                    if (key.isValid()) {
                        if (key.isAcceptable()) {
                            accept(key);
                        }
                        if(key.isReadable()){
                            read(key);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();

            }
        }

    }


    private  void accept(SelectionKey key){

        try {

            //获取服务器通道
            ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
            //执行阻塞方法
            SocketChannel socketChannel = ssc.accept();
            //设置阻塞 模式为非阻塞
            socketChannel.configureBlocking(false);
            //注册到多路复用器上，并设置读取标志
            socketChannel.register(selector,SelectionKey.OP_READ);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void read(SelectionKey key) {
        try {
            //清空缓冲区中的旧数据
            byteBuffer.clear();
            //获取之前检验过的socketchannel通道
            SocketChannel socketChannel = (SocketChannel) key.channel();
            //将数据放入buffer中
            int count = socketChannel.read(byteBuffer);
            if(count==-1){
                key.channel().close();
                key.channel();
                return;
            }
            //读到了数据 将buffer的position复位到0
            byteBuffer.flip();
            byte[] bytes = new byte[byteBuffer.remaining()];
            //将buffer中的数据写入到byte中
            byteBuffer.get(bytes);
            String body = new String(bytes).trim();
            System.out.println("server:"+body);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }


}
