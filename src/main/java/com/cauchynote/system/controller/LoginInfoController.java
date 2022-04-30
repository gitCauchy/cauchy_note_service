package com.cauchynote.system.controller;

import com.cauchynote.system.service.LoginInfoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录信息控制层
 *
 * @Author Cauchy
 * @ClassName LoginInfoController
 */
@RestController
@AllArgsConstructor
@RequestMapping("/loginInfo")
public class LoginInfoController {
    LoginInfoService loginInfoService;

    @GetMapping("/loginCountInfo")
    public ResponseEntity<Map<String, Map<String, Integer>>> getLoginCount(@RequestParam(value = "userId") Integer userId) {
        Map<String, Integer> userLoginInfo = loginInfoService.getUserLoginCount(userId);
        Map<String, Integer> totalLoginInfo = loginInfoService.getTotalLoginCount();
        Map<String, Map<String, Integer>> returnMap = new HashMap<>(2);
        returnMap.put("user", userLoginInfo);
        returnMap.put("total", totalLoginInfo);
        return new ResponseEntity<>(returnMap, HttpStatus.OK);
    }

}
