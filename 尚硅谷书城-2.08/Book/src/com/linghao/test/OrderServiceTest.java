package com.linghao.test;

import com.linghao.pojo.Cart;
import com.linghao.pojo.CartItem;
import com.linghao.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author zoulinghao
 * @create 2021-02-07-21:55
 */
public class OrderServiceTest {
    @Test
    public void createOrder(){
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"玲豪芜湖",1,new BigDecimal(30),new BigDecimal(1*30)));
        cart.addItem(new CartItem(1,"玲豪芜湖",1,new BigDecimal(30),new BigDecimal(1*30)));
        cart.addItem(new CartItem(2,"玲豪芜湖",1,new BigDecimal(30),new BigDecimal(1*30)));

        OrderServiceImpl orderService = new OrderServiceImpl();
        System.out.println("订单号是:"+orderService.createOrder(cart,1));


    }
}
