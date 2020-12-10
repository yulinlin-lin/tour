package com.sgu.tourism.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sgu.tourism.entity.THotelInfo;
import com.sgu.tourism.service.ITHotelInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

@Controller
public class THotelInfoController {

    @Autowired
    private ITHotelInfoService hotelInfoService;

    @RequestMapping("/showHotelList")
    public String showHotelList(){
        return "hotel/hotelList";
    }



    @RequestMapping("/hotelGetAll")
    @ResponseBody
    public JSONObject sceneryGetAll(HttpServletRequest request){
        JSONObject obj = new JSONObject();
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        String key = request.getParameter("key");

        List<THotelInfo> hotelInfos = hotelInfoService.findAllHotels(page,limit,key);

        int totalCount = 0;
        totalCount = hotelInfoService.getCount(key);

        System.out.println("请求的条数： "+totalCount);

        Object o = JSON.toJSON(hotelInfos);
        obj.put("code",0);
        obj.put("data",o);
        obj.put("msg","success");
        obj.put("count",totalCount);
        return obj;
    }


    @RequestMapping("/showAddHotel")
    public String showAddHotel(){
        return "hotel/addHotel";
    }



    @RequestMapping("/addHotel")
    @ResponseBody
    public JSONObject addHotelInfo(THotelInfo hotel){
        System.out.println("=========================================");
        System.out.println("=========================================");
        System.out.println(hotel);

        JSONObject obj = new JSONObject();
        System.out.println(hotel);

        boolean flag = hotelInfoService.insertHotel(hotel);

        if (flag == false){
            obj.put("code",500);
            obj.put("msg","添加酒店失败");
        }else {
            obj.put("code",0);
            obj.put("msg","添加酒店成功！");
        }
        return obj;
    }



    @RequestMapping("/editHotel")
    public String editHotelShow(){
        return "hotel/editHotel";
    }



    @RequestMapping("/updateHotel")
    @ResponseBody
    public JSONObject updateHotel(THotelInfo hotel){
        JSONObject obj = new JSONObject();
        System.out.println(hotel);
        int count = hotelInfoService.updateHotelById(hotel);
        if (count < 1){
            obj.put("code",500);
            obj.put("msg","修改失败！");
        }else {
            obj.put("code",0);
            obj.put("msg","success");
        }
        return obj;
    }




    @RequestMapping("/deleteHotelById")
    @ResponseBody
    public JSONObject deleteHotelById(String id){
        JSONObject obj = new JSONObject();
        Integer sid = Integer.parseInt(id);

        THotelInfo hotel = hotelInfoService.queryHotelById(sid);
        System.out.println(hotel);


        if (hotel == null){
            obj.put("code",500);
            obj.put("msg","查无此人");
            return obj;
        }

        String imgFileName = hotel.getImgFileName();
        int count = hotelInfoService.deleteSceneryById(sid);
        if (count < 0){
            obj.put("code",500);
            obj.put("msg","删除失败");
        }else {
            File file = new File("D:\\eclipseWork\\ideaWork\\upload\\hotel",imgFileName);
            boolean delete = file.delete();
            if (delete){
                obj.put("code",200);
                obj.put("msg","删除成功！");
            }else{
                obj.put("code",500);
                obj.put("msg","图片没有删除成功！");
            }
        }
        return obj;
    }





    @RequestMapping("/updateHotelStatus")
    @ResponseBody
    public JSONObject updateHotelStatus(@RequestParam("id") Integer id,@RequestParam("status") Integer status){
        JSONObject object = new JSONObject();

        System.out.println("要修改的尖：  "+id);
        System.out.println("要修改的尖：  "+status);

        boolean flag = hotelInfoService.updateHotelStatus(id,status);

        if (flag == false){
            object.put("code",500);
            object.put("msg","更新状态失败");
        }else {
            object.put("code",0);
            object.put("msg","更新状态失败");
        }

        return object;
    }




}
