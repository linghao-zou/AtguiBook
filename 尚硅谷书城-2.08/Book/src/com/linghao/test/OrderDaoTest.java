package com.linghao.test;

import com.linghao.dao.impl.OrderDaoImpl;
import com.linghao.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zoulinghao
 * @create 2021-02-07-21:43
 */
public class OrderDaoTest {
    @Test
    public void saveOrder(){
        OrderDaoImpl orderDao = new OrderDaoImpl();
        orderDao.saveOrder(new Order("123345",new Date(),new BigDecimal(100),0,1));


    }
}
