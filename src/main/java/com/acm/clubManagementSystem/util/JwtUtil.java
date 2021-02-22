package com.acm.clubManagementSystem.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Data;

import java.util.Date;

/**
 * @author lkh
 * @create 2021-1-18 13:38
 * @desc JWT工具类
 **/
@Data
public class JwtUtil {
    // 过期时间24小时
    private static final long EXPIRE_TIME = 60 * 24 * 60 * 1000;

    private static String JWTUsername="ACM_SYSTEM";
    private static String JWTPassword="root";

    public static String getJWTUsername() {
        return JWTUsername;
    }

    public static String getJWTPassword() {
        return JWTPassword;
    }

    /**
     * 校验token是否正确
     * @param token    密钥ss
     * @param username 登录名
     * @param password 密码
     * @return
     */
    public static boolean verify(String token, String username, String password) {
        Algorithm algorithm = Algorithm.HMAC256(password);
        JWTVerifier verifier = JWT.require(algorithm).withClaim("username", username).build();
        try {
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取登录名
     * @param token
     * @return
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);

            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成签名
     * @param username
     * @param password
     * @return
     */
    public static String sign(String username, String password) {
        try {
            // 指定过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            // 加密算法
            Algorithm algorithm = Algorithm.HMAC256(password);
//            创建JWT builder
            JWTCreator.Builder builder = JWT.create();
//            生成认证的token
            return builder
                    .withClaim("username", username)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            return null;
        }
    }
}
