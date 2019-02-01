package io.github.brightloong.java.all.learn.concurrent.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * UseCAS class
 *
 * @author BrightLoong
 * @date 2018/6/10
 */
public class UseCAS {
    private AtomicInteger value = new AtomicInteger(0);

    public AtomicInteger getValue() {
        return value;
    }

    public static void main(String[] args) {

        UseCAS noUseCAS = new UseCAS();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    noUseCAS.getValue().getAndAdd(1);
                }
            }).start();
        }
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("最后结果：" + noUseCAS.getValue());
    }
}
