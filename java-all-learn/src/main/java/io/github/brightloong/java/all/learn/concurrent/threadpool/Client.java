package io.github.brightloong.java.all.learn.concurrent.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Client class
 *
 * @author BrightLoong
 * @date 2018/6/22
 */
public class Client {
    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(1,1,60L, TimeUnit.SECONDS,  new ArrayBlockingQueue<Runnable>(2));
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {

                }
            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("123");
            }
        });
        executorService.shutdown();

        while (true) {

        }
    }
}
