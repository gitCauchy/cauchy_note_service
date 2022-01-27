package com.cauchynote.system.service;

import com.cauchynote.system.entity.Permission;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Cauchy
 * @ClassName PermissionServiceImpl
 * @Description 权限管理实现类
 * @Date 21/12/08
 * @Version 0.1
 */

@Service
public interface PermissionService {

    /**
     * @param roleId
     * @return
     * @description 根据角色获取权限列表
     */
    List<Permission> getPermissionByRoleId(Long roleId);

    /**
     * @return
     * @description 获取所有权限信息
     */
    List<Permission> getAllPermisions();

    /**
     * @param roleId
     * @param permissionIds
     * @return
     * @description 为角色添加权限
     */
    boolean addPermissionOfRole(Long roleId, List<Long> permissionIds);
}
