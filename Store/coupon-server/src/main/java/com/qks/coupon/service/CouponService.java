package com.qks.coupon.service;

import com.alibaba.fastjson.JSONObject;
import com.qks.common.DTO.CouponDTO;
import com.qks.common.PO.CouponTemp;
import com.qks.common.entity.Coupon;
import com.qks.common.entity.DiscountCoupon;
import com.qks.common.entity.ReductionCoupon;
import com.qks.common.exception.ServiceException;
import com.qks.coupon.mapper.CouponMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-06-14 22:38
 */
@Service
public class CouponService {

    @Resource
    private CouponMapper couponMapper;

    /**
     * 获取优惠券列表
     * @return
     */
    public List<CouponDTO> getCouponList() {
        List<CouponDTO> result = new ArrayList<>();

        List<ReductionCoupon> reductionCoupon = couponMapper.getReductionCoupon();
        result.add(CouponDTO.builder()
                .commodityId("0xxx")
                .createTime(reductionCoupon.get(0).getCreateTime())
                .commodityName("满减券")
                .couponList(new ArrayList<>(reductionCoupon))
                .build());

        List<CouponTemp> couponList= couponMapper.getCouponList();
        for (CouponTemp couponTemp : couponList) {
            List<DiscountCoupon> discountCoupon = couponMapper.getDiscountCoupon(couponTemp.getCommodityId());
            List<Coupon> coupon = new ArrayList<>(discountCoupon);

            result.add(CouponDTO.builder()
                    .commodityId(couponTemp.getCommodityId())
                    .createTime(couponTemp.getCreateTime())
                    .commodityName(couponTemp.getCommodityName())
                    .couponList(coupon)
                    .build());
        }

        return result;
    }

    /**
     * 获取某个商品的优惠券
     * @return
     */
    public List<List<Coupon>> getCouponListByCommodity(String commodityId) {
        List<ReductionCoupon> reductionCoupon = couponMapper.getReductionCoupon();
        List<List<Coupon>> result = new ArrayList<>();
        result.add(new ArrayList<>(reductionCoupon));
        result.add(new ArrayList<>(couponMapper.getDiscountCoupon(commodityId)));
        return result;
    }

    /**
     * 增加折扣优惠卷
     * @param jsonObject
     */
    public void addDiscountCoupon(JSONObject jsonObject) throws ServiceException {
        String commodityId = jsonObject.getString("commodityId");
        Double discount = jsonObject.getDouble("discount");
        Integer number = jsonObject.getInteger("number");

        Coupon oldCoupon = couponMapper.getCouponByCommodityId(commodityId);
        List<DiscountCoupon> oldDiscountCoupon = couponMapper.getDisCountCouponByState(commodityId, discount);

        Coupon coupon = new Coupon();
        coupon.setId(UUID.randomUUID().toString());

        DiscountCoupon discountCoupon = DiscountCoupon.builder()
                .id(UUID.randomUUID().toString())
                .commodityId(commodityId)
                .discount(discount)
                .number(number)
                .build();
        System.out.println(discountCoupon);
        if (oldCoupon == null) {
            if (couponMapper.addCoupon(coupon) < 1 || couponMapper.addDisCountCoupon(discountCoupon) < 1) {
                throw new ServiceException("添加优惠券失败");
            }
        } else {
            if (oldDiscountCoupon.size() != 0) {
                // 从已有的优惠券中找到相同的折扣，更新数量
                for (DiscountCoupon dc : oldDiscountCoupon) {
                    if (dc.getDiscount().equals(discount)) {
                        dc.setNumber(dc.getNumber() + number);
                        if (couponMapper.updateDisCountCoupon(dc) < 1) {
                            throw new ServiceException("更新优惠券失败");
                        }
                    }
                }
            } else {
                if (couponMapper.addDisCountCoupon(discountCoupon) < 1) {
                    throw new ServiceException("添加优惠券失败");
                }
            }
        }
    }

    /**
     * 增加减免优惠卷
     * @param jsonObject
     * @return
     * @throws ServiceException
     */
    public void addReduceCoupon(JSONObject jsonObject) throws ServiceException {
        Integer number = jsonObject.getInteger("number");
        Integer count = jsonObject.getInteger("count");
        Integer reduce = jsonObject.getInteger("reduce");

        if (reduce > number) {
            throw new ServiceException("减免金额不能大于优惠券数量");
        }

        ReductionCoupon oldReductionCoupon = couponMapper.getReductionCouponByState(count, reduce);
        ReductionCoupon reductionCoupon = ReductionCoupon.builder()
                .id(UUID.randomUUID().toString())
                .count(count)
                .reduce(reduce)
                .number(number)
                .build();

        // 有该类型的就修改数目，没有就新建
        if (oldReductionCoupon == null) {
            if (couponMapper.addReduceCoupon(reductionCoupon) < 1) {
                throw new ServiceException("添加优惠券失败");
            }
        } else {
            oldReductionCoupon.setNumber(oldReductionCoupon.getNumber() + number);
            if (couponMapper.updateReduceCoupon(oldReductionCoupon) < 1) {
                throw new ServiceException("更新优惠券失败");
            }
        }
    }
}
