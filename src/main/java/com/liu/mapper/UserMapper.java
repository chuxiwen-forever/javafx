package com.liu.mapper;


import com.liu.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    User selectUserByIf(@Param("username") String username, @Param("password") String password);
    int insertUser(User user);
    List<User> selectAllUser();
    User selectAllUserById(@Param("id")String id);
    int updatePassword(@Param("username") String username ,@Param("password") String password);
    String selectPasswordByUsername(@Param("username") String username);
    Integer selectIdByUsername(@Param("username") String username);
}
