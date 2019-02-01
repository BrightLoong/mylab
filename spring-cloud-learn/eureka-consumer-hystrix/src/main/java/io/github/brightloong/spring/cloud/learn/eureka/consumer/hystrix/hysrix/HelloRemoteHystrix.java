package io.github.brightloong.spring.cloud.learn.eureka.consumer.hystrix.hysrix;

import io.github.brightloong.spring.cloud.learn.eureka.consumer.hystrix.feign.HelloRemote;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author BrightLoong
 * @date 2019-02-01 15:53
 * @description
 */
@Component
public class HelloRemoteHystrix implements HelloRemote {

    @Override
    public String hello(@RequestParam(value = "name") String name) {
        return "hello " +name+", this messge send failed ";
    }
}
