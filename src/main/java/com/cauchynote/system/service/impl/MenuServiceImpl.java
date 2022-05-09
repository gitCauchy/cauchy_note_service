package com.cauchynote.system.service.impl;

import com.cauchynote.system.entity.Menu;
import com.cauchynote.system.mapper.MenuMapper;
import com.cauchynote.system.service.MenuService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单管理服务层
 *
 * @Author Cauchy
 * @ClassName MenuService
 */

@Service
@AllArgsConstructor
public class MenuServiceImpl implements MenuService {

    MenuMapper menuMapper;

    @Override
    public List<Menu> getMenuByRoleId(Integer roleId) {
        // 获取菜单
        List<Menu> menus = menuMapper.getMenuByRoleId(roleId);
        // 遍历获取菜单的子菜单
        for (Menu menu : menus) {
            if (menu.getChildrenId() != null && !"".equals(menu.getChildrenId())) {
                menu.setChildren(menuMapper.getChildMenus(menu.getChildrenId()));
            }
        }
        return menus;
    }

    @Override
    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }

    @Override
    public boolean addMenuOfRole(Integer roleId, List<Integer> menuIds) {
        for (Integer menuId : menuIds) {
            menuMapper.addMenuOfRole(roleId, menuId);
        }
        return true;
    }
}
