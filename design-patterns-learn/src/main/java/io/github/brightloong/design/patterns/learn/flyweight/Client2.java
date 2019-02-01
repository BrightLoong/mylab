package io.github.brightloong.design.patterns.learn.flyweight;

import java.util.ArrayList;
import java.util.List;

/**
 * Client2 class
 *
 * @author BrightLoong
 * @date 2018/6/11
 */
public class Client2 {
    public static void main(String[] args) {
        FlyWeightFactory factory = FlyWeightFactory.getInstance();
        List<String> states = new ArrayList<>();
        states.add("a");
        states.add("b");
        states.add("c");
        FlyWeight flyWeight = factory.getFlyWeight(states);
        FlyWeight flyWeight2 = factory.getFlyWeight(states);

        //并不相等，输出false，复合享元对象不可共享
        System.out.println(flyWeight == flyWeight2);
        //多个单纯享元对象具有相同的外部状态hello
        flyWeight.operation("hello");
    }
}
