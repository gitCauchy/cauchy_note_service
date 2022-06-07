package com.cauchynote.friend.mapper;

import com.cauchynote.system.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 好友持久层
 *
 * @author Cauchy
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
    String getFriendIds(Integer userId);

    /**
     * 获取好友列表
     *
     * @param userId    用户 ID
     * @param friendIds 好友 ID 列表
     * @param startNum  页起始位置
     * @param pageSize  页大小
     * @return 好友列表
     */
    @Select("<script>" +
        "select note_user.id, note_user.user_name, note_user.email, note_friend_remark.remark_name from note_user " +
        "left join note_friend_remark on note_user.id = note_friend_remark.friend_id where note_friend_remark.user_id" +
        " =#{userId} and note_user.id in " +
        "<foreach item='item' index='index' collection='list' open='(' separator=',' close=')'> " +
        "#{item} " +
        "</foreach> limit #{startNum}, #{pageSize} " +
        "</script>")
    List<Map<String, Object>> getFriendList(Integer userId, @Param("list") List<Integer> friendIds, Integer startNum,
                                            Integer pageSize);

    /**
     * 设置好友备注
     *
     * @param userId     用户 ID
     * @param friendId   好友 ID
     * @param remarkName 备注名称
     * @return 成功 - 1，失败 - 0
     */
    @Insert("insert into note_friend_remark(user_id, friend_id, remark_name) value(#{userId}, #{friendId}, " +
        "#{remarkName})")
    Integer setRemarkName(Integer userId, Integer friendId, String remarkName);

    /**
     * 修改好于备注
     *
     * @param userId     用户 ID
     * @param friendId   好友 ID
     * @param remarkName 备注名称
     * @return 成功 - 1，失败 - 0
     */
    @Update("update note_friend_remark set remark_name = #{remarkName} where user_id = #{userId} and friend_id = " +
        "#{friendId} ")
    Integer modifyRemarkName(Integer userId, Integer friendId, String remarkName);

    /**
     * 是否设置过备注名
     *
     * @param userId   用户 ID
     * @param friendId 好友 ID
     * @return 1 - 是 | 0 - 否
     */
    @Select("select count(*) from note_friend_remark where user_id = #{userId} and friend_id = #{friendId}")
    Integer isSetRemarkName(Integer userId, Integer friendId);

    /**
     * 更新好友信息
     *
     * @param userId    用户ID
     * @param friendIds 好友列表
     * @return 1- 成功 0 - 失败
     */
    @Update("update note_friend set friend_ids = #{friendIds} where user_id = #{userId}")
    Integer updateFriend(Integer userId, String friendIds);

    /**
     * 查询好友
     *
     * @param friendName 好友用户名称
     * @return 好友对象
     */
    @Select("select id, user_name, email, password, create_time, is_non_expired, is_non_locked, is_password_non_expired, " +
        "is_enable from note_user where user_name = #{friendName}")
    @Results(id = "friendResultMap", value = {
        @Result(column = "id", property = "id", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
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
     * @return 1 - 成功 | 0 - 失败
     */
    @Insert("insert into note_friend(user_id, friend_ids) value(#{userId}, #{friendIds})")
    Integer addNewRecord(Integer userId, String friendIds);

    /**
     * 新增好友请求记录
     *
     * @param userId           用户Id
     * @param friendRequestIds 好友ID
     * @return 1 - 新增成功 0 - 新增失败
     */
    @Insert("insert into note_friend(user_id, friend_request_ids) value(#{userId}, #{friendRequestIds})")
    Integer addNewFriendRequestRecord(Integer userId, String friendRequestIds);

    /**
     * 获取好友请求ID列表
     *
     * @param userId 用户ID
     * @return 好友请求ID列表
     */
    @Select("select friend_request_ids from note_friend where user_id = #{userId}")
    String getFriendRequestIds(Integer userId);

    /**
     * 更新好友请求信息
     *
     * @param userId           用户ID
     * @param friendRequestIds 好友请求ID列表
     * @return 1- 成功 0 - 失败
     */
    @Update("update note_friend set friend_request_ids = #{friendRequestIds} where user_id = #{userId}")
    Integer updateFriendRequest(Integer userId, String friendRequestIds);

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
    @ResultMap(value = "friendResultMap")
    List<User> getFriendRequestList(@Param("list") List<Integer> friendRequestIds);

    /**
     * 查询记录数量
     *
     * @param userId 用户ID
     * @return 记录数量
     */
    @Select("select count(*) from note_friend where user_id = #{userId}")
    Integer getRecordCountOfUser(Integer userId);
}
