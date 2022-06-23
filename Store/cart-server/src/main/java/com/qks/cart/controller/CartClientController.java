package com.qks.cart.controller;

import com.qks.cart.service.CartClientService;
import com.qks.common.VO.ResultHelper;
import com.qks.common.VO.ResultVO;
import com.qks.common.entity.Cart;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-06-10 18:11
 */
@RestController
@RequestMapping("/cart/client")
public class CartClientController {

    @Resource
    private CartClientService cartClientService;

    @GetMapping("/cart/{userId}")
    Cart selectUserCartByUserId(@PathVariable("userId") String userId) {
        return cartClientService.selectUserCartByUserId(userId);
    }

    @PostMapping("/cart/create")
    ResultVO<Boolean> createCart(@RequestBody String userId) {
        Boolean result = cartClientService.createCart(userId);
        return ResultHelper.success(result);
    }
}
