package com.sgu.tourism.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sgu.tourism.entity.User;
import com.sgu.tourism.service.IUserService;
import com.sgu.tourism.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

@Controller
public class UserController {


    @Autowired
    private IUserService userService;

    /***
     * 返回用户管理首页；
     * @return
     */
    @RequestMapping("/userList")
    public String userList(){
        return "user/userList";
    }


    @RequestMapping("/userfindAllUser")
    @ResponseBody
    public JSONObject userfindAllUser(HttpServletRequest request, HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
        JSONObject obj = new JSONObject();

        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        String key = request.getParameter("key");

        List<User> users = userService.findAllUsers(page,limit,key);

//        查找总数；
        int totalCount = userService.getCount(key);

        Object o = JSON.toJSON(users);
        obj.put("code",0);
        obj.put("data",o);
        obj.put("msg","success");
        obj.put("count",totalCount);
        return obj;
    }


    /***
     * 返回添加用户页面
     * @return
     */
    @RequestMapping("/userAddUser")
    public String userAddUser(){
        return "user/addUser";
    }


    /**
     * 添加用户
     * @param user
     * @return
     */
    @RequestMapping("/userRegister")
    @ResponseBody
    public JSONObject userRegister(User user){

        System.out.println(user);

        JSONObject obj = new JSONObject();
        System.out.println(user);
        User userByName = userService.findUserByName(user.getUserName());
        if (userByName != null){
            obj.put("code",500);
            obj.put("msg","用户名已经被占用！");
            return obj;
        }

        boolean flag = userService.add(user);
        if (flag == false){
            obj.put("code",500);
            obj.put("msg","添加失败！");
        }else {
            obj.put("code",0);
            obj.put("msg","添加成功！");
        }
        return obj;
    }


    /***
     * 返回修改用户页面
     * @return
     */
    @RequestMapping("/userEdit")
    public String returnUserEdit(){
        return "user/userEdit";
    }


    @RequestMapping("/updateById")
    @ResponseBody
    public JSONObject updateById(HttpServletRequest request){
        JSONObject obj = new JSONObject();

        String user_id = request.getParameter("user_id");
        String sexStr = request.getParameter("sex");
        String roleIdStr = request.getParameter("roleId");

        Integer userId = Integer.parseInt(user_id);
        Integer sex = Integer.parseInt(sexStr);
        Integer reloId = Integer.parseInt(roleIdStr);

        String user_name = request.getParameter("user_name");
        String pass = request.getParameter("pass");
        String phone = request.getParameter("phone");

        User user = new User();
        user.setUserId(userId);
        user.setSex(sex);
        user.setRoleType(reloId);
        user.setUserName(user_name);
        user.setPassword(MD5Util.getPwd(pass));
        user.setPhone(phone);

        int count = userService.updateUserById(user);

        if (count < 1){
            obj.put("code",500);
            obj.put("msg","修改失败！");
        }else {
            obj.put("code",200);
            obj.put("msg","success");
        }
        return obj;
    }


    @RequestMapping("/deleteById")
    @ResponseBody
    public JSONObject deleteById(String id){
        JSONObject obj = new JSONObject();
        Integer uid = Integer.parseInt(id);

        System.out.println("要删除的id号：  "+uid);

        User user  = userService.queryUserById(uid);
        if (user == null){
            obj.put("code",500);
            obj.put("msg","查无此人");
            return obj;
        }
        System.out.println("要删除的人： "+user);

        String imgFileName = user.getImgFileName();

        int count = userService.deleteUserById(uid);

        if (count < 0){
            obj.put("code",500);
            obj.put("msg","系统发生错误,数据库数据 删除失败");
        }else {

//            File file = new File("D:\\eclipseWork\\ideaWork\\upload\\user",imgFileName);
            boolean delete = true;
            if (delete){
                obj.put("code",0);
                obj.put("msg","数据和图片删除成功！");
            }else{
                obj.put("code",0);
                obj.put("msg","图片没有删除成功！");
            }
        }
        return obj;
    }










}
