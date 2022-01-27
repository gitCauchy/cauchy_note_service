package com.cauchynote.system.mapper;

import com.cauchynote.system.entity.Menu;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Cauchy
 * @ClassName MenuMapper
 * @Description 菜单持久层
 * @Date 22/01/25
 * @Version 0.1
 */
@Repository
public interface MenuMapper {
    /**
     * @param roleId 角色 id
     * @return 菜单列表
     * @description 根据 role_id 获取该角色所有的菜单
     */
    @Select("select note_menu.id, note_menu.path, note_menu.name, note_menu.label, note_menu.icon" +
            " from note_menu left join note_role_menu on note_menu.id = " +
            "note_role_menu.menu_id where role_id = #{roleId}")
    @Results(id = "get=MenuByRoleId", value = {
            @Result(column = "id", property = "id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(column = "path", property = "path", javaType = String.class, jdbcType = JdbcType.CHAR),
            @Result(column = "name", property = "name", javaType = String.class, jdbcType = JdbcType.CHAR),
            @Result(column = "label", property = "label", javaType = String.class, jdbcType = JdbcType.CHAR),
            @Result(column = "icon", property = "icon", javaType = String.class, jdbcType = JdbcType.CHAR),
    })
    List<Menu> getMenuByRoleId(Long roleId);

    /**
     * @return List<Permission>
     * @description 获取所有权限信息
     */
    @Select("select id, path, name, label, icon from note_menu")
    @Results(id = "getAllMenus", value = {
            @Result(column = "id", property = "id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(column = "path", property = "path", javaType = String.class, jdbcType = JdbcType.CHAR),
            @Result(column = "name", property = "name", javaType = String.class, jdbcType = JdbcType.CHAR),
            @Result(column = "label", property = "label", javaType = String.class, jdbcType = JdbcType.CHAR),
            @Result(column = "label", property = "label", javaType = String.class, jdbcType = JdbcType.CHAR)
    })
    List<Menu> getAllMenus();

    /**
     * @param roleId 角色 ID
     * @param menuId 菜单 ID
     * @return
     * @Description 为角色添加权限
     */
    @Insert("insert into note_role_menu(role_id,menu_id) value(#{roleId},#{menuId})")
    Integer addMenuOfRole(Long roleId, Long menuId);
}
