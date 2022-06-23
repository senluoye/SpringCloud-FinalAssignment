package com.qks.cart.mapper;

import com.qks.common.DTO.CartDTO;
import com.qks.common.entity.Cart;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author 15998
 */
@Mapper
public interface CartClientMapper {

    @Select("SELECT * FROM cart where creatorId = #{id}")
    Cart selectUserCartByUserId(String id);

    @Insert("INSERT INTO cart (id, creatorId, createTime, commodities) " +
            "VALUES (#{id}, #{creatorId}, #{createTime}, #{commodities})")
    Boolean createCart(Cart cart);

}
