package com.cug.cs.overseaprojectinformationsystem.bean.common;


import org.apache.poi.ss.formula.functions.T;

/**
 * @author shentong
 * @date 2023/11/24 17:25
 * @description
 */
public class ResUtil<T> {
    private ResData<T> resData;

    public ResUtil() {
        resData = new ResData<>();
    }

    public ResData<T> setData(T t) {
        this.resData.setData(t);
        this.resData.setSuccess(true);
        resData.setCode(200);
        return this.resData;
    }

    public ResData<T> setData(T t, String msg) {
        this.resData.setData(t);
        this.resData.setSuccess(true);
        this.resData.setMessage(msg);
        resData.setCode(200);
        return this.resData;
    }

    public ResData<T> setErrorMsg(String msg) {
        this.resData.setSuccess(false);
        this.resData.setMessage(msg);
        resData.setCode(500);
        return this.resData;
    }

    public ResData<T> setErrorMsg(Integer code, String msg) {
        this.resData.setSuccess(false);
        this.resData.setMessage(msg);
        resData.setCode(500);
        return this.resData;
    }
}
