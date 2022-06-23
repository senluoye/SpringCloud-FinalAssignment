package com.qks.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.qks.common.VO.ResultHelper;
import com.qks.common.VO.ResultVO;
import com.qks.common.exception.ServiceException;
import org.springframework.web.bind.annotation.*;
import com.qks.common.helper.JWTUtils;
import com.qks.common.entity.*;
import com.qks.user.service.UserInfoService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 15998
 */
@RestController
@RequestMapping("/user/info")
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;

    @GetMapping("")
    public ResultVO<UserInfo> selectUserInfoById(@RequestHeader("token") String token) {
        if (!JWTUtils.verify(token)) {
            return ResultHelper.error(-1, "授权过期");
        }

        try {
            UserInfo userInfo = userInfoService.getUserInfoByToken(token);
            if (userInfo == null) {
                return ResultHelper.error(-1, "未找到用户数据");
            }
            return ResultHelper.success(userInfo);
        } catch (Exception e) {
            return ResultHelper.error(-1, "错误的用户名");
        }
    }

    @PostMapping("")
    public ResultVO<String> modifyUserInfoById(@RequestBody JSONObject json) {
        try {
            userInfoService.updateUserInfo(json);
            return ResultHelper.success("修改成功");
        } catch (Exception e) {
            return ResultHelper.error(-1, "错误的用户名");
        }
    }

    /**
     * @Description: 获取用户订单列表
     * @Params: [token]
     * @Author: 谢浚霖
     * @Date: 12/21/2021
     */
    @GetMapping("/indentList")
    public ResultVO<List<Indent>> selectIndentList(@RequestHeader("token") String token) {

        if (!JWTUtils.verify(token)) {
            return ResultHelper.error(-1, "授权过期");
        }

        try {
            List<Indent> indentList = userInfoService.getIndentList(token);
            if (indentList == null) {
                return ResultHelper.error(-1, "请检查token是否正确");
            }
            return ResultHelper.success(indentList);
        } catch (Exception e) {
            return ResultHelper.error(-1, "错误的用户名");
        }
    }

    /**
     * @Description: 获取用户购物车列表
     * @Params: [token]
     * @Return: com.qks.store.helper.ResultVO<java.util.List < model.Cart>>
     * @Author: 谢浚霖
     * @Date: 12/21/2021
     */
    @GetMapping("/cart")
    public ResultVO<Cart> selectCartList(@RequestHeader("token") String token) throws ServiceException {
        if (!JWTUtils.verify(token)) {
            return ResultHelper.error(-1, "授权过期");
        }

        Cart cartList = userInfoService.getCart(token);
        return ResultHelper.success(cartList);
    }

    /**
     * @Description: 获取收藏的商品
     * @Params: [token]
     * @Return: com.qks.store.helper.ResultVO<java.util.List < model.Commodity>>
     * @Author: 谢浚霖
     * @Date: 12/21/2021
     */
    @GetMapping("/favorCommodity")
    public ResultVO<List<Commodity>> selectFavorCommodity(@RequestHeader("token") String token) {

        if (!JWTUtils.verify(token)) {
            return ResultHelper.error(-1, "授权过期");
        }

        try {
            List<Commodity> commodityList = userInfoService.getFavorCommodityList(token);
            if (commodityList == null) {
                return ResultHelper.error(-1, "请检查token是否正确");
            }
            return ResultHelper.success(commodityList);
        } catch (Exception e) {
            return ResultHelper.error(-1, "错误的用户名");
        }
    }

    /**
     * @Description: 获取收货地址列表
     * @Params: [token]
     * @Return: com.qks.store.helper.ResultVO<java.util.List < model.Address>>
     * @Author: 谢浚霖
     * @Date: 12/21/2021
     */
    @GetMapping("/addressList")
    public ResultVO<List<Address>> selectAddressList(@RequestHeader("token") String token) {

        if (!JWTUtils.verify(token)) {
            return ResultHelper.error(-1, "授权过期");
        }

        try {
            List<Address> addressList = userInfoService.getAddressList(token);
            if (addressList == null) {
                return ResultHelper.error(-1, "请检查token是否正确");
            }
            return ResultHelper.success(addressList);
        } catch (Exception e) {
            return ResultHelper.error(-1, "错误的用户名");
        }
    }

}
