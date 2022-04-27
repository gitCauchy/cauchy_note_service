package com.cauchynote.friend.service;

import com.cauchynote.system.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Cauchy
 * @ClassName FriendsService.java
 * @createTime 2022年04月10日 10:50:00
 */

@Service
public interface FriendService {
    /**
     * 获取好友列表
     *
     * @param userId   用户ID
     * @param pageSize 页大小
     * @param pageNum  页号
     * @return 好友列表
     */
    List<User> getFriendsList(Integer userId, Integer pageSize, Integer pageNum);

    /**
     * 添加好友
     *
     * @param userId   用户ID
     * @param friendId 好友ID
     * @return 添加状态
     */
    Integer addFriend(Integer userId, Integer friendId);

    /**
     * 删除好友
     *
     * @param userId   用户ID
     * @param friendId 好友ID
     * @return 删除状态
     */
    Integer deleteFriend(Integer userId, Integer friendId);

    /**
     * 搜索好友
     *
     * @param friendName 好友用户名
     * @return 用户对象
     */
    User searchFriend(String friendName);

    /**
     * 添加好友请求
     *
     * @param userId   用户ID
     * @param friendId 好友ID
     * @return 添加状态
     */
    Integer addFriendRequest(Integer userId, Integer friendId);

    /**
     * 获取好友请求列表
     *
     * @param userId 用户ID
     * @return List<User>
     */
    List<User> getFriendRequestList(Integer userId);

    /**
     * 获取好友请求状态
     * @param userId 用户 ID
     * @param friendId 好友 ID
     * @return 变更数量
     */
    Integer deleteFriendRequest(Integer userId, Integer friendId);
}
