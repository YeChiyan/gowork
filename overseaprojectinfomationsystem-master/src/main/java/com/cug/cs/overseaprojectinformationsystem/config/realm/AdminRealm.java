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
public class AdminRealm extends AuthorizingRealm {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Value("${custom.jwt.expire_time}")
    private long adminExpireTime;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken && ((JwtToken) token).isAdmin();
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("开启AdminRealm 权限认证-----------");
        String token = (String) SecurityUtils.getSubject().getPrincipal();
        String username = JwtUtil.getUsername(token);
        
        // 这里应该查询数据库获取管理员权限
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        HashSet<String> set = new HashSet<>();
        set.add("admin:manage");
        set.add("admin:delete");
        info.setStringPermissions(set);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("-------------开始管理员身份认证------------------");
        String jwtToken = (String) token.getCredentials();
        String username = JwtUtil.getUsername(jwtToken);

        if (username == null || !username.startsWith("admin_")) {
            throw new AuthenticationException("管理员令牌无效");
        }

        if (!adminTokenRefresh(jwtToken, username)) {
            throw new AuthenticationException("管理员令牌已失效");
        }

        return new SimpleAuthenticationInfo(token, token, getName());
    }

    private boolean adminTokenRefresh(String token, String username) {
        String redisKey = "admin:" + token;
        String redisToken = redisTemplate.opsForValue().get(redisKey);

        if (redisToken != null && JwtUtil.verify(redisToken, username)) {
            String newToken = JwtUtil.generateAdminToken(username);
            redisTemplate.delete(redisKey);
            redisTemplate.opsForValue().set("admin:" + newToken, newToken, 
                adminExpireTime * 2 / 1000, TimeUnit.SECONDS);
            log.info("管理员令牌刷新成功");
            return true;
        }
        return false;
    }
}
