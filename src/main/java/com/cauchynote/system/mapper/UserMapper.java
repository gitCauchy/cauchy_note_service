package com.cauchynote.system.mapper;

import com.cauchynote.system.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @Author Cauchy
 * @ClassName UserMapper
 * @Description 用户持久层，实现 CRUD
 * @Date 21/12/06
 * @Version 0.2
 */
@Repository
public interface UserMapper {

    /**
     * @param id
     * @return User
     * @description 根据用户 id 查询用户信息
     */
    @Select("select id, user_name, email, password, create_time, is_non_expired, is_non_locked, is_password_non_expired, " + "is_enable from note_user where id = #{id}")
    @Results(id = "getAllInfoById", value = {@Result(column = "id", property = "id", javaType = Long.class, jdbcType = JdbcType.BIGINT), @Result(column = "user_name", property = "username", javaType = String.class, jdbcType = JdbcType.VARCHAR), @Result(column = "email", property = "email", javaType = String.class, jdbcType = JdbcType.VARCHAR), @Result(column = "password", property = "password", javaType = String.class, jdbcType = JdbcType.VARCHAR), @Result(column = "create_time", property = "createTime", javaType = Date.class, jdbcType = JdbcType.DATE), @Result(column = "is_non_expired", property = "isNonExpired", javaType = Integer.class, jdbcType = JdbcType.TINYINT), @Result(column = "is_non_locked", property = "isNonLocked", javaType = Integer.class, jdbcType = JdbcType.TINYINT), @Result(column = "is_password_non_expired", property = "isPasswordNonExpired", javaType = Integer.class, jdbcType = JdbcType.TINYINT), @Result(column = "is_enable", property = "isEnable", javaType = Integer.class, jdbcType = JdbcType.TINYINT),})
    User getUserById(Long id);

    /**
     * @param id
     * @return Integer
     * @description 删除用户，这里使用逻辑删除，将 is_enable 设置为 1 - 不可用
     */
    @Update("update note_user set is_enable = 1 where id = #{id}")
    Integer deleteUser(Long id);

    @Insert("insert into note_user(user_name, email, password, is_non_expired, is_non_locked, is_password_non_expired, " + "is_enable) values(#{username}, #{email}, #{password}, 0, 0, 0, 0)")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    Integer addUser(User user);

    @Select("select id, user_name, email, password, create_time, is_non_expired, is_non_locked, is_password_non_expired, " + "is_enable from note_user where user_name = #{username}")
    @Results(id = "findUserByUsername", value = {@Result(column = "id", property = "id", javaType = Long.class, jdbcType = JdbcType.BIGINT), @Result(column = "user_name", property = "username", javaType = String.class, jdbcType = JdbcType.VARCHAR), @Result(column = "email", property = "email", javaType = String.class, jdbcType = JdbcType.VARCHAR), @Result(column = "password", property = "password", javaType = String.class, jdbcType = JdbcType.VARCHAR), @Result(column = "create_time", property = "createTime", javaType = Date.class, jdbcType = JdbcType.DATE), @Result(column = "is_non_expired", property = "isNonExpired", javaType = Integer.class, jdbcType = JdbcType.TINYINT), @Result(column = "is_non_locked", property = "isNonLocked", javaType = Integer.class, jdbcType = JdbcType.TINYINT), @Result(column = "is_password_non_expired", property = "isPasswordNonExpired", javaType = Integer.class, jdbcType = JdbcType.TINYINT), @Result(column = "is_enable", property = "isEnable", javaType = Integer.class, jdbcType = JdbcType.TINYINT),})
    User findUserByUsername(String username);

    /**
     * @param user 用户
     * @return Integer
     * @description 修改用户信息
     */
    @Update("update note_user set user_name = #{username}, email = #{email}, password = #{password} where id = #{id}")
    Integer updateUser(User user);

    @Select("select id, user_name, email, create_time, is_non_expired, is_non_locked, is_password_non_expired, "
        + "is_enable from note_user where user_name like '%${keyword}%' limit #{startNum}, #{pageSize}")
    @Results(id = "getAllUsers", value = {
        @Result(column = "id", property = "id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
        @Result(column = "user_name", property = "username", javaType = String.class, jdbcType = JdbcType.VARCHAR),
        @Result(column = "email", property = "email", javaType = String.class, jdbcType = JdbcType.VARCHAR),
        @Result(column = "password", property = "password", javaType = String.class, jdbcType = JdbcType.VARCHAR),
        @Result(column = "create_time", property = "createTime", javaType = Date.class, jdbcType = JdbcType.DATE),
        @Result(column = "is_non_expired", property = "isNonExpired", javaType = Integer.class, jdbcType = JdbcType.TINYINT),
        @Result(column = "is_non_locked", property = "isNonLocked", javaType = Integer.class, jdbcType = JdbcType.TINYINT),
        @Result(column = "is_password_non_expired", property = "isPasswordNonExpired", javaType = Integer.class, jdbcType = JdbcType.TINYINT),
        @Result(column = "is_enable", property = "isEnable", javaType = Integer.class, jdbcType = JdbcType.TINYINT),})
    List<User> getAllUsers(Integer pageSize, Integer startNum, String keyword);

    @Select("select count(id) from note_user where user_name like '%${keyword}%'")
    Integer getUserTotal(String keyword);

    /**
     * @param userId 通过 ID 获取用户名
     * @return 用户名
     */
    @Select("select user_name from note_user where id = #{userId}")
    String getUsernameById(Long userId);

    /**
     * 修改密码
     *
     * @param password 密码
     * @param username 用户名
     * @return 修改条目数量
     */
    @Update("update note_user set password = #{password} where user_name = #{username}")
    Integer modifyPassword(String password, String username);
}
