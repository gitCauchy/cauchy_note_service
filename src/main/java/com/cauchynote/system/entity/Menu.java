package com.cauchynote.system.entity;

import lombok.Data;

import java.util.List;

/**
 * 菜单实体类
 *
 * @Author Cauchy
 * @ClassName Menu
 */
@Data
public class Menu {
    /**
     * 主键 ID
     */
    Integer id;
    /**
     * 前端路由
     */
    String path;
    /**
     * 名称
     */
    String name;
    /**
     * 标签
     */
    String label;
    /**
     * 图标
     */
    String icon;
    /**
     * 子菜单
     */
    List<Menu> children;
    /**
     * 子菜单 id 列表
     */
    String childrenId;
    /**
     * url
     */
    String url;
}
