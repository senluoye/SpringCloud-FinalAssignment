package com.qks.commodity.controller;


import com.qks.common.DTO.PagesDTO;
import com.qks.common.exception.ServiceException;
import com.qks.common.helper.JWTUtils;
import com.qks.common.helper.ResultHelper;
import com.qks.common.helper.ResultVO;
import com.qks.common.entity.Commodity;
import com.qks.commodity.service.CommodityService;
import kotlin.Result;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/commodity")
public class CommodityController {

    String uploadPath = "F:\\AllStudy\\ForStudy\\微服务\\image\\";

    @Resource
    CommodityService commodityService;

    int PAGESIZE = 12;

    @RequestMapping("/list")
    public ResultVO<PagesDTO<Commodity>> getCommoditiesList(@RequestParam("page") Integer page) {
        return ResultHelper.success(commodityService.getPagesCommodity(this.PAGESIZE, page));
    }

    @RequestMapping("/detail")
    public ResultVO<Commodity> getCommodityDetail(@RequestParam("id") String id) throws Exception {
        Commodity good = commodityService.getDetailById(id);
        if (good == null) {
            throw new Exception("该商品不存在");
        }
        return ResultHelper.success(good);
    }

    @RequestMapping(value = "/images/{id}", method = RequestMethod.GET, produces = "image/jpg")
    private byte[] getPicture(@PathVariable(name = "id") String id) throws IOException {
        File file = new File("F:\\AllStudy\\ForStudy\\微服务\\image" + id);
        FileInputStream inputstream = new FileInputStream(file);
        byte[] bytes = new byte[inputstream.available()];
        inputstream.read(bytes, 0, inputstream.available());
        inputstream.close();
        return bytes;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    private ResultVO<Object> addCommodity(HttpServletRequest httpServletRequest,
                                          @RequestPart("commodity") Commodity commodity,
                                          @RequestPart("file") MultipartFile file) throws IOException, ServiceException {
        String token = httpServletRequest.getHeader("token");
        if (!JWTUtils.verify(token)) {
            return ResultHelper.error(-1, "授权过期");
        }
        String userId = (String) JWTUtils.parser(token).get("userId");
        commodity.setCreatorId(userId);
        commodityService.addCommodity(commodity, file);
        return ResultHelper.success(null);
    }

    /**
     * 获取图片
     * @param id
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {MediaType.IMAGE_JPEG_VALUE,
            MediaType.IMAGE_PNG_VALUE,
            MediaType.IMAGE_GIF_VALUE})
    public byte[] getImage(@PathVariable("id") String id) throws IOException {
        File file = new File(uploadPath + id);
        System.out.println(file.getAbsolutePath());
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes, 0, inputStream.available());
        inputStream.close();
        return bytes;
    }
}
