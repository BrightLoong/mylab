package io.github.brightloong.netty.learn.fake.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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

        ServerSocket server = null;

        try {
            server = new ServerSocket(port);
            System.out.println("The time server is start in port : " + port);
            Socket socket = null;
            TimeServerHandlerExecutePool timeServerHandlerExecutePool = new TimeServerHandlerExecutePool(50, 100);
            while (true) {
                socket = server.accept();
                timeServerHandlerExecutePool.execute(new TimeServerHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class TimeServerHandlerExecutePool {
        private ExecutorService executor;

        public TimeServerHandlerExecutePool(int maxPoolSize, int queueSize) {
            executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), maxPoolSize, 120L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(queueSize));
        }

        public void execute(Runnable task) {
            executor.execute(task);
        }
    }

    private static class TimeServerHandler implements Runnable{
        private Socket socket;

        public TimeServerHandler(Socket socket) {
            this.socket = socket;
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
            //使用try with resource
            try(Socket socket = this.socket;
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
                String currentTime = null;
                String body = null;

                while (true) {
                    body = in.readLine();
                    if (body == null) {
                        break;
                    }
                    System.out.println("The time server receive order : " + body);
                    currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
                    //注意使用println，因为使用的是readLine读取
                    out.println(currentTime);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
