package com.cauchynote.friend.service.impl;

import com.cauchynote.friend.mapper.FriendMapper;
import com.cauchynote.friend.service.FriendService;
import com.cauchynote.system.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Cauchy
 * @ClassName FriendServiceImpl.java
 * @createTime 2022年04月10日 10:50:00
 */

@Service
public class FriendServiceImpl implements FriendService {
    @Autowired
    FriendMapper friendMapper;

    @Override
    public List<User> getFriendsList(Long userId) {
        String friendIdStr = friendMapper.getFriendIds(userId);
        // 11,22,33,44,55
        String[] userIdArray = friendIdStr.split(",");
        List<Long> friendIds = new ArrayList<>();
        for (String arrayItem : userIdArray) {
            friendIds.add(Long.parseLong(arrayItem));
        }
        return friendMapper.getFriendList(friendIds);
    }

    @Override
    public Integer addFriend(Long userId, Long friendId) {
        String friendIds = friendMapper.getFriendIds(userId);
        friendIds += "," + friendId;
        return friendMapper.updateFriend(userId, friendIds);
    }

    @Override
    public Integer deleteFriend(Long userId, Long friendId) {
        String friendIds = friendMapper.getFriendIds(userId);
        String[] friendIdArray = friendIds.split(",");
        List<String> friendIdList = new ArrayList<>();
        Collections.addAll(friendIdList, friendIdArray);
        friendIdList.remove(friendId.toString());
        StringBuilder sb = new StringBuilder();
        for (String listItem : friendIdList) {
            sb.append(listItem);
            sb.append(",");
        }
        return friendMapper.updateFriend(userId, sb.toString());
    }

    @Override
    public User searchFriend(String friendName) {
        return friendMapper.searchFriend(friendName);
    }
}
