package com.qks.cart.controller;

import com.alibaba.fastjson.JSONObject;
import com.qks.common.exception.ServiceException;
import com.qks.common.helper.JWTUtils;
import com.qks.common.helper.ResultHelper;
import com.qks.common.helper.ResultVO;
import com.qks.cart.service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 15998
 */
@RestController
@RequestMapping("/cart")
public class CartController {
    @Resource
    private CartService cartService;

    /**
     * 更新新购物车信息
     *         传输用户id、购物车id以及新的商品列表信息
     *         即可向数据库中更新
     *         {
     *             userId, cartId, commodities: [
     *                 {id, name, description, price, number},
     *                 {id, name, description, price, number},
     *                 ......
     *             ]
     *         }
     * @param jsonObject
     * @param token
     * @return
     */
    @PostMapping("/add")
    public ResultVO<String> addToCart(@RequestBody JSONObject jsonObject,
                                      @RequestHeader("token") String token) throws ServiceException {
        // 检验 token
        if (!JWTUtils.verify(token)) {
            return ResultHelper.error(-1, "授权过期");
        }

        cartService.updateCartByCartId(jsonObject);
        return ResultHelper.success("添加成功");
    }

    /**
     * @Description: 删除用户选中的商品
     * @Params: [jsonObject, token]
     * @Return: com.qks.store.helper.ResultVO<com.qks.store.DTO.CartDTO>
     * @Author: 韦俊宇
     * @Date: 12/21/2021
     */
    @PostMapping("/delete")
    public ResultVO<String> deleteToCart(@RequestBody JSONObject jsonObject,
                                         @RequestHeader("token") String token) {
        // 检验 token
        if (!JWTUtils.verify(token)) {
            return ResultHelper.error(-1, "授权过期");
        }
        String cartId = jsonObject.getString("cartId");
        String deletedCommodityId = jsonObject.getString("commodityId");

        if (cartService.deleteCartByCartId(cartId, deletedCommodityId)) {
            return ResultHelper.success("删除成功");
        } else {
            return ResultHelper.error(-1, "删除失败");
        }
    }

    @PostMapping("/removeAll")
    public ResultVO<String> removeAllItemInCart(@RequestBody JSONObject jsonObject,
                                                @RequestHeader("token") String token) {
        if (!JWTUtils.verify(token)) {
            return ResultHelper.error(-1, "授权过期");
        }

        String cartId = jsonObject.getString("cartId");
        if (cartService.removeAllCartItem(cartId)) {
            return ResultHelper.success("清空购物车成功");
        } else {
            return ResultHelper.error(-1, "清空购物车失败");
        }
    }

    /**
     * @Description: 一般来说，用户只能拥有一个购物车，如果用户没有购物车的话，就给他新建一个购物车
     * @Params: [jsonObject, token]
     * @Return: com.qks.store.helper.ResultVO<java.lang.String>
     * @Author: 韦俊宇
     * @Date: 12/21/2021
     */
    @PostMapping("/create")
    public ResultVO<String> createCart(@RequestBody JSONObject jsonObject,
                                       @RequestHeader("token") String token) {
        // 检验 token
        if (!JWTUtils.verify(token)) {
            return ResultHelper.error(-1, "授权过期");
        }

        // 给对应 userId 的用户新建购物车
        String userId = jsonObject.getString("userId");
        if (cartService.createNewCartByUserId(userId)) {
            return ResultHelper.success("创建购物车成功");
        } else {
            return ResultHelper.error(-1, "创建失败");
        }
    }
}
