package com.linghao.dao.impl;

import com.linghao.dao.OrderItemDao;
import com.linghao.pojo.OrderItem;

/**
 * @author zoulinghao
 * @create 2021-02-07-21:41
 */
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql="insert into t_order_item(name,count,price,total_price,order_id) values(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }
}
