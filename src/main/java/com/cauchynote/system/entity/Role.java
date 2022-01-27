package com.cauchynote.system.entity;

import lombok.Data;

/**
 * @Author Cauchy
 * @ClassName Role
 * @Description 角色实体类
 * @Date 21/12/06
 * @Version 0.1
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
