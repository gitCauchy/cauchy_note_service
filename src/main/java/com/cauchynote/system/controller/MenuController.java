package com.cauchynote.system.controller;

import com.cauchynote.system.entity.Menu;
import com.cauchynote.system.service.MenuService;
import com.cauchynote.utils.SystemConstantDefine;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 菜单控制层
 *
 * @Author Cauchy
 * @ClassName MenuController
 */
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/menu")
public class MenuController {

    MenuService menuService;

    @GetMapping("/getMenuByRoleId")
    public ResponseEntity<List<Menu>> findRolesByUserId(@RequestParam(value = "id") Integer roleId) {
        List<Menu> menuList = menuService.getMenuByRoleId(roleId);
        if (menuList != null) {
            return new ResponseEntity<>(menuList, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/getAllMenus")
    public ResponseEntity<List<Menu>> getAllMenus() {
        List<Menu> menus = menuService.getAllMenus();
        if (menus != null) {
            return new ResponseEntity<>(menus, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/addMenuOfRole")
    @SuppressWarnings("unchecked")
    public ResponseEntity<Integer> addMenuOfRole(@RequestBody Map<String, Object> requestMap) {
        Integer roleId = Integer.valueOf(String.valueOf(requestMap.get("roleId")));
        List<Integer> intMenuIds = (List<Integer>) (requestMap.get("menuIds"));
        List<Integer> menuIds = new ArrayList<>(intMenuIds);
        boolean result = menuService.addMenuOfRole(roleId, menuIds);
        if (result) {
            return new ResponseEntity<>(SystemConstantDefine.SUCCESS, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(SystemConstantDefine.FAIL, HttpStatus.OK);
        }
    }
}
