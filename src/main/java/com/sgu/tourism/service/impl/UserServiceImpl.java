package com.sgu.tourism.service.impl;

import com.sgu.tourism.entity.User;
import com.sgu.tourism.mapper.IUserMapper;
import com.sgu.tourism.service.IUserService;
import com.sgu.tourism.util.DataToos;
import com.sgu.tourism.vo.UserChartsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserMapper userMapper;


    private final String  imageSavePath = "D:\\eclipseWork\\ideaWorkSpace\\tourism\\src\\main\\resources\\static\\images\\user";

    @Override
    public List<User> findAllUsers(String pageStr, String limitStr, String key) {
//        对数据进行进行转换
        Integer page = null;
        Integer limit = null;
        if (pageStr != null && !"".equals(pageStr)){
            page = Integer.parseInt(pageStr);
        }
        if (limitStr!=null && !"".equals(limitStr)){
            limit = Integer.parseInt(limitStr);
        }

        List<User> users = userMapper.findAllUser((page-1)*limit,limit,key);
        return users;
    }


    @Override
    public User findUserByName(String user_name) {
        String trim = user_name.trim();
        User user = userMapper.findUserByName(trim);
        return user;
    }

    @Override
    public int getCount(String key) {
        int count = 0;
        count = userMapper.getCount(key);
        return count;
    }

    @Override
    public int updateUserById(User user) {
        int count = 0;
        count = userMapper.updateUserById(user);
        return count;
    }

    @Override
    public int deleteUserById(Integer uid) {
        int count = userMapper.deleteUserById(uid);
        return count;
    }

    @Override
    public boolean add(User user) {
        boolean flag = false;

        if (user.getImgFileName() == null || user.getImgFileName() == ""){
            user.setImgFileName("1605595520418_pika.jpg");
        }

        if (user.getImgFileName() != null && !"".equals(user.getImgFileName())){
            String imgFileNameOld = user.getImgFileName();
            int i = imgFileNameOld.lastIndexOf("\\");
            String imgFileNameNew = imgFileNameOld.substring(i+1);
            System.out.println("截取后的字符 ：   "+imgFileNameNew);
            user.setImgFileName(imgFileNameNew);
        }

        user.setCreateTime(DataToos.getNowDateYYYY_MM_DD_2(new Date()));
        Integer integer = userMapper.insertObject(user);
        if (integer > 0){
            flag = true;
        }
        return flag;
    }

    @Override
    public User queryUserById(Integer uid) {

        return userMapper.queryUserById(uid);
    }

    @Override
    public User findUserByNameAndPassword(String userName, String pwd) {
        return userMapper.findUserByNameAndPassword(userName,pwd);
    }

    @Override
    public List<UserChartsVo> getUserNames() {
        return userMapper.getUsersName();
    }

    @Override
    public int updateUserImgFileNameById(User updateUser) {
        return userMapper.updateUserImgFileNameById(updateUser);
    }


}
