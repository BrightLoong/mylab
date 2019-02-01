package io.github.brightloong.design.patterns.learn.facade;

/**
 * @author: BrightLoong
 * @date: 2018/8/26 20:46
 * @description:
 */
public class Facde {
    private SystemOne systemOne;

    private SystemTwo systemTwo;

    public Facde(SystemOne systemOne, SystemTwo systemTwo) {
        this.systemOne = systemOne;
        this.systemTwo = systemTwo;
    }

    public void systemStart() {
        systemOne.start();
        systemTwo.start();
        System.out.println("System start");
    }

    public void systemShutdown() {
        systemOne.shutdown();
        systemTwo.shutdown();
        System.out.println("System shutdown");
    }
}
