package com.cauchynote.message.service.impl;

import com.cauchynote.message.entity.Message;
import com.cauchynote.message.mapper.MessageMapper;
import com.cauchynote.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Cauchy
 * @ClassName MessageServiceImpl.java
 * @createTime 2022年04月25日 10:41:00
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper messageMapper;

    @Override
    public Integer addNewMessage(Message message) {
        return messageMapper.addNewMessage(message);
    }

    @Override
    public List<Message> getMessageList(Long receiverId) {
        return messageMapper.getMessageList(receiverId);
    }

    @Override
    public Integer deleteMessage(Long id) {
        return null;
    }

    @Override
    public Integer readMessage(Long id) {
        return messageMapper.changeStatus(id, 1);
    }

    @Override
    public Integer getMessageCount(Long userId) {
        return messageMapper.getMessageCount(userId);
    }
}
