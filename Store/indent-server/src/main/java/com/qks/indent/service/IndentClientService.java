package com.qks.indent.service;

import com.qks.common.entity.Indent;
import com.qks.indent.mapper.IndentClientMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-06-10 20:08
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class IndentClientService {

    @Resource
    private IndentClientMapper indentClientMapper;

    public List<Indent> selectUserIndentById(String userId) {
        return indentClientMapper.selectUserIndentById(userId);
    }
}
