package com.study.spring.entity;

/**
 * 前端响应实体类型
 *
 * @author Jeffrey
 * @since 2017/7/5 17:47
 */
public class WebResponse {

    /**
     * http code
     */
    private int code;

    /**
     * http message
     */
    private String message;

    /**
     * success
     */
    private boolean success;

    /**
     * response
     */
    private Object response;

    public WebResponse() {
    }

    public WebResponse(int code, String message, boolean success, Object response) {
        this.code = code;
        this.message = message;
        this.success = success;
        this.response = response;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("WebResponse{");
        sb.append("code=").append(code);
        sb.append(", message='").append(message).append('\'');
        sb.append(", success=").append(success);
        sb.append(", response=").append(response);
        sb.append('}');
        return sb.toString();
    }

}
