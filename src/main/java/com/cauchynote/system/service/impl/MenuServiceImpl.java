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
        return menuMapper.getMenuByRoleId(roleId);
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
