package com.cauchynote.friend.service.impl;

import com.cauchynote.friend.mapper.FriendMapper;
import com.cauchynote.friend.service.FriendService;
import com.cauchynote.message.entity.Message;
import com.cauchynote.system.entity.User;
import com.cauchynote.system.service.UserService;
import com.cauchynote.utils.SystemConstantDefine;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.cauchynote.message.service.MessageService;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 好友服务层实现类
 *
 * @author Cauchy
 * @ClassName FriendServiceImpl.java
 * @createTime 2022年04月10日 10:50:00
 */

@AllArgsConstructor
@Service
public class FriendServiceImpl implements FriendService {

    public static final String ID_SEPARATOR = ",";

    FriendMapper friendMapper;
    MessageService messageService;
    UserService userService;

    @Override
    public List<Map<String, Object>> getFriendsList(Integer userId, Integer pageSize, Integer pageNum) {
        String friendIdStr = friendMapper.getFriendIds(userId);
        // 11,22,33,44,55
        if (isNullRecord(friendIdStr)) {
            return new ArrayList<>();
        }
        String[] userIdArray = friendIdStr.split(ID_SEPARATOR);
        List<Integer> friendIds = new ArrayList<>();
        for (String arrayItem : userIdArray) {
            if ("".equals(arrayItem)) {
                continue;
            }
            friendIds.add(Integer.valueOf(arrayItem));
        }
        List<Map<String, Object>> rawFriendList = friendMapper.getFriendList(userId, friendIds, (pageNum - 1) * pageSize,
            pageSize);
        // 遍历，修改 key
        List<Map<String, Object>> retFriendList = new ArrayList<>();
        for (Map<String, Object> rawMap : rawFriendList) {
            Map<String, Object> tmpMap = new HashMap<>();
            tmpMap.put("friendId", rawMap.get("id"));
            tmpMap.put("username", rawMap.get("user_name"));
            tmpMap.put("email", rawMap.get("email"));
            tmpMap.put("remarkName", rawMap.get("remark_name"));
            retFriendList.add(tmpMap);
        }
        return retFriendList;
    }

    @Override
    public Integer addFriend(Integer userId, Integer friendId) {
        /*
        先查询好友表内是否有当前用户的记录,这里有两种情况：
        1. 表中有该用户的记录，但是 friend_ids 为空，这种情况出现在该用户主动添加其他用户为好友
        2. 表中没有该用户的记录
         */
        Integer recordCount = friendMapper.getRecordCountOfUser(userId);
        String friendIds = friendMapper.getFriendIds(userId);
        if (friendIds == null || "".equals(friendIds)) {
            friendIds = friendId.toString();
            if (recordCount == 0) {
                return friendMapper.addNewRecord(userId, friendIds);
            }
            return friendMapper.updateFriend(userId, friendIds);
        }
        String[] friendIdArray = friendIds.split(ID_SEPARATOR);
        Set<String> idStrSet = new HashSet<>(Arrays.asList(friendIdArray));
        idStrSet.add(friendId.toString());
        StringBuilder sb = new StringBuilder();
        for (String idItem : idStrSet) {
            sb.append(idItem);
            sb.append(ID_SEPARATOR);
        }
        return friendMapper.updateFriend(userId, sb.toString());
    }

