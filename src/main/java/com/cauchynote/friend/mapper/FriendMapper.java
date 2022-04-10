package com.cauchynote.friend.mapper;

import com.cauchynote.system.entity.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 好友持久层
 */
@Repository
public interface FriendMapper {

    /**
     * 获取好友ID列表
     *
     * @param id 用户ID
     * @return 好友ID列表
     */
    @Select("select friend_ids from note_user where id = #{id}")
    String getFriendIds(Long id);

    /**
     * 获取好友列表
     * @param userIds 好友ID列表
     * @return 好友列表
     */
    @Select("select id, username, email from note_user where id in #{user_ids}")
    @Results(id = "findUserByUsername", value = {
        @Result(column = "id", property = "id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
        @Result(column = "user_name", property = "username", javaType = String.class, jdbcType = JdbcType.VARCHAR),
        @Result(column = "email", property = "email", javaType = String.class, jdbcType = JdbcType.VARCHAR)
    })
    List<User> getFriendList(List<Long> userIds);

    /**
     * 更新好友信息
     * @param userId 用户ID
     * @param friendIds 好友列表
     * @return 1- 成功 0 - 失败
     */
    @Update("update note_user set friend_ids = #{friendIds} where id = #{userId}")
    Integer updateFriend(Long userId, String friendIds);

    /**
     * 查询好友
     * @param friendName 好友用户名称
     * @return 好友对象
     */
    @Select("select id, username, email from note_user where username = {friendName}")
    @Results(id = "findUserByUsername", value = {
        @Result(column = "id", property = "id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
        @Result(column = "user_name", property = "username", javaType = String.class, jdbcType = JdbcType.VARCHAR),
        @Result(column = "email", property = "email", javaType = String.class, jdbcType = JdbcType.VARCHAR)
    })
    User searchFriend(String friendName);
}
