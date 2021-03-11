package com.linghao.service;

import com.linghao.pojo.User;

/**
 * @author zoulinghao
 * @create 2021-01-30-18:13
 */
public interface UserService {
    /**
     * 注册用户
     * @param user
     */
    public void registUser(User user);

    /**
     * 登录
     * @param user
     */
    public User login(User user);

    /**
     * 检查用户名是否可用
     * @param username
     * @return 返回true表示可用 false表示不可用
     */
    public boolean existsUsername(String username);
}
