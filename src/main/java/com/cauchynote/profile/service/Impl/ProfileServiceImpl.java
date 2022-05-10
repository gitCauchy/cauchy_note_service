package com.cauchynote.profile.service.Impl;

import com.cauchynote.profile.entity.Profile;
import com.cauchynote.profile.mapper.ProfileMapper;
import com.cauchynote.profile.service.ProfileService;
import com.cauchynote.system.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author lingling
 * @Description
 * @Date 2022/5/4
 * @Version
 */
@Service
@AllArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private ProfileMapper profileMapper;




    @Override
    public Integer addNewProfile(Profile profile) {
        return profileMapper.addProfile(profile);
    }

    @Override
    public Integer modifyProfile(Profile profile) {
        return profileMapper.modifyProfile(profile);
    }

    @Override
    public Profile getProfile(Integer userId) {
        return profileMapper.getProfileById(userId);
    }
}
