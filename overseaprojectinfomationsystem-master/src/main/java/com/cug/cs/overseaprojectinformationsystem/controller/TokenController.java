package com.cug.cs.overseaprojectinformationsystem.controller;

import com.cug.cs.overseaprojectinformationsystem.dto.Result;
import com.cug.cs.overseaprojectinformationsystem.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/auth")
@Slf4j
public class TokenController {
    @Autowired
    private StringRedisTemplate redisTemplate;

    // 生成普通用户token（带Redis存储）
    @GetMapping("/user/token")
    public Result userToken(@RequestParam String username) {
        log.info("    @GetMapping(\"/user/token\")被调用了哦");
        String token = JwtUtil.generateToken(username);
        String redisKey = "user:" + token;
        redisTemplate.opsForValue().set(redisKey, token, 100000, TimeUnit.SECONDS);
        log.info("生成的 token 为"+token);
        return Result.ok(token);
    }

    // 生成管理员token（带前缀校验）
    @GetMapping("/admin/token")
    public Result adminToken(@RequestParam String username) {
        if (!username.startsWith("admin_")) {
            return Result.fail("管理员用户名必须以admin_开头");
        }
        String token = JwtUtil.generateAdminToken(username);
        String redisKey = "admin:" + token;
        redisTemplate.opsForValue().set(redisKey, token, 100000, TimeUnit.SECONDS);
        return Result.ok(token);
    }

    // 生成超级管理员token（带双重校验）
    @GetMapping("/superadmin/token")
    public Result superAdminToken(@RequestParam String username) {
        if (!username.startsWith("superadmin_")) {
            return Result.fail("超级管理员用户名必须以superadmin_开头");
        }
        String token = JwtUtil.generateSuperAdminToken(username);
        String redisKey = "superadmin:" + token;
        redisTemplate.opsForValue().set(redisKey, token, 100000, TimeUnit.SECONDS);
        return Result.ok(token);
    }
} 