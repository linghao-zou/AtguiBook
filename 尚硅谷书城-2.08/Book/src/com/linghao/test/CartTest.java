package com.linghao.test;

import com.linghao.pojo.Cart;
import com.linghao.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author zoulinghao
 * @create 2021-02-07-18:17
 */
public class CartTest {
    @Test
    public void addItem(){
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"玲豪芜湖",1,new BigDecimal(30),new BigDecimal(1*30)));
        cart.addItem(new CartItem(1,"玲豪芜湖",1,new BigDecimal(30),new BigDecimal(1*30)));
        cart.addItem(new CartItem(2,"玲豪芜湖",1,new BigDecimal(30),new BigDecimal(1*30)));
        System.out.println(cart);



        cart.updateCount(1,3);
        System.out.println(cart);
    }
}
