package com.cug.cs.overseaprojectinformationsystem.service;



import com.cug.cs.overseaprojectinformationsystem.bean.common.ResponseData;
import com.cug.cs.overseaprojectinformationsystem.dto.LoginFormDto;
import com.cug.cs.overseaprojectinformationsystem.dto.UserLoginResponse;
import com.cug.cs.overseaprojectinformationsystem.form.UserLoginRequest;

public interface ILoginService {

    /**
         实现用户登录功能
     */
    UserLoginResponse login(UserLoginRequest request);

    ResponseData sendCode(String phone);

    ResponseData loginWithPassword(LoginFormDto loginForm);

    ResponseData loginWithPhoneCode(LoginFormDto loginForm);

}
