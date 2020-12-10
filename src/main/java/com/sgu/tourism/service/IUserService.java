package com.sgu.tourism.service;

import com.sgu.tourism.entity.User;
import com.sgu.tourism.vo.UserChartsVo;

import java.util.List;

public interface IUserService {

    List<User> findAllUsers(String page, String limit, String key);


    User findUserByName(String user_name);

    int getCount(String key);

    int updateUserById(User user);

    int deleteUserById(Integer parseInt);


    boolean add(User user);

    User queryUserById(Integer uid);

    User findUserByNameAndPassword(String userName, String pwd);

    List<UserChartsVo> getUserNames();

    int updateUserImgFileNameById(User updateUser);

}
