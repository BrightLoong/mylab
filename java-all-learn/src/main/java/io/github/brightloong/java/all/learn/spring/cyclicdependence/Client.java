package io.github.brightloong.java.all.learn.spring.cyclicdependence;

/**
 * @author BrightLoong
 * @date 2018/9/13 11:19
 * @description
 */
public class Client {

    public static void main(String[] args) {
        ObjectFactory factory = new ObjectFactory();
        ClassB classB = factory.getObject(ClassB.class);
        classB.say();
        System.out.println("-----我是分割线-----");

        ClassA classA = factory.getObject(ClassA.class);
        classA.say();
        System.out.println("classB.getClassA() == classA:" + (classB.getClassA() == classA));

        System.out.println("classA.getClassB() == classB:" + (classA.getClassB() == classB));
    }
}
