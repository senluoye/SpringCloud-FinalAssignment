package com.qks.cart.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qks.cart.mapper.CartMapper;
import com.qks.common.DTO.CartDTO;
import com.qks.common.DTO.CommodityDTO;
import com.qks.common.entity.Cart;
import com.qks.common.exception.ServiceException;
import com.qks.feignclient.service.CommondityClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * @author 15998
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CartService {
    @Resource
    private CartMapper cartMapper;

    @Resource
    private CommondityClient commondityClient;

    public void  updateCartByCartId(JSONObject jsonObject) throws ServiceException {
        String cartId = jsonObject.getString("cartId");
        String commodityId = jsonObject.getString("commodityId");
        Integer number = jsonObject.getInteger("number");
        Double price = jsonObject.getDouble("price");
        String discountCouponId = jsonObject.getString("discountCouponId");

        // 获取用户的购物车
        CartDTO cart = cartMapper.selectById(cartId);
        // 获取当前添加的商品的信息
        CommodityDTO addedCommodityInfo = commondityClient.getCommodityDTOById(commodityId);
        // 获取购物车中的商品列表
        List<CommodityDTO> nowCommodityDTOList = JSON.parseArray(cart.getCommodities(), CommodityDTO.class);

        if (addedCommodityInfo == null) {
            throw new ServiceException("商品不存在");
        }

        int flag = 1;
        for (CommodityDTO commodityDTO : nowCommodityDTOList) {
            // 找到跟当前商品一样的商品 && 价格一样
            if (commodityDTO.getId().equals(commodityId) && commodityDTO.getPrice().equals(price)) {
                int count = commodityDTO.getNum() + number;
                commodityDTO.setNum(count);
                flag = 0;
                break;
            }
        }
        if (flag == 1) {
            CommodityDTO commodityDTO = new CommodityDTO();
            commodityDTO.setId(commodityId);
            commodityDTO.setName(addedCommodityInfo.getName());
            commodityDTO.setDescription(addedCommodityInfo.getDescription());
            commodityDTO.setCover(addedCommodityInfo.getCover());
            commodityDTO.setPrice(price);
            commodityDTO.setCreatorId(addedCommodityInfo.getCreatorId());
            commodityDTO.setNum(number);
            nowCommodityDTOList.add(commodityDTO);
        }
        cart.setCommodities(JSONObject.toJSONString(nowCommodityDTOList));

        if (cartMapper.update(cart) < 1) {
            throw new ServiceException("添加失败");
        }

        if (discountCouponId != null && cartMapper.updateCouponNumber(discountCouponId) < 1) {
            throw new ServiceException("使用优惠券失败");
        }
    }

    public Boolean deleteCartByCartId(String cartId, String deletedCommodityId) {
        CommodityDTO item = null;
        CartDTO nowCart = cartMapper.selectById(cartId);
        List<CommodityDTO> nowCommodityDTOList = JSON.parseArray(nowCart.getCommodities(), CommodityDTO.class);
        for (int i = 0; i < nowCommodityDTOList.size(); i++) {
            item = nowCommodityDTOList.get(i);
            if (item.getId().equals(deletedCommodityId)) {
                nowCommodityDTOList.remove(i);
                break;
            }
        }
        nowCart.setCommodities(JSONObject.toJSONString(nowCommodityDTOList));
        try {
            cartMapper.update(nowCart);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Boolean createNewCartByUserId(String userId) {
        try {
            Cart cart = cartMapper.selectByUserId(userId);
            if (cart != null) {
                return false;
            }
            Cart newCart = new Cart();
            newCart.setId(UUID.randomUUID().toString());
            newCart.setCreatorId(userId);
            newCart.setCreateTime(System.currentTimeMillis());
            newCart.setCommodities("[]");
            cartMapper.insert(newCart);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Boolean removeAllCartItem(String cartId) {
        CartDTO nowCart = cartMapper.selectById(cartId);
        try {
            nowCart.setCommodities("[]");
            cartMapper.update(nowCart);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
