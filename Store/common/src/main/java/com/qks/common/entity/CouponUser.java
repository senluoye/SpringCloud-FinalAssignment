package com.qks.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-06-14 22:33
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CouponUser {
    private String id;
    private String couponId;
    private String userId;
    private String createTime;
}
