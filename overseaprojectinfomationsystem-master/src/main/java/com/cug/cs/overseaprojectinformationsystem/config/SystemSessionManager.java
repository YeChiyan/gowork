package com.cug.cs.overseaprojectinformationsystem.config;

import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * @description: TODO 解决跨域问题Session一致性问题(系统后续扩展，集群)
 * @author: ShengHui
 * @date: 2023-09-01  23:41
 */

public class SystemSessionManager extends DefaultWebSessionManager {
    private static final String SYSTEM_ADMIN_TOKEN = "X-System-Admin-Token";
    private static final String SYSTEM_WX_TOKEN = "X-System-Token";
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String sessionId = httpServletRequest.getHeader(SYSTEM_ADMIN_TOKEN);
        if (sessionId != null && !"".equals(sessionId)) {
            return sessionId;
        }
        String sessionId2 = httpServletRequest.getHeader(SYSTEM_WX_TOKEN);
        if (sessionId2 != null && !"".equals(sessionId2)) {
            return sessionId2;
        }
        return super.getSessionId(request, response);
    }
}
