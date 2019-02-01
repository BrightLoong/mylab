package io.github.brightloong.spring.cloud.learn.eureka.consumer.hystrix.feign;

import io.github.brightloong.spring.cloud.learn.eureka.consumer.hystrix.hysrix.HelloRemoteHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author BrightLoong
 * @date 2019-02-01 15:20
 * @description
 */
@FeignClient(name= "eureka-producer",fallback = HelloRemoteHystrix.class)
public interface HelloRemote {
    @RequestMapping(value = "/hello")
    public String hello(@RequestParam(value = "name") String name);
}
