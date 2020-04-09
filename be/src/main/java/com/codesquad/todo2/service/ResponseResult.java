package com.codesquad.todo2.service;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult {
    private Boolean result;
    private String errorMessage;
    private Object data;

    public ResponseResult(boolean result, String errorMessage) {
        this.result = result;
        this.errorMessage = errorMessage;
    }

    public ResponseResult(boolean result, Object data) {
        this.result = result;
        this.data = data;
    }

    public static ResponseResult failed(String errorMessage) {
        return new ResponseResult(false, errorMessage);
    }

    public static ResponseResult succeed() {
        return new ResponseResult(true, "처리되었습니다.");
    }

    public static ResponseResult succeed(Object data) {
        return new ResponseResult(true, data);
    }

    public Boolean getResult() {
        return result;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Object getData() {
        return data;
    }
}
