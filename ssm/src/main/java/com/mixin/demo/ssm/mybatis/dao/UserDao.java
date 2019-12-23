package com.mixin.demo.ssm.mybatis.dao;

import com.mixin.demo.ssm.mybatis.entity.UserDomain;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserDao {

    int insert(UserDomain record);

    void deleteUserById(@Param("userId") Integer userId);

    void updateUser(UserDomain userDomain);

    List<UserDomain> selectUsers();

    @Select("select * from t_user where user_id = #{uid}")
    public UserDomain find(int uid);

}
