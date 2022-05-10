package com.cauchynote.profile.mapper;
import com.cauchynote.profile.entity.Profile;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @Author lingling
 * @Description
 * @Date 2022/5/4
 * @Version
 */
@Repository
public interface ProfileMapper {
    /**
     * 添加个人信息
     * @param profile
     * @return  添加个人信息是否成功 1表示成功 0 表示失败
     */
    @Insert("insert into note_profile(user_id,nick_name,birthday,gender,photo,telephone,address)values" +
        "(#{userId},#{nickName},#{birthday},#{gender},#{photo},#{telephone},#{address})")
    int addProfile(Profile profile);


    /**
     * 修改个人信息(不包含邮箱）
     * @param profile
     * @return
     */

    @Update("update note_profile set nick_name = #{nickName}, birthday = #{birthday}, gender = #{gender}," +
        "photo=#{photo},telephone=#{telephone},address=#{address} where user_id = #{userId}")
    int modifyProfile(Profile profile);

    /**
     * 获取用户id个人信息
     * @param userId
     * @return
     */


    @Select("select user_id,nick_name,birthday,gender,photo,telephone,address from note_profile where user_id= #{userId}" )
    @Results(id = "profileMap", value = {
        @Result(column = "user_id", property = "userId", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
        @Result(column = "nick_name", property = "nickName", javaType = String.class, jdbcType = JdbcType.CHAR),
        @Result(column = "birthday", property = "birthday", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
        @Result(column = "gender", property = "gender", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
        @Result(column = "photo", property = "photo", javaType = Byte[].class, jdbcType = JdbcType.BLOB),
        @Result(column = "telephone", property = "telephone", javaType = String.class, jdbcType = JdbcType.CHAR),
        @Result(column = "address", property = "address", javaType = String.class, jdbcType = JdbcType.CHAR)})
    Profile getProfileById(Integer userId);

    /**
     * 根据用户id查看生日
     * @param userId
     * @return
     */

    @Select("select birthday from note_profile user_id={#userId}" )
    Date getBirthdayById(Integer userId);






}
