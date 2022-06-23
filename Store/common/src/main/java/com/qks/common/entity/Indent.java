package com.qks.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 15998
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Indent implements Serializable {
    private String id;
    /**
     * -1:已取消，0:未支付，1:已支付
     */
    private Integer status;
    private String createTime;
    private String creatorId;
    private String commodities;
    private Double price;
}
