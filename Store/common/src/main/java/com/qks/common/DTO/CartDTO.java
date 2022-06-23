package com.qks.common.DTO;

import com.qks.common.entity.Cart;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author 15998
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CartDTO extends Cart {
    private List<CommodityDTO> commodityList;
}
