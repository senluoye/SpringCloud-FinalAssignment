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
public class Commodity implements Serializable {
    private String id;
    private String name;
    private String description;
    private Double price;
    private String cover;
    private String creatorId;
    private Integer num;
    private String createTime;
}
