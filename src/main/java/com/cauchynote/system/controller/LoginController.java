package com.cauchynote.system.controller;

import com.cauchynote.system.entity.LoginInfo;
import com.cauchynote.system.entity.User;
import com.cauchynote.system.service.LoginInfoService;
import com.cauchynote.system.service.UserService;
import com.cauchynote.system.service.impl.UserServiceImpl;
import com.cauchynote.utils.JWTUtil;
import com.cauchynote.utils.SystemConstantDefine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Cauchy
 * @ClassName LoginController
 * @Description 登录校验
 * @Date 21/12/30
 * @Version 0.1
 */
@CrossOrigin
@RestController
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    UserServiceImpl userServiceImpl;
    @Autowired
    LoginInfoService loginInfoService;

    @PostMapping("/login")
    public ResponseEntity<Map> login(@RequestBody User user) {
        Map<String, Object> responseMap = new HashMap<>();
        if (userService.login(user.getUsername(), user.getPassword())) {
            User userInfo = (User)userServiceImpl.loadUserByUsername(user.getUsername());
            // 将登录信息记录下来
            loginInfoService.addLoginInfo(userInfo.getId());
            responseMap.put("token", JWTUtil.createToken(user.getUsername()));
            responseMap.put("userInfo", userInfo);
            responseMap.put("SystemStatusCode", SystemConstantDefine.SUCCESS);
            return new ResponseEntity<>(responseMap, HttpStatus.OK);
        }
        responseMap.put("SystemStatusCode", SystemConstantDefine.FAIL);
        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Integer> register(@RequestBody User user) {
        return new ResponseEntity(userService.register(user), HttpStatus.OK);
    }

    @GetMapping("/checkToken")
    public ResponseEntity<Boolean> checkToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        String username = request.getHeader("username");
        return new ResponseEntity<>(JWTUtil.checkToken(token, username), HttpStatus.OK);
    }

}
