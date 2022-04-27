package com.cauchynote.system.service;

import com.cauchynote.system.entity.Permission;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 权限管理实现类
 *
 * @Author Cauchy
 * @ClassName PermissionServiceImpl
 */

@Service
public interface PermissionService {

    /**
     * 根据角色获取权限列表
     *
     * @param roleId 角色ID
     * @return 权限列表
     */
    List<Permission> getPermissionByRoleId(Integer roleId);

    /**
     * 获取所有权限信息
     *
     * @return 权限信息
     */
    List<Permission> getAllPermissions();

    /**
     * 为角色添加权限
     *
     * @param roleId        角色ID
     * @param permissionIds 权限 ID 列表
     * @return 是否成功
     */
    boolean addPermissionOfRole(Integer roleId, List<Integer> permissionIds);
}
