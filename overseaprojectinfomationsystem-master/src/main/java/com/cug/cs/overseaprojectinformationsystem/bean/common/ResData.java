package com.cug.cs.overseaprojectinformationsystem.bean.common;



/**
 * @author shentong
 * @date 2023/11/24 17:24
 * @description
 */
public class ResData<T> {
    private boolean success;

    private String message; //消息

    private int code;

    private T data; //返回的数据

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
