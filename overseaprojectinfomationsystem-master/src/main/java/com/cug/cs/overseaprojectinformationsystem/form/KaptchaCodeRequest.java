package com.cug.cs.overseaprojectinformationsystem.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @description: TODO
 * @author: ShengHui
 * @date: 2023-09-12  06:26
 */
@Data
public class KaptchaCodeRequest {
    @NotEmpty
    private String code;
    @NotEmpty
    private String uuid;
}
