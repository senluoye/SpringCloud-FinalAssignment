package com.qks.feignclient.service;

import com.alibaba.fastjson.JSONObject;
import com.qks.common.VO.ResultVO;
import com.qks.common.entity.Cart;
import com.qks.feignclient.service.backImpl.CartBackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-06-10 16:27
 */
@FeignClient(value = "cartservice", fallback = CartBackImpl.class)
public interface CartClient {
    /**
     * 创建购物车
     * @param jsonObject
     * @param token
     * @return
     */
    @PostMapping("/cart/client/cart/create")
    ResultVO<Boolean> createCart(@RequestBody String userId);

    /**
     * 查询用户购物车
     * @param userId
     * @return
     */
    @GetMapping("/cart/client/cart/{userId}")
    Cart selectUserCartByUserId(@PathVariable("userId") String userId);
}
