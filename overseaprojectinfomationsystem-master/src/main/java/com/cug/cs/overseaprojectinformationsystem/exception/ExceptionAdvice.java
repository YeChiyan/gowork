package com.cug.cs.overseaprojectinformationsystem.exception;

import com.cug.cs.overseaprojectinformationsystem.bean.common.BaseRespVo;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @description: TODO 统一异常处理
 * @author: ShengHui
 * @date: 2023-07-21  18:51
 */
@RestControllerAdvice
public class ExceptionAdvice {
    
    /**
     * @descripetion 未认证异常
     * @return com.cug.cs.overseaprojectinformationsystem.bean.common.BaseRespVo
     * @author huangshenghui
     * @since 2023/09/01 23:50
     */
    @ExceptionHandler({AuthorizationException.class})
    public BaseRespVo authorizationExceptionResolver() {
        return BaseRespVo.unAuthc();
    }
    
    /**
     * 登录异常
     * @return com.cug.cs.overseaprojectinformationsystem.bean.common.BaseRespVo
     * @author huangshenghui
     * @since 2023/09/01 23:52
     */
    public BaseRespVo authenticationExceptionResolver() {
        return BaseRespVo.invalidParameter(700, "账号密码不对");
    }
    
    /**
     * 数字转换异常
     * @return com.cug.cs.overseaprojectinformationsystem.bean.common.BaseRespVo
     * @author huangshenghui
     * @since 2023/09/01 23:54
     */
    @ExceptionHandler(value = {NumberFormatException.class, BindException.class})
    public BaseRespVo numberFormatException() {
        return BaseRespVo.invalidParameter(402, "参数值不对");
    }
}
