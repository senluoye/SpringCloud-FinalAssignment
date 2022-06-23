package com.qks.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 15998
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo implements Serializable {
    private String id;
    private String firstName;
    private String lastName;
    private String sex;
    private Integer age;
    private String birthday;
    private String description;
    private String iconURL;
    private String phone;
    private String userId;
    private String userAccount;
    private String createTime;
}
