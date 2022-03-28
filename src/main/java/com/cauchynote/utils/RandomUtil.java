package com.cauchynote.utils;

import java.util.Random;

/**
 * @author Cauchy
 * @ClassName RandomUtil.java
 * @createTime 2022年03月28日 12:26:00
 */
public class RandomUtil {
    /**
     * 返回一个 6 位的随机数
     *
     * @return int
     */
    public static int getRandomInt() {
        Random random = new Random();
        return random.nextInt(899999) + 100000;
    }
}
