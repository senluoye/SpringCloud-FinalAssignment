package com.qks.commodity.service;

import com.qks.commodity.mapper.CommodityMapper;
import com.qks.common.DTO.CommodityDTO;
import com.qks.common.DTO.PagesDTO;
import com.qks.common.entity.Commodity;
import com.qks.common.exception.ServiceException;
import com.qks.common.utils.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author 15998
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CommodityService {
    String uploadPath = "F:\\AllStudy\\ForStudy\\微服务\\image\\";

    @Resource
    CommodityMapper commodityMapper;

    public PagesDTO<Commodity> getPagesCommodity(Integer pageSize, Integer pageIndex) {

        Integer recordIndex = (pageIndex - 1)*pageSize;
        Integer totalRecord = commodityMapper.getAllCount();

        Integer totalPage = (totalRecord+pageSize-1)/pageSize;

        Map<String,Object> map = new HashMap<>();

        map.put("pageSize",pageSize);
        map.put("recordIndex",recordIndex);
        List<Commodity> data = commodityMapper.getPageCommodity(map);
        return new PagesDTO<Commodity>(totalPage,pageIndex,data);
    }

    public Commodity getDetailById(String id) {
        return commodityMapper.getDetailCommodityById(id);
    }

    public CommodityDTO getCommodityDTOById(String id) {
        return commodityMapper.getCommodityDTOById(id);
    }

    public void addCommodity(Commodity commodity, MultipartFile file) throws ServiceException {
        try {
            String newName = FileUtils.upload(file, uploadPath);
            commodity.setCover("/commodity/" + newName);
        } catch (Exception e) {
            throw new ServiceException("营业执照未上传");
        }

        commodity.setId(UUID.randomUUID().toString());
        commodityMapper.addCommodity(commodity);
    }
}
