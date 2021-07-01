package com.liu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Common {
    private String u_number;
    private String u_username;
    private String t_paperName;
    private String t_teacher;
    private Integer grade;
}
