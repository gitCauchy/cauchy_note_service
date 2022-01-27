package com.cauchynote.system.service.impl;

import com.cauchynote.system.entity.Role;
import com.cauchynote.system.mapper.RoleMapper;
import com.cauchynote.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Cauchy
 * @ClassName RoleServiceImpl
 * @Description 角色管理实现类
 * @Date 21/12/08
 * @Version 0.1
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

    // TODO 这里暂时先这样写 ， 但是有明显的缺陷， 应当采用事务管理处理这里，后续改进
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
