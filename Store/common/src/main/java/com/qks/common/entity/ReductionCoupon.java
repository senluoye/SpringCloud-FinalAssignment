package com.qks.common.entity;

import lombok.*;

/**
 * @ClassName Dessert
 * @Description 满减优惠券
 * @Author QKS
 * @Version v1.0
 * @Create 2022-06-17 15:53
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReductionCoupon extends Coupon {
    private String id;
    private Integer number;
    /**
     * 满足金额
     */
    private Integer count;
    /**
     * 减少金额
     */
    private Integer reduce;
    private String createTime;
}
