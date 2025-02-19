package com.cug.cs.overseaprojectinformationsystem.dto;

import lombok.Data;


/**
 * @description: TODO
 * @author: ShengHui
 * @date: 2023-09-12  06:28
 */
@Data
public class UserLoginResponse {
    
    private Long id;
    
    private String username;
    
    private String sex;
    
    private String description;
    
    private int state;
    
    private String token;

    private String phone;
}
