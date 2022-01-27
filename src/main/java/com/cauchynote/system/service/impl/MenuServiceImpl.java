package com.cauchynote.system.service.impl;

import com.cauchynote.system.entity.Menu;
import com.cauchynote.system.mapper.MenuMapper;
import com.cauchynote.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Cauchy
 * @ClassName MenuService
 * @Description 菜单管理服务层
 * @Date 21/12/08
 * @Version 0.1
 */

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuMapper menuMapper;

    @Override
    public List<Menu> getMenuByRoleId(Long roleId) {
        return menuMapper.getMenuByRoleId(roleId);
    }

    @Override
    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }

    @Override
    public boolean addMenuOfRole(Long roleId, List<Long> menuIds) {
        for (Long menuId : menuIds) {
            menuMapper.addMenuOfRole(roleId, menuId);
        }
        return true;
    }
}
