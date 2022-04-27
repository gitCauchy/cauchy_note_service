package com.cauchynote.system.mapper;

import com.cauchynote.system.entity.Role;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色持久层
 *
 * @Author Cauchy
 * @ClassName RoleMapper
 */
@Repository
public interface RoleMapper {

    /**
     * 根据用户 ID 查询角色
     *
     * @param userId 用户 ID
     * @return List<Role>
     */
    @Select("select note_role.id, note_role.role_name, note_role.role_desc from note_role left join note_user_role" +
        " on note_role.id = note_user_role.role_id where user_id = #{userId}")
    @Results(id = "getRoleByUserId", value = {
        @Result(column = "id", property = "id", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
        @Result(column = "role_name", property = "roleName", javaType = String.class, jdbcType = JdbcType.VARCHAR),
        @Result(column = "role_desc", property = "roleDescription", javaType = String.class, jdbcType = JdbcType.VARCHAR),
    })
    List<Role> findRolesByUserId(Integer userId);

    /**
     * 根据角色名称查询角色信息
     *
     * @param roleName 角色名称
     * @return 角色对象
     */
    @Select("select note_role.id, note_role.role_name, note_role.role_desc from note_role where role_name = #{roleName}")
    Role findRoleByName(String roleName);

    /**
     * 新增角色
     *
     * @param role 角色对象
     * @return 1- 成功 0 - 失败
     */
    @Insert("insert into note_role(role_name, role_desc) value( #{roleName}, #{roleDescription})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    Integer addNewRole(Role role);

    /**
     * 修改角色信息
     *
     * @param role 角色对象
     * @return 1 - 成功 0 - 失败
     */
    @Update("update note_role set role_name = #{roleName}, role_desc = #{roleDescription} where id = #{id}")
    Integer updateRole(Role role);

    /**
     * 删除角色
     *
     * @param id 角色 ID
     * @return 1 - 成功 0 - 失败
     */
    @Delete("delete from note_role where id = #{id}")
    Integer deleteRole(Integer id);

    /**
     * 获取所有角色信息
     * @param pageSize 页码
     * @param startNum 其实位置
     * @param keyword 关键词
     * @return List<Role>
     */
    @Select("select id, role_name, role_desc from note_role " +
        "where role_name like '%${keyword}%' limit #{startNum}, #{pageSize}")
    @Results(id = "getAllRoles", value = {
        @Result(column = "id", property = "id", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
        @Result(column = "role_name", property = "roleName", javaType = String.class, jdbcType = JdbcType.VARCHAR),
        @Result(column = "role_desc", property = "roleDescription", javaType = String.class, jdbcType = JdbcType.VARCHAR)
    })
    List<Role> getAllRoles(Integer pageSize, Integer startNum, String keyword);

    /**
     * 为用户添加角色
     *
     * @param userId 用户 ID
     * @param roleId 角色 ID
     */
    @Insert("insert into note_user_role(user_id, role_id) value(#{userId},#{roleId})")
    void addRoleOfUser(Integer userId, Integer roleId);

    /**
     * 获取角色数量
     *
     * @param keyword 关键词
     * @return 数量
     */
    @Select("select count(id) from note_role where role_name like '%${keyword}%'")
    Integer getRoleTotal(String keyword);
}
