package com.linghao.web;

import com.google.gson.Gson;
import com.linghao.pojo.User;
import com.linghao.service.UserService;
import com.linghao.service.impl.UserServiceImpl;
import com.linghao.utils.WebUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author zoulinghao
 * @create 2021-02-05-17:20
 */
public class UserServlet extends BaseServlet {
    private UserService userService=new UserServiceImpl();
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.销毁Session中的user信息
        //2.重定向到首页
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath());
    }

    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数 username
        String username = req.getParameter("username");
        //2.调用userService.existsUsername 来检验
        boolean existsUsername = userService.existsUsername(username);
        //把返回结果封装成map对象
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("existsUsername",existsUsername);
        Gson gson=new Gson();
        String json = gson.toJson(resultMap);
        resp.getWriter().write(json);
    }

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User login = userService.login(new User(null, username, password, null));
        if(login==null){
            req.setAttribute("msg","用户或密码错误");
            req.setAttribute("username",username);


            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }else{
            HttpSession session = req.getSession();
            session.setAttribute("user",login);


            System.out.println("登录成功");
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }

    }
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        User login= (User) WebUtils.copyParamToBean(req.getParameterMap(),new User());

        if (token!=null&&token.equalsIgnoreCase(code)){
            if (userService.existsUsername(username)) {


                req.setAttribute("msg","用户名已存在");
                req.setAttribute("username",username);
                req.setAttribute("email",email);

                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            }else{


                req.getSession().setAttribute("user",login);
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
