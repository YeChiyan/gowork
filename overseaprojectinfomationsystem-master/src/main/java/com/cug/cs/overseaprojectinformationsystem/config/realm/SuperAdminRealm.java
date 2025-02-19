package com.cug.cs.overseaprojectinformationsystem.config.realm;

import com.cug.cs.overseaprojectinformationsystem.config.JwtToken;
import com.cug.cs.overseaprojectinformationsystem.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class SuperAdminRealm extends AuthorizingRealm {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Value("${custom.jwt.expire_time}")
    private long superAdminExpireTime;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken && ((JwtToken) token).isSuperAdmin();
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("开启SuperAdminRealm权限认证-----------");
        String token = (String) SecurityUtils.getSubject().getPrincipal();
        String username = JwtUtil.getUsername(token);
        
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        HashSet<String> permissions = new HashSet<>();
        permissions.add("superadmin:manage");
        permissions.add("superadmin:delete");
        permissions.add("admin:manage"); // 继承管理员权限
        info.setStringPermissions(permissions);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("-------------开始超级管理员身份认证------------------");
        String jwtToken = (String) token.getCredentials();
        String username = JwtUtil.getUsername(jwtToken);

        if (username == null || !username.startsWith("superadmin_")) {
            throw new AuthenticationException("超级管理员令牌无效");
        }

        if (!superAdminTokenRefresh(jwtToken, username)) {
            throw new AuthenticationException("超级管理员令牌已失效");
        }

        return new SimpleAuthenticationInfo(token, token, getName());
    }

    private boolean superAdminTokenRefresh(String token, String username) {
        String redisKey = "superadmin:" + token;
        String redisToken = redisTemplate.opsForValue().get(redisKey);

        if (redisToken != null && JwtUtil.verify(redisToken, username)) {
            String newToken = JwtUtil.generateSuperAdminToken(username);
            redisTemplate.delete(redisKey);
            redisTemplate.opsForValue().set("superadmin:" + newToken, newToken, 
                superAdminExpireTime * 2 / 1000, TimeUnit.SECONDS);
            log.info("超级管理员令牌刷新成功");
            return true;
        }
        return false;
    }
} 