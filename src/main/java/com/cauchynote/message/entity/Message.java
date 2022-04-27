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
    private Integer id;
    /**
     * 发送者 ID
     */
    private Integer senderId;
    /**
     * 接收者 ID
     */
    private Integer receiverId;
    /**
     * 消息类型 1- 好友请求，2 - 分享笔记
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
     * 消息状态 0 - 新消息 1 - 已读
     */
    private Integer status;
}
