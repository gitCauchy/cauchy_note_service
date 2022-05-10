package com.cauchynote.profile.service;

import com.cauchynote.profile.entity.Profile;
import org.springframework.stereotype.Service;

/**
 * 个人信息服务层
 *
 * @Author lingling
 * @Date 2022/5/4
 */

@Service
public interface ProfileService {

    /**
     * 添加新的个人简介（不包括邮箱）
     *
     * @param profile Profile 对象
     * @return 1 - 成功 | 0 - 失败
     */
    Integer addNewProfile(Profile profile);

    /**
     * 修改个人信息（不包括邮箱）
     *
     * @param profile Profile 对象
     * @return 1 - 成功 0 - 失败
     */
    Integer modifyProfile(Profile profile);

    /**
     * 获取个人信息（包括邮箱）
     *
     * @param userId 用户 ID
     * @return Profile 对象
     */
    Profile getProfile(Integer userId);


}
