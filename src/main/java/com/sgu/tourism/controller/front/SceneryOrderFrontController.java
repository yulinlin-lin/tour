package com.sgu.tourism.controller.front;

import com.alibaba.fastjson.JSONObject;
import com.sgu.tourism.entity.LineOrder;
import com.sgu.tourism.entity.SceneryOrder;
import com.sgu.tourism.entity.User;
import com.sgu.tourism.service.ISceneryOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author huang
 * @date 2020/11/23 11:25
 */
@Controller
@RequestMapping("/sceneryOrderFront")
public class SceneryOrderFrontController {


    @Autowired
    private ISceneryOrderService iSceneryOrderService;


    @RequestMapping("/addSceneryOrder")
    @ResponseBody
    public JSONObject addSceneryOrder(HttpServletRequest request, SceneryOrder sceneryOrder){
        JSONObject obj = new JSONObject();
        System.out.println(sceneryOrder);


        User user = (User) request.getSession().getAttribute("user");
        sceneryOrder.setOrderUserName(user.getUserName());
        boolean flag = iSceneryOrderService.addSceneryOrder(sceneryOrder);


        obj.put("code",0);
        return obj;
    }

}
