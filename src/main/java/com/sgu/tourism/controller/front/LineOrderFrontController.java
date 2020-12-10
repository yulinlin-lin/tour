package com.sgu.tourism.controller.front;

import com.alibaba.fastjson.JSONObject;
import com.sgu.tourism.entity.LineOrder;
import com.sgu.tourism.entity.User;
import com.sgu.tourism.service.ILineOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author huang
 * @date 2020/11/21 13:15
 */
@Controller
@RequestMapping("/lineOrderFront")
public class LineOrderFrontController {

    @Autowired
    private ILineOrderService iLineOrderService;


    /***
     * 前台用户， 新添加一个线路订单；
     * @param request
     * @param lineOrder
     * @return
     */
    @RequestMapping("/addLineOrder")
    @ResponseBody
    public JSONObject addLineOrder(HttpServletRequest request,LineOrder lineOrder){
        JSONObject obj = new JSONObject();
        System.out.println(lineOrder);
        User user = (User) request.getSession().getAttribute("user");
        lineOrder.setUserId(user.getUserId());
        boolean flag = iLineOrderService.addLineOrder(lineOrder);
        obj.put("code",0);
        return obj;
    }



}
