package com.study.spring.web;

import com.study.spring.annotation.profile.RetrofitEnv;
import com.study.spring.entity.Student;
import com.study.spring.service.retrofit.RetrofitService;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @author Jeffrey
 * @since 2017/04/07 16:30
 */
@RestController
@RequestMapping("retrofit")
@RetrofitEnv
public class RetrofitController {

    @Autowired
    private RetrofitService retrofitService;

    @GetMapping("base")
    @ApiOperation(value = "retrofit被转发请求")
    public ResponseEntity base() {
        return ResponseEntity.ok(new Student(1, "测试"));
    }

    @GetMapping("test")
    @ApiOperation(value = "retrofit转发请求")
    public ResponseEntity test() throws IOException {
        Call<Student> call = retrofitService.getPersion();
        Response<Student> response = call.execute();
        if (response.isSuccessful()) {
            return ResponseEntity.ok(response.body());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(response.raw().toString());
        }
    }
}
