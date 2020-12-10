package com.sgu.tourism.controller.front;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sgu.tourism.entity.Scenery;
import com.sgu.tourism.entity.THotelInfo;
import com.sgu.tourism.service.ITHotelInfoService;
import com.sgu.tourism.vo.HotelCommentsVo;
import com.sgu.tourism.vo.SceneryCommentsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author huang
 * @date 2020/11/19 21:05
 */
@Controller
public class HotelFrontController {


    @Autowired
    private ITHotelInfoService itHotelInfoService;

    @RequestMapping("/front_getHotelByType")
    @ResponseBody
    public JSONObject getHotelsByType(Integer hotelType){
        JSONObject obj= new JSONObject();
        System.out.println("要景点类型： "+hotelType);
        List<THotelInfo> hotels = itHotelInfoService.getSceneryByType(hotelType);
        for (THotelInfo hotel : hotels) {

            System.out.println(hotel);
        }
        obj.put("code",0);
        obj.put("msg","");
        Object o = JSON.toJSON(hotels);
        obj.put("data",hotels);
        return obj;
    }





    @RequestMapping(value = "/findOneHotel",method = RequestMethod.GET)
    public ModelAndView findOneHotel(Model model, @RequestParam("hotelId") Integer hotelId) {
        JSONObject obj = new JSONObject();
        System.out.println("要查找的酒店的id:  "+hotelId);
        ModelAndView mv = new ModelAndView("front/hotelDetail");
        THotelInfo hotelInfo = itHotelInfoService.queryHotelById(hotelId);
        System.out.println(hotelInfo);

        List<HotelCommentsVo> vos = itHotelInfoService.findHotelCommentVos(hotelId,0);
        for (HotelCommentsVo vo : vos) {
            System.out.println(vo);
        }
        mv.addObject("hotelInfo",hotelInfo);
        mv.addObject("vos",vos);
        return mv;
    }



    @RequestMapping("/hotelDetilOrderShow")
    public ModelAndView sceneryDetilOrderShow(@RequestParam(value = "number",required = false,defaultValue = "2") Integer number,
                                              @RequestParam(value = "price",required = false,defaultValue = "123") Integer price,
                                              @RequestParam(value = "tel",required = false,defaultValue = "电话") String tel,
                                              @RequestParam(value = "hName",required = false,defaultValue = "酒店名称") String hName,
                                              @RequestParam(value = "count",required = false,defaultValue = "666") Integer count){
        System.out.println("数量 ： "+number);
        System.out.println("单价 ： "+price);
        System.out.println("电话 ： "+tel);
        System.out.println("名称 ： "+hName);
        System.out.println("总计 ： "+count);

        ModelAndView mv = new ModelAndView("front/hotelDetailOrder");
        mv.addObject("number",number);  //人数量
        mv.addObject("price",price);  //单价
        mv.addObject("tel",tel); //开始日期
        mv.addObject("count",count);  //总计
        mv.addObject("hName",hName);  //标题
        return mv;
    }



}
