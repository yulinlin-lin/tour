package com.sgu.tourism.controller;


import com.sgu.tourism.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/***
 * 后台页面处理跳转首页的处理类；
 */
@Controller
public class IndexController {


    @RequestMapping("/index")
    private String indexlist(HttpSession session){
        User admin = (User) session.getAttribute("admin");
//        如果从sessioin中取出的管理员信息是空的，那就直接返回再次登录 ；
        if (admin == null){
            return "redirect:/backlogin";
        }
        return "index/index";
    }

    @RequestMapping("/main")
    private String mainIndex(){
        return "main/main";
    }








}
