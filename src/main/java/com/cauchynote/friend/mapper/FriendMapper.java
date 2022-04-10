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
 * @author mailc
 */

@Repository
public interface FriendMapper {

    @Select("select friend_ids from note_user where id = #{id}")
    String getFriendIds(Long id);

    @Select("select id, username, email from note_user where id in #{user_ids}")
    @Results(id = "findUserByUsername", value = {
        @Result(column = "id", property = "id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
        @Result(column = "user_name", property = "username", javaType = String.class, jdbcType = JdbcType.VARCHAR),
        @Result(column = "email", property = "email", javaType = String.class, jdbcType = JdbcType.VARCHAR)
    })
    List<User> getFriendList(List<Long> userIds);

    @Update("update note_user set friend_ids = #{friendIds} where id = #{userId}")
    Integer updateFriend(Long userId, String friendIds);

    @Select("select id, username, email from note_user where username = {friendName}")
    @Results(id = "findUserByUsername", value = {
        @Result(column = "id", property = "id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
        @Result(column = "user_name", property = "username", javaType = String.class, jdbcType = JdbcType.VARCHAR),
        @Result(column = "email", property = "email", javaType = String.class, jdbcType = JdbcType.VARCHAR)
    })
    User searchFriend(String friendName);
}
