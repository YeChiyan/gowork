package com.cug.cs.overseaprojectinformationsystem.bean.common;

/**
 * @description: TODO 属于ResponseData的工具类，主要用于封装返回数据，内部方法根据情况增加
 * @author: ShengHui
 * @date: 2023-09-12  07:33
 */

public class ResponseUtil<T> {
    private ResponseData<T> responseData;
    
    public ResponseUtil() {
        responseData = new ResponseData<>();
    }
    
    public ResponseData<T> setData(T t) {
        this.responseData.setResult(t);
        this.responseData.setSuccess(true);
        responseData.setCode(200);
        return this.responseData;
    }
    
    public ResponseData<T> setData(T t, String msg) {
        this.responseData.setResult(t);
        this.responseData.setSuccess(true);
        this.responseData.setMessage(msg);
        responseData.setCode(200);
        return this.responseData;
    }
    
    public ResponseData<T> setErrorMsg(String msg) {
        this.responseData.setSuccess(false);
        this.responseData.setMessage(msg);
        responseData.setCode(500);
        return this.responseData;
    }
    
    public ResponseData<T> setErrorMsg(Integer code, String msg) {
        this.responseData.setSuccess(false);
        this.responseData.setMessage(msg);
        responseData.setCode(500);
        return this.responseData;
    }
}
