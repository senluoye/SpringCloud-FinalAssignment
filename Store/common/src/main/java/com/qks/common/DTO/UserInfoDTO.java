package com.qks.common.DTO;

import com.qks.common.entity.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserInfoDTO extends UserInfo {
    private List<Indent> indentList;
    private List<Cart> cartList;
    private List<Commodity> favorCommodity;
    private List<Address> addressList;
}
