package com.cug.cs.overseaprojectinformationsystem.util;

import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class JwtFilter extends AccessControlFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtFilter.class);

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String token = httpRequest.getHeader("token");
        
        if (token == null) {
            return false;
        }

        try {
            // 解析token获取用户类型
            boolean isAdmin = JwtUtil.isAdminToken(token);
            boolean isSuperAdmin = JwtUtil.isSuperAdminToken(token);
            
            // 创建token时设置正确的标志
            JwtToken jwtToken = new JwtToken(token, isAdmin, isSuperAdmin);
            
            // 添加调试日志
            log.debug("Token信息 - isAdmin: {}, isSuperAdmin: {}", isAdmin, isSuperAdmin);
            
            getSubject(request, response).login(jwtToken);
            return true;
        } catch (Exception e) {
            log.error("Token验证失败: {}", e.getMessage());
            return false;
        }
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        return executeLogin(request, response);
    }
} 