package com.sgu.tourism.mapper;

import com.sgu.tourism.common.dao.BaseDao;
import com.sgu.tourism.entity.User;
import com.sgu.tourism.vo.UserChartsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IUserMapper extends BaseDao<User> {

    List<User> findAllUser(@Param("page") Integer page, @Param("limit") Integer limit,
                           @Param("key") String key);

    User findUserByName(@Param("userName")String trim);

    int getCount(@Param("userName") String key);

    int updateUserById(User user);

    int deleteUserById(@Param("uid")Integer uid);

    User queryUserById(Integer uid);


    User findUserByNameAndPassword(@Param("userName") String userName,
                                   @Param("pwd") String pwd);

    List<UserChartsVo> getUsersName();

    int updateUserImgFileNameById(User updateUser);

}
