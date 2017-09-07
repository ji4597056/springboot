package com.study.spring.common.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 请求响应体处理
 *
 * @author Jeffrey
 * @since 2017/05/12 13:36
 */
@ControllerAdvice
public class ResBodyAdviceHandler implements ResponseBodyAdvice<Object> {

    private static final Logger logger = LoggerFactory.getLogger(ResBodyAdviceHandler.class);

    @Override
    public boolean supports(MethodParameter methodParameter,
        Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType,
        Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest,
        ServerHttpResponse serverHttpResponse) {
        logger.info("response[method:{},url:{}]", serverHttpRequest.getMethod(),
            serverHttpRequest.getURI());
        return o;
    }
}
