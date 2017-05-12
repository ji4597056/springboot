package com.study.spring.config;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import okhttp3.OkHttpClient;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * restTemplate配置
 *
 * @author Jeffrey
 * @since 2017/04/17 15:52
 */
@Configuration
public class WebConfig {

    private final Integer connectTimeout = 2000;

    private final Integer readTimeout = 60000;

    private final Integer writeTimeout = 60000;

    /**
     * 定义restTemplate
     */
    @Bean
    public RestTemplate restTemplate() {
        // formhttpmessageconverter
        List<HttpMessageConverter<?>> partConverters = new ArrayList<>(3);
        partConverters.add(new ByteArrayHttpMessageConverter());
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(
            StandardCharsets.UTF_8);
        stringHttpMessageConverter.setWriteAcceptCharset(false);
        partConverters.add(stringHttpMessageConverter);
        partConverters.add(new ResourceHttpMessageConverter());
        FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
        formHttpMessageConverter.setPartConverters(partConverters);
        // messageconverters
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>(3);
        messageConverters.add(stringHttpMessageConverter);
        messageConverters.add(formHttpMessageConverter);
        messageConverters.add(new MappingJackson2HttpMessageConverter());
        return new RestTemplateBuilder().requestFactory(clientHttpRequestFactory())
            .messageConverters(messageConverters).build();
    }

    private ClientHttpRequestFactory clientHttpRequestFactory() {
        OkHttp3ClientHttpRequestFactory factory = new OkHttp3ClientHttpRequestFactory(
            okHttpClient());
        factory.setConnectTimeout(connectTimeout);
        factory.setReadTimeout(readTimeout);
        factory.setWriteTimeout(writeTimeout);
        return factory;
    }

    private OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder().build();
    }

    /**
     * 文件上传配置
     */
    @Bean(name = "multipartResolver")
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
//		resolver.setResolveLazily(true);//resolveLazily属性启用是为了推迟文件解析，以在在UploadAction中捕获文件大小异常
        resolver.setMaxInMemorySize(40960);
        resolver.setMaxUploadSize(50 * 1024 * 1024);//上传文件大小 50M 50*1024*1024
        return resolver;
    }

    @Bean
    public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
        ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet);
        registration.getUrlMappings().clear();
        registration.addUrlMappings("/");
        return registration;
    }
}
