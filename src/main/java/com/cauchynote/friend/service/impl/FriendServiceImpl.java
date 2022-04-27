package com.cauchynote.friend.service.impl;

import com.cauchynote.friend.mapper.FriendMapper;
import com.cauchynote.friend.service.FriendService;
import com.cauchynote.system.entity.User;
import org.apache.ibatis.annotations.Select;
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
    public List<User> getFriendsList(Integer userId, Integer pageSize, Integer pageNum) {
        String friendIdStr = friendMapper.getFriendIds(userId);
        // 11,22,33,44,55
        if (friendIdStr == null || ",".equals(friendIdStr) || "".equals(friendIdStr)) {
            return new ArrayList<>();
        }
        String[] userIdArray = friendIdStr.split(",");
        List<Integer> friendIds = new ArrayList<>();
        for (String arrayItem : userIdArray) {
            if ("".equals(arrayItem)) {
                continue;
            }
            friendIds.add(Integer.valueOf(arrayItem));
        }
        return friendMapper.getFriendList(friendIds, (pageNum - 1) * pageSize, pageSize);
    }

    @Override
    public Integer addFriend(Integer userId, Integer friendId) {
        /*
        先查询好友表内是否有当前用户的记录,这里有两种情况：
        1. 表中有该用户的记录，但是 friend_ids 为空，这种情况出现再该用户主动添加其他用户为好友
        2. 表中没有该用户的记录
         */
        Integer recordCount = friendMapper.getRecordCountOfUser(userId);
        String friendIds = friendMapper.getFriendIds(userId);
        if (recordCount == 0 && friendIds == null) {
            friendIds = friendId.toString();
            return friendMapper.addNewRecord(userId, friendIds);
        } else if (recordCount == 1 && friendIds == null) {
            friendIds = friendId.toString();
            return friendMapper.updateFriend(userId, friendIds);
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
    public Integer deleteFriend(Integer userId, Integer friendId) {
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

    @Override
    public Integer addFriendRequest(Integer userId, Integer friendId) {
        // 1. 先检查该用户是否为好友
        String friendIds = friendMapper.getFriendIds(userId);
        if(friendIds.contains(friendId.toString())){
            return -1;
        }
        String friendRequestIds = friendMapper.getFriendRequestIds(userId);
        if (friendRequestIds == null) {
            friendRequestIds = friendId.toString();
            friendMapper.addNewFriendRequestRecord(userId, friendRequestIds);
        }
        String[] friendRequestIdArray = friendRequestIds.split(",");
        Set<String> requestIdStrSet = new HashSet<>(Arrays.asList(friendRequestIdArray));
        requestIdStrSet.add(friendId.toString());
        StringBuilder sb = new StringBuilder();
        for (String idItem : requestIdStrSet) {
            sb.append(idItem);
            sb.append(",");
        }
        return friendMapper.updateFriendRequest(userId, sb.toString());
    }

    @Override
    public List<User> getFriendRequestList(Integer userId) {
        String friendRequestIdStr = friendMapper.getFriendRequestIds(userId);
        // 11,22,33,44,55
        if (friendRequestIdStr == null || ",".equals(friendRequestIdStr) || "".equals(friendRequestIdStr)) {
            return new ArrayList<>();
        }
        String[] userIdArray = friendRequestIdStr.split(",");
        List<Integer> friendRequestIds = new ArrayList<>();
        for (String arrayItem : userIdArray) {
            if ("".equals(arrayItem)) {
                continue;
            }
            friendRequestIds.add(Integer.valueOf(arrayItem));
        }
        return friendMapper.getFriendRequestList(friendRequestIds);
    }

    @Override
    public Integer deleteFriendRequest(Integer userId, Integer friendId) {
        String friendRequestIds = friendMapper.getFriendRequestIds(userId);
        String[] friendRequestIdArray = friendRequestIds.split(",");
        List<String> friendRequestIdList = new ArrayList<>();
        Collections.addAll(friendRequestIdList, friendRequestIdArray);
        friendRequestIdList.remove(friendId.toString());
        StringBuilder sb = new StringBuilder();
        for (String listItem : friendRequestIdList) {
            sb.append(listItem);
            sb.append(",");
        }
        return friendMapper.updateFriendRequest(userId, sb.toString());
    }
}
