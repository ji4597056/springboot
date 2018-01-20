package com.study.spring.common.websocket;

import org.springframework.web.socket.WebSocketSession;

/**
 * @author Jeffrey
 * @since 2017/11/28 17:20
 */
public class TestForwardHandler extends AbstractForwardHandler{

    @Override
    public String getForwardUrl(WebSocketSession session) {
        return "ws://localhost:8081" + session.getUri().getPath();
    }
}
