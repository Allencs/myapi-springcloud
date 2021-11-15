package com.allen.usercenter;
import com.allen.commons.CreateJson;
import com.allen.commons.JWTUtil;
import com.allen.commons.PersonProperties;
import com.alibaba.fastjson.JSONObject;
import com.allen.commons.Token;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

@RestController //定义一个Rest服务，将自动序列化及反序列化请求/响应到JSON
public class myApi {
	
	@RequestMapping(value = "/myApi/post", method = RequestMethod.POST) //公开HTTP端点
	public PersonProperties Api() {
		
		PersonProperties personProperties = new PersonProperties();
		personProperties.setCode(200);
		personProperties.setJob("PerformanceTestEngineer");
		personProperties.setCompany("Perfma");
		personProperties.setName("GoodBoy");
		personProperties.setMessage("Congratulation! You got the message!");
		return personProperties;
	}
	
	
	@RequestMapping(value = "/myApi/token", method = RequestMethod.GET)
	public Token GetToken() {
		
		String str = JWTUtil.createJWT("1", "allen", "myApi", 1000*60*5);//2分钟
		Token token = new Token();
		token.setStatus("success");
		token.setToken(str);
		return token;
	}
	
	@RequestMapping(value = "/myApi/personInfo", method = RequestMethod.POST)
	public JSONObject GetPersonInfo(HttpServletRequest request) {
		
		CreateJson createJson = new CreateJson();
		JSONObject jsonObj = createJson.create();
		return jsonObj;
		
	}
	
}
