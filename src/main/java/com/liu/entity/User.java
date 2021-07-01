package com.liu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String u_username;  //姓名
    private String u_password;//密码
    private String u_sex;  //性别
    private String u_number;//学号
}
