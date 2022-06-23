package com.qks.commodity.controller;


import com.qks.commodity.service.CommodityClientService;
import com.qks.common.DTO.CommodityDTO;
import com.qks.common.entity.Commodity;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-06-10 18:11
 */
@RestController
@RequestMapping("/commodity/client")
public class CommodityClientController {

    @Resource
    public CommodityClientService commodityClientService;

    @PostMapping("/commodityDTO/{addedCommodityId}")
    public CommodityDTO getCommodityDTOById(@PathVariable("addedCommodityId") String addedCommodityId){
        return commodityClientService.getCommodityDTOById(addedCommodityId);
    }

    @GetMapping("/commodities/{userId}")
    public List<Commodity> getCommoditiesByUserId(@PathVariable("userId") String userId) {
        return commodityClientService.getCommoditiesByUserId(userId);
    }

    @PostMapping("/commodities")
    public void addCommodity(@RequestBody Commodity commodity) {
        commodityClientService.addCommodity(commodity);
    }

    @PutMapping("/commodities")
    public int modifyCommodity(@RequestBody JSONObject jsonObject) {
        System.out.println(jsonObject);
        return commodityClientService.modifyCommodity(jsonObject);
    }

    @DeleteMapping("/commodities")
    public void deleteCommodity(@RequestBody JSONObject jsonObject) {
        String userId = jsonObject.getString("userId");
        String commodityId = jsonObject.getString("commodityId");
        commodityClientService.deleteCommodity(userId, commodityId);
    }
}
