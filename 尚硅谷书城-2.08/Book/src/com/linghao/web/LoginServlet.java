package com.linghao.web;

import com.linghao.pojo.User;
import com.linghao.service.UserService;
import com.linghao.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zoulinghao
 * @create 2021-01-30-19:49
 */
public class LoginServlet extends HttpServlet {
    private UserService userService=new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User login = userService.login(new User(null, username, password, null));
        if(login==null){
            req.setAttribute("msg","用户或密码错误");
            req.setAttribute("username",username);


            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }else{
            System.out.println("登录成功");
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }

    }
}
