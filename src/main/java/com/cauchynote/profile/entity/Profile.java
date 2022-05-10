package com.cauchynote.profile.entity;

import lombok.Data;

import java.util.Date;

/**
 * 个人信息实体
 *
 * @Author lingling
 * @Date 2022/5/4
 */
@Data
public class Profile {

    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 生日
     */
    private Date birthday;
    /**
     * 性别
     */
    private Integer gender;
    /**
     * 电话号码
     */
    private String telephone;
    /**
     * 图片
     */
    private byte[] photo;
    /**
     * 地址
     */
    private String address;
}
