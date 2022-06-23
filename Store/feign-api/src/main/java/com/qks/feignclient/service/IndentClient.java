package com.qks.feignclient.service;

import com.qks.common.entity.Indent;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-06-10 20:10
 */
@FeignClient(value = "indentservice")
public interface IndentClient {

    @GetMapping("/indent/client/indents/{userId}")
    List<Indent>  selectUserIndentById(@PathVariable("userId") String userId);
}
