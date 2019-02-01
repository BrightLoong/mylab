package io.github.brightloong.design.patterns.learn.facade;

import java.math.BigDecimal;

/**
 * @author: BrightLoong
 * @date: 2018/8/26 15:56
 * @description:
 */
public class Client {
    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal("0.0");
        System.out.println(bigDecimal);
        Facde facde = new Facde(new SystemOne(), new SystemTwo());
        facde.systemStart();

        System.out.println("========================");

        facde.systemShutdown();
    }
}
