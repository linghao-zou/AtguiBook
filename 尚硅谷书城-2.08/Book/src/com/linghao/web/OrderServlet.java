package com.linghao.web;

import com.linghao.pojo.Cart;
import com.linghao.pojo.User;
import com.linghao.service.OrderService;
import com.linghao.service.impl.OrderServiceImpl;
import com.linghao.utils.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zoulinghao
 * @create 2021-02-07-21:57
 */
public class OrderServlet extends BaseServlet{
    private OrderService orderService=new OrderServiceImpl();
    /**
     * 生成订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //先获取Cart购物车对象
        Cart cart= (Cart) req.getSession().getAttribute("cart");
        //获取userId
        User loginUser = (User) req.getSession().getAttribute("user");

        if (loginUser==null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }

        Integer userId = loginUser.getId();
        //调动orderService.createOrder(Cart,userid)

        String orderId = null;
        orderId = orderService.createOrder(cart, userId);

        req.setAttribute("orderId",orderId);
        req.getSession().setAttribute("orderId",orderId);

        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");

    }
}
