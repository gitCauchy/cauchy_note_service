package com.cauchynote.system.controller;

import com.cauchynote.system.entity.User;
import com.cauchynote.system.service.UserService;
import com.cauchynote.utils.EmailUtil;
import com.cauchynote.utils.RandomUtil;
import com.cauchynote.utils.RedisUtil;
import com.cauchynote.utils.SystemConstantDefine;
import lombok.AllArgsConstructor;
import org.apache.commons.mail.EmailException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户控制层
 *
 * @Author Cauchy
 * @ClassName UserController
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    private RedisUtil redisUtil;

    @PostMapping("/addUser")
    public ResponseEntity<Integer> addUser(@RequestBody User user) {
        Integer result = userService.addUser(user);
        if (result == 1) {
            return new ResponseEntity<>(SystemConstantDefine.SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(SystemConstantDefine.FAIL, HttpStatus.OK);
    }

    @GetMapping("/deleteUser")
    public ResponseEntity<Integer> deleteUser(@RequestParam Integer id) {
        Integer result = userService.deleteUser(id);
        if (result == 1) {
            return new ResponseEntity<>(SystemConstantDefine.SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(SystemConstantDefine.FAIL, HttpStatus.OK);
    }

    @PostMapping("/updateUser")
    public ResponseEntity<Integer> updateUser(@RequestBody User user) {
        Integer result = userService.updateUser(user);
        if (result == 1) {
            return new ResponseEntity<>(SystemConstantDefine.SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(SystemConstantDefine.FAIL, HttpStatus.OK);
    }

    @GetMapping("/queryUser")
    public ResponseEntity<User> queryUser(@RequestParam(value = "id") Integer id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/queryAllUsers")
    public ResponseEntity<Map<String, Object>> queryAllUsers(@RequestParam(value = "pageSize") Integer pageSize, @RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "keyword") String keyword) {
        Map<String, Object> retMap = new HashMap<>(2);
        List<User> users = userService.getAllUsers(pageSize, pageNum, keyword);
        Integer total = userService.getUserTotal(keyword);
        retMap.put("users", users);
        retMap.put("total", total);
        if (users != null) {
            return new ResponseEntity<>(retMap, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * @param passwordInfo 密码信息，旧密码，新密码
     * @return 是否修改成功
     */
    @PostMapping("/modifyPassword")
    public ResponseEntity<Map<String, Integer>> modifyPassword(@RequestBody Map<String, String> passwordInfo) {
        // 获取新密码
        String newPassword = passwordInfo.get("newPassword");
        // 检查密码是否符合安全性要求

        // 获取用户输入的旧密码
        String oldPassword = passwordInfo.get("oldPassword");
        String username = passwordInfo.get("username");
        // 数据库
        User user = userService.findUserByUsername(username);
        // 校验旧密码是否正确
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Map<String, Integer> responseMap = new HashMap<>(1);
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
    public ResponseEntity<Map<String, Integer>> sendCheckCode(@RequestParam String username, @RequestParam String subject) {
        // 通过用户名查找用户的邮箱
        User user = userService.findUserByUsername(username);
        int checkCode = RandomUtil.getRandomInt();
        redisUtil.set(username, checkCode);
        Map<String, Integer> responseMap = new HashMap<>(1);
        try {
            EmailUtil.sendMsg(user.getEmail(), "Cauchy Note", subject, "您的验证码是:" + checkCode);
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
        String newPassword = (String) requestMap.get("newPassword");
        Map<String, Integer> responseMap = new HashMap<>(1);
        List<String> illegalPassword = Arrays.asList(SystemConstantDefine.ILLEGAL_PASSWORD);
        // 1. 检查密码是否过于简单
        if (illegalPassword.contains(newPassword)) {
            responseMap.put("SystemStatusCode", SystemConstantDefine.PASSWORD_ILLEGAL);
            return new ResponseEntity<>(responseMap, HttpStatus.OK);
        }
        int checkCode = Integer.parseInt((String) requestMap.get("checkCode"));
        int checkCodeFromRedis = (int) redisUtil.get(username);
        // 2. 检查用户传递的验证码是否和从 Redis 中获取的一致
        if (checkCode != checkCodeFromRedis) {
            responseMap.put("SystemStatusCode", SystemConstantDefine.CHECKCODE_INVALID);
            return new ResponseEntity<>(responseMap, HttpStatus.OK);
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePassword = encoder.encode(newPassword);
        if (userService.modifyPassword(encodePassword, username) == 1) {
            responseMap.put("SystemStatusCode", SystemConstantDefine.SUCCESS);
            return new ResponseEntity<>(responseMap, HttpStatus.OK);
        }
        responseMap.put("SystemStatusCode", SystemConstantDefine.FAIL);
        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }

    @PostMapping("/modifyUserEmail")
    public ResponseEntity<Integer> modifyUserEmail(@RequestBody Map<String, Object> requestMap) {
        String username = (String) requestMap.get("username");
        String newEmail = (String) requestMap.get("newEmail");
        int checkCode = Integer.parseInt((String) requestMap.get("checkCode"));
        if(redisUtil.get(username) == null){
            return new ResponseEntity<>(SystemConstantDefine.CHECKCODE_INVALID, HttpStatus.OK);
        }
        int checkCodeFromRedis = (int) redisUtil.get(username);
        if (checkCode != checkCodeFromRedis) {
            return new ResponseEntity<>(SystemConstantDefine.CHECKCODE_INVALID, HttpStatus.OK);
        }
        User user = userService.findUserByUsername(username);
        user.setEmail(newEmail);
        Integer result = userService.updateUser(user);
        boolean deleteResult = redisUtil.deleteKey(username);
        if (result == 1 && deleteResult) {
            return new ResponseEntity<>(SystemConstantDefine.SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(SystemConstantDefine.FAIL, HttpStatus.OK);
    }
}
