package com.cug.cs.overseaprojectinformationsystem.bean.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: TODO 统一返回给前端的数据（返回错误数据）
 * @author: ShengHui
 * @date: 2023-07-21  18:52
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BaseRespVo<T> {
    
    T data;
    String errmsg;
    int errno;
    
    public static <T> BaseRespVo ok(T data) {
        BaseRespVo baseRespVo = new BaseRespVo();
        baseRespVo.setData(data);
        return baseRespVo;
    }
    
    /**
     * @description: shiro认证返回
     * @param:
     * @return:
     */
    public static <T> BaseRespVo unAuthc() {
        BaseRespVo baseRespVo = new BaseRespVo();
        baseRespVo.setErrno(502);
        baseRespVo.setErrmsg("认证失败");
        return baseRespVo;
    }
    
    /**
     * @description: 参数传入处理
     * @param: errno
     * @return:
     */
    public static BaseRespVo invalidParameter(Integer errno, String errmsg) {
        BaseRespVo baseRespVo = new BaseRespVo();
        baseRespVo.setErrmsg(errmsg);
        baseRespVo.setErrno(errno);
        return baseRespVo;
    }
}
