package com.qks.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.qks.common.VO.ResultHelper;
import com.qks.common.VO.ResultVO;
import com.qks.common.exception.ServiceException;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;
import com.qks.common.helper.JWTUtils;
import com.qks.common.entity.*;
import com.qks.user.service.UserService;

import javax.annotation.Resource;

/**
 * @author 15998
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/login")
    public ResultVO<User> login(@RequestBody JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String password = jsonObject.getString("password");
        try {
            User user = userService.login(name, password);
            return ResultHelper.success(user);
        } catch (ServiceException e) {
            return ResultHelper.error(-1, e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResultVO<User> register(@RequestBody JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String password = jsonObject.getString("password");
        try {
            User user = userService.register(name, password);
            return ResultHelper.success(user);
        } catch (ServiceException e) {
            return ResultHelper.error(-1, e.getMessage());
        }
    }

    @GetMapping("/checkToken")
    public ResultVO<String> check(@Param("token") String token){
        System.out.println("??");
        if(JWTUtils.verify(token)){
            return ResultHelper.success("token有效");
        }else{
            return ResultHelper.error(-100,"token失效");
        }
    }
}
