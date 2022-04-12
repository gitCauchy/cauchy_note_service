package com.cauchynote.friend.service.impl;

import com.cauchynote.friend.mapper.FriendMapper;
import com.cauchynote.friend.service.FriendService;
import com.cauchynote.system.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 好友服务层实现类
 *
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
        if (friendIdStr == null || ",".equals(friendIdStr) || "".equals(friendIdStr)) {
            return new ArrayList<>();
        }
        String[] userIdArray = friendIdStr.split(",");
        List<Long> friendIds = new ArrayList<>();
        for (String arrayItem : userIdArray) {
            if("".equals(arrayItem)){
                continue;
            }
            friendIds.add(Long.parseLong(arrayItem));
        }
        return friendMapper.getFriendList(friendIds);
    }

    @Override
    public Integer addFriend(Long userId, Long friendId) {
        // 先查询好友表内是否有当前用户的记录
        String friendIds = friendMapper.getFriendIds(userId);
        if (friendIds == null) {
            friendIds = friendId.toString();
            friendMapper.addNewRecord(userId, friendIds);
        }
        String[] friendIdArray = friendIds.split(",");
        Set<String> idStrSet = new HashSet<>(Arrays.asList(friendIdArray));
        idStrSet.add(friendId.toString());
        StringBuilder sb = new StringBuilder();
        for (String idItem : idStrSet) {
            sb.append(idItem);
            sb.append(",");
        }
        return friendMapper.updateFriend(userId, sb.toString());
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
