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
     *
     * @param userId
     * @return
     */
    List<User> getFriendsList(Long userId);

    /**
     *
     * @param userId
     * @param friendId
     * @return
     */
    Integer addFriend(Long userId, Long friendId);

    /**
     *
     * @param userId
     * @param friendId
     * @return
     */
    Integer deleteFriend(Long userId, Long friendId);

    User searchFriend(String friendName);
}
