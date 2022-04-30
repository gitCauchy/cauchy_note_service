package com.cauchynote.message.mapper;

import com.cauchynote.message.entity.Message;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.Date;
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
        "#{senderId}, #{receiverId}, #{messageInfo}, #{messageType}, now(), #{status})")
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
        " where receiver_id = #{receiverId} and status = 0")
    @Results(id = "messageResultMap", value = {
        @Result(column = "id", property = "id", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
        @Result(column = "sender_id", property = "senderId", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
        @Result(column = "receiver_id", property = "receiverId", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
        @Result(column = "message_info", property = "messageInfo", javaType = String.class, jdbcType = JdbcType.VARCHAR),
        @Result(column = "message_type", property = "messageType", javaType = Integer.class, jdbcType = JdbcType.TINYINT),
        @Result(column = "send_date", property = "sendDate", javaType = Date.class, jdbcType = JdbcType.TINYINT),
        @Result(column = "status", property = "status", javaType = Integer.class, jdbcType = JdbcType.TINYINT)})
    List<Message> getMessageList(Integer receiverId);

    /**
     * 修改消息状态
     *
     * @param id     消息ID
     * @param status 状态
     * @return 变更结果数量
     */
    @Update("update note_message set status = #{status} where id = #{id}")
    Integer changeStatus(Integer id, Integer status);

    @Select("select count(*) from note_message where receiver_id = #{id} and status = 0")
    Integer getMessageCount(Integer id);
}
