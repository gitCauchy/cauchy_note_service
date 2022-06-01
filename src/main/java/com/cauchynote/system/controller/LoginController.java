package com.cauchynote.system.controller;

import com.cauchynote.profile.entity.Profile;
import com.cauchynote.profile.service.ProfileService;
import com.cauchynote.system.entity.User;
import com.cauchynote.system.service.LoginInfoService;
import com.cauchynote.system.service.UserService;
import com.cauchynote.system.service.impl.UserServiceImpl;
import com.cauchynote.utils.JWTUtil;
import com.cauchynote.utils.SystemConstantDefine;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录控制层
 *
 * @Author Cauchy
 * @ClassName LoginController
 */
@CrossOrigin
@AllArgsConstructor
@RestController
public class LoginController {

    private UserService userService;
    UserServiceImpl userServiceImpl;
    LoginInfoService loginInfoService;
    UserDetailsService userDetailsService;
    ProfileService profileService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User user) {
        Map<String, Object> responseMap = new HashMap<>(4);
        if (userService.login(user.getUsername(), user.getPassword()) == 1) {
            User userInfo = (User) userDetailsService.loadUserByUsername(user.getUsername());
            // 将登录信息记录下来
            loginInfoService.addLoginInfo(userInfo.getId());
            // 获取 profile 信息
            Profile profile = profileService.getProfile(userInfo.getId());
            responseMap.put("profile", profile);
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
        Integer result = userService.register(user);
        if (result == -2) {
            return new ResponseEntity<>(SystemConstantDefine.FAIL, HttpStatus.OK);
        } else if (result == -1) {
            return new ResponseEntity<>(SystemConstantDefine.USERNAME_EXIST_ALREADY, HttpStatus.OK);
        }
        return new ResponseEntity<>(SystemConstantDefine.SUCCESS, HttpStatus.OK);
    }

    @GetMapping("/checkToken")
    public ResponseEntity<Boolean> checkToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        String username = request.getHeader("username");
        return new ResponseEntity<>(JWTUtil.checkToken(token, username), HttpStatus.OK);
    }

}
