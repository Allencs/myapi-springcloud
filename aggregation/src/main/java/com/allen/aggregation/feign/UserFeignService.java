package com.allen.aggregation.feign;

import com.alibaba.fastjson.JSONObject;

import feign.Headers;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
//FeignConfig局部配置
//@FeignClient(value = "mall-order",path = "/order",configuration = FeignConfig.class)
@FeignClient(value = "user-center",path = "/myApi")
public interface UserFeignService {

//    @Headers({"Content-Type: application/json","Accept: application/json"})
    @RequestLine("POST /personInfo")
    JSONObject getPersonInfo();


//    @RequestMapping(value = "/toekn", method = RequestMethod.POST)
//    R save(@RequestBody OrderVo order);

    @RequestLine("GET /token")
    String getToken();


//    @RequestLine("GET /findOrderByUserId/{userId}")
//    R findOrderByUserId(@Param("userId") Integer userId);
}
