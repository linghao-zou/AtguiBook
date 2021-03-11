package com.linghao.test;

import com.linghao.pojo.Page;
import com.linghao.service.impl.BookServiceImpl;
import com.linghao.pojo.Book;
import com.linghao.service.BookService;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author zoulinghao
 * @create 2021-02-05-21:11
 */
public class BookServiceTest {
    private BookService bookService=new BookServiceImpl();
    @Test
    public void addBook(){
        bookService.addBook(new Book(null,"阿豪","123214",new BigDecimal(1234),123,123,null));

    }
    @Test
    public void updateBook(){
        bookService.updateBook(new Book(24,"；omgjap","123",new BigDecimal(2134),12345,32145,null));

    }
    @Test
    public void queryBookByID(){
        Book book = bookService.queryBookByID(24);
        System.out.println(book);
    }
    @Test
    public void queryBooks(){
        List<Book> books = bookService.queryBooks();
        for (Book book:
             books) {
            System.out.println(book);
        }
    }
    @Test
    public void deleteByID(){
        bookService.deleteBookById(24);
    }
    @Test
    public void page(){
        Page<Book> page = bookService.page(1, 4);
        System.out.println(page);
    }
}
