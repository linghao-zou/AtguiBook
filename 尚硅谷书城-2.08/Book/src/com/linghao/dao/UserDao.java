package com.linghao.dao;

import com.linghao.pojo.User;

/**
 * @author zoulinghao
 * @create 2021-01-30-14:52
 */
public interface UserDao {

    /**
     *根据用户名查询用户信息
     * @param username 用户名
     * @return  如果返回null 说明没有这个用户 反之亦然
     */
    public User queryUserByUserName(String username);

    /**
     * 保存用户信息
     * @param user
     * @return
     */
    public int saveUser(User user);

    /**
     * 根据用户名和密码查询用户信息
     * @param username
     * @param password
     * @return 返回null则用户名或者是密码错误
     */
    public User queryUserByUsernameAndPassword(String username,String password);

}
