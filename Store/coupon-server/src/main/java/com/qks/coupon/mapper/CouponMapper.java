package com.qks.coupon.mapper;

import com.qks.common.DTO.CouponDTO;
import com.qks.common.PO.CouponTemp;
import com.qks.common.entity.Coupon;
import com.qks.common.entity.DiscountCoupon;
import com.qks.common.entity.ReductionCoupon;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-06-14 22:39
 */
@Mapper
public interface CouponMapper {

    @Select("select * from coupon where commodity_id = #{commodityId}")
    Coupon getCouponByCommodityId(String commodityId);


    @Select("select coupon.id id, commodity_id, coupon.create_time createTime, name commodityName " +
            "from coupon, commodity " +
            "where commodity_id = commodity.id")
    List<CouponTemp> getCouponList();

    /**
     * 获取优惠券
     * @param commodity_id
     * @param discount
     * @return
     */
    @Select("select * from discount_coupon where commodity_id = #{commodity_id} and discount = #{discount}")
    List<DiscountCoupon> getDisCountCouponByState(@Param("commodity_id") String commodity_id, @Param("discount") Double discount);

    @Select("select * from discount_coupon where commodity_id = #{commodityId}")
    List<DiscountCoupon> getDiscountCoupon(String commodityId);

    @Select("select * from reduction_coupon order by create_time")
    List<ReductionCoupon> getReductionCoupon();

    @Select("select * " +
            "from reduction_coupon " +
            "where count = #{count} " +
            "and reduce = #{reduce}")
    ReductionCoupon getReductionCouponByState(@Param("count") Integer count, @Param("reduce") Integer reduce);

    @Insert("insert into coupon (id, commodity_id) " +
            "values (#{id}, #{commodityId}) ")
    Integer addCoupon(Coupon coupon);

    @Insert("insert into discount_coupon(id, discount, commodity_id, number) " +
            "values (#{id}, #{discount}, #{commodityId}, #{number})")
    Integer addDisCountCoupon(DiscountCoupon discountCoupon);

    @Insert("insert into reduction_coupon(id, count, reduce, number) " +
            "values (#{id}, #{count}, #{reduce}, #{number})")
    Integer addReduceCoupon(ReductionCoupon reductionCoupon);

    /**
     * I will be with you from dust till down
     * @param discountCoupon
     * @return
     */
    @Update("update discount_coupon " +
            "set number = #{number} " +
            "where discount = #{discount}")
    Integer updateDisCountCoupon(DiscountCoupon discountCoupon);

    @Update("update reduction_coupon " +
            "set number = #{number} " +
            "where count = #{count} " +
            "and reduce = #{reduce}")
    Integer updateReduceCoupon(ReductionCoupon reductionCoupon);
}
