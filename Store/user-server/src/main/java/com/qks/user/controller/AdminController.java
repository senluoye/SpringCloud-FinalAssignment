package com.qks.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.qks.common.VO.ResultHelper;
import com.qks.common.VO.ResultVO;
import com.qks.common.exception.ServiceException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.qks.common.helper.JWTUtils;
import com.qks.common.entity.*;
import com.qks.user.service.AdminService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 15998
 */
@RestController
@RequestMapping("/user/admin")
public class AdminController {
    @Resource
    AdminService adminService;

    @RequestMapping(value = "/commodities", method = RequestMethod.GET)
    public ResultVO<List<Commodity>> getCommodities(@RequestHeader("token") String token) throws ServiceException {
        if (!JWTUtils.verify(token)) {
            throw new ServiceException("授权过期");
        }

        String userId = (String) JWTUtils.parser(token).get("userId");
        return ResultHelper.success(adminService.getCommoditiesByUserId(userId));
    }

    @RequestMapping(value = "commodities", method = RequestMethod.POST)
    public ResultVO<String> addCommodity(@RequestPart("file") MultipartFile file,
                                         @RequestPart("commodity") String str,
                                         @RequestHeader("token") String token) throws Exception {
        if (!JWTUtils.verify(token)) {
            return ResultHelper.error(-1, "授权过期");
        }
        Commodity good = JSONObject.parseObject(String.valueOf(str), Commodity.class);
        if (adminService.addCommodity(good, file)) {
            return ResultHelper.success("添加成功");
        } else {
            return ResultHelper.error(-1, "添加失败");
        }
    }

    @RequestMapping(value = "commodities", method = RequestMethod.DELETE)
    public ResultVO<String> delCommodity(@RequestBody JSONObject map,
                                         @RequestHeader("token") String token) {
        if (!JWTUtils.verify(token)) {
            return ResultHelper.error(-1, "授权过期");
        }

        String id = map.getString("commodityId");
        String userid = (String) JWTUtils.parser(token).get("userId");
        if (adminService.delCommodity(userid, id)) {
            return ResultHelper.success("删除成功");
        } else {
            return ResultHelper.error(-1, "删除失败");
        }
    }

    @RequestMapping(value = "commodities", method = RequestMethod.PUT)
    public ResultVO<String> modifyCommodity(@RequestBody JSONObject map,
                                            @RequestHeader("token") String token) throws ServiceException {
        if (!JWTUtils.verify(token)) {
            return ResultHelper.error(-1, "授权过期");
        }

        Commodity commodity = map.getObject("commodity", Commodity.class);
        if (adminService.updateCommodity(commodity)) {
            return ResultHelper.success("修改成功");
        } else {
            return ResultHelper.error(-1, "修改失败");
        }
    }
}
