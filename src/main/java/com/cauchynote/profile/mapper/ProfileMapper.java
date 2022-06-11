package com.cauchynote.profile.mapper;

import com.cauchynote.profile.entity.Profile;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * 个人信息持久层
 *
 * @Author lingling
 * @Date 2022/5/4
 */
@Repository
public interface ProfileMapper {
    /**
     * 查询用户信息是否存在
     */
    @Select("select count(*) from note_profile where user_id = #{userId}")
    Integer countOfUserProfile(Integer userId);

    /**
     * 添加个人信息
     *
     * @param profile profile 对象
     * @return 添加个人信息是否成功 1表示成功 0 表示失败
     */
    @Insert("insert into note_profile(user_id, nick_name, birthday, gender, photo, telephone, address) values" +
        "(#{userId}, #{nickName}, #{birthday}, #{gender}, #{photo}, #{telephone}, #{address})")
    int addProfile(Profile profile);


    /**
     * 修改个人信息(不包含邮箱）
     *
     * @param profile profile 对象
     * @return 1 - 成功修改 0 - 修改失败
     */

    @Update("update note_profile set nick_name = #{nickName}, birthday = #{birthday}, gender = #{gender}," +
        "photo=#{photo}, telephone=#{telephone}, address=#{address} where user_id = #{userId}")
    int modifyProfile(Profile profile);

    /**
     * 获取用户个人信息
     *
     * @param userId 用户 ID
     * @return Profile 对象
     */
    @Select("select note_profile.user_id, note_profile.nick_name, note_profile.birthday, note_profile.gender, " +
        "note_profile.photo, note_profile.telephone, note_profile.address, note_user.email from note_profile left " +
        "join note_user on note_profile.user_id = note_user.id where user_id= #{userId}")
    @Results(id = "profileMap", value = {
        @Result(column = "user_id", property = "userId", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
        @Result(column = "nick_name", property = "nickName", javaType = String.class, jdbcType = JdbcType.CHAR),
        @Result(column = "birthday", property = "birthday", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
        @Result(column = "gender", property = "gender", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
        @Result(column = "photo", property = "photo", javaType = Byte[].class, jdbcType = JdbcType.BLOB),
        @Result(column = "telephone", property = "telephone", javaType = String.class, jdbcType = JdbcType.CHAR),
        @Result(column = "email", property = "email", javaType = String.class, jdbcType = JdbcType.CHAR),
        @Result(column = "address", property = "address", javaType = String.class, jdbcType = JdbcType.CHAR)})
    Profile getProfileById(Integer userId);

    /**
     * 根据用户id查看生日
     *
     * @param userId 用户 ID
     * @return 用户生日
     */
    @Select("select birthday from note_profile user_id={#userId}")
    Date getBirthdayById(Integer userId);
}
