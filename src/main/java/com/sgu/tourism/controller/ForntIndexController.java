package com.sgu.tourism.controller;

import com.sgu.tourism.entity.Scenery;
import com.sgu.tourism.entity.THotelInfo;
import com.sgu.tourism.entity.TLineInfo;
import com.sgu.tourism.entity.User;
import com.sgu.tourism.service.ISceneryService;
import com.sgu.tourism.service.ITHotelInfoService;
import com.sgu.tourism.service.ITLineInfoService;
import javafx.scene.Scene;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 响应前台的controller;
 * @author huang
 * @date 2020/11/16 16:51
 */
@Controller
public class ForntIndexController {
//    酒店
    @Autowired
    private ITHotelInfoService hotelInfoService;

//    线路
    @Autowired
    private ITLineInfoService tLineInfoService;

//   景点
    @Autowired
    private ISceneryService sceneryService;


    @RequestMapping("/frontIndex")
    public String showForntIndex(HttpSession session, Model model){

        User user  = (User)session.getAttribute("user");
        if (user == null){
            System.out.println("用户没有登录 ，或者已经过期，请先登录！");
            return "redirect:/login";
        }

//        查询酒店   的最新的8条数据 ；
        List<THotelInfo> hotels = hotelInfoService.findAllHotels();
//        查询所有的线路：
        List<TLineInfo> lines = tLineInfoService.findAllLines();

        List<Scenery> sceneries = sceneryService.findAllScenerys();

        model.addAttribute("hotels",hotels);
        model.addAttribute("lines",lines);
        model.addAttribute("sceneries",sceneries);

        return "front/index";
    }







}
