package com.cug.cs.overseaprojectinformationsystem.config.realm;

import com.cug.cs.overseaprojectinformationsystem.config.JwtToken;
import com.cug.cs.overseaprojectinformationsystem.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
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

/**
 * @description: TODO  提供信息：授权信息与认证信息（普通用户）
 * @author: ShengHui
 * @date: 2023-07-21  16:29
 */
@Slf4j
@Component
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Value("${custom.jwt.expire_time}")
    private long expireTime;


    @Override
    public boolean supports(AuthenticationToken token) {
        // 确保能处理JwtToken类型
        if (!(token instanceof JwtToken)) {
            return false;
        }
        JwtToken jwtToken = (JwtToken) token;
        // 普通用户的token：既不是admin也不是superAdmin
        return !jwtToken.isAdmin() && !jwtToken.isSuperAdmin();
    }

    //权限认证
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("开启UserRealm 权限认证-----------");
        String token = (String) SecurityUtils.getSubject().getPrincipal();
        String username = JwtUtil.getUsername(token);
        //从数据库中进行校验用户名和密码
//        //先写死吧
//        if (!"Coco".equals(username)) {
//            return null;
//        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        HashSet<String> set = new HashSet<>();
        set.add("user:show");
        set.add("user:admin");
        info.setStringPermissions(set);
        log.info("用户ID {} 拥有权限：{}", token, info.getStringPermissions());
        return info;
    }

    //认证逻辑
    //验证token 的有效性和用户信息
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("-------------开始身份认证------------------");
        String jwtToken = (String)token.getCredentials();
        String username=null;
        try {
            username = JwtUtil.getUsername(jwtToken);
        } catch (AuthenticationException e) {
            throw new AuthenticationException("header的token拼写错误或者值为空");
        }

        if(username==null){
            throw new AuthenticationException("token无效------");
        }
        //l
        // 校验token是否超时失效 & 或者账号密码是否错误
        if (!jwtTokenRefresh(jwtToken, username)) {
            throw new AuthenticationException("Token失效，请重新登录!");
        }
        //返回身份认证信息
        //理论上应该把密码设置进去
//        return new SimpleAuthenticationInfo(token, "123", "UserRealm");
        return new SimpleAuthenticationInfo(token, token, getName());
    }

    public boolean jwtTokenRefresh(String token, String userName) {
        // 从 Redis 获取存储的 token
        String redisKey = "user:" + token; // 添加命名空间

        String redisToken = redisTemplate.opsForValue().get(redisKey);

        if(redisToken==null){
            log.error("redisToken为 null");
        }
//        String redisToken = (String) redisTemplate.opsForValue().get(token);
        log.info("redisToken"+redisToken);
        // 如果 Redis 中存在 token
        if (redisToken != null) {
            System.out.println("token 不为 null");
            // 验证 token 是否有效
            if (JwtUtil.verify(redisToken, userName)) {
                // 如果有效，则生成新的 token
                String newToken = JwtUtil.generateToken(userName);

                // 删除旧的 token
                redisTemplate.delete(token);

                // 设置新的 token 到 Redis，设置过期时间为原来的两倍（根据需要调整）
                redisTemplate.opsForValue().set(newToken, newToken, expireTime * 2 / 1000, TimeUnit.SECONDS);

                log.info("Token 刷新成功！");
                return true;
            } else {
                log.warn("旧的 token 已经失效，无法刷新！");
            }
        }
        return false;
    }
}
