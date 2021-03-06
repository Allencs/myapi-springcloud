package com.allen.aggregation.feign;

import com.alibaba.fastjson.JSONObject;
import com.allen.aggregation.config.FeignConfig;
import com.allen.commons.PersonProperties;
import com.allen.commons.Token;
import feign.Body;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.RequestBody;


//FeignConfig局部配置
//@FeignClient(value = "user-gateway", url = "http://localhost:7777", path = "/myApi", configuration = FeignConfig.class)
@FeignClient(value = "user-gateway", path = "/myApi", configuration = FeignConfig.class)
public interface UserFeignService {

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @RequestLine("POST /personInfo")
    JSONObject getPersonInfo(PersonProperties personProperties);

//    @RequestMapping(value = "/token", method = RequestMethod.GET)
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @RequestLine("GET /token")
    Token getToken();

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @RequestLine("POST /post")
    @Body("{body}")
    PersonProperties post(@Param("body") JSONObject body);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @RequestLine("POST /demo")
    @Body("{body}")
    PersonProperties demo(@Param("body") JSONObject body);


//    @RequestLine("GET /findOrderByUserId/{userId}")
//    R findOrderByUserId(@Param("userId") Integer userId);
}
