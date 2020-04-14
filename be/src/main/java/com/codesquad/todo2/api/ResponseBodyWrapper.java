package com.codesquad.todo2.api;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseBodyWrapper {
    private Boolean result;
    private Object data;
    private String errorMessage;

    private ResponseBodyWrapper(boolean result, Object data, String errorMessage) {
        this.result = result;
        this.data = data;
        this.errorMessage = errorMessage;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public static ResponseBodyWrapper ok() {
        return new ResponseBodyWrapper(true, null, null);
    }

    public static ResponseBodyWrapper ok(Object data) {
        return new ResponseBodyWrapper(true, data, null);
    }

    public static ResponseBodyWrapper failed(String errorMessage) {
        return new ResponseBodyWrapper(false, null, errorMessage);
    }
}
