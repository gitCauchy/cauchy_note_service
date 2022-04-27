package com.cauchynote.message.service;

import com.cauchynote.message.entity.Message;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Cauchy
 * @ClassName MessageService.java
 * @createTime 2022年04月25日 10:41:00
 */
@Service
public interface MessageService {
    /**
     * 添加新消息
     *
     * @param message 消息对象
     * @return 状态
     */
    Integer addNewMessage(Message message);

    /**
     * 获取消息列表
     *
     * @param receiverId 消息接收者 ID
     * @return List<Message>
     */
    List<Message> getMessageList(Integer receiverId);

    /**
     * 删除消息
     *
     * @return 状态
     */
    Integer deleteMessage(Integer id);

    /**
     * 修改消息状态
     *
     * @param id 消息 ID
     * @return 修改数据条目
     */
    Integer readMessage(Integer id);

    /**
     * 获取未读信息数量
     *
     * @param userId 用户ID
     * @return 信息数量
     */
    Integer getMessageCount(Integer userId);
}
