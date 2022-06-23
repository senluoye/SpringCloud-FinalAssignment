package com.qks.coupon.controller;

import com.alibaba.fastjson.JSONObject;
import com.qks.common.DTO.CouponDTO;
import com.qks.common.entity.Coupon;
import com.qks.common.exception.ServiceException;
import com.qks.common.helper.JWTUtils;
import com.qks.common.helper.ResultHelper;
import com.qks.common.helper.ResultVO;
import com.qks.coupon.service.CouponService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.xml.transform.Result;
import java.util.List;

/**
 *
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-06-08 13:46
 */
@RestController
@RequestMapping("/coupon")
public class CouponController{

    @Resource
    private CouponService couponService;

    @RequestMapping("/list")
    public ResultVO<List<CouponDTO>> getCouponList(@RequestHeader("token") String token) throws ServiceException {
        if (!JWTUtils.verify(token)) {
            return ResultHelper.error(-1, "授权过期");
        }
        return ResultHelper.success(couponService.getCouponList());
    }

    @RequestMapping("/list/{id}")
    public ResultVO<List<List<Coupon>>> getCouponListByCommodity(@PathVariable("id") String commodityId,
                                                              @RequestHeader("token") String token) throws ServiceException {
        if (!JWTUtils.verify(token)) {
            return ResultHelper.error(-1, "授权过期");
        }
        return ResultHelper.success(couponService.getCouponListByCommodity(commodityId));
    }

    /**
     * @Description: 添加满减优惠卷
     * @param jsonObject
     * @param token
     * @return
     * @throws ServiceException
     * @throws com.qks.common.helper.ServiceException
     */
    @RequestMapping("/admin/add/reduce")
    public ResultVO<Boolean> addReduceCoupon(@RequestBody JSONObject jsonObject,
                                       @RequestHeader("token") String token) throws ServiceException, ServiceException {
        if (!JWTUtils.verify(token)) {
            return ResultHelper.error(-1, "授权过期");
        }
        couponService.addReduceCoupon(jsonObject);
        return ResultHelper.success(null);
    }

    /**
     * @Description: 添加打折优惠卷
     * @param jsonObject
     * @param token
     * @return
     * @throws ServiceException
     * @throws com.qks.common.helper.ServiceException
     */
    @RequestMapping("/admin/add/discount")
    public ResultVO<Boolean> addDiscountCoupon(@RequestBody JSONObject jsonObject,
                                       @RequestHeader("token") String token) throws ServiceException, ServiceException {
        if (!JWTUtils.verify(token)) {
            return ResultHelper.error(-1, "授权过期");
        }
        couponService.addDiscountCoupon(jsonObject);
        return ResultHelper.success(null);
    }
}
