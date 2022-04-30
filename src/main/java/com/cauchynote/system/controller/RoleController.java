package com.cauchynote.system.controller;

import com.cauchynote.system.entity.Role;
import com.cauchynote.system.service.RoleService;
import com.cauchynote.utils.SystemConstantDefine;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色管理控制层
 *
 * @Author Cauchy
 * @ClassName RoleController
 */
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/role")
public class RoleController {
    RoleService roleService;

    @GetMapping("/findRoleByUserId")
    public ResponseEntity<List<Role>> findRolesByUserId(@RequestParam(value = "id") Integer userId) {
        List<Role> roles = roleService.findRolesByUserId(userId);
        if (roles != null) {
            return new ResponseEntity<>(roles, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }

    @PostMapping("/addNewRole")
    public ResponseEntity<Integer> addNewRole(@RequestBody Role role) {
        boolean result = roleService.addNewRole(role);
        if (result) {
            return new ResponseEntity<>(SystemConstantDefine.SUCCESS, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(SystemConstantDefine.FAIL, HttpStatus.OK);
        }
    }

    @PostMapping("/updateRole")
    public ResponseEntity<Integer> updateRole(@RequestBody Role role) {
        boolean result = roleService.updateRole(role);
        if (result) {
            return new ResponseEntity<>(SystemConstantDefine.SUCCESS, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(SystemConstantDefine.FAIL, HttpStatus.OK);
        }
    }

    @GetMapping("/deleteRole")
    public ResponseEntity<Integer> deleteRole(@RequestParam(value = "id") Integer roleId) {
        boolean result = roleService.deleteRole(roleId);
        if (result) {
            return new ResponseEntity<>(SystemConstantDefine.SUCCESS, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(SystemConstantDefine.FAIL, HttpStatus.OK);
        }
    }

    @GetMapping("/getAllRoles")
    public ResponseEntity<Map<String, Object>> getAllRoles(
        @RequestParam(value = "pageSize") Integer pageSize,
        @RequestParam(value = "pageNum") Integer pageNum,
        @RequestParam(value = "keyword") String keyword
    ) {
        Map<String, Object> retMap = new HashMap<>(2);
        List<Role> roles = roleService.getAllRoles(pageSize, pageNum, keyword);
        Integer total = roleService.getRoleTotal(keyword);
        retMap.put("roles", roles);
        retMap.put("total", total);
        if (roles != null) {
            return new ResponseEntity<>(retMap, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }

    @PostMapping("/addRoleOfUser")
    @SuppressWarnings("unchecked")
    public ResponseEntity<Integer> addRoleOfUser(@RequestBody Map<String, Object> requestBody) {
        Integer userId = (Integer) requestBody.get("userId");
        List<Role> userRoles = roleService.findRolesByUserId(userId);
        List<Integer> intRoleIds = (List<Integer>) requestBody.get("roleIds");
        List<Integer> roleIds = new ArrayList<>(intRoleIds);
        for (Role userRole : userRoles) {
            roleIds.remove(userRole.getId());
        }
        boolean result = roleService.addRoleOfUser(userId, roleIds);
        if (result) {
            return new ResponseEntity<>(SystemConstantDefine.SUCCESS, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(SystemConstantDefine.FAIL, HttpStatus.OK);
        }
    }
}
