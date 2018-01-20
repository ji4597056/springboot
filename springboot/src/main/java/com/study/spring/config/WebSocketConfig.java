package com.study.spring.config;

import com.study.spring.common.websocket.AbstractForwardHandler;
import com.study.spring.common.websocket.TestForwardHandler;
import com.study.spring.common.websocket.MessageWebSocketInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * websocket 配置
 *
 * @author Jeffrey
 * @since 2017/1/22 18:14
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        //with sockjs
        webSocketHandlerRegistry
            .addHandler(messageWebSocketHandler(), "/websocket/sockjs/message")
            .addInterceptors(messageWebSocketInterceptor())
            .setAllowedOrigins("*")
            .withSockJS();
        //without sockjs
        webSocketHandlerRegistry
            .addHandler(messageWebSocketHandler(), "/websocket/*")
            .addInterceptors(messageWebSocketInterceptor())
            .setAllowedOrigins("*");
    }

    @Bean
    public AbstractForwardHandler messageWebSocketHandler() {
        return new TestForwardHandler();
    }

    @Bean
    public MessageWebSocketInterceptor messageWebSocketInterceptor() {
        return new MessageWebSocketInterceptor();
    }
}
