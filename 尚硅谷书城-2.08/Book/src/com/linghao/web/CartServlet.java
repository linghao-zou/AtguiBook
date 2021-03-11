package com.linghao.web;

import com.google.gson.Gson;
import com.linghao.pojo.Book;
import com.linghao.pojo.Cart;
import com.linghao.pojo.CartItem;
import com.linghao.service.BookService;
import com.linghao.service.impl.BookServiceImpl;
import com.linghao.utils.WebUtils;
import org.apache.taglibs.standard.tag.common.fmt.RequestEncodingSupport;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zoulinghao
 * @create 2021-02-07-18:40
 */
public class CartServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数 商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //根据商品编号 获取图书的信息
        Book book = bookService.queryBookByID(id);
        //bookService.queryBookById(id) book
        //把图书信息转换为CartItem商品项。
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        //调用Cart.addItem 添加商品项。
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }

        cart.addItem(cartItem);
        System.out.println(cart);
        req.getSession().setAttribute("lastName",cartItem.getName());
        //重定向回商品列表页面
        resp.sendRedirect(req.getHeader("Referer"));

    }
    protected void updateCount(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        //获取请求的参数 商品编号 商品数量
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        int count = WebUtils.parseInt(req.getParameter("count"), 1);
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart!=null){
            cart.updateCount(id,count);
            resp.sendRedirect(req.getHeader("Referer"));
        }

    }
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            //删除了购物车商品项
            cart.deleteItem(id);
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart!=null){
            cart.clear();
            resp.sendRedirect(req.getHeader("Referer"));
        }

    }

    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数 商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //根据商品编号 获取图书的信息
        Book book = bookService.queryBookByID(id);
        //bookService.queryBookById(id) book
        //把图书信息转换为CartItem商品项。
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        //调用Cart.addItem 添加商品项。
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }

        cart.addItem(cartItem);
        System.out.println(cart);
        req.getSession().setAttribute("lastName",cartItem.getName());
        //重定向回商品列表页面
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("totalCount",cart.getTotalCount());
        resultMap.put("lastName",cartItem.getName());
        Gson gson=new Gson();
        String resultMapJsonString = gson.toJson(resultMap);
        resp.getWriter().write(resultMapJsonString);


    }

}