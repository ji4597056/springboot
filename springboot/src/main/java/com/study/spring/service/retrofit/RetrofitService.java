package com.study.spring.service.retrofit;

import com.study.spring.entity.Student;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @author Jeffrey
 * @since 2017/04/07 16:39
 */
public interface RetrofitService {

    @GET("/retrofit/base")
    Call<Student> getPersion();
}
