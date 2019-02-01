package io.github.brightloong.netty.learn.aio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;

/**
 * @author BrightLoong
 * @date 2018/11/14 14:54
 * @description
 */
public class TimeClient {
    public static void main(String[] args) {
        int port = 8080;

        try {
            if (args != null && args.length > 0) {
                port = Integer.valueOf(args[0]);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        new Thread(new AsyncTimeClientHandler("127.0.0.1", port)).start();

    }


    private static class AsyncTimeClientHandler implements CompletionHandler<Void, AsyncTimeClientHandler>, Runnable {

        private AsynchronousSocketChannel asynchronousSocketChannel;

        private String host;

        private int port;

        private CountDownLatch countDownLatch;


        public AsyncTimeClientHandler(String host, int port) {
            this.host = host;
            this.port = port;

            try {
                asynchronousSocketChannel = AsynchronousSocketChannel.open();
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
            countDownLatch = new CountDownLatch(1);
            asynchronousSocketChannel.connect(new InetSocketAddress(host, port), this, this);
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                asynchronousSocketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * Invoked when an operation has completed.
         *
         * @param result     The result of the I/O operation.
         * @param attachment
         */
        @Override
        public void completed(Void result, AsyncTimeClientHandler attachment) {
            byte[] req = "QUERY TIME ORDER".getBytes();

            ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
            writeBuffer.put(req);
            writeBuffer.flip();

            asynchronousSocketChannel.write(writeBuffer, writeBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    if (attachment.hasRemaining()) {
                        asynchronousSocketChannel.write(attachment, attachment, this);
                    } else {
                        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                        asynchronousSocketChannel.read(readBuffer, readBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                            @Override
                            public void completed(Integer result, ByteBuffer attachment) {
                                attachment.flip();
                                byte[] bytes = new byte[attachment.remaining()];
                                attachment.get(bytes);
                                String body = new String(bytes, StandardCharsets.UTF_8);

                                System.out.println("Now is : " + body);
                                countDownLatch.countDown();
                            }

                            @Override
                            public void failed(Throwable exc, ByteBuffer attachment) {
                                try {
                                    asynchronousSocketChannel.close();
                                    countDownLatch.countDown();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    try {
                        asynchronousSocketChannel.close();
                        countDownLatch.countDown();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        /**
         * Invoked when an operation fails.
         *
         * @param exc        The exception to indicate why the I/O operation failed
         * @param attachment
         */
        @Override
        public void failed(Throwable exc, AsyncTimeClientHandler attachment) {

        }
    }
}
