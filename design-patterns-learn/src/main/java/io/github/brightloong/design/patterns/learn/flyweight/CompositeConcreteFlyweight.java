package io.github.brightloong.design.patterns.learn.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * CompositeConcreteFlyweight class
 *
 * @author BrightLoong
 * @date 2018/6/11
 */
public class CompositeConcreteFlyweight implements FlyWeight {
    Map<String, FlyWeight> flyWeights = new HashMap<>();

    /**
     * flyWeights是单纯享元对象的集合，它们具有相同的外部状态extrinsicState，
     * 调用的时候使用循环调用单纯享元对象的operation方法
     * @param extrinsicState 外部状态
     */
    @Override
    public void operation(String extrinsicState) {
        for (Map.Entry<String, FlyWeight> entry : flyWeights.entrySet()) {
            entry.getValue().operation(extrinsicState);
        }
    }

    /**
     * 添加单纯享元对象.
     * @param state
     * @param flyWeight
     */
    public void add(String state, FlyWeight flyWeight) {
        flyWeights.put(state, flyWeight);
    }

    /**
     * 移除单纯享元对象.
     * @param state
     */
    private void remove(String state) {
        flyWeights.remove(state);
    }
}
