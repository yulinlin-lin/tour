package com.sgu.tourism.controller.front;

import com.alibaba.fastjson.JSONObject;
import com.sgu.tourism.entity.HotelOrder;
import com.sgu.tourism.entity.SceneryOrder;
import com.sgu.tourism.entity.User;
import com.sgu.tourism.service.IHotelOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author huang
 * @date 2020/11/23 19:31
 */
@Controller
@RequestMapping("/hotelOrderFront")
public class HotelOrderFrontController {


    @Autowired
    private IHotelOrderService iHotelOrderService;

    @RequestMapping("/addHotelOrder")
    @ResponseBody
    public JSONObject addHotelOrder(HttpServletRequest request, HotelOrder hotelOrder){
        JSONObject obj = new JSONObject();
        System.out.println(hotelOrder);

        User user = (User) request.getSession().getAttribute("user");
        hotelOrder.setOrderUserName(user.getUserName());
        System.out.println("session中的用户名是：  "+user.getUserName());
        hotelOrder.setDel(0);
        boolean flag = iHotelOrderService.addHotelOrder(hotelOrder);
        if (flag == false){
            obj.put("code",500);
            return obj;
        }

        obj.put("code",0);
        return obj;
    }



}
