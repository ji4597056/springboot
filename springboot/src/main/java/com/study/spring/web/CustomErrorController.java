package com.study.spring.web;

import com.study.spring.entity.WebResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletRequestAttributes;
import springfox.documentation.annotations.ApiIgnore;

/**
 * custom error controller
 *
 * @author Jeffrey
 * @since 2017/07/06 13:48
 */
@RestController
@ApiIgnore
public class CustomErrorController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @Autowired
    private ErrorAttributes errorAttributes;

    @GetMapping(ERROR_PATH)
    public WebResponse error(HttpServletRequest request, HttpServletResponse response) {
        Throwable throwable = getException(request);
        response.setStatus(getErrorStatus(throwable, response));
        return new WebResponse(getErrorStatus(throwable, response), getErrorMessage(throwable),
            false, null);
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    /**
     * 获取异常
     *
     * @param request HttpServletRequest
     * @return Throwable
     */
    private Throwable getException(HttpServletRequest request) {
        ServletRequestAttributes requestAttributes = new ServletRequestAttributes(request);
        Throwable throwable = errorAttributes.getError(requestAttributes);
        if (throwable == null) {
            throwable = new Throwable(
                (String) errorAttributes.getErrorAttributes(requestAttributes, false)
                    .get("message"));
        }
        return throwable;
    }

    /**
     * 获取错误状态码
     *
     * @param throwable Throwable
     * @param response HttpServletResponse
     * @return response http status
     */
    private int getErrorStatus(Throwable throwable, HttpServletResponse response) {
        return response.getStatus();
    }

    /**
     * 获取错误信息
     *
     * @param throwable Throwable
     * @return error message
     */
    private String getErrorMessage(Throwable throwable) {
        return throwable.getMessage();
    }

}
