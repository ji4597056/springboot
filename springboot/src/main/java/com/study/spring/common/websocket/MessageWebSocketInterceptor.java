package com.study.spring.common.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/**
 * websocket interceptor
 *
 * @author Jeffrey
 * @since 2017/01/22 17:30
 */
public class MessageWebSocketInterceptor implements HandshakeInterceptor {

  private static final Logger logger = LoggerFactory.getLogger(MessageWebSocketInterceptor.class);

  @Override
  public boolean beforeHandshake(
      ServerHttpRequest request,
      ServerHttpResponse response,
      WebSocketHandler wsHandler,
      Map<String, Object> attributes)
      throws Exception {
    logger.info("Before Handshake");
    return true;
  }

  @Override
  public void afterHandshake(
      ServerHttpRequest request,
      ServerHttpResponse response,
      WebSocketHandler wsHandler,
      Exception exception) {
    logger.info("After Handshake");
  }
}
