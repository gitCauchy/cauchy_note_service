package com.cauchynote.system.service;

import com.cauchynote.system.entity.Menu;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单信息服务层
 *
 * @Author Cauchy
 * @ClassName MenuService
 */
@Service
public interface MenuService {
    /**
     * 根据角色获取菜单列表
     * @param roleId 角色ID
     * @return 菜单列表
     */
    List<Menu> getMenuByRoleId(Integer roleId);

    /**
     * 获取所有菜单信息
     * @return 所有菜单信息
     */
    List<Menu> getAllMenus();

    /**
     * 为角色添加菜单
     * @param roleId 角色ID
     * @param menuIds 菜单ID列表
     * @return 是否成功
     * @description
     */
    boolean addMenuOfRole(Integer roleId, List<Integer> menuIds);
}
