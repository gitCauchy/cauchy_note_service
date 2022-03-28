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
     * 权限标签 - 添加用户
     */
    public static final String PERMISSION_TAG_USER_ADD = "USER_ADD";
    /**
     * 权限标签 - 删除用户
     */
    public static final String PERMISSION_TAG_USER_DEL = "USER_DEL";
    /**
     * 权限标签 - 修改用户信息
     */
    public static final String PERMISSION_TAG_USER_MOD = "USER_MOD";
    /**
     * 权限标签 - 查询用户信息
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
    /**
     * 修改失败，密码错误
     */
    public static final Integer PASSWORD_INVALID = -300003;
    /**
     * 验证码错误
     */
    public static final Integer CHECKCODE_INVALID = -300004;

    /**
     * 角色ID - 超级管理员
     */
    public static final String ROOT_NAME = "ROOT";
    /**
     * 角色ID - 管理员
     */
    public static final String ADMIN_NAME = "ADMIN";
    /**
     * 角色ID - 普通用户
     */
    public static final String USER_NAME = "USER";

    // -------------------- 邮箱信息 ---------------------
    /**
     * Host
     */
    public static final String SMTPHOST = "smtp.163.com";
    /**
     * Adress
     */
    public static final String FROM = "cauchynote@163.com";
    /**
     * Password
     */
    public static final String PASSWORD = "SYFFVCZJDKBQILNQ";

    // -------------------- Redis ------------------------
    /**
     * redis - host
     */
    public static final String REDIS_HOST = "192.18.31.197";
    /**
     * redis - port
     */
    public static final int REDIS_PORT = 6379;
}
