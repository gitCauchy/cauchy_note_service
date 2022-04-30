package com.cauchynote.system.service.impl;

import com.cauchynote.system.entity.Menu;
import com.cauchynote.system.entity.Permission;
import com.cauchynote.system.entity.Role;
import com.cauchynote.system.entity.User;
import com.cauchynote.system.mapper.MenuMapper;
import com.cauchynote.system.mapper.PermissionMapper;
import com.cauchynote.system.mapper.RoleMapper;
import com.cauchynote.system.mapper.UserMapper;
import com.cauchynote.system.service.UserService;
import com.cauchynote.utils.SystemConstantDefine;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 用户服务层实现类
 *
 * @Author Cauchy
 * @ClassName UserServiceImpl
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserMapper userMapper;
    private RoleMapper roleMapper;
    private PermissionMapper permissionMapper;
    private MenuMapper menuMapper;

    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    @Override
    public Integer deleteUser(Integer id) {
        return userMapper.deleteUser(id);
    }

    @Override
    public Integer addUser(User user) {
        // 对传入的密码进行加密处理
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode(user.getPassword());
        user.setPassword(password);
        return userMapper.addUser(user);
    }

    @Override
    public Integer updateUser(User user) {
        String rawPassword = user.getPassword();
        if (rawPassword != null) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String password = encoder.encode(rawPassword);
            user.setPassword(password);
        }
        return userMapper.updateUser(user);
    }

    @Override
    public List<User> getAllUsers(Integer pageSize, Integer pageNum, String keyword) {
        return userMapper.getAllUsers(pageSize, (pageNum - 1) * pageSize, keyword);
    }

    @Override
    public Integer login(String username, String originPassword) {
        User user = (User) this.loadUserByUsername(username);
        // 如果用户不存在或者当前用户为不可用状态
        if (user == null || user.getIsEnable() == 1) {
            return -1;
        }
        // 对传入的明文密码做 hash
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (encoder.matches(originPassword, user.getPassword())) {
            return 1;
        }
        return -1;
    }

    @Override
    public Integer register(User user) {
        User getUser = userMapper.findUserByUsername(user.getUsername());
        // 1. 检查当前用户名是否存在
        if (getUser != null) {
            return -1;
        }
        user.setIsEnable(0);
        user.setIsNonExpired(0);
        user.setIsPasswordNonExpired(0);
        user.setIsNonLocked(0);
        if (this.addUser(user) == 1) {
            // 为用户分配最基础的角色
            User saveUser = userMapper.findUserByUsername(user.getUsername());
            Role role = roleMapper.findRoleByName(SystemConstantDefine.USER_NAME);
            roleMapper.addRoleOfUser(saveUser.getId(), role.getId());
            return 1;
        }
        return -2;
    }

    @Override
    public Integer getUserTotal(String keyword) {
        return userMapper.getUserTotal(keyword);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        // 根据 username 查找到该用户
        User user = userMapper.findUserByUsername(username);
        if (user == null) {
            return null;
        }
        // 用于存放 permission 的集合，因为可能存在重复的情况， 这里使用 Set 集合
        Set<Permission> permissions = new HashSet<>();
        Set<Menu> menus = new HashSet<>();
        List<Role> roles = roleMapper.findRolesByUserId(user.getId());
        // 遍历角色，获取该用户的所有权限、菜单
        for (Role role : roles) {
            List<Permission> permissionList = permissionMapper.getPermissionByRoleId(role.getId());
            permissions.addAll(permissionList);
            List<Menu> menuList = menuMapper.getMenuByRoleId(role.getId());
            menus.addAll(menuList);
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Permission permission : permissions) {
            GrantedAuthority authority = new SimpleGrantedAuthority(permission.getPermissionTag());
            authorities.add(authority);
        }
        List<Menu> deduplicateMenus = new ArrayList<>(menus);
        user.setAuthorities(authorities);
        user.setMenus(deduplicateMenus);
        return user;
    }

    @Override
    public User findUserByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }

    @Override
    public Integer modifyPassword(String password, String username) {
        return userMapper.modifyPassword(password, username);
    }
}
