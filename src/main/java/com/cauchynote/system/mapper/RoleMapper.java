package com.cauchynote.system.mapper;

import com.cauchynote.system.entity.Role;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Cauchy
 * @ClassName RoleMapper
 * @Description 服务层实现类
 * @Date 21/12/06
 * @Version 0.1
 */
@Repository
public interface RoleMapper {

    /**
     * @param userId
     * @return List<Role>
     * @description 根据用户 ID 查询角色
     */
    @Select("select note_role.id, note_role.role_name, note_role.role_desc from note_role left join note_user_role" +
            " on note_role.id = note_user_role.role_id where user_id = #{userId}")
    @Results(id = "getRoleByUserId", value = {
            @Result(column = "id", property = "id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(column = "role_name", property = "roleName", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "role_desc", property = "roleDescription", javaType = String.class, jdbcType = JdbcType.VARCHAR),
    })
    List<Role> findRolesByUserId(Long userId);

    /**
     * @param role
     * @return
     * @description 新增角色
     */
    @Insert("insert into note_role(role_name, role_desc) value( #{roleName}, #{roleDescription})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    Integer addNewRole(Role role);

    /**
     * @param role
     * @return
     * @description 修改角色信息
     */
    @Update("update note_role set role_name = #{roleName}, role_desc = #{roleDescription} where id = #{id}")
    Integer updateRole(Role role);

    /**
     * @param id
     * @return
     * @description 删除角色
     */
    @Delete("delete from note_role where id = #{id}")
    Integer deleteRole(Long id);

    /**
     * @return List<Role>
     * @description 获取所有角色信息
     */
    @Select("select id, role_name, role_desc from note_role " +
            "where role_name like '%${keyword}%' limit #{startNum}, #{pageSize}")
    @Results(id = "getAllRoles", value = {
            @Result(column = "id", property = "id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(column = "role_name", property = "roleName", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "role_desc", property = "roleDescription", javaType = String.class, jdbcType = JdbcType.VARCHAR)
    })
    List<Role> getAllRoles(Integer pageSize, Integer startNum, String keyword);

    /**
     * @param userId 用户 ID
     * @param roleId 角色 ID
     * @return
     * @Description 为用户添加角色
     */
    @Insert("insert into note_user_role(user_id, role_id) value(#{userId},#{roleId})")
    Integer addRoleOfUser(Long userId, Long roleId);

    @Select("select count(id) from note_role where role_name like '%${keyword}%'")
    Integer getRoleTotal(String keyword);
}
