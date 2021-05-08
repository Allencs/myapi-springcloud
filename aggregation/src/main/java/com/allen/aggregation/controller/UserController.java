package com.allen.aggregation.controller;


import com.alibaba.fastjson.JSONObject;
import com.allen.aggregation.feign.UserFeignService;
import com.allen.commons.PersonProperties;
import feign.RequestLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class UserController {

    @Autowired
    UserFeignService userFeignService;

    @RequestMapping(value = "/getPersonInfo", method = RequestMethod.POST)
    public JSONObject getPersonInfo() {
        //feign调用
        JSONObject result = userFeignService.getPersonInfo();
        return result;
    }

    @RequestMapping(value = "/userToken", method = RequestMethod.GET)
    public String getUserToken() {
        System.out.println(userFeignService);
        //feign调用
        String result = userFeignService.getToken();
        return result;
    }
    
    
//    @RequestMapping(value = "/save")
//    public R save(@RequestBody OrderVo order){
//        return orderFeignService.save(order);
//    }
    
   
}
