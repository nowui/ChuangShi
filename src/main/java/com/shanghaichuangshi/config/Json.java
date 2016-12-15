package com.shanghaichuangshi.config;

public final class Json {

    private int code;
    private String message;
    private Integer total;
    private Object data;

    public Json(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Json(int code, String message, Integer total, Object data) {
        this.code = code;
        this.message = message;
        this.total = total;
        this.data = data;
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

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
