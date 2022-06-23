package com.qks.feignclient.service.backImpl;

import com.qks.common.VO.ResultHelper;
import com.qks.common.VO.ResultVO;
import com.qks.common.entity.Cart;
import com.qks.common.exception.ServiceException;
import com.qks.feignclient.service.CartClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-06-16 16:44
 */
@Slf4j
@Component
public class CartBackImpl implements CartClient {
    @Override
    public ResultVO<Boolean> createCart(String userId) {
        log.error("CartBackImpl createCart error, userId: {}", userId);
        return ResultHelper.error(-1, "CartBackImpl createCart error, userId: " + userId);
    }

    @Override
    public Cart selectUserCartByUserId(String userId) {
        log.error("CartBackImpl selectUserCartByUserId error, userId: {}", userId);
        return Cart.builder().build();
    }
}
