package com.study.spring.common.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

/**
 * websocket handler
 *
 * @author Jeffrey
 * @since 2017/01/22 17:33
 */
public class MessageWebSocketHandler extends AbstractWebSocketHandler {

  private static final Logger logger = LoggerFactory.getLogger(MessageWebSocketHandler.class);

  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    logger.info("receive message:{}" + message.toString());
    super.handleTextMessage(session, message);
  }

  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    logger.info("connect to the websocket({}) chat success......", this.toString());
  }

  @Override
  public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
    if (session.isOpen()) {
      session.close();
    }
    logger.info("websocket({}) chat connection closed......", this.toString());
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus)
      throws Exception {
    logger.info("websocket({}) chat connection closed......", this.toString());
  }

  @Override
  public boolean supportsPartialMessages() {
    return false;
  }
}
