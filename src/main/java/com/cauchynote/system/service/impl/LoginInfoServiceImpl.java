package com.cauchynote.system.service.impl;

import com.cauchynote.system.mapper.LoginInfoMapper;
import com.cauchynote.system.service.LoginInfoService;
import com.cauchynote.utils.DateUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录信息记录服务层实现类
 *
 * @Author Cauchy
 * @ClassName LoginInfoServiceImpl
 */
@Service
@AllArgsConstructor
public class LoginInfoServiceImpl implements LoginInfoService {

    LoginInfoMapper loginInfoMapper;

    @Override
    public void addLoginInfo(Integer userId) {
        loginInfoMapper.addLoginInfo(userId);
    }

    @Override
    public Map<String, Integer> getUserLoginCount(Integer userId) {
        Date today = new Date();
        Date startWeek = DateUtil.startWeek(today);
        Date startMonth = DateUtil.startBeforeNumMonth(today, 0);
        Date startYear = DateUtil.startYear(today);
        int countOfWeek = loginInfoMapper.getUserLoginCount(userId, startWeek);
        int countOfMonth = loginInfoMapper.getUserLoginCount(userId, startMonth);
        int countOfYear = loginInfoMapper.getUserLoginCount(userId, startYear);
        Map<String, Integer> resultMap = new HashMap<>(3);
        resultMap.put("countOfWeek", countOfWeek);
        resultMap.put("countOfMonth", countOfMonth);
        resultMap.put("countOfYear", countOfYear);
        return resultMap;
    }

    @Override
    public Map<String, Integer> getTotalLoginCount() {
        Date today = new Date();
        Date startWeek = DateUtil.startWeek(today);
        Date startMonth = DateUtil.startBeforeNumMonth(today, 0);
        Date startYear = DateUtil.startYear(today);
        int countOfWeek = loginInfoMapper.getTotalLoginCount(startWeek);
        int countOfMonth = loginInfoMapper.getTotalLoginCount(startMonth);
        int countOfYear = loginInfoMapper.getTotalLoginCount(startYear);
        Map<String, Integer> resultMap = new HashMap<>(3);
        resultMap.put("countOfWeek", countOfWeek);
        resultMap.put("countOfMonth", countOfMonth);
        resultMap.put("countOfYear", countOfYear);
        return resultMap;
    }
}
