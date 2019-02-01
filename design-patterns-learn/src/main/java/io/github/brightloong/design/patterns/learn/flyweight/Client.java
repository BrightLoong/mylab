package io.github.brightloong.design.patterns.learn.flyweight;

/**
 * Client class
 *
 * @author BrightLoong
 * @date 2018/6/11
 */
public class Client {
    public static void main(String[] args) {
        FlyWeightFactory factory = FlyWeightFactory.getInstance();
        FlyWeight flyWeight1 = factory.getFlyWeight("a");
        FlyWeight flyWeight2 = factory.getFlyWeight("b");
        FlyWeight flyWeight3 = factory.getFlyWeight("a");
        FlyWeight flyWeight4 = factory.getFlyWeight("c");

        //flyWeight1和flyWeight3具有相同的内部状态a,使用同一个实例，下面结果输出true
        System.out.println(flyWeight1 == flyWeight3);

        //不同的外部状态从方法传入
        flyWeight2.operation("hello");
        flyWeight2.operation("hi");
    }
}
