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
import java.math.BigDecimal;
import java.util.List;

/**
 * @author zoulinghao
 * @create 2021-02-05-21:22
 */
public class BookServlet extends BaseServlet {
    BookService bookService=new BookServiceImpl();

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0);
        pageNo+=1;
        String book_name = req.getParameter("book_name");
        String book_price = req.getParameter("book_price");
        String book_author = req.getParameter("book_author");
        String book_sales = req.getParameter("book_sales");
        String book_stock = req.getParameter("book_stock");
        Book book=new Book(null,book_name,book_author,new BigDecimal(book_price),new Integer(book_sales),new Integer(book_stock),null);

        bookService.addBook(book);
        resp.sendRedirect("http://localhost:8080/Book/bookServlet?action=page&pageNo="+pageNo);

    }
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        int i=Integer.parseInt(id);
        bookService.deleteBookById(i);
        resp.sendRedirect("http://localhost:8080/Book/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));

    }
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String book_name = req.getParameter("book_name");
        String book_price = req.getParameter("book_price");
        String book_author = req.getParameter("book_author");
        String book_sales = req.getParameter("book_sales");
        String book_stock = req.getParameter("book_stock");

        String id = req.getParameter("id");

        Book book=new Book(new Integer(id),book_name,book_author,new BigDecimal(book_price),new Integer(book_sales),new Integer(book_stock),null);
        bookService.updateBook(book);
        resp.sendRedirect("http://localhost:8080/Book/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));

    }
    protected void getBook(HttpServletRequest req,HttpServletResponse resp) throws  ServletException,IOException{
        String id = req.getParameter("id");

        Book book = bookService.queryBookByID(new Integer(id));
        req.setAttribute("book",book);

        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);

    }
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = bookService.queryBooks();
        req.setAttribute("books",books);
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);

    }
    protected void page(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        //1.获取请求的参数pageNo 和pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        //2.调用BookService.page(pageNo,pageSize)
        Page<Book> page = bookService.page(pageNo, pageSize);

        System.out.println(pageNo+"="+pageSize);

        page.setUrl("http://localhost:8080/Book/bookServlet?action=page");

        //3.保存Page对象到Request域当中
        req.setAttribute("page",page);
        //4.请求转发到/book_manager.jsp
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }

}
