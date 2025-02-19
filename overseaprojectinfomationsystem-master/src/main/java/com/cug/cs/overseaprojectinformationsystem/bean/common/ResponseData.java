package com.cug.cs.overseaprojectinformationsystem.bean.common;

/**
 * @description: TODO 返回给前端的数据（一般是成功的，正确的数据）
 * @author: ShengHui
 * @date: 2023-09-12  07:32
 */

public class ResponseData<T> {
    private boolean success;
    
    private String message; //消息
    
    private int code;
    
    private T result; //返回的数据
    
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
    
    public T getResult() {
        return result;
    }
    
    public void setResult(T result) {
        this.result = result;
    }
}
