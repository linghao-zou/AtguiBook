package com.linghao.test;

import com.linghao.dao.UserDao;
import com.linghao.dao.impl.UserDaoImpl;
import com.linghao.pojo.User;
import org.junit.Test;

/**
 * @author zoulinghao
 * @create 2021-01-30-15:00
 */
public class UserTest {
    @Test
    public void ByName(){
        UserDaoImpl userDao = new UserDaoImpl();
       if (userDao.queryUserByUserName("admin")==null){
           System.out.println("用户名可用");
       }else{
           System.out.println("用户名已存在");
       }
    }
    @Test
    public void ByPasswordByName(){
        UserDaoImpl userDao = new UserDaoImpl();
        if(userDao.queryUserByUsernameAndPassword("admin","adminpassword")==null){
            System.out.println("用户名和密码错误");
        }else{
            System.out.println("登陆成功");
        }
    }
    @Test
    public void SaveUser(){
        User linghao = new User(null,"ling", "1993112", "5625043@qq.com");
        UserDaoImpl userDao = new UserDaoImpl();
        int i = userDao.saveUser(linghao);


    }
}
