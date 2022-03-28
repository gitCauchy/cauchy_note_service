package com.cauchynote.utils;

import redis.clients.jedis.Jedis;

/**
 * @author Cauchy
 * @ClassName RedisUtil.java
 * @createTime 2022年03月28日 12:29:00
 */
public class RedisUtil {
    /**
     * 存储验证码
     *
     * @param username    用户名
     * @param checkCode 生产的验证码
     */
    public static void setKey(String username, Integer checkCode) {
        Jedis jedis = new Jedis(SystemConstantDefine.REDIS_HOST, SystemConstantDefine.REDIS_PORT);
        jedis.set(username, String.valueOf(checkCode));
        jedis.close();
    }

    /**
     * 获取验证码
     * @param username 用户名
     * @return 验证码
     */
    public static int getKey(String username) {
        Jedis jedis = new Jedis(SystemConstantDefine.REDIS_HOST, SystemConstantDefine.REDIS_PORT);
        return Integer.parseInt(jedis.get(username));
    }
}
