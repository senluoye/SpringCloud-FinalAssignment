package com.qks.common.DTO;

import com.qks.common.entity.Coupon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-06-17 15:27
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CouponDTO {
    private String commodityName;
    private String commodityId;
    private String createTime;
    private Integer discountNumber;
    private Integer reductionNumber;
    List<Coupon> couponList;
}
