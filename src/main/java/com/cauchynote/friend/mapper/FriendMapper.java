package com.cauchynote.friend.mapper;

import com.cauchynote.system.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 好友持久层
 */
@Repository
public interface FriendMapper {

    /**
     * 获取好友ID列表
     *
     * @param userId 用户ID
     * @return 好友ID列表
     */
    @Select("select friend_ids from note_friend where user_id = #{userId}")
    String getFriendIds(Long userId);

    /**
     * 获取好友列表
     *
     * @param friendIds 好友ID列表
     * @return 好友列表
     */
    @Select("<script>" +
        "select id, user_name, email,password, create_time, is_non_expired, is_non_locked, is_password_non_expired, " +
        "is_enable from note_user where id in " +
        "<foreach item='item' index='index' collection='list' open='(' separator=',' close=')'> " +
        "#{item} " +
        "</foreach>" +
        "</script>")
    @Results(id = "getFriendList", value = {
        @Result(column = "id", property = "id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
        @Result(column = "user_name", property = "username", javaType = String.class, jdbcType = JdbcType.VARCHAR),
        @Result(column = "email", property = "email", javaType = String.class, jdbcType = JdbcType.VARCHAR),
        @Result(column = "password", property = "password", javaType = String.class, jdbcType = JdbcType.VARCHAR),
        @Result(column = "create_time", property = "createTime", javaType = Date.class, jdbcType = JdbcType.DATE),
        @Result(column = "is_non_expired", property = "isNonExpired", javaType = Integer.class, jdbcType = JdbcType.TINYINT),
        @Result(column = "is_non_locked", property = "isNonLocked", javaType = Integer.class, jdbcType = JdbcType.TINYINT),
        @Result(column = "is_password_non_expired", property = "isPasswordNonExpired", javaType = Integer.class, jdbcType = JdbcType.TINYINT),
        @Result(column = "is_enable", property = "isEnable", javaType = Integer.class, jdbcType = JdbcType.TINYINT)
    })
    List<User> getFriendList(@Param("list") List<Long> friendIds);

    /**
     * 更新好友信息
     *
     * @param userId    用户ID
     * @param friendIds 好友列表
     * @return 1- 成功 0 - 失败
     */
    @Update("update note_friend set friend_ids = #{friendIds} where user_id = #{userId}")
    Integer updateFriend(Long userId, String friendIds);

    /**
     * 查询好友
     *
     * @param friendName 好友用户名称
     * @return 好友对象
     */
    @Select("select id, user_name, email, password, create_time, is_non_expired, is_non_locked, is_password_non_expired, " +
        "is_enable from note_user where user_name = #{friendName}")
    @Results(id = "searchFriendByUsername", value = {
        @Result(column = "id", property = "id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
        @Result(column = "user_name", property = "username", javaType = String.class, jdbcType = JdbcType.VARCHAR),
        @Result(column = "email", property = "email", javaType = String.class, jdbcType = JdbcType.VARCHAR),
        @Result(column = "password", property = "password", javaType = String.class, jdbcType = JdbcType.VARCHAR),
        @Result(column = "create_time", property = "createTime", javaType = Date.class, jdbcType = JdbcType.DATE),
        @Result(column = "is_non_expired", property = "isNonExpired", javaType = Integer.class, jdbcType = JdbcType.TINYINT),
        @Result(column = "is_non_locked", property = "isNonLocked", javaType = Integer.class, jdbcType = JdbcType.TINYINT),
        @Result(column = "is_password_non_expired", property = "isPasswordNonExpired", javaType = Integer.class, jdbcType = JdbcType.TINYINT),
        @Result(column = "is_enable", property = "isEnable", javaType = Integer.class, jdbcType = JdbcType.TINYINT)
    })
    User searchFriend(String friendName);

    /**
     * 新增好友记录
     *
     * @param userId    用户Id
     * @param friendIds 好友ID
     */
    @Insert("insert into note_friend(user_id, friend_ids) value(#{userId}, #{friendIds})")
    void addNewRecord(Long userId, String friendIds);
}
