package com.cauchynote.profile.controller;
import com.cauchynote.profile.entity.Setting;
import com.cauchynote.profile.service.SettingService;
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
@RequestMapping("/setting")
@CrossOrigin
public class SettingController {
    private SettingService settingService;
    @PostMapping("/addSetting")
    public ResponseEntity<Integer> addNewSetting(@RequestBody Setting setting){
        Integer result =settingService.addNewSetting(setting) ;
        if (result == 1) {
            return new ResponseEntity<>(SystemConstantDefine.SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(SystemConstantDefine.FAIL, HttpStatus.OK);
    }

    /**
     * 修改个人信息（不包括邮箱）
     * @param setting
     * @return
     */


    @PostMapping("/modifySetting")
    public ResponseEntity<Integer> modifySetting(@RequestBody Setting setting) {
        Integer result = settingService.modifySetting(setting);
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

    @GetMapping("/getSetting")
    public ResponseEntity<Setting> getSetting(@RequestParam Integer userId) {
        Setting setting = settingService.getSettingById(userId);
        return new ResponseEntity<>(setting, HttpStatus.OK);
    }

}
