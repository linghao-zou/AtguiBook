package com.linghao.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author zoulinghao
 * @create 2021-02-05-19:02
 */
public abstract class BaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        resp.setContentType("text/html;charset=UTF-8");

        Method method = null;
        try {
            method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        method.setAccessible(true);
        try {
            method.invoke(this,req,resp);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
