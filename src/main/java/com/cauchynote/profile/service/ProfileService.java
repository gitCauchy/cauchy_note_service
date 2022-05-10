package com.cauchynote.profile.service;

import com.cauchynote.profile.entity.Profile;
import org.springframework.stereotype.Service;

/**
 * @Author lingling
 * @Description
 * @Date 2022/5/4
 * @Version
 */

@Service
public interface ProfileService {
    /**
     * 添加新的个人简介（不包括邮箱）
     * @param profile
     * @return
     */
    Integer addNewProfile(Profile profile);

    /**
     * 修改个人信息（不包括邮箱）
     * @param profile
     * @return
     */

    Integer modifyProfile(Profile profile);

    /**
     * 获取个人信息（包括邮箱）
     * @param userId
     * @return
     */
    Profile getProfile(Integer userId);


}
