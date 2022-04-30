package com.cauchynote.system.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 用户实体类，实现 UserDetails 接口
 *
 * @Author Cauchy
 * @ClassName User
 */
@Data
public class User implements UserDetails {
    /**
     * ID
     */
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 密码
     */
    private String password;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 是否过期 0 - 是 1 - 否
     */
    private Integer isNonExpired;
    /**
     * 是否锁定 0 - 是 1 - 否
     */
    private Integer isNonLocked;
    /**
     * 是否可用 0 - 是 1 - 否
     */
    private Integer isEnable;
    /**
     * 密码是否过期 0 - 是 1 - 否
     */
    private Integer isPasswordNonExpired;
    /**
     * 用户权限列表
     */
    private List<GrantedAuthority> authorities;
    /**
     * 用户菜单列表
     */
    private List<Menu> menus;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return checkStatus(isNonExpired);
    }

    @Override
    public boolean isAccountNonLocked() {
        return checkStatus(isNonLocked);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return checkStatus(isPasswordNonExpired);
    }

    @Override
    public boolean isEnabled() {
        return checkStatus(isEnable);
    }

    /**
     * @param rawStatus 状态
     * @return true|false
     * @description 状态检查
     */
    private boolean checkStatus(Integer rawStatus) {
        return rawStatus == 0;
    }
}
