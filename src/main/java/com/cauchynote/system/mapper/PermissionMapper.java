package com.cauchynote.system.mapper;

import com.cauchynote.system.entity.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 权限持久层
 *
 * @Author Cauchy
 * @ClassName RoleMapper
 */
@Repository
public interface PermissionMapper {

    /**
     * 根据 role_id 获取该角色所有的权限
     *
     * @param roleId 角色 id
     * @return 权限列表
     * @description
     */
    @Select("select note_permission.id, note_permission.permission_name, note_permission.permission_tag" +
        " from note_permission left join note_role_permission on note_permission.id = " +
        "note_role_permission.permission_id where role_id = #{roleId}")
    @Results(id = "getPermissionByRoleId", value = {
        @Result(column = "id", property = "id", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
        @Result(column = "permission_name", property = "permissionName", javaType = String.class, jdbcType = JdbcType.VARCHAR),
        @Result(column = "permission_tag", property = "permissionTag", javaType = String.class, jdbcType = JdbcType.VARCHAR),
    })
    List<Permission> getPermissionByRoleId(Integer roleId);

    /**
     * 获取所有权限信息
     *
     * @return List<Permission>
     */
    @Select("select id, permission_name, permission_tag from note_permission")
    @Results(id = "getAllPermissions", value = {
        @Result(column = "id", property = "id", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
        @Result(column = "permission_name", property = "permissionName", javaType = String.class, jdbcType = JdbcType.VARCHAR),
        @Result(column = "permission_tag", property = "permissionTag", javaType = String.class, jdbcType = JdbcType.VARCHAR)
    })
    List<Permission> getAllPermissions();

    /**
     * 为角色添加权限
     *
     * @param roleId       角色 ID
     * @param permissionId 角色 ID
     */
    @Insert("insert into note_role_permission(role_id,permission_id) value(#{roleId},#{permissionId})")
    void addPermissionOfRole(Integer roleId, Integer permissionId);
}
