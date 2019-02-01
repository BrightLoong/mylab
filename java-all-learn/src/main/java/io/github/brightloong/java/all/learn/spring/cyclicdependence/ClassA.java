package io.github.brightloong.java.all.learn.spring.cyclicdependence;

/**
 * @author BrightLoong
 * @date 2018/9/13 11:17
 * @description
 */
public class ClassA {

    private ClassB classB;

    public ClassB getClassB() {
        return classB;
    }

    public void setClassB(ClassB classB) {
        this.classB = classB;
    }

    public void say() {
        System.out.println("I am ClassA");
    }
}
