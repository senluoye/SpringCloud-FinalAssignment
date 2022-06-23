package com.qks.cart.service;

import com.alibaba.fastjson.JSONObject;
import com.qks.cart.mapper.CartClientMapper;
import com.qks.common.entity.Cart;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-06-10 18:13
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CartClientService {

    @Resource
    private CartClientMapper cartClientMapper;

    public Cart selectUserCartByUserId(String userId) {
        return cartClientMapper.selectUserCartByUserId(userId);
    }

    public Boolean createCart(String userId) {
        Cart cart = Cart.builder()
                .id(UUID.randomUUID().toString())
                .creatorId(userId)
                .createTime(System.currentTimeMillis())
                .commodities("[]")
                .build();
        System.out.println(cart);
        return cartClientMapper.createCart(cart);
    }
}
