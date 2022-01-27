package com.cauchynote.system.service;

import com.cauchynote.system.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Cauchy
 * @ClassName UserService
 * @Description 服务层接口
 * @Date 21/12/06
 * @Version 0.1
 */
@Service
public interface UserService {
    /**
     * @param id
     * @return User
     * @description 获取用户信息
     */
    User getUserById(Long id);

    /**
     * @param id
     * @return 1 - 删除成功 0 - 删除失败
     * @description 删除用户，逻辑删除
     */
    boolean deleteUser(Long id);

    /**
     * @param user
     * @return 0 - 添加成功 1 - 添加失败
     * @description 新增用户
     */
    boolean addUser(User user);

    /**
     * 修改用户信息
     *
     * @param user
     * @return 0 - 修改成功 1 - 修改失败
     */
    boolean updateUser(User user);

    /**
     * @return List<User>
     * @description 获取所有用户
     */
    List<User> getAllUsers(Integer pageSize, Integer pageNum, String keyword);

    /**
     * @param username       用户名
     * @param originPassword 明文密码
     * @return
     * @description 登录校验
     */
    boolean login(String username, String originPassword);

    /**
     * @param user
     * @return
     * @description 用户注册
     */
    Integer register(User user);

    /**
     * @return
     * @description 获取总数
     */
    Integer getUserTotal(String keyword);
}
