package com.cauchynote.system.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * 登录信息持久层
 *
 * @Author Cauchy
 * @ClassName LoginInfoMapper
 */
@Repository
public interface LoginInfoMapper {
    /**
     * 新增登录信息
     *
     * @param userId 用户ID
     */
    @Insert("insert into note_login_info(user_id, login_time) values(#{userId},now())")
    void addLoginInfo(Integer userId);

    /**
     * 登录次数
     *
     * @param userId    用户ID
     * @param startDate 开始日期
     * @return 次数
     */
    @Select("select count(*) from note_login_info where user_id = #{userId} and login_time > #{startDate}")
    int getUserLoginCount(Integer userId, Date startDate);

    /**
     * 获取登录总次数
     *
     * @param startDate 开始日期
     * @return 总次数
     */
    @Select("select count(*) from note_login_info where login_time > #{startDate}")
    int getTotalLoginCount(Date startDate);


}
