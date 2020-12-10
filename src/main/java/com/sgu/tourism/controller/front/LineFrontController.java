package com.sgu.tourism.controller.front;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sgu.tourism.entity.TLineInfo;
import com.sgu.tourism.service.ITLineInfoService;
import com.sgu.tourism.vo.UsersCommentsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class LineFrontController {
    //    线路
    @Autowired
    private ITLineInfoService tLineInfoService;

    @RequestMapping(value = "/front_getLineByLIneType",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject show(@RequestParam("lineType") Integer lineType){
        JSONObject obj= new JSONObject();
        System.out.println("要查找的线路类型： "+lineType);
        List<TLineInfo> lins = tLineInfoService.findLinesByLineType(lineType);
        obj.put("code",0);
        obj.put("msg","");
        Object o = JSON.toJSON(lins);
        obj.put("data",lins);
        return obj;
    }




    @RequestMapping("/findOneLine")
    public ModelAndView findOneLine(@RequestParam("lineId") Integer lineId) {
        System.out.println("查看的id:  "+lineId);
        ModelAndView mv = new ModelAndView("front/lineDetail");

        TLineInfo lineInfo = tLineInfoService.findOneLineById(lineId);
        System.out.println(lineInfo);
        List<UsersCommentsVo> vos = tLineInfoService.findUsersCommentsVos(lineId,0);

        List<TLineInfo> allLines = tLineInfoService.findAllLines();

        mv.addObject("lineInfo",lineInfo);
        mv.addObject("vos",vos);
        mv.addObject("allLines",allLines);
//        Map<String ,String> map = new HashMap<>();
//        map.put("name","haha");
//        map.put("age","12");
//        model.addAttribute("maps",map);
        return mv;
    }


    @RequestMapping("/lineDetilOrderShow")
    public ModelAndView lineDetilOrderShow(@RequestParam(value = "number",required = false,defaultValue = "2") Integer number,
                                           @RequestParam(value = "price",required = false,defaultValue = "123") Integer price,
                                           @RequestParam(value = "canback",required = false,defaultValue = "可退") String canback,
                                           @RequestParam(value = "sdata",required = false,defaultValue = "2020-12-32") String sdata,
                                           @RequestParam(value = "lineTitle",required = false,defaultValue = "我是线路的一个标题") String lineTitle,
                                           @RequestParam(value = "count",required = false,defaultValue = "666") Integer count){
        System.out.println("数量 ： "+number);
        System.out.println("数量 ： "+price);
        System.out.println("数量 ： "+canback);
        System.out.println("数量 ： "+sdata);
        System.out.println("数量 ： "+count);
        System.out.println("标题 ： "+lineTitle);

        ModelAndView mv = new ModelAndView("front/lineDetailOrder");
        mv.addObject("number",number);  //人数量
        mv.addObject("price",price);  //单价
        mv.addObject("canback",canback);  //是否可退
        mv.addObject("sdata",sdata); //开始日期
        mv.addObject("count",count);  //总计
        mv.addObject("lineTitle",lineTitle);  //标题
        return mv;
    }




}
