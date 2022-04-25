package com.cauchynote.message.mapper;

import com.cauchynote.message.entity.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Cauchy
 * @ClassName MessageMapper.java
 * @createTime 2022年04月25日 10:40:00
 */
@Repository
public interface MessageMapper {
    /**
     * 添加新消息
     *
     * @param message 消息对象
     * @return 插入数据条目
     */
    @Insert("insert into note_message(sender_id, receiver_id, message_info, message_type, send_date, status) value(" +
        "sendId, receiverId, messageInfo, messageType, sendDate, status)")
    Integer addNewMessage(Message message);

    void deleteMessage();

    void changeStatus();

    /**
     * 获取消息列表
     *
     * @param receiverId 接收者 ID
     * @return List<Message>
     */
    @Select("select id, sender_id, receiver_id, message_info, message_type, send_date, status from note_message" +
        "where receiver_id = #{receiverId}")
    List<Message> getMessageList(Long receiverId);
}
