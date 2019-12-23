package com.mixin.demo.ssm.service;

import com.mixin.demo.ssm.entity.UserDomain;

import java.util.List;

/**
 * Created by Donghua.Chen on 2018/7/25.
 */
public interface UserService {

    int insert(UserDomain record);

    void deleteUserById(Integer userId);

    void updateUser(UserDomain userDomain);

    List<UserDomain> selectUsers();

    UserDomain find(int uid);

    UserDomain selectById(int uid);


}
