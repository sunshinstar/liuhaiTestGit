package testdemo.socket.third;

import org.junit.jupiter.api.Test;
import testdemo.socket.third.server.Server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author liuhai
 * @date 2019/8/14 18:02
 */
public class NioTest {

    @Test
    void server() {

        new Thread(new Server(9002)).start();

    }

    @Test
    void client() {
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 9002);
        SocketChannel channel = null;
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            //打开通道
            channel = SocketChannel.open();
            //建立链接
            channel.connect(inetSocketAddress);
            while (true) {
                byte[] bytes = new byte[1024];
                System.in.read(bytes);
                //把输入的数据放入缓冲区
                buffer.put(bytes);
                //复位操作
                buffer.flip();
                //将buffer的数据写入通道
                channel.write(buffer);
                //清空缓冲区的数据
                buffer.clear();

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (channel != null) {
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }


}
