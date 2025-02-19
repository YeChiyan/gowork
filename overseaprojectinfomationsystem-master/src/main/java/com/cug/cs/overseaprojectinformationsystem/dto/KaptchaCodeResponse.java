package com.cug.cs.overseaprojectinformationsystem.dto;

import lombok.Data;

/**
 * @description: TODO
 * @author: ShengHui
 * @date: 2023-09-12  06:25
 */
@Data
public class KaptchaCodeResponse {
    private String imageCode;
    
    private String uuid;
}
