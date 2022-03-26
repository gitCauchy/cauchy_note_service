package com.cauchynote.system.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Author Cauchy
 * @ClassName LoginInfo
 * @Description 登录信息实体类
 * @Date 22/02/04
 * @Version 0.1
 */
@Data
public class LoginInfo {
    /**
     * 主键 ID
     */
    Long id;
    /**
     * 用户 ID
     */
    String username;
    /**
     * 登录时间
     */
    Date loginTime;
    /**
     * 登录 IP
     */
    String ip;
}
