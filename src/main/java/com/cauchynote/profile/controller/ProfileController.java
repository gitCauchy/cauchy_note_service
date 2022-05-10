package com.cauchynote.profile.controller;

import com.cauchynote.article.entity.Article;
import com.cauchynote.message.entity.Message;
import com.cauchynote.profile.entity.Profile;
import com.cauchynote.profile.service.ProfileService;
import com.cauchynote.utils.SystemConstantDefine;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Author lingling
 * @Description
 * @Date 2022/5/4
 * @Version
 */
@RestController
@AllArgsConstructor
@RequestMapping("/profile")
@CrossOrigin
public class ProfileController {
    private ProfileService profileService;

    /**
     * 添加个人信息不包括邮箱
     * @param profile
     * @return
     */
    @PostMapping("/addProfile")
    public ResponseEntity<Integer> addNewProfile(@RequestBody Profile profile){
        Integer result = profileService.addNewProfile(profile);
        if (result == 1) {
            return new ResponseEntity<>(SystemConstantDefine.SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(SystemConstantDefine.FAIL, HttpStatus.OK);
    }

    /**
     * 修改个人信息（不包括邮箱）
     * @param profile
     * @return
     */


    @PostMapping("/modifyProfile")
    public ResponseEntity<Integer> modifyArticle(@RequestBody Profile profile) {
        Integer result = profileService.modifyProfile(profile);
        if (result == 1) {
            return new ResponseEntity<>(SystemConstantDefine.SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(SystemConstantDefine.FAIL, HttpStatus.OK);
    }

    /**
     * 获取个人信息包括邮箱
     * @param userId
     * @return
     */

    @GetMapping("/getProfile")
    public ResponseEntity<Profile> getProfile(@RequestParam Integer userId) {
        Profile profile = profileService.getProfile(userId);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }





}
