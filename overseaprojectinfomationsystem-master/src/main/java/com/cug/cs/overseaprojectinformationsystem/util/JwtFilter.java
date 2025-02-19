package com.cug.cs.overseaprojectinformationsystem.util;

import com.alibaba.fastjson.JSONObject;
import com.cug.cs.overseaprojectinformationsystem.bean.common.ResponseData;
import com.cug.cs.overseaprojectinformationsystem.bean.common.ResponseUtil;
import com.cug.cs.overseaprojectinformationsystem.config.JwtToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.Filter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class JwtFilter extends BasicHttpAuthenticationFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(JwtUtil.class);

    /**
     * 执行登录
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String token = httpRequest.getHeader("token");
        
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
            // 统一异常处理
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.getWriter().write("Invalid token");
            return false;
        }
    }

    /**
     * 执行登录认证
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        try {
            return executeLogin(request, response);
        } catch (Exception e) {
            log.error("JwtFilter过滤验证失败!");
            return false;
        }
    }

    /**
     * 认证失败时，自定义返回json数据
     *
     * @param request  请求
     * @param response 响应
     * @return boolean* @throws Exception 异常
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        ResponseData responseData = new ResponseUtil().setErrorMsg(-1, "认证失败");
//        Result result = Result.fail(-1,"认证失败");
        Object parse = JSONObject.toJSON(responseData);
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(parse);
        return super.onAccessDenied(request, response);
    }

    /**
     * 对跨域提供支持
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }
}
