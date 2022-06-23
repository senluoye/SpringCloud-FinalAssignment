package com.qks.commodity.mapper;

import com.qks.common.DTO.CommodityDTO;
import com.qks.common.entity.Commodity;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author 15998
 */
@Mapper
public interface CommodityClientMapper {

    @Select("SELECT * FROM commodity WHERE id = #{id}")
    CommodityDTO getCommodityDTOById(@Param("id") String id);

    @Select("SELECT * FROM commodity WHERE creatorId = #{id}")
    List<Commodity> getCommoditiesByUserId(@Param("id") String userId);

    @Insert("INSERT INTO commodity VALUES (#{id},#{name},#{description},#{price},#{cover},#{creatorId},#{num})")
    void addCommodity(Commodity good);

    @Update("UPDATE commodity SET name=#{name}, description=#{description}, num = #{num}, price=#{price} " +
            "WHERE id=#{id} AND creatorId = #{creatorId}")
    Integer modifyCommodity(Commodity commodity);

    @Delete("DELETE FROM commodity WHERE id=#{id} AND creatorId = #{creatorId}")
    void deleteCommodity(@Param("creatorId") String userid, @Param("id") String commodityId);
}
