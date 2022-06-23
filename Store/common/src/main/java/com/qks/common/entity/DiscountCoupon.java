package com.qks.common.entity;

import lombok.*;

/**
 * @ClassName Dessert
 * @Description 打折优惠券
 * @Author QKS
 * @Version v1.0
 * @Create 2022-06-17 15:53
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DiscountCoupon extends Coupon {
    private String id;
    private Double discount;
    private Integer number;
    private String createTime;
    private String commodityId;
}
