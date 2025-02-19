package com.cug.cs.overseaprojectinformationsystem.controller;

import com.cug.cs.overseaprojectinformationsystem.dto.Result;
import com.cug.cs.overseaprojectinformationsystem.util.JwtUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/test")
public class PermissionTestController {


    @Autowired
    private StringRedisTemplate redisTemplate;

    @Value("${custom.jwt.expire_time}")
    private long expireTime;


    // 普通用户权限测试
    @GetMapping("/user")
    @RequiresPermissions("user:show")
    public Result userTest() {
        return Result.ok("普通用户权限测试通过");
    }

    // 管理员基础权限测试
    @GetMapping("/admin/manage")
    @RequiresPermissions("admin:manage")
    public Result adminManageTest() {
        return Result.ok("管理员管理权限测试通过");
    }

    // 管理员删除权限测试
    @GetMapping("/admin/delete")
    @RequiresPermissions("admin:delete")
    public Result adminDeleteTest() {
        return Result.ok("管理员删除权限测试通过");
    }

    // 超级管理员专属权限测试
    @GetMapping("/superadmin/manage")
    @RequiresPermissions("superadmin:manage")
    public Result superAdminManageTest() {
        return Result.ok("超级管理员管理权限测试通过");
    }

    // 超级管理员删除权限测试
    @GetMapping("/superadmin/delete")
    @RequiresPermissions("superadmin:delete")
    public Result superAdminDeleteTest() {
        return Result.ok("超级管理员删除权限测试通过");
    }

    // 角色继承测试（超级管理员应能访问管理员接口）
    @GetMapping("/admin/inherit")
    @RequiresRoles("admin")
    public Result adminInheritTest() {
        return Result.ok("管理员角色继承测试通过");
    }

    // 超级管理员角色测试
    @GetMapping("/superadmin/role")
    @RequiresRoles("superadmin")
    public Result superAdminRoleTest() {
        return Result.ok("超级管理员角色测试通过");
    }


    // 生成普通用户token
    @GetMapping("/user1/getToken")
    public Result userToken(@RequestParam String username) {
        String token = JwtUtil.generateToken(username);
        String redisKey="user:"+token;
        redisTemplate.opsForValue().set(redisKey,token, 60*60, TimeUnit.SECONDS);

        return Result.ok(token);
    }

    // 生成管理员token
    @GetMapping("/admin1/getToken")
    public Result adminToken(@RequestParam String username) {
        String token = JwtUtil.generateAdminToken(username);
        String redisKey="admin:"+token;
        redisTemplate.opsForValue().set(redisKey,token, 60*60, TimeUnit.SECONDS);
        return Result.ok(token);

    }

    // 生成超级管理员token
    @GetMapping("/superadmin1/getToken")
    public Result superAdminToken(@RequestParam String username) {
        String token = JwtUtil.generateSuperAdminToken(username);
        String redisKey="superadmin:"+token;
        redisTemplate.opsForValue().set(redisKey,token, 60*60, TimeUnit.SECONDS);
        return Result.ok(token);
    }
} 