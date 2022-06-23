package com.qks.commodity.mapper;

import com.qks.common.DTO.CommodityDTO;
import com.qks.common.entity.Commodity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommodityMapper {

    @Select("SELECT count(*) FROM commodity")
    Integer getAllCount();

    @Select("SELECT * FROM commodity LIMIT #{map.pageSize} OFFSET #{map.recordIndex}")
    List<Commodity> getPageCommodity(@Param("map") Map<String, Object> map);

    @Select("SELECT * FROM commodity WHERE id = #{id}")
    Commodity getDetailCommodityById(@Param("id") String id);

    @Select("SELECT * FROM commodity WHERE id = #{id}")
    CommodityDTO getCommodityDTOById(@Param("id") String id);

    @Insert("INSERT INTO commodity(id,name,price,description,cover, creatorId, num) " +
            "VALUES(#{id}, #{name}, #{price}, #{description}, #{cover}, #{creatorId}, #{num})")
    Integer addCommodity(Commodity commodity);

}
