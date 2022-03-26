package com.cauchynote.system.controller;

import com.cauchynote.system.service.LoginInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Cauchy
 * @ClassName LoginInfoController
 * @Description 获取登录信息
 * @Date 22/02/05
 * @Version 0.1
 */
@RestController
@RequestMapping("/loginInfo")
public class LoginInfoController {
    @Autowired
    LoginInfoService loginInfoService;

    @GetMapping("/loginCountInfo")
    public ResponseEntity<Map<String, Map>> getLoginCount(@RequestParam(value = "userId") Long userId) {
        Map userLoginInfo = loginInfoService.getUserLoginCount(userId);
        Map totalLoginInfo = loginInfoService.getTotalLoginCount();
        Map<String, Map> returnMap = new HashMap<>(2);
        returnMap.put("user", userLoginInfo);
        returnMap.put("total", totalLoginInfo);
        return new ResponseEntity<>(returnMap, HttpStatus.OK);
    }

}
