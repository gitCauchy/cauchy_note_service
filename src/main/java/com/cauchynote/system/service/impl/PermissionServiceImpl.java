package com.cauchynote.system.service.impl;

import com.cauchynote.system.entity.Permission;
import com.cauchynote.system.mapper.PermissionMapper;
import com.cauchynote.system.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Cauchy
 * @ClassName PermissionService
 * @Description 权限管理服务层
 * @Date 21/12/08
 * @Version 0.1
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
    public List<Permission> getAllPermisions() {
        return permissionMapper.getAllPermisions();
    }

    @Override
    public boolean addPermissionOfRole(Long roleId, List<Long> permissionIds) {
        for (Long permissionId : permissionIds) {
            permissionMapper.addPermissionOfRole(roleId, permissionId);
        }
        return true;
    }
}
