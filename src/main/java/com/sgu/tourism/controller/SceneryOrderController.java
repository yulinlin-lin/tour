package com.sgu.tourism.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sgu.tourism.entity.HotelOrder;
import com.sgu.tourism.entity.SceneryOrder;
import com.sgu.tourism.service.IHotelOrderService;
import com.sgu.tourism.service.ISceneryOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author huang
 * @date 2020/12/1 21:43
 */
@Controller
public class SceneryOrderController {

    @Autowired
    private ISceneryOrderService iSceneryOrderService;

//    单纯只是页面跳转
    @RequestMapping("/toSceneryOrderListPage")
    public String toSceneryOrderListPage(){
        return "scenery/sceneryOrderList";
    }


    /***
     * 查询所有景点订单
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryAllSceneryOrdes")
    public JSONObject queryAllSceneryOrdes(@RequestParam("page") Integer page,
                                         @RequestParam("limit")  Integer limit,
                                         String key){
        JSONObject obj = new JSONObject();
        List<SceneryOrder> sceneryOrders =  iSceneryOrderService.queryAllSceneryOrdes(page,limit,key);

        int totalCount = 0;
        totalCount = iSceneryOrderService.getCount(key);
        System.out.println("请求的条数： "+totalCount);
        Object o = JSON.toJSON(sceneryOrders);
        obj.put("code",0);
        obj.put("data",o);
        obj.put("msg","success");
        obj.put("count",totalCount);
        return obj;
    }


    /***
     * 跳转到修改景点订单页面；
     * @return
     */
    @RequestMapping("/toEditSceneryOrderPage")
    public String toEditSceneryOrderPage(){
        return "scenery/sceneryOrderEdit";
    }


    /***
     * 更新景点订单的信息；
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateSceneryOrder")
    public JSONObject updateSceneryOrder(SceneryOrder sceneryOrder){
        System.out.println(sceneryOrder);
        JSONObject obj = new JSONObject();
//        只修改部分的内容；
        int count = iSceneryOrderService.updateSceneryOrderById(sceneryOrder);
        if (count < 1){
            obj.put("code",500);
            obj.put("msg","修改失败！");
        }else {
            obj.put("code",0);
            obj.put("msg","更新成功！");
        }
        return obj;
    }



    /***
     * 根据id  进行逻辑删除 景点订单；
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteSceneryOrderById")
    public JSONObject deleteSceneryOrderById(@RequestParam("id") Integer id){
        JSONObject obj = new JSONObject();
        int count =  iSceneryOrderService.updateDelBySceneryOrderId(id);

        if (count < 1){
            obj.put("code",500);
            obj.put("msg","删除失败！");
        }else {
            obj.put("code",0);
            obj.put("msg","删除成功！");
        }
        return obj;
    }



}
