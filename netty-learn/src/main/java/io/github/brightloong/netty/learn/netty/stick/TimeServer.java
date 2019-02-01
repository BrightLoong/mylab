package io.github.brightloong.netty.learn.netty.stick;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author BrightLoong
 * @date 2018/11/18 11:52
 * @description
 */
public class TimeServer {

    public static void main(String[] args) throws InterruptedException {
        new TimeServer().bind(8080);
    }

    public void bind(int port) throws InterruptedException {

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1204)
                    .childHandler(new ChildChannelHandler());

            ChannelFuture f = b.bind(port).sync();
            f.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

        /**
         * This method will be called once the {@link Channel} was registered. After the method returns this instance
         * will be removed from the {@link ChannelPipeline} of the {@link Channel}.
         *
         * @param ch the {@link Channel} which was registered.
         * @throws Exception is thrown if an error occurs. In that case it will be handled by
         *                   {@link #exceptionCaught(ChannelHandlerContext, Throwable)} which will by default close
         *                   the {@link Channel}.
         */
        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            ch.pipeline().addLast(new TimeServerHandler());
        }
    }

    private class TimeServerHandler extends ChannelInboundHandlerAdapter {

        private int counter;

        /**
         * Calls {@link ChannelHandlerContext#fireChannelRead(Object)} to forward
         * to the next {@link ChannelInboundHandler} in the {@link ChannelPipeline}.
         * <p>
         * Sub-classes may override this method to change behavior.
         *
         * @param ctx
         * @param msg
         */
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf buf = (ByteBuf) msg;
            byte[] req = new byte[buf.readableBytes()];
            buf.readBytes(req);
            String body = new String(req, StandardCharsets.UTF_8).substring(0, req.length - System.getProperty("line.separator").length());
            System.out.println("receive order : " + body + "; the counter is : " + ++counter);
            String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
            ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
            ctx.write(resp);

        }

        /**
         * Calls {@link ChannelHandlerContext#fireChannelReadComplete()} to forward
         * to the next {@link ChannelInboundHandler} in the {@link ChannelPipeline}.
         * <p>
         * Sub-classes may override this method to change behavior.
         *
         * @param ctx
         */
        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            ctx.flush();
        }

        /**
         * Calls {@link ChannelHandlerContext#fireExceptionCaught(Throwable)} to forward
         * to the next {@link ChannelHandler} in the {@link ChannelPipeline}.
         * <p>
         * Sub-classes may override this method to change behavior.
         *
         * @param ctx
         * @param cause
         */
        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            ctx.close();
        }
    }
}
