package com.liu.mapper;

import com.liu.entity.TextPaper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TextPaperMapper {
    int insertTextPaper(TextPaper textPaper);
    int delTextPaper(@Param("name")String name);
    List<TextPaper> selectAllTextPaper();
    List<TextPaper> selectAllTextPaperByName(@Param("name") String name);
    Integer selectIdByName(@Param("name") String name);
}
