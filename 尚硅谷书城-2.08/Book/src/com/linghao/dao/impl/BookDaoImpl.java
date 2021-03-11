package com.linghao.dao.impl;

import com.linghao.dao.BookDao;
import com.linghao.pojo.Book;

import java.util.List;

/**
 * @author zoulinghao
 * @create 2021-02-05-20:52
 */
public class BookDaoImpl extends BaseDao implements BookDao  {
    @Override
    public int addBook(Book book) {
        String sql="insert into t_book(name,author,price,sales,stock,img_path) values(?,?,?,?,?,?)";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath());

    }

    @Override
    public int deleteBookById(Integer id) {

        String sql="delete from t_book where id=?";
        return update(sql,id);

    }

    @Override
    public int updateBook(Book book) {

        String sql="update t_book set name=?,author=?,price=?,sales=?,stock=?,img_path=? where id=?";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath(),book.getId());

    }

    @Override
    public Book queryBookByID(Integer id) {
        String sql="select id,name,author,price,sales,stock,img_path from t_book where id=?";

        return queryForOne(Book.class,sql,id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql="select id,name,author,price,sales,stock,img_path from t_book ";
        return queryForList(Book.class,sql);

    }

    @Override
    public int queryForPageTotal() {
        String sql="select count(*) from t_book";
        return ((Number) queryForSingleValue(sql)).intValue();
    }

    @Override
    public List<Book> queryForItems(int begin,int pageSize) {
        String sql="select id,name,author,price,sales,stock,img_path from t_book limit ?,?";
        return queryForList(Book.class,sql,begin,pageSize);
    }

    @Override
    public int queryForPageTotalCountByPrice(int min, int max) {
        String sql="select count(*) from t_book where price between ? and ?";
        return ((Number)queryForSingleValue(sql,min,max)).intValue();
    }

    @Override
    public List<Book> queryForItemsByPrice(int begin, int pageSize, int min, int max) {
        String sql="select id,name,author,price,sales,stock,img_path from t_book where price between ? and ? limit ?,?";
        return queryForList(Book.class,sql,min,max,begin,pageSize);
    }
}
