package com.study.spring.config;

import com.study.spring.entity.Person;
import com.study.spring.service.retrofit.RetrofitService;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 *
 * @author Jeffrey
 * @since 2017/04/07 15:08
 */
@Configuration
public class RetrofitConfig {

    Logger logger = LoggerFactory.getLogger(RetrofitConfig.class);

    @Value("${okhttp.client.connectTimeout}")
    private Long connectTimeout;

    @Value("${okhttp.client.readTimeout}")
    private Long readTimeout;

    @Value("${okhttp.client.writeTimeout}")
    private Long writeTimeout;

    @Value("${retrofit.base.url}")
    private String baseUrl;

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
            .connectTimeout(connectTimeout, TimeUnit.SECONDS)
            .readTimeout(readTimeout, TimeUnit.SECONDS)
            .writeTimeout(writeTimeout, TimeUnit.SECONDS)
            .addInterceptor(chain -> {
                Request request = chain.request();

                long t1 = System.nanoTime();
                logger.info(String.format("Sending request %s on %s%n%s",
                    request.url(), chain.connection(), request.headers()));

                Response response = chain.proceed(request);

                long t2 = System.nanoTime();
                logger.info(String.format("Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));

                return response;
            }).build();
    }

    @Bean
    public Retrofit retrofit(OkHttpClient client) {
        return new Builder()
            .addConverterFactory(JacksonConverterFactory.create())
            .baseUrl(baseUrl)
            .client(client)
            .build();
    }

    @Bean
    public RetrofitService randomNumberService(Retrofit retrofit) {
        return retrofit.create(RetrofitService.class);
    }
}
