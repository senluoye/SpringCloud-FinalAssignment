package com.qks.indent.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qks.common.entity.Indent;
import com.qks.common.entity.ReductionCoupon;
import com.qks.common.exception.ServiceException;
import com.qks.common.helper.JWTUtils;
import com.qks.indent.mapper.IndentMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
public class IndentService {

    @Resource
    private IndentMapper indentMapper;

    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 获取用户的订单信息
     * @param userId
     * @return
     */
    public List<Indent> getIndentsByUserId(String userId) {
        return indentMapper.getIndentsByUserId(userId);
    }

    /**
     * 支付
     * @param indentId
     * @return
     * @throws ServiceException
     */
    public boolean modifyIndentStatus(String indentId) throws ServiceException {
        if (indentMapper.updateIndentByIndentId(indentId, 1) < 1) {
            throw new ServiceException("支付失败");
        }
        return true;
    }

    /**
     * 创建订单
     *
     * @param map
     * @param token
     * @throws ServiceException
     * @throws InterruptedException
     */
    public void createIndent(JSONObject map, String token) throws ServiceException, InterruptedException {
        String userId = (String) JWTUtils.parser(token).get("userId");
        JSONArray commodities = map.getJSONArray("commodities");
        String couponId = map.getString("couponId");
        ReductionCoupon reductionCoupon = indentMapper.getReductionCouponById(couponId);
        Double price = 0.0;

        for (int i = 0; i < commodities.size(); i++) {
            JSONObject commodity = commodities.getJSONObject(i);
            price += commodity.getDouble("price") * commodity.getInteger("num");
        }
        if (price < reductionCoupon.getCount()) {
            throw new ServiceException("满" + reductionCoupon.getCount() + "元才能使用优惠券");
        }

        price -= reductionCoupon.getReduce();

        Indent item = Indent.builder()
                .id(UUID.randomUUID().toString())
                .creatorId(userId)
                .commodities(commodities.toString())
                .price(price)
                .build();

        if (indentMapper.createIdent(item) < 1 || indentMapper.updateCouponStatus(couponId) < 1) {
            throw new ServiceException("创建失败");
        }

        rabbitTemplate.convertAndSend("order.event.exchange", "order.create", item.getId());
    }

    /**
     * 取消订单
     * @param userid
     * @param indentId
     * @return
     */
    public boolean cancelIdent(String userid, String indentId) {
        boolean res = true;
        try {
            Indent item = indentMapper.getIndentByIndentId(indentId);
            if (item.getStatus() == 0) {
                indentMapper.updateIndentByIndentId(indentId, -1);
            } else {
                res = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            res = false;
        }
        return res;
    }
}
