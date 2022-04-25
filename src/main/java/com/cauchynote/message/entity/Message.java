package com.cauchynote.message.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author Cauchy
 * @ClassName Message.java
 * @createTime 2022年04月25日 10:39:00
 */
@Data
public class Message {
    /**
     * ID
     */
    private Long id;
    /**
     * 发送者 ID
     */
    private Long senderId;
    /**
     * 接收者 ID
     */
    private Long receiverId;
    /**
     * 消息类型
     */
    private Integer messageType;
    /**
     * 消息内容
     */
    private String messageInfo;
    /**
     * 发送日期
     */
    private Date sendDate;
    /**
     * 消息状态
     */
    private Integer status;
}
