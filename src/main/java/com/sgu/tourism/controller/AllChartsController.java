package com.sgu.tourism.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sgu.tourism.service.*;
import com.sgu.tourism.vo.ChartsVo;
import com.sgu.tourism.vo.UserChartsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author huang
 * @date 2020/11/21 22:47
 */
@Controller
public class AllChartsController {

    @Autowired
    private IUserService iUserService;


    @Autowired
    private ISceneryService iSceneryService;

    @Autowired
    private ITHotelInfoService itHotelInfoService;


    @Autowired
    private ILineOrderService iLineOrderService;


    @Autowired
    private IHotelOrderService iHotelOrderService;


    @Autowired
    private ISceneryOrderService iSceneryOrderService;


    /***
     * 用户新增统计
     * @return
     */
    @RequestMapping("/userChartsList")
    public String userChartsList(){
        return "charts/userCharts";
    }

    @RequestMapping("/getUserNames")
    @ResponseBody
    public JSONObject getUserNames(){
        JSONObject obj = new JSONObject();

        List<UserChartsVo> uservos = iUserService.getUserNames();
        for (UserChartsVo uservo : uservos) {

            System.out.println(uservo);
        }
        obj.put("code",0);
        obj.put("msg","");
        Object o = JSON.toJSON(uservos);
        obj.put("data",o);
        return obj;
    }


    /***
     * 景点产品新增统计
     * @return
     */
    @RequestMapping("/sceneryChartsList")
    public String sceneryChartsList(){
        return "charts/sceneryCharts";
    }

    @RequestMapping("/getSceneryNames")
    @ResponseBody
    public JSONObject getSceneryNames(){
        JSONObject obj = new JSONObject();

        List<ChartsVo> chartsVos = iSceneryService.getSceneryNames();
        for (ChartsVo chartsVo : chartsVos) {
            System.out.println(chartsVo);
        }
        obj.put("code",0);
        obj.put("msg","");
        Object o = JSON.toJSON(chartsVos);
        obj.put("data",o);
        return obj;
    }


    /***
     * 酒店产品新增统计
     * @return
     */
    @RequestMapping("/hotelChartsList")
    public String hotelChartsList(){
        return "charts/HotelCharts";
    }

    @RequestMapping("/getHotelNames")
    @ResponseBody
    public JSONObject getHotelNames(){
        JSONObject obj = new JSONObject();

        List<ChartsVo> chartsVos = itHotelInfoService.getHotelNames();
        for (ChartsVo chartsVo : chartsVos) {
            System.out.println(chartsVo);
        }
        obj.put("code",0);
        obj.put("msg","");
        Object o = JSON.toJSON(chartsVos);
        obj.put("data",o);
        return obj;
    }


    /***
     * 线路订单
     * @return
     */

    @RequestMapping("/toLineOrderChartsList")
    public String toLineOrderChartsList(){
        return "charts/lineOrderCharts";
    }

    @ResponseBody
    @RequestMapping("/lineOrderChartsList")
    public JSONObject lineOrderChartsList(Model m){
        JSONObject obj = new JSONObject();
        List<ChartsVo> chartsVos =  iLineOrderService.getLineInCome();
        obj.put("code",0);
        obj.put("msg","");
        Object o = JSON.toJSON(chartsVos);
        obj.put("data",o);
        return obj;
    }


    /***
     * 酒店订单收入统计
     */
    @RequestMapping("/toHotelOrderChartsList")
    public String toHotelOrderChartsList(){
        return "charts/hotelOrderCharts";
    }

    @ResponseBody
    @RequestMapping("/hotelOrderChartsList")
    public JSONObject hotelOrderChartsList(Model m){
        JSONObject obj = new JSONObject();
        List<ChartsVo> chartsVos =  iHotelOrderService.getHotelInCome();
        obj.put("code",0);
        obj.put("msg","");
        Object o = JSON.toJSON(chartsVos);
        obj.put("data",o);
        return obj;
    }




    /***
     * 景点订单收入统计
     */
    @RequestMapping("/toSceneryOrderChartsList")
    public String toSceneryOrderChartsList(){
        return "charts/sceneryOrderCharts";
    }

    @ResponseBody
    @RequestMapping("/sceneryOrderChartsList")
    public JSONObject sceneryOrderChartsList(Model m){
        JSONObject obj = new JSONObject();
        List<ChartsVo> chartsVos =  iSceneryOrderService.getSceneryInCome();
        obj.put("code",0);
        obj.put("msg","");
        Object o = JSON.toJSON(chartsVos);
        obj.put("data",o);
        return obj;
    }

}
