package com.qks.commodity.service;

import com.qks.commodity.mapper.CommodityClientMapper;
import com.qks.common.DTO.CommodityDTO;
import com.qks.common.entity.Commodity;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 15998
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CommodityClientService {

    @Resource
    private CommodityClientMapper commodityClientMapper;

    public CommodityDTO getCommodityDTOById(String id) {
        return commodityClientMapper.getCommodityDTOById(id);
    }

    public List<Commodity> getCommoditiesByUserId(String userId) {
        return commodityClientMapper.getCommoditiesByUserId(userId);
    }

    public void addCommodity(Commodity commodity) {
        commodityClientMapper.addCommodity(commodity);
    }

    public Integer modifyCommodity(JSONObject jsonObject) {
        Commodity commodity = Commodity.builder()
                .id(jsonObject.getString("id"))
                .name(jsonObject.getString("name"))
                .description(jsonObject.getString("description"))
                .price(jsonObject.getDouble("price"))
                .num(jsonObject.getInt("num"))
                .creatorId(jsonObject.getString("creatorId"))
                .build();
        return commodityClientMapper.modifyCommodity(commodity);
    }

    public void deleteCommodity(String userId, String commodityId) {
        commodityClientMapper.deleteCommodity(userId, commodityId);
    }
}
