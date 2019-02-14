package io.github.brightloong.java.all.learn.proxy.jdk;

/**
 * @author BrightLoong
 * @date 2019-02-14 15:02
 * @description
 */
public class SimpleProxyDemo {
    public static void main(String[] args) throws SecurityException, NoSuchMethodException {
        ProxyClassImpl c = new ProxyClassImpl();
        DynamicProxyHandler proxyHandler = new DynamicProxyHandler(c);
        IProxyClass proxyClass = (IProxyClass)proxyHandler.newProxyInstance();
        System.out.println(proxyClass.getClass().getName());
        System.out.println(proxyClass.doSomething2(5));
    }
}
