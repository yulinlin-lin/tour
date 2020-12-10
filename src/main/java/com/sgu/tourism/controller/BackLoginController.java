package com.sgu.tourism.controller;

import com.alibaba.fastjson.JSONObject;
import com.sgu.tourism.entity.User;
import com.sgu.tourism.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author huang
 * @date 2020/12/8 13:06
 */
@Controller
public class BackLoginController {


    @Autowired
    private IUserService iUserService;


    @RequestMapping("/backlogin")
    public String Backstage(){
        return "login";
    }


    /***
     * 管理员登录
     * @param no
     * @param pass
     * @return
     */
    @ResponseBody
    @RequestMapping("/adminlogin")
    public JSONObject adminlogin(@RequestParam("no") String no,
                                 @RequestParam("pass") String pass,
                                 HttpServletRequest request){
        JSONObject obj = new JSONObject();
        System.out.println("用户名： "+no);
        System.out.println("密码： "+pass);

        User user = iUserService.findUserByName(no);
        if (user == null){
            obj.put("code",500);
            obj.put("msg","没有该用户！");
            return obj;
        }

        String password = user.getPassword();

        if (!password.equals(pass)){
            obj.put("code",501);
            obj.put("msg","密码错误！");
            return obj;
        }

//        把管理员的信息保存在session中
        HttpSession session = request.getSession();
        session.setAttribute("admin",user);

        obj.put("code",0);
        return obj;
    }
}
