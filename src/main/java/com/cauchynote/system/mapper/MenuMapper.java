package com.cauchynote.system.mapper;

import com.cauchynote.system.entity.Menu;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 菜单持久层
 *
 * @Author Cauchy
 * @ClassName MenuMapper
 */
@Repository
public interface MenuMapper {
    /**
     * 根据 role_id 获取该角色所有的菜单
     *
     * @param roleId 角色 id
     * @return 菜单列表
     */
    @Select("select note_menu.id, note_menu.path, note_menu.name, note_menu.label, note_menu.icon" +
        " from note_menu left join note_role_menu on note_menu.id = " +
        "note_role_menu.menu_id where role_id = #{roleId}")
    @Results(id = "menuResultMap", value = {
        @Result(column = "id", property = "id", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
        @Result(column = "path", property = "path", javaType = String.class, jdbcType = JdbcType.CHAR),
        @Result(column = "name", property = "name", javaType = String.class, jdbcType = JdbcType.CHAR),
        @Result(column = "label", property = "label", javaType = String.class, jdbcType = JdbcType.CHAR),
        @Result(column = "icon", property = "icon", javaType = String.class, jdbcType = JdbcType.CHAR),
    })
    List<Menu> getMenuByRoleId(Integer roleId);

    /**
     * 获取所有权限信息
     *
     * @return List<Permission>
     */
    @Select("select id, path, name, label, icon from note_menu")
    @ResultMap(value = "menuResultMap")
    List<Menu> getAllMenus();

    /**
     * 为角色添加权限
     *
     * @param roleId 角色 ID
     * @param menuId 菜单 ID
     */
    @Insert("insert into note_role_menu(role_id,menu_id) value(#{roleId},#{menuId})")
    void addMenuOfRole(Integer roleId, Integer menuId);
}
