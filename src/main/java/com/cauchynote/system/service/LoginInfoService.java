package com.cauchynote.system.service;

import com.cauchynote.system.entity.LoginInfo;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author Cauchy
 * @ClassName LoginInfoService
 * @Description 登录信息记录实体类
 * @Date 22/02/05
 * @Version 0.1
 */
@Service
public interface LoginInfoService {
    /**
     * 新增登录信息
     *
     * @param userId 用户ID
     * @return 新增状态
     */
    int addLoginInfo(Long userId);

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
