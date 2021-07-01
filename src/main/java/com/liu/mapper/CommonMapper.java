package com.liu.mapper;

import com.liu.entity.Common;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommonMapper {
    List<Common> selectAllMessage();
    List<Common> selectMessageById(@Param("name")String name);
}
