package com.study.spring.tmp;

import okhttp3.*;
import okio.ByteString;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * OkHttp3 测试
 *
 * @author Jeffrey
 * @since 2017/01/17 10:54
 */
public class OkHttpTest {

  @Test
  public void testGetBaiDu() {
    String url = "https://www.baidu.com/";
    OkHttpClient okHttpClient = new OkHttpClient();
    Request request = new Request.Builder().url(url).build();
    Call call = okHttpClient.newCall(request);

    try {
      Response response = call.execute();
      System.out.println("=====head=====");
      System.out.println(response.headers().toString());
      System.out.println("=====body=====");
      System.out.println(response.body().string());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * websocket client test
   * @throws InterruptedException
   */
  @Test
  public void testWebSocketClient() throws InterruptedException {
    String webSocketUrl = "ws://localhost:8888/websocket/message";
    //    String webSocketUrl = "ws://echo.websocket.org";
    WebSocketListener webSocketListener = new MyWebSocketListener();
    OkHttpClient client = new OkHttpClient.Builder().readTimeout(0, TimeUnit.MILLISECONDS).build();
    Request request = new Request.Builder().url(webSocketUrl).build();
    WebSocket ws = client.newWebSocket(request, webSocketListener);
    client.dispatcher().executorService().shutdown();
  }

  /**
   * websocket listener
   */
  public class MyWebSocketListener extends WebSocketListener {
    @Override
    public void onOpen(WebSocket webSocket, Response response) {
      webSocket.send("Hello...");
      webSocket.send("...World!");
      webSocket.send(ByteString.decodeHex("deadbeef"));
      webSocket.close(1000, "Goodbye, World!");
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
      System.out.println("MESSAGE: " + text);
    }

    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {
      System.out.println("MESSAGE: " + bytes.hex());
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
      webSocket.close(1000, null);
      System.out.println("CLOSE: " + code + " " + reason);
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
      t.printStackTrace();
    }
  }
}
