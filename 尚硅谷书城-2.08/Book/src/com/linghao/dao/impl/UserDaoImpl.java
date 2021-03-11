package com.linghao.dao.impl;

import com.linghao.dao.UserDao;
import com.linghao.pojo.User;

/**
 * @author zoulinghao
 * @create 2021-01-30-14:55
 */
public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User queryUserByUserName(String username) {
        String sql="select * from t_user where username=?";

        return queryForOne(User.class,sql,username);
    }

    /**
     *
     * @param user
     * @return返回-1表示插入失败
     */
    @Override
    public int saveUser(User user) {
        String email = user.getEmail();
        String password = user.getPassword();
        String username = user.getUsername();
        String sql="INSERT INTO t_user(username,PASSWORD,email) VALUES(?,?,?)";
        return update(sql,username,password,email);
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql="select * from t_user where username=? and PASSWORD=?";
        return queryForOne(User.class,sql,username,password);
    }
}
