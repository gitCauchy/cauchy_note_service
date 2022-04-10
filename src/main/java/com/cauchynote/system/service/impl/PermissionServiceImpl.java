package com.cauchynote.system.service.impl;

import com.cauchynote.system.entity.Permission;
import com.cauchynote.system.mapper.PermissionMapper;
import com.cauchynote.system.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 权限管理服务层
 *
 * @Author Cauchy
 * @ClassName PermissionService
 */

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public List<Permission> getPermissionByRoleId(Long roleId) {
        return permissionMapper.getPermissionByRoleId(roleId);
    }

    @Override
    public List<Permission> getAllPermissions() {
        return permissionMapper.getAllPermissions();
    }

    @Override
    public boolean addPermissionOfRole(Long roleId, List<Long> permissionIds) {
        for (Long permissionId : permissionIds) {
            permissionMapper.addPermissionOfRole(roleId, permissionId);
        }
        return true;
    }
}
