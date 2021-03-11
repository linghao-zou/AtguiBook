package com.linghao.service.impl;

import com.linghao.dao.BookDao;
import com.linghao.dao.OrderDao;
import com.linghao.dao.OrderItemDao;
import com.linghao.dao.impl.BookDaoImpl;
import com.linghao.dao.impl.OrderDaoImpl;
import com.linghao.dao.impl.OrderItemDaoImpl;
import com.linghao.pojo.*;
import com.linghao.service.OrderService;

import java.util.Date;
import java.util.Map;

/**
 * @author zoulinghao
 * @create 2021-02-07-21:48
 */
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao=new OrderDaoImpl();
    private OrderItemDao orderDaoItem=new OrderItemDaoImpl();
    private BookDao bookDao=new BookDaoImpl();
    @Override
    public String createOrder(Cart cart, Integer userId) {
        //订单号==唯一性
        String orderId=System.currentTimeMillis()+""+userId;
        //创建一个订单对象
        Order order=new Order(orderId,new Date(),cart.getTotalPrice(),0,userId);
        //保存订单
        orderDao.saveOrder(order);

//        int i=12/0;

        //遍历购物车当中每一个商品项 转换成订单项保存到数据库
        for (Map.Entry<Integer, CartItem> entry:
        cart.getItems().entrySet()) {
            //获取每一个购物车当中的商品项
            CartItem cartItem = entry.getValue();
            //转化为每一个订单项
            OrderItem orderItem=new OrderItem(null,cartItem.getName(), cartItem.getCount(), cartItem.getPrice(),cartItem.getTotalPrice(),orderId);
            //将订单项保存到数据库
            orderDaoItem.saveOrderItem(orderItem);

            Book book = bookDao.queryBookByID(cartItem.getId());
            book.setSales(book.getSales()+cartItem.getCount());
            book.setStock(book.getStock()-cartItem.getCount());
            bookDao.updateBook(book);

        }
        cart.clear();

        return orderId;
    }
}
