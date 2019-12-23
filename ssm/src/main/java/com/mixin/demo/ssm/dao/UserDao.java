package com.mixin.demo.ssm.dao;

import com.mixin.demo.ssm.entity.UserDomain;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//@Mapper
public interface UserDao {

    int insert(UserDomain record);

    void deleteUserById(@Param("userId") Integer userId);

    void updateUser(UserDomain userDomain);

    List<UserDomain> selectUsers();

    UserDomain selectById(int uid);

    @Select("select * from t_user where user_id = #{uid}")
    public UserDomain find(int uid);

}
