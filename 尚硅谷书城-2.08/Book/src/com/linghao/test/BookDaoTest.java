package com.linghao.test;

import com.linghao.dao.BookDao;
import com.linghao.dao.impl.BookDaoImpl;
import com.linghao.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author zoulinghao
 * @create 2021-02-05-20:59
 */
public class BookDaoTest {
    private BookDao bookDao=new BookDaoImpl();
    @Test
    public void addBook(){
        int linghao = bookDao.addBook(new Book(null, "阿豪为什么这么帅", "191125", new BigDecimal(9999), 1000, 0, null));
        System.out.println(linghao);

    }
    @Test
    public void deleteBookByID(){
        int i = bookDao.deleteBookById(54);
        System.out.println(i);
    }
    @Test
    public void updateBook(){
        int linghao = bookDao.updateBook(new Book(22, "linghao", "12234", new BigDecimal(8888), 12134, 0, null));
        System.out.println(linghao);

    }
    @Test
    public void queryBookByid(){
        Book book = bookDao.queryBookByID(22);
        System.out.println(book);
    }
    @Test
    public void quertLists(){
        List<Book> books = bookDao.queryBooks();
        Iterator<Book> iterator = books.iterator();
        while(iterator.hasNext()){
            Book next = iterator.next();
            System.out.println(next);
        }
    }
    @Test
    public void queryCount() {
        int i = bookDao.queryForPageTotal();
        System.out.println(i);
    }
    @Test
    public void queryForItem(){
        List<Book> books = bookDao.queryForItems(0, 4);
        System.out.println(Arrays.asList(books));
    }
}
