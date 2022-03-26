package com.cauchynote.system.service.impl;

import com.cauchynote.system.entity.LoginInfo;
import com.cauchynote.system.mapper.LoginInfoMapper;
import com.cauchynote.system.service.LoginInfoService;
import com.cauchynote.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Cauchy
 * @ClassName LoginInfoServiceImpl
 * @Description 登录信息记录服务层实现类
 * @Date 22/02/05
 * @Version 0.1
 */
@Service
public class LoginInfoServiceImpl implements LoginInfoService {

    @Autowired
    LoginInfoMapper loginInfoMapper;

    @Override
    public int addLoginInfo(Long userId) {
        return loginInfoMapper.addLoginInfo(userId);
    }

    @Override
    public Map<String, Integer> getUserLoginCount(Long userId) {
        Date today = new Date();
        Date startWeek = DateUtil.startWeek(today);
        Date startMonth = DateUtil.startBeforeNMonth(today, 0);
        Date startYear = DateUtil.startYear(today);
        int countOfWeek = loginInfoMapper.getUserLoginCount(userId, startWeek);
        int countOfMonth = loginInfoMapper.getUserLoginCount(userId, startMonth);
        int countOfYear = loginInfoMapper.getUserLoginCount(userId, startYear);
        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("countOfWeek", countOfWeek);
        resultMap.put("countOfMonth", countOfMonth);
        resultMap.put("countOfYear", countOfYear);
        return resultMap;
    }

    @Override
    public Map<String, Integer> getTotalLoginCount() {
        Date today = new Date();
        Date startWeek = DateUtil.startWeek(today);
        Date startMonth = DateUtil.startBeforeNMonth(today, 0);
        Date startYear = DateUtil.startYear(today);
        int countOfWeek = loginInfoMapper.getTotalLoginCount(startWeek);
        int countOfMonth = loginInfoMapper.getTotalLoginCount(startMonth);
        int countOfYear = loginInfoMapper.getTotalLoginCount(startYear);
        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("countOfWeek", countOfWeek);
        resultMap.put("countOfMonth", countOfMonth);
        resultMap.put("countOfYear", countOfYear);
        return resultMap;
    }
}
