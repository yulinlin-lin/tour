package com.sgu.tourism.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sgu.tourism.entity.LineOrder;
import com.sgu.tourism.entity.THotelInfo;
import com.sgu.tourism.service.ILineOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLConnection;
import java.util.List;

/**
 * @author huang
 * @date 2020/12/1 18:43
 */
@Controller
public class LineOrderController {

    @Autowired
    private ILineOrderService iLineOrderService;

    /***
     * 跳转线路订单页面
     * @return
     */
    @RequestMapping("/toLineOrderListPage")
    public String toLineOrderListPage(){
        return "line/lineOrderList";
    }

    /***
     * 查询所有线路订单信息；
     * @return     page=1&limit=10
     */
    @ResponseBody
    @RequestMapping("/queryAllLineOrdes")
    public JSONObject queryAllLineOrdes(@RequestParam("page") Integer page,
                                        @RequestParam("limit")  Integer limit,
                                        String key){
        JSONObject obj = new JSONObject();
        List<LineOrder> lineOrders =  iLineOrderService.queryAllLineOrdes(page,limit,key);
        int totalCount = 0;
        totalCount = iLineOrderService.getCount(key);

        System.out.println("请求的条数： "+totalCount);

        Object o = JSON.toJSON(lineOrders);
        obj.put("code",0);
        obj.put("data",o);
        obj.put("msg","success");
        obj.put("count",totalCount);
        return obj;
    }


    /***
     * 去修改线路订单页面去；
     *
     */
    @RequestMapping("/toEditLineOrderPage")
    public String toEditLineOrderPage(){
        return "line/lineOrderEdit";
    }

    /**
     * 根据id来修改线路订单的部分内容；
     * @param lineOrder
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateLineOrder")
    public JSONObject updateLineOrder(LineOrder lineOrder){
        JSONObject obj = new JSONObject();
        System.out.println(lineOrder);
//        只修改部分的内容；
        int count = iLineOrderService.updateLineOrderById(lineOrder);
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
     *  进行逻辑删除；
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteLineOrderById")
    public JSONObject deleteLineOrderById(@RequestParam("id") Integer id){
        JSONObject obj = new JSONObject();
       int count =  iLineOrderService.updateDelByUserId(id);

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
