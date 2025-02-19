package com.cug.cs.overseaprojectinformationsystem.util;

import com.cug.cs.overseaprojectinformationsystem.dto.MemberDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {
    // 测试用简单密钥（仅限开发环境）
    private static final String SECRET_KEY = "testkey12fdwefewfdeedwdwqdwqdwqdqwddededededededqwws3";
    public static final long EXpirationTime = 1000 * 60 * 60 * 24; // 1天

    public static String generateJwtToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXpirationTime))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // 保持HS256算法
                .compact();
    }
    // 验证 JWT Token 是否有效
    public static boolean validateToken(String token, String username) {
        String extractedUsername = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

        return extractedUsername.equals(username) && !isTokenExpired(token);
    }

    // 检查 Token 是否过期
    public static boolean isTokenExpired(String token) {
        Date expirationDate = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();

        return expirationDate.before(new Date());
    }

    // 从 Token 获取用户名
    public static String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public static void main(String[] args) {
        MemberDto memberDto = new MemberDto();
        memberDto.setName("lili");
        String jwtToken = generateJwtToken(memberDto.getPhone());
        System.out.println("生成 token 为:"+jwtToken);
    }
}
