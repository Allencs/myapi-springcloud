package com.allen.aggregation.remote;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Fox
 */
public interface RemoteService {
    
//    @Headers({"Content-Type: application/json","Accept: application/json"})
//    @RequestLine("GET /order/findOrderByUserId/{userId}")
//    R findOrderByUserId(@Param("userId") Integer userId);
//
//    @Headers({"Content-Type: application/json","Accept: application/json"})
//    @RequestLine("POST /order/save")
//    public R save(@RequestBody OrderVo order);

//    @RequestMapping(value = "/myApi/token", method = RequestMethod.GET)
    @RequestLine("GET /myApi/token")
    String getToken();

}
