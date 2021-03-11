package com.linghao.service.impl;

import com.linghao.dao.UserDao;
import com.linghao.dao.impl.UserDaoImpl;
import com.linghao.pojo.User;
import com.linghao.service.UserService;

/**
 * @author zoulinghao
 * @create 2021-01-30-18:15
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao=new UserDaoImpl();

    @Override
    public void registUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
       return userDao.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());

    }

    @Override
    public boolean existsUsername(String username) {
        User user = userDao.queryUserByUserName(username);
        if (user!=null) {
        return true;
        }
        return false;
    }
}
