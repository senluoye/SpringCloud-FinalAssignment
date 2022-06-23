package com.qks.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.qks.common.entity.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user WHERE id=#{id}")
    User selectById(String id);

    @Select("INSERT INTO user (id, name,  password, token) VALUES(#{id}, #{name}, #{password}, #{token})")
    User addUser(User user);

    @Select("SELECT * FROM user WHERE name=#{name} ")
    List<User> selectByNameOrPhone(@Param("name") String name);

    @Select("SELECT * FROM user WHERE (name=#{name} AND password=#{password}) ")
    User login(@Param("name") String name,  @Param("password") String password);

    @Update("UPDATE user SET token=#{token} WHERE id=#{id}")
    void updateToken(User user);
}
