package com.cug.cs.overseaprojectinformationsystem.service;


import com.cug.cs.overseaprojectinformationsystem.dto.KaptchaCodeResponse;
import com.cug.cs.overseaprojectinformationsystem.form.KaptchaCodeRequest;

public interface IKaptchaService {

    /**
     * 获取图形验证码
     * @return
     */
    KaptchaCodeResponse getKaptchaCode();

    /**
     * 验证图形验证码
     * @param request
     * @return
     */
    void validateKaptchaCode(KaptchaCodeRequest request);

}
