package com.cauchynote.utils;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.UUID;

/**
 * @Author Cauchy
 * @ClassName JWTUtil
 * @Description //JWT 工具类
 * @Date 22/01/24
 * @Version 0.1
 */
public class JWTUtil {

    public static String createToken(String username) {
        long time = 1000 * 60 * 60 * 24;
        JwtBuilder jwtBuilder = Jwts.builder();
        String jwtToken = jwtBuilder
                .setHeaderParam("typ", "JWTUtil")
                .setHeaderParam("alg", "HS256")
                .claim("username", username)
                .setSubject("jwt-login")
                .setExpiration(new Date(System.currentTimeMillis() + time))
                .setId(UUID.randomUUID().toString())
                .signWith(SignatureAlgorithm.HS256, username)
                .compact();
        return jwtToken;
    }


    public static boolean checkToken(String token, String signature) {
        if (token == null) {
            return false;
        }
        JwtParser jwtParser = Jwts.parser();
        try {
            jwtParser.setSigningKey(signature).parseClaimsJws(token);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
