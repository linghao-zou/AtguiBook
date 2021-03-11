package com.linghao.service;

import com.linghao.pojo.Book;
import com.linghao.pojo.Page;

import java.util.List;

/**
 * @author zoulinghao
 * @create 2021-02-05-21:06
 */
public interface BookService {
    public void addBook(Book book);

    public void deleteBookById(Integer id);

    public void updateBook(Book book);

    public Book queryBookByID(Integer id);

    public List<Book> queryBooks();

    Page<Book> pageByPrice(int PageNo,int PageSize,int min,int max);

    Page<Book> page(int pageNo,int pageSize);

}
