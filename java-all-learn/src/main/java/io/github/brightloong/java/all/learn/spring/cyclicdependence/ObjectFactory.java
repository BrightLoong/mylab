package io.github.brightloong.java.all.learn.spring.cyclicdependence;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author BrightLoong
 * @date 2018/9/13 11:19
 * @description
 */
public class ObjectFactory {

    /**用于缓存正在初始化中的对象*/
    private static final Map<Class, Object> currentInitObjects = new ConcurrentHashMap<>();

    /**用于缓存初始化好的单例对象*/
    private static final Map<Class, Object> objects = new ConcurrentHashMap<>();

    /**
     * 获取对象，并设值对象属性。
     * 1. 不考虑并发问题，简单的示例
     * 2. 解决单例set循环依赖
     *
     * @param cls
     * @param <T>
     * @return
     */
    public <T> T getObject(Class<T> cls) {
        //如果已经初始化过直接返回
        if (objects.containsKey(cls)) {
            return (T) objects.get(cls);
        }
        try {
            T t;
            //1. 简单的使用构造函数创建对象，并提前暴露到currentInitObjects中
            t = cls.newInstance();
            //提前暴露到currentInitObjects中
            currentInitObjects.put(cls, t);
            //2. 解决依赖属性值
            resolveDependence(t, cls);
            //3. 放入单例缓存中
            objects.put(cls, t);
            return t;
        } catch (Exception e) {
            System.out.println("初始化对象失败：" + cls);
            return null;
        } finally {
            //4. 从正在初始化缓存中移除
            currentInitObjects.remove(cls);
        }
    }

    /**
     * 解决依赖属性设值.
     * @param object 对象
     * @param cls 对象class
     */
    private void resolveDependence(Object object, Class cls) {
        //获取对象的属性,并进行赋值，省去了复杂的判断，就认为是对象

        //1.获取所有属性
        Field[] fields = cls.getDeclaredFields();

        //2.循环处理属性值
        Arrays.stream(fields).forEach(field -> {
            field.setAccessible(true);
            //2.1 获取属性class属性
            Class fieldClass = field.getType();
            Object value;
            //2.2 判断是否已经初始化过
            if (objects.containsKey(fieldClass)) {
                value = objects.get(fieldClass);
            } else if (currentInitObjects.containsKey(fieldClass)) {
                //2.3 判断当前初始化的类中有没有这个属性.
                value = currentInitObjects.get(fieldClass);
            } else {
                //2.4 如果都没有，进行初始化
                value = getObject(fieldClass);
            }
            //3. 使用反射设置属性的值
            try {
                field.set(object, value);
            } catch (IllegalAccessException e) {
                System.out.println("设置对象属性失败：" + cls + "-" + field.getName());
            }
        });
    }
}
