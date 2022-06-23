package com.qks.feignclient.service;

import com.qks.common.DTO.CommodityDTO;
import com.qks.common.entity.Commodity;
import org.json.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-06-10 16:32
 */
@FeignClient(value = "commodityservice")
public interface CommondityClient {

    @PostMapping("/commodity/client/commodityDTO/{id}")
    CommodityDTO getCommodityDTOById(@PathVariable("id") String addedCommodityId);

    @GetMapping("/commodity/client/commodities/{userId}")
    List<Commodity> getCommoditiesByUserId(@PathVariable("userId") String userId);

    @PostMapping("/commodity/client/commodities")
    void addCommodity(@RequestBody Commodity commodity);

    @PutMapping("/commodity/client/commodities")
    void modifyCommodity(@RequestBody JSONObject jsonObject);

    @DeleteMapping("/commodity/client/commodities")
    void deleteCommodity(@RequestBody JSONObject jsonObject);
}
