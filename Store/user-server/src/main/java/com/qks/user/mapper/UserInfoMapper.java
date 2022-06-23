package com.qks.user.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.qks.common.entity.*;

import java.util.List;

@Mapper
public interface UserInfoMapper {

    @Select("SELECT id FROM user where token = #{token}")
    String selectUserIdByToken(String token);

    @Select("SELECT * FROM userinfo where userid = #{id}")
    UserInfo selectUserInfoById(String id);

    @Select("SELECT * FROM address where creatorId = #{id}")
    List<Address> selectUserAddressById(String id);

    @Update("UPDATE userinfo SET firstName = #{firstName}, " +
            "lastName = #{lastName}, sex = #{sex}, age = #{age}, birthday = #{birthday}, " +
            "description = #{description}, iconURL = #{iconURL}, phone = #{phone}, userAccount = #{userAccount} where userid = #{userId}")
    void updateUserInfo(UserInfo userInfo);

    @Insert("INSERT INTO userinfo (id,firstName,lastName,sex,age,birthday,description,iconURL,phone,userid,userAccount) " +
            "values(default,#{firstName},#{lastName},#{sex},#{age},#{birthday},#{description},#{iconURL},#{phone},#{userId},#{userAccount})")
    void createUserInfo(UserInfo userInfo);

}
