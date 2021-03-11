package com.linghao.service;

import com.linghao.pojo.Cart;

/**
 * @author zoulinghao
 * @create 2021-02-07-21:48
 */
public interface OrderService {
    public String createOrder(Cart cart,Integer userId);
}
