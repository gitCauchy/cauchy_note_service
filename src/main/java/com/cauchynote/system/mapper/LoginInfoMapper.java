package com.cauchynote.system.mapper;

import com.cauchynote.system.entity.LoginInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @Author Cauchy
 * @ClassName LoginInfoMapper
 * @Description 登录信息持久层
 * @Date 22/02/05
 * @Version 0.1
 */
@Repository
public interface LoginInfoMapper {

    @Insert("insert into note_login_info(user_id, login_time) values(#{userId},now())")
    int addLoginInfo(Long userId);

    @Select("select count(*) from note_login_info where user_id = #{userId} and login_time > #{startDate}")
    int getUserLoginCount(Long userId, Date startDate);

    @Select("select count(*) from note_login_info where login_time > #{startDate}")
    int getTotalLoginCount(Date startDate);


}
