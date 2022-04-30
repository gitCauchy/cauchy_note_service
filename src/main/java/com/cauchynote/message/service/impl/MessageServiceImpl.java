package com.cauchynote.message.service.impl;

import com.cauchynote.message.entity.Message;
import com.cauchynote.message.mapper.MessageMapper;
import com.cauchynote.message.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Cauchy
 * @ClassName MessageServiceImpl.java
 * @createTime 2022年04月25日 10:41:00
 */
@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {
    private MessageMapper messageMapper;

    @Override
    public Integer addNewMessage(Message message) {
        return messageMapper.addNewMessage(message);
    }

    @Override
    public List<Message> getMessageList(Integer receiverId) {
        return messageMapper.getMessageList(receiverId);
    }

    @Override
    public Integer readMessage(Integer id) {
        return messageMapper.changeStatus(id, 1);
    }

    @Override
    public Integer getMessageCount(Integer userId) {
        return messageMapper.getMessageCount(userId);
    }
}
