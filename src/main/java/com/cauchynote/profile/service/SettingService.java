package com.cauchynote.profile.service;

import com.cauchynote.profile.entity.Profile;
import com.cauchynote.profile.entity.Setting;
import org.springframework.stereotype.Service;

/**
 * @Author lingling
 * @Description
 * @Date 2022/5/4
 * @Version
 */
@Service
public interface SettingService {
    Integer addNewSetting(Setting setting);
    Integer modifySetting(Setting setting);
    Setting getSettingById(Integer userId);
}
