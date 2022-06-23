package com.qks.user.service;

import com.alibaba.fastjson.JSONObject;
import com.qks.common.exception.ServiceException;
import com.qks.common.helper.JWTUtils;
import com.qks.feignclient.service.CartClient;
import com.qks.feignclient.service.CommondityClient;
import com.qks.feignclient.service.IndentClient;
import com.qks.user.mapper.UserInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.qks.common.entity.*;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 15998
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class UserInfoService {
    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private CommondityClient commondityClient;

    @Resource
    private CartClient cartClient;

    @Resource
    private IndentClient indentClient;

    public UserInfo getUserInfoByToken(String token) {
        String userId = JWTUtils.parser(token).get("userId").toString();
//        String userId = userInfoMapper.selectUserIdByToken(token);
//        if (userId == null) {
//            return null;
//        }
        return userInfoMapper.selectUserInfoById(userId);
    }

    public List<Indent> getIndentList(String token) {
        String userId = userInfoMapper.selectUserIdByToken(token);
        if (userId == null) {
            return null;
        }
        return indentClient.selectUserIndentById(userId);
    }

    public Cart getCart(String token) throws ServiceException {
        String userid = userInfoMapper.selectUserIdByToken(token);

        Cart data = cartClient.selectUserCartByUserId(userid);
        if (data.getId() == null) {
            log.error("获取购物车失败:" + userid);
            throw new ServiceException("获取购物车失败");
        }
        return data;
    }

    public List<Address> getAddressList(String token) {
        String userid = userInfoMapper.selectUserIdByToken(token);
        if (userid == null) {
            return null;
        }
        return userInfoMapper.selectUserAddressById(userid);
    }

    public List<Commodity> getFavorCommodityList(String token) {
        String userid = userInfoMapper.selectUserIdByToken(token);
        if (userid == null) {
            return null;
        }
        return commondityClient.getCommoditiesByUserId(userid);
    }


    public void updateUserInfo(JSONObject json) {
        UserInfo userInfo = new UserInfo();
        userInfo.setFirstName(json.getString("firstName"));
        userInfo.setLastName(json.getString("lastName"));
        userInfo.setSex(json.getString("sex"));
        userInfo.setAge(Integer.valueOf(json.getString("age")));
        userInfo.setBirthday(json.getString("birthday"));
        userInfo.setDescription(json.getString("description"));
        userInfo.setIconURL(json.getString("iconURL"));
        userInfo.setPhone(json.getString("phone"));
        userInfo.setUserId(json.getString("userId"));
        userInfo.setUserAccount(json.getString("userAccount"));

        userInfoMapper.updateUserInfo(userInfo);
    }
}
