package com.allen.usercenter.filter;

import com.alibaba.fastjson.JSON;
import com.allen.commons.JWTUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Component
@WebFilter(urlPatterns={"/myApi"}, filterName="tokenAuthorFilter")
public class HeaderFilter extends HttpServlet implements Filter{
	
	/**
	 * 序列化/反序列化版本号，只有一致情况下，才能反序列化成功
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory
            .getLogger(HeaderFilter.class);
	
	public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("==>DemoFilter启动成功");
        
    }
	
	
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		//设置待验证的头信息
		Map<String, String> Headers = new HashMap<String, String>();
		Headers.put("position", "IT");
		Headers.put("behavior", "PerformanceTest");
		Map<String, String> Bodies = new HashMap<String, String>();
		Bodies.put("username", "GoodBoy");
		Bodies.put("pw", "root123");
		// 将请求转换成HttpServletRequest 请求 
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        //获取请求体，将请求的json保存至Map中
        BufferedReader streamReader = new BufferedReader(new InputStreamReader(req.getInputStream(), "UTF-8"));
        StringBuilder responseStrBuilder = new StringBuilder();
        String inputStr;
        while((inputStr = streamReader.readLine()) != null) {
        	responseStrBuilder.append(inputStr);
        	
        }
        
        
        @SuppressWarnings("unchecked")
		Map<String, String> RequestBodies = JSON.parseObject(responseStrBuilder.toString(),Map.class);
        logger.info("RequestBodies: " + RequestBodies);
        
        //设置允许跨域的配置
        // 这里填写你允许进行跨域的主机ip（正式上线时可以动态配置具体允许的域名和IP）
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods","POST");
        resp.setHeader("Access-Control-Allow-Headers","token");
        resp.setHeader("Create-By", "Allen");
        
        String token = req.getHeader("access-token");
        String exception = JWTUtil.parseJWT(token).toString();
        logger.info(exception);
        if (token != null) {
        	if (ExpiredJwtException.class.getName().equals(exception)){
        		logger.info("token过期！");
        		String info = "{'code':401, 'msg':'No Access,token is overtime!'}";
        		resp.sendError(401, info);
            
        	}else if (SignatureException.class.getName().equals(exception)){
                logger.info("token sign解析失败");
                String info_2 = "{'code':401, 'msg':'token is invalid!'}";
                resp.sendError(401, info_2);
        	}
        	else if (MalformedJwtException.class.getName().equals(exception)){
        		logger.info("token的head解析失败");
                String info_2 = "{'code':401, 'msg':'token is invalid!'}";
                resp.sendError(401, info_2);
        	}
        	else if ("success".equals(exception)){
        		logger.info("用户授权认证通过");
                Integer count = 0;
                //验证头信息
                for(String headerName : Headers.keySet()) {
                	
                	if(Headers.get(headerName).equals(req.getHeader(headerName))) {
                		count += 1;
                	}
                }
                if(count == 2) {
                    
                    Integer countParam = 0;
                    for(String bodyName : Bodies.keySet()){
                    	if(Bodies.get(bodyName).equals(RequestBodies.get(bodyName))) {
                    		countParam += 1;
                    	}
                    }
                    
                    if(countParam == 2) {
                    	filterChain.doFilter(req, resp);
                    }
                    else {
                    	String info_6 = "{'code':400, 'Incorrect Json'}";
                    	resp.sendError(400, info_6);
                    	logger.info(info_6);
                    }
                }else {
                	String info_5 = "{'code':403, 'No Permission'}";
                	resp.sendError(403, info_5);
                	logger.info("Incorrect Headers");
                }
                          
                
        	}
        	else {
              String info_3 = "{'msg':'token will invalid at 2 minutes！please refresh'}";
              resp.sendError(401, info_3);
        	}
        }
        else {
        	String info_4 = "{'code':403, 'No Access!'}";
        	resp.sendError(401, info_4);
        	logger.info("Found no token");
        }
        
	
        
	}

    @Override
    public void destroy() {

    }
}
