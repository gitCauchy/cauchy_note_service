package com.cauchynote.utils;

/**
 * @Author Cauchy
 * @ClassName SystemConstantDefine
 * @Description 常量定义类
 * @Date 21/12/07
 * @Version 0.1
 */
public class SystemConstantDefine {
    /**
     权限标签 - 添加用户
     */
    public static final String PERMISSION_TAG_USER_ADD = "USER_ADD";
    /**
     * 权限标签 - 删除用户
      */
    public static final String PERMISSION_TAG_USER_DEL = "USER_DEL";
    /**
    权限标签 - 修改用户信息
     */
    public static final String PERMISSION_TAG_USER_MOD = "USER_MOD";
    /**
     权限标签 - 查询用户信息
     */
    public static final String PERMISSION_TAG_USER_QRY = "USER_QRY";

    //----------------- 定义返回状态 ------------------

    /**
     * 成功
     */
    public static final Integer SUCCESS = 100000;
    /**
     * 失败
     */
    public static final Integer FAIL = -200000;

    //----------------- 注册失败原因 ------------------

    /**
     * 用户名已存在
     */
    public static final Integer USERNAME_EXIST_ALREADY = -300000;
    /**
     * 邮箱已被注册
     */
    public static final Integer EMAIL_REGISTED_ALREADY = -300001;
    /**
     * 密码不符合安全需求
     */
    public static final Integer PASSWORD_IS_TOO_SIMPLE = -300002;

}
