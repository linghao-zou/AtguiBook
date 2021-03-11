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
 * @create 2021-01-30-18:44
 */
public class RegistServlet extends HttpServlet {

    private UserService userService=new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        if ("abcde".equalsIgnoreCase(code)){
          if (userService.existsUsername(username)) {

            req.setAttribute("msg","用户名已存在");
            req.setAttribute("username",username);
            req.setAttribute("email",email);

            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
          }else{
              userService.registUser(new User(null,username,password,email));
              req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
          }
        }else{
            //把回显的信息添加到Request域当中
            req.setAttribute("msg","邮箱错误");
            req.setAttribute("username",username);
            req.setAttribute("email",email);


        req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }
    }
}
