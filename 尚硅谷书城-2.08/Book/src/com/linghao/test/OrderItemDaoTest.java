package com.linghao.test;

import com.linghao.dao.impl.OrderItemDaoImpl;
import com.linghao.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author zoulinghao
 * @create 2021-02-07-21:45
 */
public class OrderItemDaoTest {
    @Test
    public void saveOrderItem(){
        OrderItemDaoImpl orderItemDao = new OrderItemDaoImpl();
        orderItemDao.saveOrderItem(new OrderItem(null,"java",1,new BigDecimal(100),new BigDecimal(100),"123345"));
        orderItemDao.saveOrderItem(new OrderItem(null,"linghao",1,new BigDecimal(200),new BigDecimal(100),"123345"));
        orderItemDao.saveOrderItem(new OrderItem(null,"bisheng",1,new BigDecimal(200),new BigDecimal(200),"123345"));
    }
}
