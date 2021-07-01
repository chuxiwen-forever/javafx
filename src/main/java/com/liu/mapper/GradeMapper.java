package com.liu.mapper;

import com.liu.entity.Grade;
import com.liu.entity.MadeGrade;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeMapper {
    List<Grade> selectAllGrade(@Param("name")String name);
    Grade selectBeTrue(@Param("username")String username,@Param("textName")String textName);
    int insertGrade(MadeGrade grade);
}
