package com.cauchynote.profile.entity;

import lombok.Data;

/**
 * 个人设置实体
 *
 * @Author lingling
 * @Date 2022/5/4
 */
@Data
public class Setting {
    /**
     * 用户 ID
     */
    private Integer userId;
    /**
     * 主题
     */
    private Integer theme;
    /**
     * 皮肤
     */
    private Integer skin;
}
