package com.cauchynote.friend.controller;

import com.cauchynote.friend.service.FriendService;
import com.cauchynote.system.entity.User;
import com.cauchynote.utils.SystemConstantDefine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Cauchy
 * @ClassName FriendsController.java
 * @createTime 2022年04月10日 10:49:00
 */

@CrossOrigin
@RestController
@RequestMapping("/friend")
public class FriendController {
    @Autowired
    FriendService friendService;

    /**
     * 获取好友列表
     *
     * @param userId 用户ID
     * @return 好友列表
     */
    @GetMapping("/getFriendList")
    public ResponseEntity<List<User>> getFriendList(@RequestParam Long userId,
                                                    @RequestParam Integer pageSize,
                                                    @RequestParam Integer pageNum) {
        List<User> friendList = friendService.getFriendsList(userId, pageSize, pageNum);
        return new ResponseEntity<>(friendList, HttpStatus.OK);
    }

    /**
     * 添加好友
     *
     * @param userId   用户 ID
     * @param friendId 好友 ID
     * @return 1 - 成功 0 - 失败
     */
    @GetMapping("/addFriend")
    public ResponseEntity<Integer> addFriend(@RequestParam Long userId, @RequestParam Long friendId) {
        Integer status = friendService.addFriend(userId, friendId);
        if (status == 1) {
            return new ResponseEntity<>(SystemConstantDefine.SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(SystemConstantDefine.FAIL, HttpStatus.OK);
    }

    /**
     * 删除好友
     *
     * @param userId   用户 ID
     * @param friendId 好友 ID
     * @return 1 - 成功 0 - 失败
     */
    @GetMapping("/deleteFriend")
    public ResponseEntity<Integer> deleteFriend(@RequestParam Long userId, @RequestParam Long friendId) {
        Integer status = friendService.deleteFriend(userId, friendId);
        if (status == 1) {
            return new ResponseEntity<>(SystemConstantDefine.SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(SystemConstantDefine.FAIL, HttpStatus.OK);
    }

    /**
     * 查找好友
     *
     * @param friendName 好友用户名
     * @return 用户对象
     */
    @GetMapping("searchFriend")
    public ResponseEntity<Object> searchFriend(@RequestParam String friendName) {
        User user = friendService.searchFriend(friendName);
        if (user == null) {
            return new ResponseEntity<>(SystemConstantDefine.USER_NOT_EXIST, HttpStatus.OK);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
