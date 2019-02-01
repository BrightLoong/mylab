package io.github.brightloong.netty.learn.aio;

import io.netty.util.internal.StringUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.concurrent.*;

/**
 * @author BrightLoong
 * @date 2018/11/14 11:52
 * @description
 */
public class TimeServer {
    public static void main(String[] args) {
        int port = 8080;

        try {
            if (args != null && args.length > 0) {
                port = Integer.valueOf(args[0]);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        AsyncTimeServerHandler asyncTimeServerHandler = new AsyncTimeServerHandler(port);
        new Thread(asyncTimeServerHandler, "AIO-asyncTimeServer-001").start();
    }


    private static class AsyncTimeServerHandler implements Runnable {

        private int port;

        CountDownLatch latch;

        AsynchronousServerSocketChannel asynchronousServerSocketChannel;


        public AsyncTimeServerHandler(int port) {
            this.port = port;

            try {
                asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();
                asynchronousServerSocketChannel.bind(new InetSocketAddress(port));

                System.out.println("The time server is start in port : " + port);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {
            latch = new CountDownLatch(1);
            doAccept();
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


        public void doAccept() {
            asynchronousServerSocketChannel.accept(this, new AcceptCompletionHandler());
        }
    }

    private static class AcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, AsyncTimeServerHandler> {

        /**
         * Invoked when an operation has completed.
         *
         * @param result     The result of the I/O operation.
         * @param attachment
         */
        @Override
        public void completed(AsynchronousSocketChannel result, AsyncTimeServerHandler attachment) {
            attachment.asynchronousServerSocketChannel.accept(attachment, this);

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            result.read(byteBuffer, byteBuffer, new ReadCompletionHandler(result));
        }

        /**
         * Invoked when an operation fails.
         *
         * @param exc        The exception to indicate why the I/O operation failed
         * @param attachment
         */
        @Override
        public void failed(Throwable exc, AsyncTimeServerHandler attachment) {
            exc.printStackTrace();
            //结束主线程
            attachment.latch.countDown();
        }
    }


    private static class ReadCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {

        private AsynchronousSocketChannel asynchronousSocketChannel;

        public ReadCompletionHandler(AsynchronousSocketChannel asynchronousSocketChannel) {
            this.asynchronousSocketChannel = asynchronousSocketChannel;
        }

        /**
         * Invoked when an operation has completed.
         *
         * @param result     The result of the I/O operation.
         * @param attachment
         */
        @Override
        public void completed(Integer result, ByteBuffer attachment) {
            attachment.flip();
            byte[] bytes = new byte[attachment.remaining()];
            attachment.get(bytes);

            String body = new String(bytes, StandardCharsets.UTF_8);
            System.out.println("The time server receive order : " + body);
            String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
            doWrite(currentTime);
        }

        /**
         * Invoked when an operation fails.
         *
         * @param exc        The exception to indicate why the I/O operation failed
         * @param attachment
         */
        @Override
        public void failed(Throwable exc, ByteBuffer attachment) {
            try {
                this.asynchronousSocketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void doWrite(String currentTime) {
            if (!StringUtil.isNullOrEmpty(currentTime)) {
                byte[] bytes = currentTime.getBytes();
                ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
                writeBuffer.put(bytes);
                writeBuffer.flip();

                asynchronousSocketChannel.write(writeBuffer, writeBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                    @Override
                    public void completed(Integer result, ByteBuffer attachment) {
                        if (attachment.hasRemaining()) {
                            asynchronousSocketChannel.write(attachment, attachment, this);
                        }
                    }

                    @Override
                    public void failed(Throwable exc, ByteBuffer attachment) {
                        try {
                            asynchronousSocketChannel.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }

}
