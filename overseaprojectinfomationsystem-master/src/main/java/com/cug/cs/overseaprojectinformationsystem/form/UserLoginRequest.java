package com.cug.cs.overseaprojectinformationsystem.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @description: TODO
 * @author: ShengHui
 * @date: 2023-09-08  21:07
 */
@Data
public class UserLoginRequest {
    @NotEmpty
    private String userName;
    @NotEmpty
    private String userPwd;
    @NotEmpty
    private String captcha;
}
