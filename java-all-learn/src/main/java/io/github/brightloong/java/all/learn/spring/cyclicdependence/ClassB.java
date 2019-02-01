package io.github.brightloong.java.all.learn.spring.cyclicdependence;

/**
 * @author BrightLoong
 * @date 2018/9/13 11:17
 * @description
 */
public class ClassB {

    private ClassA classA;

    public ClassA getClassA() {
        return classA;
    }

    public void setClassA(ClassA classA) {
        this.classA = classA;
    }

    public void say() {
        System.out.println("I am ClassB. Who are you?");
        classA.say();
    }
}
