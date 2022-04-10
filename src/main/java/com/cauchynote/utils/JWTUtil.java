package com.cauchynote.utils;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.UUID;

/**
 * JWT 工具类
 *
 * @Author Cauchy
 * @ClassName JWTUtil
 */
public class JWTUtil {

    public static String createToken(String username) {
        long time = 1000 * 60 * 60 * 24;
        JwtBuilder jwtBuilder = Jwts.builder();
        return jwtBuilder
            .setHeaderParam("typ", "JWTUtil")
            .setHeaderParam("alg", "HS256")
            .claim("username", username)
            .setSubject("jwt-login")
            .setExpiration(new Date(System.currentTimeMillis() + time))
            .setId(UUID.randomUUID().toString())
            .signWith(SignatureAlgorithm.HS256, username)
            .compact();
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
