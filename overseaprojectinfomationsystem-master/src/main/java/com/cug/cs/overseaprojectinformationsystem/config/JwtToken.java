package com.cug.cs.overseaprojectinformationsystem.config;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @description: TODO
 * @author: ShengHui
 * @date: 2023-09-17  11:42
 */

public class JwtToken implements AuthenticationToken {

    private final String token;
    private final boolean isAdmin;
    private final boolean isSuperAdmin;

    //是否是管理员，是否是超级管理员
    public JwtToken(String token, boolean isAdmin, boolean isSuperAdmin) {
        this.token = token;
        this.isAdmin = isAdmin;
        this.isSuperAdmin = isSuperAdmin;
    }

    public boolean isAdmin() {
        return isAdmin || isSuperAdmin;
    }

    public boolean isSuperAdmin() {
        return isSuperAdmin;
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
