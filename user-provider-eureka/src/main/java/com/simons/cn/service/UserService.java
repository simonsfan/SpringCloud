package com.simons.cn.service;

import com.simons.cn.bean.User;

import java.util.List;

/**
 * 项目名称：user-provider
 * 类名称：com.simons.cn.service
 * 类描述：
 * 创建人：simonsfan
 * 创建时间：2018/7/19 11:02
 */
public interface UserService {
    List<User> getUserByName(String name);
}
