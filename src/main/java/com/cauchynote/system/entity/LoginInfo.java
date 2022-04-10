package com.cauchynote.system.entity;

import lombok.Data;

import java.util.Date;

/**
 * 登录信息实体类
 *
 * @Author Cauchy
 * @ClassName LoginInfo
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
