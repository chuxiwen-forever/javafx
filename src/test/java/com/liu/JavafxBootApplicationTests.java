package com.liu;


import com.liu.entity.*;
import com.liu.mapper.CommonMapper;
import com.liu.mapper.GradeMapper;
import com.liu.mapper.TextPaperMapper;
import com.liu.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JavafxBootApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TextPaperMapper textPaperMapper;
    @Autowired
    private CommonMapper commonMapper;
    @Autowired
    private GradeMapper gradeMapper;
    @Test
    public void contextLoads() {
    }

}
