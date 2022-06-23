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
public interface CartMapper {

    @Update("update discount_coupon set number = number - 1 where id = #{id} and number > 0")
    Integer updateCouponNumber(String id);

    @Update("UPDATE cart SET commodities = #{commodities} WHERE id = #{id}")
    Integer update(Cart cart);

    @Select("SELECT * FROM cart WHERE id=#{id}")
    CartDTO selectById(String id);

    @Select("SELECT * FROM cart WHERE creatorId = #{userId}")
    Cart selectByUserId(String userId);

    @Insert("INSERT INTO cart (id, creatorId, createTime, commodities) " +
            "VALUES (#{id}, #{creatorId}, #{createTime}, #{commodities})")
    void insert(Cart cart);
}
