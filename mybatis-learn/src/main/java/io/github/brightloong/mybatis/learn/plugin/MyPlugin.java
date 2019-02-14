package io.github.brightloong.mybatis.learn.plugin;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

/**
 * @author BrightLoong
 * @date 2019-02-14 16:37
 * @description
 */

@Intercepts({@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class MyPlugin implements Interceptor {

    Properties properties = null;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.err.println("before......");
        Object proceed = invocation.proceed();
        System.err.println("after......");
        return proceed;
    }

    @Override
    public Object plugin(Object target) {
        System.err.println("生成代理对象。。。。。。");
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        System.err.println(properties.get("dbType"));
        this.properties = properties;
    }
}
