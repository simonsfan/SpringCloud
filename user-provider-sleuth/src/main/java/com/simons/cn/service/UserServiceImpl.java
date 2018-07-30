package com.simons.cn.service;

import com.simons.cn.bean.User;
import com.simons.cn.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目名称：user-provider
 * 类名称：com.simons.cn.service
 * 类描述：
 * 创建人：simonsfan
 * 创建时间：2018/7/19 10:54
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getUserByName(String name) {
        List<User> userList = userMapper.getUserByName(name);
        return userList;
    }

}
