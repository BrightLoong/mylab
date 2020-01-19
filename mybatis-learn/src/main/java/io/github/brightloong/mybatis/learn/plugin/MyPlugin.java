package io.github.brightloong.mybatis.learn.plugin;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.javassist.tools.reflect.Metaobject;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;

import java.util.Properties;

/**
 * @author BrightLoong
 * @date 2019-02-14 16:37
 * @description
 */

@Intercepts({@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class MyPlugin implements Interceptor {

    private Properties properties = null;

    private static final Logger logger = Logger.getLogger(MyPlugin.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        logger.info("before......");
        /*MetaObject executorMeta = SystemMetaObject.forObject(invocation.getTarget());
        //找到最后一层，也就是被代理的executor，剥离plugin
        while (executorMeta.hasGetter("target")) {
            executorMeta = SystemMetaObject.forObject(executorMeta.getValue("target"));
        }
        //获取executor下面configuration里面的属性cacheEnabled
        Object value = executorMeta.getValue("configuration.cacheEnabled");
        logger.info("cacheEnabled:" + value);*/
        Object proceed = invocation.proceed();
        logger.info("after......");
        return proceed;
    }

    @Override
    public Object plugin(Object target) {
        //打印四次，分别对应Executor SatementHandler ParameterHandler ResultHandler
        logger.info("代理对象——" + target.getClass());
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        logger.info("插件配置属性" + properties.get("dbType"));
        this.properties = properties;
    }
}
