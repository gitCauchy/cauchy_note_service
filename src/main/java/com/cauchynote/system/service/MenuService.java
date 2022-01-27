package com.cauchynote.system.service;

import com.cauchynote.system.entity.Menu;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Cauchy
 * @ClassName MenuServie
 * @Description //TODO
 * @Date 22/01/25
 * @Version 0.1
 */
@Service
public interface MenuService {
    /**
     * @param roleId
     * @return
     * @description 根据角色获取菜单列表
     */
    List<Menu> getMenuByRoleId(Long roleId);

    /**
     * @return
     * @description 获取所有菜单信息
     */
    List<Menu> getAllMenus();

    /**
     * @param roleId
     * @param menuIds
     * @return
     * @description 为角色添加菜单
     */
    boolean addMenuOfRole(Long roleId, List<Long> menuIds);
}
