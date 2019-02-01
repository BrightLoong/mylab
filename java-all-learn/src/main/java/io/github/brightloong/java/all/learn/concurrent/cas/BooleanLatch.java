package io.github.brightloong.java.all.learn.concurrent.cas;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * BooleanLatch class
 *
 * @author BrightLoong
 * @date 2018/6/21
 */
public class BooleanLatch {

    private static class Sync extends AbstractQueuedSynchronizer {
        boolean isSignalled() { return getState() != 0; }

        protected int tryAcquireShared(int ignore) {
            return isSignalled() ? 1 : -1;
        }

        protected boolean tryReleaseShared(int ignore) {
            setState(1);
            return true;
        }
    }

    private final Sync sync = new Sync();
    public boolean isSignalled() { return sync.isSignalled(); }
    public void signal()         { sync.releaseShared(1); }
    public void await() throws InterruptedException {
        sync.acquireSharedInterruptibly(1);
    }

    public static void main(String[] args) throws InterruptedException {
        BooleanLatch latch =new BooleanLatch();


        for (int i = 0; i < 3; i ++) {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("准备就绪，开始执行");
                }
            }).start();
        }

        Thread.sleep(2000);

        /*for (int i = 0; i < 1; i ++) {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("准备工作：" + finalI);
                        Thread.sleep(2000);
                        System.out.println("准备工作完成：" + finalI);
                        latch.signal();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }*/


    }
}
