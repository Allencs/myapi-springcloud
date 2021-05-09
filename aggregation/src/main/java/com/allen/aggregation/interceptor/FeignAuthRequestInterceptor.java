package com.allen.aggregation.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.UUID;

/**
 * -- RequestInterceptor接口定义了apply方法，其参数为RequestTemplate；它有一个抽象类为BaseRequestInterceptor，
 * 还有几个实现类分别为BasicAuthRequestInterceptor、FeignAcceptGzipEncodingInterceptor、FeignContentGzipEncodingInterceptor
 * -- BasicAuthRequestInterceptor实现了RequestInterceptor接口，其apply方法往RequestTemplate添加名为Authorization的header
 * -- BaseRequestInterceptor定义了addHeader方法，往requestTemplate添加非重名的header；
 * FeignAcceptGzipEncodingInterceptor继承了BaseRequestInterceptor，
 * 它的apply方法往RequestTemplate添加了名为Accept-Encoding，值为gzip,deflate的header；
 * FeignContentGzipEncodingInterceptor继承了BaseRequestInterceptor，其apply方法先判断是否需要compression，
 * 即mimeType是否符合要求以及content大小是否超出阈值，需要compress的话则添加名为Content-Encoding，值为gzip,deflate的header
 */
public class FeignAuthRequestInterceptor implements RequestInterceptor {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void apply(RequestTemplate template) {
        // 业务逻辑  模拟认证逻辑
//        String access_token = UUID.randomUUID().toString();
//        template.header("Authorization",access_token);

        /**
         * 添加需要转发的头信息
         */
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();

        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                String values = request.getHeader(name);
                template.header(name, values);
            }

            logger.debug("feign interceptor header:{}",template);
        }
    }
}
