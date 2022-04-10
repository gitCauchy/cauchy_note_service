package com.cauchynote.system.service;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 登录信息记录实体类
 *
 * @Author Cauchy
 * @ClassName LoginInfoService
 */
@Service
public interface LoginInfoService {
    /**
     * 新增登录信息
     *
     * @param userId 用户ID
     */
    void addLoginInfo(Long userId);

    /**
     * 获取用户登录次数
     *
     * @param userId 用户ID
     * @return 登录次数
     */
    Map<String, Integer> getUserLoginCount(Long userId);

    /**
     * 获取所有用户登录次数
     *
     * @return 登录次数
     */
    Map<String, Integer> getTotalLoginCount();
}
