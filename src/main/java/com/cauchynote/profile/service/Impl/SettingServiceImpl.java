package com.cauchynote.profile.service.Impl;

import com.cauchynote.profile.entity.Setting;
import com.cauchynote.profile.mapper.SettingMapper;
import com.cauchynote.profile.service.SettingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 个人设置服务层实现类
 *
 * @Author lingling
 * @Date 2022/5/4
 */
@Service
@AllArgsConstructor
public class SettingServiceImpl implements SettingService {
    private SettingMapper settingMapper;

    @Override
    public Integer addNewSetting(Setting setting) {
        return settingMapper.addSetting(setting);
    }

    @Override
    public Integer modifySetting(Setting setting) {
        return settingMapper.modifySetting(setting);
    }

    @Override
    public Setting getSettingById(Integer userId) {
        return settingMapper.getSettingById(userId);
    }
}
