package com.qks.common.DTO;

import com.qks.common.entity.Commodity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CommodityDTO extends Commodity {
    private Integer number;
}
