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
     * @param startNum  页起始位置
     * @param pageSize  页大小
     * @return 好友列表
     */
    @Select("<script>" +
        "select id, user_name, email,password, create_time, is_non_expired, is_non_locked, is_password_non_expired, " +
        "is_enable from note_user where id in " +
        "<foreach item='item' index='index' collection='list' open='(' separator=',' close=')'> " +
        "#{item} " +
        "</foreach> limit #{startNum}, #{pageSize} " +
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
    List<User> getFriendList(@Param("list") List<Long> friendIds, Integer startNum, Integer pageSize);

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
    Integer addNewRecord(Long userId, String friendIds);

    /**
     * 新增好友请求记录
     *
     * @param userId           用户Id
     * @param friendRequestIds 好友ID
     */
    @Insert("insert into note_friend(user_id, friend_request_ids) value(#{userId}, #{friendRequestIds})")
    void addNewFriendRequestRecord(Long userId, String friendRequestIds);

    /**
     * 获取好友请求ID列表
     *
     * @param userId 用户ID
     * @return 好友请求ID列表
     */
    @Select("select friend_request_ids from note_friend where user_id = #{userId}")
    String getFriendRequestIds(Long userId);

    /**
     * 更新好友请求信息
     *
     * @param userId           用户ID
     * @param friendRequestIds 好友请求ID列表
     * @return 1- 成功 0 - 失败
     */
    @Update("update note_friend set friend_request_ids = #{friendRequestIds} where user_id = #{userId}")
    Integer updateFriendRequest(Long userId, String friendRequestIds);

    /**
     * 查询好友请求列表
     *
     * @param friendRequestIds 请求好友 ID 列表
     * @return List
     */
    @Select("<script>" +
        "select id, user_name, email,password, create_time, is_non_expired, is_non_locked, is_password_non_expired, " +
        "is_enable from note_user where id in " +
        "<foreach item='item' index='index' collection='list' open='(' separator=',' close=')'> " +
        "#{item} " +
        "</foreach> " +
        "</script>")
    @Results(id = "getFriendRequestList", value = {
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
    List<User> getFriendRequestList(@Param("list") List<Long> friendRequestIds);

    /**
     * 查询记录数量
     *
     * @param userId 用户ID
     * @return 记录数量
     */
    @Select("select count(*) from note_friend where user_id = #{userId}")
    Integer getRecordCountOfUser(Long userId);
}
