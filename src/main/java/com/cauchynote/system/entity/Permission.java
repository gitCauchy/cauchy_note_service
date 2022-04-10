package com.cauchynote.system.entity;

import lombok.Data;

/**
 * 权限实体类
 * @Author Cauchy
 * @ClassName Permission
 */
@Data
public class Permission {
    /**
     * 权限ID
     */
    private Long id;
    /**
     * 权限名称
     */
    private String permissionName;
    /**
     * 权限标签
     */
    private String permissionTag;
}
