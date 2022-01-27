package com.cauchynote.system.service;

import com.cauchynote.system.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Cauchy
 * @ClassName RoleServie
 * @Description 角色管理服务层
 * @Date 21/12/08
 * @Version 0.1
 */

@Service
public interface RoleService {
    /**
     * @param userId
     * @return
     * @description 根据用户 ID 获取角色列表
     */
    List<Role> findRolesByUserId(Long userId);

    /**
     * @param role
     * @return
     * @description 添加新角色
     */
    boolean addNewRole(Role role);

    /**
     * @param role
     * @return
     * @description 更新角色信息
     */
    boolean updateRole(Role role);

    /**
     * @param id
     * @return
     * @description 删除角色
     */
    boolean deleteRole(Long id);

    /**
     * @description 获取所有角色
     * @param pageSize 页容量
     * @param pageNum 页号
     * @param keyword 关键词
     * @return
     */
    List<Role> getAllRoles(Integer pageSize, Integer pageNum, String keyword);

    /**
     * @param userId
     * @param roleIds
     * @return
     * @descrtiption 为用户添加角色
     */
    boolean addRoleOfUser(Long userId, List<Long> roleIds);

    /**
     * @return
     * @description 获取总数
     */
    Integer getRoleTotal(String keyword);
}
