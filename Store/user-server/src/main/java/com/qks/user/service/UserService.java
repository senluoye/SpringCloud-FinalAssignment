package com.qks.user.service;

import com.alibaba.fastjson.JSON;
import com.qks.common.VO.ResultVO;
import com.qks.common.exception.ServiceException;
import com.qks.feignclient.service.CartClient;
import com.qks.user.mapper.UserInfoMapper;
import com.qks.user.mapper.UserMapper;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import com.qks.common.helper.JWTUtils;
import com.qks.common.entity.*;
import org.springframework.transaction.annotation.Transactional;

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
public class UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private CartClient cartClient;

    /**
     * 用户登陆
     * @param name
     * @param password
     * @return
     * @throws ServiceException
     */
    public User login(String name,  String password) throws ServiceException {
        User user = userMapper.login(name, password);
        if (user == null) {
            throw new ServiceException("登陆失败");
        }
        Map<String,Object> info = new HashMap<>();
        info.put("name",name);
        info.put("userId",user.getId());
        String token =  JWTUtils.createToken(info);
        user.setToken(token);
//        userMapper.updateToken(user);
        return user;
    }

    /**
     * 用户注册
     * @param name
     * @param password
     * @return
     * @throws ServiceException
     */
    public User register(String name,  String password) throws ServiceException {
        if (name == null || "".equals(name)) {
            throw new ServiceException("缺少信息");
        }
        if (password == null || "".equals(password)) {
            throw new ServiceException("缺少信息");
        }

        List<User> userList = userMapper.selectByNameOrPhone(name);
        if (userList.size() > 0) {
            throw new ServiceException("信息已被使用");
        }

        String userId = UUID.randomUUID().toString();
        Map<String,Object> info = new HashMap<>();
        info.put("name",name);
        info.put("userId",userId);
        String token =  JWTUtils.createToken(info);

        User user = User.builder()
                .id(userId)
                .name(name)
                .password(password)
                .token(token)
                .build();

        userMapper.addUser(user);

//        添加用户信息
        UserInfo userInfo = UserInfo.builder()
                .firstName("None")
                .lastName("None")
                .sex("None")
                .age(0)
                .birthday("None")
                .description("这个人很懒什么都没留下~")
                .iconURL("None")
                .phone("None")
                .userId(user.getId())
                .userAccount(user.getName())
                .build();

        userInfoMapper.createUserInfo(userInfo);

//        添加购物车
        ResultVO<Boolean> result = cartClient.createCart(user.getId());
        if (!result.getData()) {
            throw new ServiceException("创建购物车失败");
        }

        return user;
    }

    public User selectById(String id) {
        return userMapper.selectById(id);
    }


}
