package com.qks.feignclient.service.backImpl;

import com.qks.common.DTO.CommodityDTO;
import com.qks.common.entity.Commodity;
import com.qks.common.exception.ServiceException;
import com.qks.feignclient.service.CommondityClient;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-06-10 16:32
 */
@Slf4j
@Component
public class CommondityBackImpl implements CommondityClient {

    @Override
    public CommodityDTO getCommodityDTOById(String addedCommodityId) {
        log.error("CommondityBackImpl getCommodityDTOById error, addedCommodityId: {}", addedCommodityId);
        return null;
    }

    @Override
    public List<Commodity> getCommoditiesByUserId(String userId) {
        log.error("CommondityBackImpl getCommoditiesByUserId error, userId: {}", userId);
        return null;
    }

    @Override
    public void addCommodity(Commodity commodity) {
        log.error("CommondityBackImpl addCommodity error, commodity: {}", commodity);
    }

    @Override
    public void modifyCommodity(JSONObject jsonObject) {
        log.error("CommondityBackImpl modifyCommodity error, jsonObject: {}", jsonObject);
    }

    @Override
    public void deleteCommodity(JSONObject jsonObject) {
        log.error("CommondityBackImpl deleteCommodity error, jsonObject: {}", jsonObject);
    }
}