    @Override
    public Integer deleteFriend(Integer userId, Integer friendId) {
        // 1. 删除好友的备注
        friendMapper.deleteRemarkName(userId, friendId);
        // 2. 删除好友表相关信息
        String friendIds = friendMapper.getFriendIds(userId);
        String[] friendIdArray = friendIds.split(ID_SEPARATOR);
        List<String> friendIdList = new ArrayList<>();
        Collections.addAll(friendIdList, friendIdArray);
        friendIdList.remove(friendId.toString());
        StringBuilder sb = new StringBuilder();
        for (String listItem : friendIdList) {
            sb.append(listItem);
            sb.append(ID_SEPARATOR);
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
        if (friendIds != null && friendIds.contains(friendId.toString())) {
            return -1;
        }
        Integer recordCountOfUser = friendMapper.getRecordCountOfUser(userId);
        String friendRequestIds = friendMapper.getFriendRequestIds(userId);
        if (friendRequestIds == null || "".equals(friendRequestIds)) {
            friendRequestIds = friendId.toString();
            if (recordCountOfUser == 0) {
                return friendMapper.addNewFriendRequestRecord(userId, friendRequestIds);
            }
            return friendMapper.updateFriendRequest(userId, friendRequestIds);
        }
        String[] friendRequestIdArray = friendRequestIds.split(ID_SEPARATOR);
        Set<String> requestIdStrSet = new HashSet<>(Arrays.asList(friendRequestIdArray));
        requestIdStrSet.add(friendId.toString());
        StringBuilder sb = new StringBuilder();
        for (String idItem : requestIdStrSet) {
            sb.append(idItem);
            sb.append(ID_SEPARATOR);
        }
        return friendMapper.updateFriendRequest(userId, sb.toString());
    }

    @Override
    public List<User> getFriendRequestList(Integer userId) {
        String friendRequestIdStr = friendMapper.getFriendRequestIds(userId);
        // 11,22,33,44,55
        if (isNullRecord(friendRequestIdStr)) {
            return new ArrayList<>();
        }
        String[] userIdArray = friendRequestIdStr.split(ID_SEPARATOR);
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
        String[] friendRequestIdArray = friendRequestIds.split(ID_SEPARATOR);
        List<String> friendRequestIdList = new ArrayList<>();
        Collections.addAll(friendRequestIdList, friendRequestIdArray);
        friendRequestIdList.remove(friendId.toString());
        StringBuilder sb = new StringBuilder();
        for (String listItem : friendRequestIdList) {
            sb.append(listItem);
            sb.append(ID_SEPARATOR);
        }
        return friendMapper.updateFriendRequest(userId, sb.toString());
    }

    @Override
    public Integer setFriendRemarkName(Integer userId, Integer friendId, String remarkName) {
        return friendMapper.modifyRemarkName(userId, friendId, remarkName);
    }

    @Override
    @Transactional
    public Integer agreeFriendRequest(Integer messageId, Integer senderId, Integer receiverId) {
        // 1. 相互添加好友
        this.addFriend(senderId, receiverId);
        this.addFriend(receiverId, senderId);
        // 2. 将消息标记为已读状态
        messageService.readMessage(messageId);
        // 3. 删除好友请求信息
        this.deleteFriendRequest(senderId, receiverId);
        // 4. 双方设置默认备注名称
        String receiverUsername = userService.getUserById(receiverId).getUsername();
        String senderUsername = userService.getUserById(senderId).getUsername();
        friendMapper.setRemarkName(senderId, receiverId, receiverUsername);
        return friendMapper.setRemarkName(receiverId, senderId, senderUsername);
    }

    @Override
    @Transactional
    public Integer rejectFriendRequest(Integer messageId, Integer senderId, Integer receiverId) {
        // 1. 清理好友请求信息
        this.deleteFriendRequest(senderId, receiverId);
        // 2. 添加拒绝好友请求的消息
        String senderName = userService.getUserById(senderId).getUsername();
        Message message = new Message();
        message.setSenderId(receiverId);
        message.setReceiverId(senderId);
        message.setMessageType(SystemConstantDefine.FRIEND_REQUEST_CALLBACK);
        message.setMessageInfo("添加" + senderName + "的好友请求被拒绝");
        messageService.addNewMessage(message);
        // 3. 将消息标记为已读
        return messageService.readMessage(messageId);
    }

    private boolean isNullRecord(String idStr) {
        return idStr == null || ID_SEPARATOR.equals(idStr) || "".equals(idStr);
    }
}
