package com.linghao.dao.impl;

import com.linghao.dao.OrderDao;
import com.linghao.pojo.Order;

/**
 * @author zoulinghao
 * @create 2021-02-07-21:39
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        String sql="insert into t_order(order_id,create_time,price,status,user_id) values(?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());

    }
}
