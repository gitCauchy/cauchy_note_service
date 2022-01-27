package com.cauchynote.system.entity;

import lombok.Data;

/**
 * @Author Cauchy
 * @ClassName Permission
 * @Description 权限实体类
 * @Date 21/12/06
 * @Version 0.1
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
