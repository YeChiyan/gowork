package com.cug.cs.overseaprojectinformationsystem.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private static final long EXPIRE_TIME = 30 * 60 * 1000;  // Token过期时间，单位为毫秒
    private static String SECRET;  // 静态变量

    @Value("${custom.jwt.secret}")
    public void setSecret(String secret) {
        JwtUtil.SECRET = secret;  // 通过 setter 方法注入
    }

    private static final Logger log = LoggerFactory.getLogger(JwtUtil.class);

    /**
     * 校验token是否正确
     *
     * @param token   密钥
     * @param username 用户名
     * @return 是否正确
     */
    public static boolean verify(String token, String username) {
        try {
            // 根据密码生成JWT效验器
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            // 校验TOKEN
            DecodedJWT jwt = verifier.verify(token);
            log.info("登录验证成功!");
            return true;
        } catch (JWTVerificationException exception) {
            // 捕获过期或者无效token的异常
            log.error("JwtUtil登录验证失败: " + exception.getMessage());
            return false;
        }
    }

    /**
     * 获得token中的信息，无需secret解密也能获得
     *
     * @param token token
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            log.error("Token解析失败: {}", e.getMessage());
            return null;
        }
    }

    /**
     * 生成token签名，EXPIRE_TIME分钟后过期
     *
     * @param username 用户名
     * @return 加密的token
     */
    public static String generateToken(String username) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        // 附带username信息，并设置过期时间
        return JWT.create()
                .withClaim("username", username)
                .withExpiresAt(date) // 设置过期时间
                .sign(algorithm);
    }

    // 生成管理员令牌（保持auth0风格）
    public static String generateAdminToken(String username) {
        Date expireDate = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(SECRET);

        return JWT.create()
                .withClaim("username", username)
                .withClaim("isAdmin", true)  // 添加管理员标识
                .withExpiresAt(expireDate)
                .sign(algorithm);
    }


    // 生成超级管理员令牌
    public static String generateSuperAdminToken(String username) {
        Date expireDate = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(SECRET);

        return JWT.create()
                .withClaim("username", username)
                .withClaim("isAdmin", true)
                .withClaim("isSuperAdmin", true)  // 添加超级管理员标识
                .withExpiresAt(expireDate)
                .sign(Algorithm.HMAC256(SECRET));
    }

    // 解析Token（统一使用auth0方式）
    public static DecodedJWT parseToken(String token) {
        try {
            return JWT.decode(token);
        } catch (JWTDecodeException e) {
            log.error("Token解析失败: {}", e.getMessage());
            return null;
        }
    }

    // 验证管理员令牌
    public static boolean isAdminToken(String token) {
        try {
            DecodedJWT jwt = parseToken(token);
            if (jwt == null) return false;

            Boolean isAdmin = jwt.getClaim("isAdmin").asBoolean();
            return isAdmin != null && isAdmin;
        } catch (Exception e) {
            log.error("管理员令牌验证异常: {}", e.getMessage());
            return false;
        }
    }

    // 验证超级管理员令牌
    public static boolean isSuperAdminToken(String token) {
        try {
            DecodedJWT jwt = parseToken(token);
            if (jwt == null) return false;
            
            Boolean isSuperAdmin = jwt.getClaim("isSuperAdmin").asBoolean();
            return isSuperAdmin != null && isSuperAdmin;
        } catch (Exception e) {
            log.error("超级管理员令牌验证异常: {}", e.getMessage());
            return false;
        }
    }

}