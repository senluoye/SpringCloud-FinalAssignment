package com.qks.user.service;

import com.alibaba.fastjson.JSON;
import com.qks.common.exception.ServiceException;
import com.qks.feignclient.service.CommondityClient;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.qks.common.helper.FileHelper;
import com.qks.common.entity.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional(rollbackFor = Exception.class)
public class AdminService {

    @Resource
    private CommondityClient commondityClient;

    static String filePath = "../src/main/resources/static/stc/images/";

    public List<Commodity> getCommoditiesByUserId(String userid) {
        List<Commodity> result = commondityClient.getCommoditiesByUserId(userid);
        System.out.println(result.size());
        return result;
    }

    public boolean addCommodity(Commodity commodity, MultipartFile file) throws Exception {
        String fileName = file.getOriginalFilename();
        String path =  FileHelper.uploadFile(file.getBytes(), AdminService.filePath, fileName);
        commodity.setId(UUID.randomUUID().toString());
        commodity.setCover(path);
        commondityClient.addCommodity(commodity);
        return true;
    }

    public boolean updateCommodity(Commodity commodity) throws ServiceException {
        Map<String, Object> map = new HashMap<>();
        map.put("id", commodity.getId());
        map.put("name", commodity.getName());
        map.put("description", commodity.getDescription());
        map.put("price", commodity.getPrice());
        map.put("num", commodity.getNum());
        map.put("creatorId", commodity.getCreatorId());
        JSONObject jsonObject = new JSONObject(map);
        System.out.println(jsonObject);
        commondityClient.modifyCommodity(jsonObject);
        return true;
    }

    public boolean delCommodity(String userId, String commodityId) {
        try {
            HashMap<String, Object> map = new HashMap<>();
            map.put("userId", userId);
            map.put("commodityId", commodityId);
            JSONObject jsonObject = new JSONObject(map);
            commondityClient.deleteCommodity(jsonObject);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
