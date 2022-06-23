package com.qks.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Principal;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-06-14 22:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coupon {
    private String id;
    private String createTime;
}
