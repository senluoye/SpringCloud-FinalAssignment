package com.qks.feignclient.service.backImpl;

import com.qks.common.entity.Indent;
import com.qks.feignclient.service.IndentClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-06-10 20:10
 */
@Slf4j
@Component
public class IndentBackImpl implements IndentClient {

    @Override
    public List<Indent> selectUserIndentById(String userId) {
        log.error("IndentBackImpl selectUserIndentById error, userId: {}", userId);
        return null;
    }
}
