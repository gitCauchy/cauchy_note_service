package com.cauchynote.friend.controller;

import com.cauchynote.friend.service.FriendService;
import com.cauchynote.system.entity.User;
import com.cauchynote.utils.SystemConstantDefine;
import lombok.AllArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Cauchy
 * @ClassName FriendsController.java
 * @createTime 2022年04月10日 10:49:00
 */

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/friend")
public class FriendController {
    FriendService friendService;

    /**
     * 获取好友列表
     *
     * @param userId 用户ID
     * @return 好友列表
     */
    @GetMapping("/getFriendList")
    public ResponseEntity<List<Map<String, Object>>> getFriendList(@RequestParam Integer userId,
                                                                   @RequestParam Integer pageSize,
                                                                   @RequestParam Integer pageNum) {
        List<Map<String, Object>> friendList = friendService.getFriendsList(userId, pageSize, pageNum);
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
    public ResponseEntity<Integer> addFriend(@RequestParam Integer userId, @RequestParam Integer friendId) {
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
    public ResponseEntity<Integer> deleteFriend(@RequestParam Integer userId, @RequestParam Integer friendId) {
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
    @GetMapping("/searchFriend")
    public ResponseEntity<Object> searchFriend(@RequestParam String friendName) {
        User user = friendService.searchFriend(friendName);
        if (user == null) {
            return new ResponseEntity<>(SystemConstantDefine.USER_NOT_EXIST, HttpStatus.OK);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * 添加好友请求
     *
     * @param userId   用户ID
     * @param friendId 好友ID
     * @return 状态码
     */
    @GetMapping("/addFriendRequest")
    public ResponseEntity<Integer> addFriendRequest(@RequestParam Integer userId, @RequestParam Integer friendId) {
        Integer status = friendService.addFriendRequest(userId, friendId);
        if (status == 1) {
            return new ResponseEntity<>(SystemConstantDefine.SUCCESS, HttpStatus.OK);
        } else if (status == -1) {
            return new ResponseEntity<>(SystemConstantDefine.USER_IS_FRIEND_ALREADY, HttpStatus.OK);
        }
        return new ResponseEntity<>(SystemConstantDefine.FAIL, HttpStatus.OK);
    }

    @GetMapping("/getFriendRequestList")
    public ResponseEntity<List<User>> getFriendRequestList(@RequestParam Integer userId) {
        List<User> friendRequestList = friendService.getFriendRequestList(userId);
        return new ResponseEntity<>(friendRequestList, HttpStatus.OK);
    }

    @GetMapping("/deleteFriendRequest")
    public ResponseEntity<Integer> deleteFriendRequest(@RequestParam Integer userId, @RequestParam Integer friendId) {
        Integer status = friendService.deleteFriendRequest(userId, friendId);
        if (status == 1) {
            return new ResponseEntity<>(SystemConstantDefine.SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(SystemConstantDefine.FAIL, HttpStatus.OK);
    }

    @GetMapping("/setRemarkName")
    public ResponseEntity<Integer> setRemarkName(@RequestParam Integer userId, @RequestParam Integer friendId,
                                                 @RequestParam String remarkName) {
        Integer status = friendService.setFriendRemarkName(userId, friendId, remarkName);
        if (status == 1) {
            return new ResponseEntity<>(SystemConstantDefine.SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(SystemConstantDefine.FAIL, HttpStatus.OK);
    }

    @GetMapping("/agreeFriendRequest")
    public ResponseEntity<Integer> agreeFriendRequest(@RequestParam Integer messageId, @RequestParam Integer senderId,
                                                      @RequestParam Integer receiverId) {
        Integer status = friendService.agreeFriendRequest(messageId, senderId, receiverId);
        if (status == 1) {
            return new ResponseEntity<>(SystemConstantDefine.SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(SystemConstantDefine.FAIL, HttpStatus.OK);
    }

    @GetMapping("/rejectFriendRequest")
    public ResponseEntity<Integer> rejectFriendRequest(@RequestParam Integer messageId, @RequestParam Integer senderId,
                                                      @RequestParam Integer receiverId) {
        Integer status = friendService.rejectFriendRequest(messageId, senderId, receiverId);
        if (status == 1) {
            return new ResponseEntity<>(SystemConstantDefine.SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(SystemConstantDefine.FAIL, HttpStatus.OK);
    }
}
