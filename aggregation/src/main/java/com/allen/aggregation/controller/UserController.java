package com.allen.aggregation.controller;


import com.alibaba.fastjson.JSONObject;
import com.allen.aggregation.feign.UserFeignService;
import com.allen.commons.PersonProperties;
import com.allen.commons.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
public class UserController {

    private static Logger logger = LoggerFactory
            .getLogger(UserController.class);

    @Autowired
    UserFeignService userFeignService;

    @RequestMapping(value = "/getPersonInfo", method = RequestMethod.POST)
    public JSONObject getPersonInfo(@RequestBody PersonProperties personProperties) {
        //feign调用
        return userFeignService.getPersonInfo(personProperties);
    }

    @RequestMapping(value = "/userToken", method = RequestMethod.GET)
    public Token getUserToken() {
        //feign调用
        return userFeignService.getToken();
    }

    @RequestMapping(value = "/userPost", method = RequestMethod.POST)
    public PersonProperties userPost(@RequestBody JSONObject body) {
        //feign调用
        return userFeignService.post(body);
    }
    
    
//    @RequestMapping(value = "/save")
//    public R save(@RequestBody OrderVo order){
//        return orderFeignService.save(order);
//    }
    
   
}
