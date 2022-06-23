package com.qks.feignclient.service;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-06-10 17:54
 */
@FeignClient("couponservice")
public interface CouponClient {
}
