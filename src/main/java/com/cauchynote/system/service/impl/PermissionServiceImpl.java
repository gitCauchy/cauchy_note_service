package com.cauchynote.system.service.impl;

import com.cauchynote.system.entity.Permission;
import com.cauchynote.system.mapper.PermissionMapper;
import com.cauchynote.system.service.PermissionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 权限管理服务层
 *
 * @Author Cauchy
 * @ClassName PermissionService
 */

@Service
@AllArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    PermissionMapper permissionMapper;

    @Override
    public List<Permission> getPermissionByRoleId(Integer roleId) {
        return permissionMapper.getPermissionByRoleId(roleId);
    }

    @Override
    public List<Permission> getAllPermissions() {
        return permissionMapper.getAllPermissions();
    }

    @Override
    public boolean addPermissionOfRole(Integer roleId, List<Integer> permissionIds) {
        for (Integer permissionId : permissionIds) {
            permissionMapper.addPermissionOfRole(roleId, permissionId);
        }
        return true;
    }
}
