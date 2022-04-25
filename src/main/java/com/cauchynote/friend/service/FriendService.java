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
    List<User> getFriendsList(Long userId, Integer pageSize, Integer pageNum);

    /**
     * 添加好友
     *
     * @param userId   用户ID
     * @param friendId 好友ID
     * @return 添加状态
     */
    Integer addFriend(Long userId, Long friendId);

    /**
     * 删除好友
     *
     * @param userId   用户ID
     * @param friendId 好友ID
     * @return 删除状态
     */
    Integer deleteFriend(Long userId, Long friendId);

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
    Integer addFriendRequest(Long userId, Long friendId);
}
