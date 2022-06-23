package com.qks.indent.mapper;

import com.qks.common.entity.Indent;
import com.qks.common.entity.ReductionCoupon;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface IndentMapper {

    @Select("SELECT * FROM indent WHERE creator_id = #{userid}")
    List<Indent> getIndentsByUserId(String userid);

    @Update("UPDATE indent SET status=#{status} WHERE id=#{id}")
    Integer updateIndentByIndentId(@Param("id") String id, @Param("status") int status);

    @Insert("INSERT INTO indent(id, creator_id, commodities, price) " +
            "VALUES (#{id}, #{creatorId}, #{commodities}, #{price})")
    Integer createIdent(Indent item);

    @Select("SELECT * FROM indent WHERE id = #{id}")
    Indent getIndentByIndentId(@Param("id") String id);

    @Delete("DELETE FROM indent WHERE id = #{id}")
    void deleteIndentByIndentId(@Param("id") String id);

    @Select("SELECT * FROM reduction_coupon WHERE id = #{id}")
    ReductionCoupon getReductionCouponById(@Param("id") String id);

    @Update("update reduction_coupon set number = number - 1 where id = #{id}")
    Integer updateCouponStatus(@Param("id") String id);
}
