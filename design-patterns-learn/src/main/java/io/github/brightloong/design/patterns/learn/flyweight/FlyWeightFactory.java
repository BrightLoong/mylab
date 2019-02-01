package io.github.brightloong.design.patterns.learn.flyweight;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FlyWeightFactory class
 * 用于创建和管理享元对象，本身是单例的。
 * @author BrightLoong
 * @date 2018/6/11
 */
public class FlyWeightFactory {

    /**唯一的factory实例*/
    private static FlyWeightFactory factory = new FlyWeightFactory();

    /**用于保存享元对象的map*/
    private Map<String, FlyWeight> flyWeights = new HashMap<>();

    /**
     * 私有的构造函数
     */
    private FlyWeightFactory() {}

    /**
     * 返回FlyWeightFactory唯一实例.
     * @return
     */
    public static FlyWeightFactory getInstance() {
        return factory;
    }

    /**
     * 单纯享元模式工厂方法.
     * @param state key
     * @return
     */
    public FlyWeight getFlyWeight(String state) {
        FlyWeight flyWeight = flyWeights.get(state);
        if (flyWeight == null) {
            flyWeight = new ConcreteFlyWeight(state);
            flyWeights.put(state, flyWeight);
        }
        return flyWeight;
    }

    /**
     * 复合享元模式工厂方法.
     * @param states
     * @return
     */
    public FlyWeight getFlyWeight(List<String> states) {
        CompositeConcreteFlyweight flyWeight = new CompositeConcreteFlyweight();
        for (String state : states) {
            //调用了单纯享元模式工厂方法,所以使用flyWeight.add加入的单纯享元对象是可以共享的
            flyWeight.add(state, this.getFlyWeight(state));
        }

        return flyWeight;
    }
}
