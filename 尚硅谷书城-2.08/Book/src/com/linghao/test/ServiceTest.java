package com.linghao.test;

import com.linghao.pojo.User;
import com.linghao.service.UserService;
import com.linghao.service.impl.UserServiceImpl;
import org.junit.Test;

/**
 * @author zoulinghao
 * @create 2021-01-30-18:18
 */
public class ServiceTest {
    private UserService userService=new UserServiceImpl();
    private User ling=new User(null,"ahao","88888888","47196612@qq.com");
    @Test
    public void registUser(){
        userService.registUser(ling);
    }
    @Test
    public void loginUser(){
        System.out.println(userService.login(new User()));
    }
    @Test
    public void exsitsUsername(){
        if(userService.existsUsername("ling")){
            System.out.println("用户名已存在");
        }else{
            System.out.println("用户名不可用");
        }
    }
}
