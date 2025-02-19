package com.cug.cs.overseaprojectinformationsystem.controller;

import com.cug.cs.overseaprojectinformationsystem.dto.Result;
import com.cug.cs.overseaprojectinformationsystem.util.JwtUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/shiro")
public class ShiroController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Value("${custom.jwt.expire_time}")
    private long expireTime;

    @RequestMapping("/getToken")
    public Result getToken(@RequestParam("username") String username){

        String token = JwtUtil.generateToken(username);
        String redisKey = "user:" + token; // 添加命名空间

        redisTemplate.opsForValue().set(redisKey,token, 60*60, TimeUnit.SECONDS);
        String jwtt = (String)redisTemplate.opsForValue().get(redisKey);
        System.out.println(jwtt);
        return Result.ok(token);
    }

    @RequiresPermissions("user:show")
    @RequestMapping("/test")
    public Result test(){
        System.out.println("test接口被访问");
        System.out.println("进入测试，只有带有令牌才可以进入该方法");
        return Result.ok("访问接口成功");
    }
}
