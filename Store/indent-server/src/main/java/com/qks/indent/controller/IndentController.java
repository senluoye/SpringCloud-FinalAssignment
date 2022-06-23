package com.qks.indent.controller;

import com.alibaba.fastjson.JSONObject;
import com.qks.common.exception.ServiceException;
import com.qks.common.helper.JWTUtils;
import com.qks.common.helper.ResultHelper;
import com.qks.common.helper.ResultVO;
import com.qks.common.entity.Indent;
import com.qks.indent.service.IndentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 15998
 */
@Controller
@RequestMapping("/indent")
public class IndentController {
    @Resource
    IndentService indentService;

    /**
     * 获取用户订单
     * @param token
     * @return
     */
    @ResponseBody
    @RequestMapping("")
    public ResultVO<List<Indent>> getUserIndents(@RequestHeader("token") String token) {
        if (!JWTUtils.verify(token)) {
            return ResultHelper.error(-1, "授权过期");
        }

        String userid = (String) JWTUtils.parser(token).get("userId");
        return ResultHelper.success(indentService.getIndentsByUserId(userid));
    }

    /**
     * 用户创建订单
     * @param map
     * @param token
     * @return
     * @throws ServiceException
     * @throws InterruptedException
     */
    @ResponseBody
    @RequestMapping("/create")
    public ResultVO<Boolean> createIndent(@RequestBody JSONObject map,
                                          @RequestHeader("token") String token) throws ServiceException, InterruptedException {
        if (!JWTUtils.verify(token)) {
            return ResultHelper.error(-1, "授权过期");
        }

        indentService.createIndent(map, token);
        return ResultHelper.success(true);
    }

    /**
     * 用户取消订单
     * @param map
     * @param token
     * @return
     */
    @ResponseBody
    @RequestMapping("/cancel")
    public ResultVO<Boolean> cancelIndent(@RequestBody JSONObject map,
                                          @RequestHeader("token") String token) {
        if (!JWTUtils.verify(token)) {
            return ResultHelper.error(-1, "授权过期");
        }
        String userid = (String) JWTUtils.parser(token).get("userId");
        String indentId = map.getString("indentId");
        if (indentService.cancelIdent(userid, indentId)) {
            return ResultHelper.success(true);
        } else {
            return ResultHelper.error(111, "订单取消失败");
        }
    }

    /**
     * 用户支付订单
     * @param map
     * @param token
     * @return
     * @throws ServiceException
     */
    @ResponseBody
    @RequestMapping("/modify")
    public ResultVO<Boolean> modifyIndent(@RequestBody JSONObject map,
                                          @RequestHeader("token") String token) throws ServiceException {
        if (!JWTUtils.verify(token)) {
            return ResultHelper.error(-1, "授权过期");
        }

        String indentId = map.getString("indentId");
        indentService.modifyIndentStatus(indentId);
        return ResultHelper.success(true);
    }
}
