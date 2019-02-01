package io.github.brightloong.design.patterns.learn.flyweight;

/**
 * FlyWeight class
 * 定义享元类接口
 * @author BrightLoong
 * @date 2018/6/11
 */
public interface FlyWeight {
    /**
     * 操作方法.
     * @param extrinsicState 外部状态
     */
    public void operation(String extrinsicState);
}
