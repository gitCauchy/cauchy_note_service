package com.cauchynote.system.service;

import com.cauchynote.system.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户服务层
 *
 * @Author Cauchy
 * @ClassName UserService
 */
@Service
public interface UserService {
    /**
     * 获取用户信息
     *
     * @param id 用户ID
     * @return User
     */
    User getUserById(Integer id);

    /**
     * 删除用户，逻辑删除
     *
     * @param id 用户ID
     * @return 1 - 删除成功 0 - 删除失败
     */
    Integer deleteUser(Integer id);

    /**
     * 新增用户
     *
     * @param user 用户对象
     * @return 0 - 添加成功 1 - 添加失败
     */
    Integer addUser(User user);

    /**
     * 修改用户信息
     *
     * @param user 用户对象
     * @return 1 - 修改成功 0 - 修改失败
     */
    Integer updateUser(User user);

    /**
     * 获取所有用户
     *
     * @param pageSize 页大小
     * @param pageNum  页数
     * @param keyword  关键词
     * @return List<User>
     */
    List<User> getAllUsers(Integer pageSize, Integer pageNum, String keyword);

    /**
     * 登录校验
     *
     * @param username       用户名
     * @param originPassword 明文密码
     * @return 1 - 成功 | -1 失败
     */
    Integer login(String username, String originPassword);

    /**
     * 用户注册
     *
     * @param user 用户对象
     * @return -4 密码不合法 | -3 - 邮箱已经被注册 | -2 - 注册失败 | -1 - 用户已经存在  | 1 - 成功
     */
    Integer register(User user);

    /**
     * 获取总数
     *
     * @param keyword 关键词
     * @return 总数
     */
    Integer getUserTotal(String keyword);

    /**
     * 通过用户名查找用户
     *
     * @param username 用户名
     * @return User
     */
    User findUserByUsername(String username);

    /**
     * 修改密码
     *
     * @param password 密码
     * @param username 用户名
     * @return 是否成功修改
     */
    Integer modifyPassword(String password, String username);
}
