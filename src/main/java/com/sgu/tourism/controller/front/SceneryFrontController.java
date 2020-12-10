package com.sgu.tourism.controller.front;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sgu.tourism.entity.Scenery;
import com.sgu.tourism.entity.TLineInfo;
import com.sgu.tourism.service.ISceneryService;
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
 * @date 2020/11/19 17:26
 */
@Controller
public class SceneryFrontController {


    @Autowired
    private ISceneryService iSceneryService;

    @RequestMapping("/front_getSceneryByType")
    @ResponseBody
    public JSONObject getSceneryByType(Integer sceneryType){
        JSONObject obj= new JSONObject();
        System.out.println("要景点类型： "+sceneryType);
        List<Scenery> sceneries = iSceneryService.getSceneryByType(sceneryType);
        obj.put("code",0);
        obj.put("msg","");
        Object o = JSON.toJSON(sceneries);
        obj.put("data",sceneries);
        return obj;
    }



    @RequestMapping(value = "/findOneScenery",method = RequestMethod.GET)
    public ModelAndView findOneScenery(Model model, @RequestParam("sceneryId") Integer sceneryId) {
        System.out.println("查看的景点的Id: "+sceneryId);
        JSONObject obj = new JSONObject();
        ModelAndView mv = new ModelAndView("front/sceneryDetail");

        Scenery scenery = iSceneryService.findSceneryById(sceneryId);
        System.out.println(scenery);
        List<SceneryCommentsVo> vos = iSceneryService.findSceneryCommentVos(sceneryId,0);
        for (SceneryCommentsVo vo : vos) {
            System.out.println(vo);
        }
        mv.addObject("scenery",scenery);
        mv.addObject("vos",vos);
        return mv;
    }




    @RequestMapping("/sceneryDetilOrderShow")
    public ModelAndView sceneryDetilOrderShow(@RequestParam(value = "number",required = false,defaultValue = "2") Integer number,
                                           @RequestParam(value = "price",required = false,defaultValue = "123") Integer price,
                                           @RequestParam(value = "gCity",required = false,defaultValue = "城市") String gCity,
                                           @RequestParam(value = "sdata",required = false,defaultValue = "2020-12-32") String sdata,
                                           @RequestParam(value = "sceneryTitle",required = false,defaultValue = "我是景点的一个标题") String sceneryTitle,
                                           @RequestParam(value = "count",required = false,defaultValue = "666") Integer count){
        System.out.println("数量 ： "+number);
        System.out.println("数量 ： "+price);
        System.out.println("数量 ： "+gCity);
        System.out.println("数量 ： "+sdata);
        System.out.println("数量 ： "+count);
        System.out.println("标题 ： "+sceneryTitle);

        ModelAndView mv = new ModelAndView("front/sceneryDetailOrder");
        mv.addObject("number",number);  //人数量
        mv.addObject("price",price);  //单价
        mv.addObject("sdata",sdata); //开始日期
        mv.addObject("count",count);  //总计
        mv.addObject("sceneryTitle",sceneryTitle);  //标题
        return mv;
    }



}
