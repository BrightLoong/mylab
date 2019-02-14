package io.github.brightloong.java.all.learn.proxy.jdk;

/**
 * @author BrightLoong
 * @date 2019-02-14 15:00
 * @description
 */
public class ProxyClassImpl implements IProxyClass {
    @Override
    public int doSomething(int num) {
        System.out.println("方法执行中.....");
        return num;
    }

    @Override
    public int doSomething2(int num) {
        System.out.println("方法2执行中.....");
        return num;
    }
}
