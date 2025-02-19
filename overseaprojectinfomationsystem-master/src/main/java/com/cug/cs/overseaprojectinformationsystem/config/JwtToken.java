package com.cug.cs.overseaprojectinformationsystem.config;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @description: TODO
 * @author: ShengHui
 * @date: 2023-09-17  11:42
 */

public class JwtToken implements AuthenticationToken {
    
    private String token;
    
    public JwtToken(String token) {
        this.token = token;
    }
    
    @Override
    public Object getPrincipal() {
        return token;
    }
    
    @Override
    public Object getCredentials() {
        return token;
    }
}
