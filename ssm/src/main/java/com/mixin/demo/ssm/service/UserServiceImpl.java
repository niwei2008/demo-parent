package com.mixin.demo.ssm.service;

import com.mixin.demo.ssm.mybatis.dao.UserDao;
import com.mixin.demo.ssm.mybatis.entity.UserDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Donghua.Chen on 2018/7/25.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;//这里会爆红，请忽略

    @Override
    public int insert(UserDomain record) {
        return userDao.insert(record);
    }


    @Override
    public void deleteUserById(Integer userId) {
        userDao.deleteUserById(userId);
    }

    @Override
    public void updateUser(UserDomain userDomain) {
        userDao.updateUser(userDomain);
    }

    @Override
    public List<UserDomain> selectUsers() {
        return userDao.selectUsers();
    }

    @Override
    public UserDomain selectById(int uid){
        return userDao.selectById(uid);
    };

    @Override
    public UserDomain find(int uid) {
        return userDao.find(uid);
    }
}
