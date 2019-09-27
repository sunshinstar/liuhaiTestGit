package testdemo.thread;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.junit.jupiter.api.Test;

import java.net.URI;

/**
 * @author liuhai
 * @date 2019/9/12 16:16
 */
public class CallBackThread {

    @Test
    void test1() {

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {

            //服务端启动类
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new HttpServerInitializer());
            ChannelFuture channelFuture = bootstrap.bind(8080).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }


    @Test
    void test2(){
        String host = "127.0.0.1";
        int port = 8080;
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new HttpClientCodec());
                            pipeline.addLast(new HttpObjectAggregator(65536));
                            pipeline.addLast(new HttpClientHandler());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            channelFuture.channel().closeFuture().sync();

        } catch (Exception e) {

            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }


    }


    public class HttpClientHandler extends SimpleChannelInboundHandler<FullHttpResponse> {

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            URI uri = new URI("http://127.0.0.1:8080");
            String msg = "Are you ok?";
            FullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET,
                    uri.toASCIIString(), Unpooled.wrappedBuffer(msg.getBytes("UTF-8")));

            // 构建http请求
//        request.headers().set(HttpHeaderNames.HOST, "127.0.0.1");
//        request.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
            request.headers().set(HttpHeaderNames.CONTENT_LENGTH, request.content().readableBytes());
            // 发送http请求
            ctx.channel().writeAndFlush(request);
        }

        @Override
        public void channelRead0(ChannelHandlerContext ctx, FullHttpResponse msg) {

            FullHttpResponse response = msg;
            response.headers().get(HttpHeaderNames.CONTENT_TYPE);
            ByteBuf buf = response.content();
            System.out.println(buf.toString(io.netty.util.CharsetUtil.UTF_8));

        }
    }


    public class HttpServerInitializer extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel sc) throws Exception {
            ChannelPipeline pipeline = sc.pipeline();
            //处理http消息的编解码
            pipeline.addLast("httpServerCodec", new HttpServerCodec());
            pipeline.addLast("aggregator", new HttpObjectAggregator(65536));

            //添加自定义的ChannelHandler
            pipeline.addLast("httpServerHandler", new HttpServerChannelHandler0());
        }
    }

    public class HttpServerChannelHandler0 extends SimpleChannelInboundHandler<FullHttpRequest> {
        private HttpRequest request;

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest  msg) throws Exception {
            ctx.channel().remoteAddress();

            FullHttpRequest request = msg;

            System.out.println("请求方法名称:" + request.method().name());

            System.out.println("uri:" + request.uri());
            ByteBuf buf = request.content();
            System.out.print("11111"+ buf.toString(CharsetUtil.UTF_8));


            ByteBuf byteBuf = Unpooled.copiedBuffer("hello world", CharsetUtil.UTF_8);
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);
            response.headers().add(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().add(HttpHeaderNames.CONTENT_LENGTH, byteBuf.readableBytes());

            ctx.writeAndFlush(response);

            }
        }
    }


