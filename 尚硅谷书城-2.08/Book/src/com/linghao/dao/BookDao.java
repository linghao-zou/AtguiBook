package com.linghao.dao;

import com.linghao.pojo.Book;

import java.util.List;

/**
 * @author zoulinghao
 * @create 2021-02-05-20:50
 */
public interface BookDao {
    public int addBook(Book book);

    public int deleteBookById(Integer id);

    public int updateBook(Book book);

    public Book queryBookByID(Integer id);

    public List<Book> queryBooks();

    public int queryForPageTotal();

    public List<Book> queryForItems(int begin,int pageSize);

    int queryForPageTotalCountByPrice(int min, int max);

    List<Book> queryForItemsByPrice(int begin, int pageSize, int min, int max);
}
