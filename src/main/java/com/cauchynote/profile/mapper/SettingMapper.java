package com.cauchynote.profile.mapper;

import com.cauchynote.profile.entity.Setting;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

/**
 * 用户配置持久层
 *
 * @Author lingling
 * @Date 2022/5/4
 */
@Repository
public interface SettingMapper {

    /**
     * 添加个人设置
     *
     * @param setting 设置对象
     * @return 1 - 成功 0 - 失败
     */
    @Insert("insert into note_setting(user_id, theme, skin) values(#{userId}, #{theme}, #{skin})")
    int addSetting(Setting setting);

    /**
     * 修改个人设置
     *
     * @param setting Setting 对象
     * @return 1 - 成功 0 - 失败
     */
    @Update("update note_setting set theme = #{theme}, skin = #{skin} where user_id= #{userId} ")
    int modifySetting(Setting setting);

    /**
     * 获取用户id个人信息
     *
     * @param userId 用户 ID
     * @return Setting 对象
     */
    @Select("select user_id,theme,skin from note_setting where user_id= #{userId}")
    @Results(id = "settingMap", value = {
        @Result(column = "user_id", property = "userId", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
        @Result(column = "theme", property = "theme", javaType = Integer.class, jdbcType = JdbcType.TINYINT),
        @Result(column = "skin", property = "skin", javaType = Integer.class, jdbcType = JdbcType.TINYINT)})
    Setting getSettingById(Integer userId);


}
