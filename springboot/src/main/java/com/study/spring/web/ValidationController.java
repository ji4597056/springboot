package com.study.spring.web;

import io.swagger.annotations.ApiOperation;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jeffrey
 * @since 2017/05/12 14:00
 */
@RestController
@RequestMapping("valid")
@Validated
public class ValidationController {

    @GetMapping("name")
    @ApiOperation(value = "验证name不能为null")
    public String validNameNotNull(
        @NotNull(message = "name不能为空") @RequestParam(required = false) String name) {
        return name;
    }

    @GetMapping("id")
    @ApiOperation(value = "验证id区间[1,10]")
    public Integer validIdBetween(
        @Min(value = 1, message = "id最小值为1") @Max(value = 10, message = "id最大值为10") @RequestParam(required = false) Integer id) {
        return id;
    }
}
