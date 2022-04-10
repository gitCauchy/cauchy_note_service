package com.cauchynote.friend.controller;

import com.cauchynote.friend.service.FriendService;
import com.cauchynote.system.entity.User;
import com.cauchynote.utils.SystemConstantDefine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Cauchy
 * @ClassName FriendsController.java
 * @createTime 2022年04月10日 10:49:00
 */

@RestController
public class FriendController {
    @Autowired
    FriendService friendService;

    public ResponseEntity<List<User>> getFriendList(@RequestParam Long userId) {
        List<User> friendList = friendService.getFriendsList(userId);
        return new ResponseEntity<>(friendList, HttpStatus.OK);
    }

    public ResponseEntity<Integer> addFriend(@RequestParam Long userId, @RequestParam Long friendId) {
        Integer status = friendService.addFriend(userId, friendId);
        if (status == 1) {
            return new ResponseEntity<>(SystemConstantDefine.SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(SystemConstantDefine.FAIL, HttpStatus.OK);
    }

    public ResponseEntity<Integer> deleteFriend(@RequestParam Long userId, @RequestParam Long friendId) {
        Integer status = friendService.deleteFriend(userId, friendId);
        if (status == 1) {
            return new ResponseEntity<>(SystemConstantDefine.SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(SystemConstantDefine.FAIL, HttpStatus.OK);
    }

    public ResponseEntity<Object> searchFriend(@RequestParam String friendName) {
        User user = friendService.searchFriend(friendName);
        if (user == null) {
            return new ResponseEntity<>(SystemConstantDefine.USER_NOT_EXIST, HttpStatus.OK);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
