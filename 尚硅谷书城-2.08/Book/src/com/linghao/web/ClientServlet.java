package com.linghao.web;

import com.linghao.pojo.Book;
import com.linghao.pojo.Page;
import com.linghao.service.BookService;
import com.linghao.service.impl.BookServiceImpl;
import com.linghao.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zoulinghao
 * @create 2021-02-06-19:49
 */
public class ClientServlet extends BaseServlet{
    BookService bookService=new BookServiceImpl();
    protected void page(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        System.out.println("ClientServlet");

        //1.获取请求的参数pageNo 和pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        //2.调用BookService.page(pageNo,pageSize)
        Page<Book> page = bookService.page(pageNo, pageSize);

        page.setUrl("http://localhost:8080/Book/clientServlet?action=page");
        //3.保存Page对象到Request域当中
        req.setAttribute("page",page);
        //4.请求转发到/book_manager.jsp
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }
    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        System.out.println("ClientServlet");

        //1.获取请求的参数pageNo 和pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.parseInt(req.getParameter("min"), 0);
        int max = WebUtils.parseInt(req.getParameter("max"), Integer.MAX_VALUE);



        //2.调用BookService.page(pageNo,pageSize)
        Page<Book> page = bookService.pageByPrice(pageNo, pageSize,min,max);

        StringBuilder sb=new StringBuilder("http://localhost:8080/Book/clientServlet?action=pageByPrice");

        if (req.getParameter("min")!=null){
            sb.append("&min=").append(req.getParameter("min"));
        }
        if (req.getParameter("max")!=null){
            sb.append("&max=").append(req.getParameter("max"));
        }


        page.setUrl(sb.toString());
        //3.保存Page对象到Request域当中
        req.setAttribute("page",page);
        //4.请求转发到/book_manager.jsp
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }
}
