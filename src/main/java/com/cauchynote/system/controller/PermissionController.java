package com.cauchynote.system.controller;

import com.cauchynote.system.entity.Permission;
import com.cauchynote.system.service.PermissionService;
import com.cauchynote.utils.SystemConstantDefine;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 权限控制层
 *
 * @Author Cauchy
 * @ClassName PermissionController
 */
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/permission")
public class PermissionController {

    PermissionService permissionService;

    @GetMapping("/getPermissionByRoleId")
    public ResponseEntity<List<Permission>> findRolesByUserId(@RequestParam(value = "id") Integer roleId) {
        List<Permission> permissions = permissionService.getPermissionByRoleId(roleId);
        if (permissions != null) {
            return new ResponseEntity<>(permissions, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }

    @GetMapping("/getAllPermissions")
    public ResponseEntity<List<Permission>> getAllPermissions() {
        List<Permission> permissions = permissionService.getAllPermissions();
        if (permissions != null) {
            return new ResponseEntity<>(permissions, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }

    @PostMapping("/addPermissionOfRole")
    @SuppressWarnings("unchecked")
    public ResponseEntity<Integer> addPermissionOfRole(@RequestBody Map<String, Object> requestMap) {
        Integer roleId = Integer.valueOf(String.valueOf(requestMap.get("roleId")));
        List<Integer> intPermissionIds = (List<Integer>) (requestMap.get("permissionIds"));
        List<Permission> rolePermissions = permissionService.getPermissionByRoleId(roleId);
        List<Integer> permissionIds = new ArrayList<>(intPermissionIds);
        for(Permission rolePermission:rolePermissions){
            permissionIds.remove(rolePermission.getId());
        }
        boolean result = permissionService.addPermissionOfRole(roleId, permissionIds);
        if (result) {
            return new ResponseEntity<>(SystemConstantDefine.SUCCESS, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(SystemConstantDefine.FAIL, HttpStatus.OK);
        }
    }
}
