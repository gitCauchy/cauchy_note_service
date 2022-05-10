package com.cauchynote.profile.service;

import com.cauchynote.profile.entity.Setting;
import org.springframework.stereotype.Service;

/**
 * 个人设置服务层
 *
 * @Author lingling
 * @Date 2022/5/4
 */
@Service
public interface SettingService {
    /**
     * 新增个人设置
     *
     * @param setting Setting 对象
     * @return 1 - 成功 | 0 - 失败
     */
    Integer addNewSetting(Setting setting);

    /**
     * 修改个人设置
     *
     * @param setting 个人设置对象
     * @return 1 - 成功 | 0 - 失败
     */
    Integer modifySetting(Setting setting);

    /**
     * 获取个人设置
     *
     * @param userId 用户 ID
     * @return Setting 对象
     */
    Setting getSettingById(Integer userId);
}
