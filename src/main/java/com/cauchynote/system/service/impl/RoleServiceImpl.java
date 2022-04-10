package com.cauchynote.system.service.impl;

import com.cauchynote.system.entity.Role;
import com.cauchynote.system.mapper.RoleMapper;
import com.cauchynote.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色管理实现类
 *
 * @Author Cauchy
 * @ClassName RoleServiceImpl
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper roleMapper;

    @Override
    public List<Role> findRolesByUserId(Long userId) {
        return roleMapper.findRolesByUserId(userId);
    }

    @Override
    public boolean addNewRole(Role role) {
        return roleMapper.addNewRole(role) == 1;
    }

    @Override
    public boolean updateRole(Role role) {
        return roleMapper.updateRole(role) == 1;
    }

    @Override
    public boolean deleteRole(Long id) {
        return roleMapper.deleteRole(id) == 1;
    }

    @Override
    public List<Role> getAllRoles(Integer pageSize, Integer pageNum, String keyword) {
        return roleMapper.getAllRoles(pageSize, (pageNum - 1) * pageSize, keyword);
    }

    @Override
    public boolean addRoleOfUser(Long userId, List<Long> roleIds) {
        for (Long roleId : roleIds) {
            roleMapper.addRoleOfUser(userId, roleId);
        }
        return true;
    }

    @Override
    public Integer getRoleTotal(String keyword) {
        return roleMapper.getRoleTotal(keyword);
    }
}
