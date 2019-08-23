package testdemo.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;


/**
 * @author liuhai
 * @date 2019/8/15 8:54
 */
public class Server {
    private int port;

    public Server(int port) {

        this.port = port;
    }

    public  void  run(){
        //用于处理服务器端接收客户端连接
        EventLoopGroup loopGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            //辅助工具类 用于服务器通道的一系列配置
            ServerBootstrap bootstrap = new ServerBootstrap();
            //绑定两个线程组
            bootstrap.group(loopGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    //配置具体的数据处理方式
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new ServerHandler());
                        }
                    })
                    //设置TCP缓冲区
                    .option(ChannelOption.SO_BACKLOG, 128)
                    //设置发送数据缓冲大小
                    .option(ChannelOption.SO_SNDBUF, 32 * 1024)
                    //设置接受数据缓冲大小
                    .option(ChannelOption.SO_RCVBUF, 32 * 1024)
                    //保持连接
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture future = bootstrap.bind(port).sync();
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();

        }finally {
            workerGroup.shutdownGracefully();
            loopGroup.shutdownGracefully();
        }
    }
    public static void main(String[] args) {
        new Server(8379).run();
    }
}
