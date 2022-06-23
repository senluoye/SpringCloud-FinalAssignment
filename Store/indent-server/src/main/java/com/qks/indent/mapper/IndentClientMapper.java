package com.qks.indent.mapper;

import com.qks.common.entity.Indent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-06-10 20:08
 */
@Mapper
public interface IndentClientMapper {

    @Select("SELECT * FROM indent where creatorId = #{id}")
    List<Indent> selectUserIndentById(String id);

}
