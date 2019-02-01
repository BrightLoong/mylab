package io.github.brightloong.netty.learn.netty.stick;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

/**
 * @author BrightLoong
 * @date 2018/11/18 12:12
 * @description
 */
public class TimeClient {

    public static void main(String[] args) throws InterruptedException {
        new TimeClient().connect(8080, "127.0.0.1");
    }

    public void connect(int port, String host) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
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
                            ch.pipeline().addLast(new TimeClientHandle());
                        }
                    });
            ChannelFuture f = b.connect(host, port).sync();
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }

    private class TimeClientHandle extends ChannelInboundHandlerAdapter {

        private  final Logger logger = Logger.getLogger(TimeClientHandle.class.getName());

        private byte[] req;

        public TimeClientHandle() {
            req = "QUERY TIME ORDER".getBytes();
        }

        /**
         * Calls {@link ChannelHandlerContext#fireChannelActive()} to forward
         * to the next {@link ChannelInboundHandler} in the {@link ChannelPipeline}.
         * <p>
         * Sub-classes may override this method to change behavior.
         *
         * @param ctx
         */
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            ByteBuf msg;
            for (int i = 0; i < 100; i++) {
                msg = Unpooled.buffer(req.length);
                msg.writeBytes(req);
                ctx.writeAndFlush(msg);
            }
        }

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
            String body = new String(req, StandardCharsets.UTF_8);
            System.out.println("Now is : " + body);
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
            logger.warning("Unexpected exception : " + cause.getMessage());
            ctx.close();
        }
    }
}
