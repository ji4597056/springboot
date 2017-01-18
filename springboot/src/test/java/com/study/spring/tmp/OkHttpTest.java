package com.study.spring.tmp;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Test;

import java.io.IOException;

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
}
