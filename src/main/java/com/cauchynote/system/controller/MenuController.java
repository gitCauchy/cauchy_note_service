package com.cauchynote.system.controller;

import com.cauchynote.system.entity.Menu;
import com.cauchynote.system.service.MenuService;
import com.cauchynote.utils.SystemConstantDefine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author Cauchy
 * @ClassName MenuController
 * @Description 菜单控制层
 * @Date 21/12/08
 * @Version 0.1
 */
@CrossOrigin
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    MenuService menuService;

    @GetMapping("/getMenuByRoleId")
    public ResponseEntity<List<Menu>> findRolesByUserId(@RequestParam(value = "id") Long roleId) {
        List<Menu> Menus = menuService.getMenuByRoleId(roleId);
        if (Menus != null) {
            return new ResponseEntity<>(Menus, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }

    @GetMapping("/getAllMenus")
    public ResponseEntity<List<Menu>> getAllMenus() {
        List<Menu> Menus = menuService.getAllMenus();
        if (Menus != null) {
            return new ResponseEntity<>(Menus, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }

    @PostMapping("/addMenuOfRole")
    public ResponseEntity<Integer> addMenuOfRole(@RequestBody Map<String, Object> requstBody) {
        Long roleId = Long.valueOf(String.valueOf(requstBody.get("roleId")));
        List<Integer> intMenuIds = (List<Integer>) (requstBody.get("MenuIds"));
        List<Long> MenuIds = new ArrayList<>();
        for (Integer MenuId : intMenuIds) {
            MenuIds.add(Long.valueOf(MenuId));
        }
        boolean result = menuService.addMenuOfRole(roleId, MenuIds);
        if (result) {
            return new ResponseEntity<>(SystemConstantDefine.SUCCESS, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(SystemConstantDefine.FAIL, HttpStatus.OK);
        }
    }
}
