package com.cauchynote.system.service;

import com.cauchynote.system.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色管理服务层
 *
 * @Author Cauchy
 * @ClassName RoleService
 */

@Service
public interface RoleService {
    /**
     * 根据用户 ID 获取角色列表
     *
     * @param userId 角色ID
     * @return 角色列表
     */
    List<Role> findRolesByUserId(Integer userId);

    /**
     * 添加新角色
     *
     * @param role 角色对象
     * @return 是否成功
     */
    boolean addNewRole(Role role);

    /**
     * 更新角色信息
     *
     * @param role 角色对象
     * @return 是否成功
     */
    boolean updateRole(Role role);

    /**
     * 删除角色
     *
     * @param id 角色ID
     * @return 是否成功
     */
    boolean deleteRole(Integer id);

    /**
     * 获取所有角色
     *
     * @param pageSize 页容量
     * @param pageNum  页号
     * @param keyword  关键词
     * @return 所有角色
     */
    List<Role> getAllRoles(Integer pageSize, Integer pageNum, String keyword);

    /**
     * 为用户添加角色
     *
     * @param userId  用户ID
     * @param roleIds 角色ID列表
     * @return 是否成功添加
     */
    boolean addRoleOfUser(Integer userId, List<Integer> roleIds);

    /**
     * 获取角色总数
     * @param keyword 关键词
     * @return 角色总数
     */
    Integer getRoleTotal(String keyword);
}
