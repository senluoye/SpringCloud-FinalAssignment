package com.qks.indent.controller;

import com.qks.common.entity.Indent;
import com.qks.indent.service.IndentClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-06-10 19:21
 */
@RestController
@RequestMapping("/indent/client")
public class IndentClientController {

    @Resource
    private IndentClientService indentClientService;

    @GetMapping("/indents/{userId}")
    List<Indent> selectUserIndentById(@PathVariable("userId") String userId){
        return indentClientService.selectUserIndentById(userId);
    }
}
