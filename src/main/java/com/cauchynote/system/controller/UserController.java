package com.cauchynote.system.controller;

import com.cauchynote.system.entity.User;
import com.cauchynote.system.service.UserService;
import com.cauchynote.utils.EmailUtil;
import com.cauchynote.utils.RandomUtil;
import com.cauchynote.utils.RedisUtil;
import com.cauchynote.utils.SystemConstantDefine;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Cauchy
 * @ClassName UserController
 * @Description 用户控制层
 * @Date 21/12/07
 * @Version 0.2
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RedisUtil redisUtil;

    @PostMapping("/addUser")
    public ResponseEntity<Integer> addUser(@RequestBody User user) {
        boolean result = userService.addUser(user);
        if (result) {
            return new ResponseEntity<>(SystemConstantDefine.SUCCESS, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(SystemConstantDefine.FAIL, HttpStatus.OK);
        }
    }

    @GetMapping("/deleteUser")
    public ResponseEntity<Integer> deleteUser(@RequestParam(value = "id") Long id) {
        boolean result = userService.deleteUser(id);
        if (result) {
            return new ResponseEntity<>(SystemConstantDefine.SUCCESS, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(SystemConstantDefine.FAIL, HttpStatus.OK);
        }
    }

    @PostMapping("/updateUser")
    public ResponseEntity<Integer> updateUser(@RequestBody User user) {
        boolean result = userService.updateUser(user);
        if (result) {
            return new ResponseEntity<>(SystemConstantDefine.SUCCESS, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(SystemConstantDefine.FAIL, HttpStatus.OK);
        }
    }

    @GetMapping("/queryUser")
    public ResponseEntity<User> queryUser(@RequestParam(value = "id") Long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping("/queryAllUsers")
    public ResponseEntity<Map<String, Object>> queryAllUsers(@RequestParam(value = "pageSize") Integer pageSize, @RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "keyword") String keyword) {
        Map<String, Object> retMap = new HashMap<>();
        List<User> users = userService.getAllUsers(pageSize, pageNum, keyword);
        Integer total = userService.getUserTotal(keyword);
        retMap.put("users", users);
        retMap.put("total", total);
        if (users != null) {
            return new ResponseEntity<>(retMap, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    /**
     * @param passwordInfo 密码信息，旧密码，新密码
     * @return 是否修改成功
     */
    @PostMapping("/modifyPassword")
    public ResponseEntity<Map<String, Integer>> modifyPassword(@RequestBody Map<String, String> passwordInfo) {
        // 先获取用户输入的旧密码
        String oldPassword = passwordInfo.get("oldPassword");
        String newPassword = passwordInfo.get("newPassword");
        String username = passwordInfo.get("username");
        // 数据库
        User user = userService.findUserByUsername(username);
        // 校验旧密码是否正确
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Map<String, Integer> responseMap = new HashMap<>();
        if (!encoder.matches(oldPassword, user.getPassword())) {
            // 密码不正确，修改失败
            responseMap.put("SystemStatusCode", SystemConstantDefine.PASSWORD_INVALID);
            return new ResponseEntity<>(responseMap, HttpStatus.OK);
        }
        String encodePassword = encoder.encode(newPassword);
        userService.modifyPassword(encodePassword, user.getUsername());
        responseMap.put("SystemStatusCode", SystemConstantDefine.SUCCESS);
        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }

    @GetMapping("/sendCheckCode")
    public ResponseEntity<Map<String, Integer>> sendCheckCode(@RequestParam String username) {
        // 通过用户名查找用户的邮箱
        User user = userService.findUserByUsername(username);
        int checkCode = RandomUtil.getRandomInt();
        redisUtil.set(username, checkCode);
        Map<String, Integer> responseMap = new HashMap<>();
        try {
            EmailUtil.sendMsg(user.getEmail(), "RESET PASSWORD SERVICE", "密码重置", "您的验证码是:" + checkCode);
        } catch (EmailException e) {
            e.printStackTrace();
            responseMap.put("SystemStatusCode", SystemConstantDefine.FAIL);
        }
        responseMap.put("SystemStatusCode", SystemConstantDefine.SUCCESS);
        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<Map<String, Integer>> resetPassword(@RequestBody Map<String, Object> requestMap) {
        String username = (String) requestMap.get("username");
        String newpassword = (String) requestMap.get("newPassword");
        int checkCode = Integer.parseInt((String) requestMap.get("checkCode"));
        int checkCodeFromRedis = (int) redisUtil.get(username);
        Map<String, Integer> responseMap = new HashMap<>();
        if (checkCode != checkCodeFromRedis) {
            responseMap.put("SystemStatusCode", SystemConstantDefine.CHECKCODE_INVALID);
            return new ResponseEntity<>(responseMap, HttpStatus.OK);
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePassword = encoder.encode(newpassword);
        userService.modifyPassword(encodePassword, username);
        responseMap.put("SystemStatusCode", SystemConstantDefine.SUCCESS);
        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }
}
