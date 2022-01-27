package com.cauchynote.system.controller;

import com.cauchynote.system.entity.User;
import com.cauchynote.system.service.UserService;
import com.cauchynote.utils.SystemConstantDefine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Cauchy
 * @ClassName UserController
 * @Description 用户控制层
 * @Date 21/12/07
 * @Version 0.1
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<Integer> addUser(@RequestBody User user) {
        boolean result = userService.addUser(user);
        if (result) {
            return new ResponseEntity<Integer>(SystemConstantDefine.SUCCESS, HttpStatus.OK);
        } else {
            return new ResponseEntity<Integer>(SystemConstantDefine.FAIL, HttpStatus.OK);
        }
    }

    @GetMapping("deleteUser")
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

    @GetMapping("queryUser")
    public ResponseEntity<User> queryUser(@RequestParam(value = "id") Long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping("queryAllUsers")
    public ResponseEntity<Map> queryAllUsers
            (@RequestParam(value = "pageSize") Integer pageSize,
             @RequestParam(value = "pageNum") Integer pageNum,
             @RequestParam(value = "keyword") String keyword
            ) {
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
}
