package com.sgu.tourism.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sgu.tourism.entity.HotelOrder;
import com.sgu.tourism.entity.LineOrder;
import com.sgu.tourism.service.IHotelOrderService;
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
public class HotelOrderController {


    @Autowired
    private IHotelOrderService iHotelOrderService;

//    单纯只是页面跳转
    @RequestMapping("/toHotelOrderListPage")
    public String toHotelOrderListPage(){
        return "hotel/hotelOrderList";
    }


    /***
     * 查询所有酒店订单
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryAllHotelOrdes")
    public JSONObject queryAllHotelOrdes(@RequestParam("page") Integer page,
                                     @RequestParam("limit")  Integer limit,
                                     String key){
        JSONObject obj = new JSONObject();
        List<HotelOrder> hotelOrders =  iHotelOrderService.queryAllHotelOrdes(page,limit,key);

        int totalCount = 0;
        totalCount = iHotelOrderService.getCount(key);
        System.out.println("请求的条数： "+totalCount);
        Object o = JSON.toJSON(hotelOrders);
        obj.put("code",0);
        obj.put("data",o);
        obj.put("msg","success");
        obj.put("count",totalCount);
        return obj;
    }


    /***
     * 跳转到修改酒店订单页面；
     * @return
     */
    @RequestMapping("/toEditHotelOrderPage")
    public String toEditHotelOrderPage(){
        return "hotel/hotelOrderEdit";
    }


    /***
     * 更新酒店订单的信息；
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateHotelOrder")
    public JSONObject updateHotelOrder(HotelOrder hotelOrder){
        System.out.println(hotelOrder);
        JSONObject obj = new JSONObject();
//        只修改部分的内容；
        int count = iHotelOrderService.updateHotelOrderById(hotelOrder);
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
     * 根据id  进行逻辑删除 酒店订单；
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteHotelOrderById")
    public JSONObject deleteHotelOrderById(@RequestParam("id") Integer id){
        JSONObject obj = new JSONObject();
        int count =  iHotelOrderService.updateDelByHotelOrderId(id);

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
