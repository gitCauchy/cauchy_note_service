package com.cauchynote.system.entity;

import lombok.Data;

/**
 * 角色实体类
 *
 * @Author Cauchy
 * @ClassName Role
 */
@Data
public class Role {
    /**
     * 角色 ID
     */
    private Long id;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色描述
     */
    private String roleDescription;
}
