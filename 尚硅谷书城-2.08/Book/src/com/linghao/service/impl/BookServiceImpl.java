package com.linghao.service.impl;

import com.linghao.dao.BookDao;
import com.linghao.dao.impl.BookDaoImpl;
import com.linghao.pojo.Book;
import com.linghao.pojo.Page;
import com.linghao.service.BookService;

import java.util.List;

/**
 * @author zoulinghao
 * @create 2021-02-05-21:09
 */
public class BookServiceImpl implements BookService {
    private BookDao bookDao=new BookDaoImpl();

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookByID(Integer id) {
        return bookDao.queryBookByID(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> page=new Page<>();

        page.setPageSize(pageSize);

        int pageTotalCount = bookDao.queryForPageTotalCountByPrice(min,max);
        page.setPageTotalCount(pageTotalCount);
        Integer pageTotal=pageTotalCount/pageSize;
        if (pageTotalCount%pageSize>0){
            pageTotal++;
        }

        page.setPageTotal(pageTotal);

        if (pageNo<1){
            pageNo=1;
        }else if (pageNo>pageTotal){
            pageNo=pageTotal;
        }

        page.setPageNo(pageNo);

        List<Book> books = bookDao.queryForItemsByPrice((pageNo-1)*pageSize,pageSize,min,max);
        page.setItems(books);

        return page;
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page=new Page<>();

        page.setPageSize(pageSize);

        int pageTotalCount = bookDao.queryForPageTotal();
        page.setPageTotalCount(pageTotalCount);
        Integer pageTotal=pageTotalCount/pageSize;
        if (pageTotalCount%pageSize>0){
            pageTotal++;
        }

        page.setPageTotal(pageTotal);

        if (pageNo<1){
            pageNo=1;
        }else if (pageNo>pageTotal){
            pageNo=pageTotal;
        }

        page.setPageNo(pageNo);

            List<Book> books = bookDao.queryForItems((pageNo-1)*pageSize,pageSize);
        page.setItems(books);

        return page;
    }
}
