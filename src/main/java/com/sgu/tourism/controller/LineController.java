package com.sgu.tourism.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sgu.tourism.entity.Scenery;
import com.sgu.tourism.entity.TLineInfo;
import com.sgu.tourism.service.ITLineInfoService;
import com.sgu.tourism.util.DataToos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.unit.DataUnit;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/***
 * 线路控制器
 */
@Controller
public class LineController {

    @Autowired
    private ITLineInfoService service;


    @RequestMapping("/showLineList")
    public String showLineList(){
        return "line/lineList";
    }


    @RequestMapping("/lineGetList")
    @ResponseBody
    public JSONObject sceneryGetAll(HttpServletRequest request){
        JSONObject obj = new JSONObject();
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        String key = request.getParameter("key");

        List<TLineInfo> lines = service.findAllLines(page,limit,key);
//
        int totalCount = service.getCount(key);

        Object o = JSON.toJSON(lines);
        obj.put("code",0);
        obj.put("data",o);
        obj.put("msg","success");
        obj.put("count",totalCount);
        return obj;
    }



    @RequestMapping("/showAddLine")
    public String showAddLine(){
        return "line/addLine";
    }



    @RequestMapping("/addLineInfo")
    @ResponseBody
    public JSONObject addLineInfo(TLineInfo line){

        JSONObject obj = new JSONObject();
        System.out.println(line);

        boolean flag = service.insertObject(line);
        if (flag == false){
            obj.put("code",0);
            obj.put("msg","success");
        }else {
            obj.put("code",0);
            obj.put("msg","success");
        }
        return obj;
    }



    @RequestMapping("/updateLineActive")
    @ResponseBody
    public JSONObject updateLineActive(HttpServletRequest request, HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
        JSONObject object = new JSONObject();
        String idStr = request.getParameter("id");
        String statusStr = request.getParameter("status");

        System.out.println(idStr);
        System.out.println(statusStr);

        Integer id = null;
        Integer status = null;

        if (idStr != null && !"".equals(idStr)){
            id = Integer.parseInt(idStr);
        }

        if (statusStr != null && !"".equals(statusStr)){
            status = Integer.parseInt(statusStr);
        }

        boolean flag = service.updateActiveStatus(id,status);

        if (flag == false){
            object.put("code",500);
            object.put("msg","更新状态失败");
        }else {
            object.put("code",0);
            object.put("msg","更新状态失败");
        }

        return object;
    }



    @RequestMapping("/showEditLine")
    public String showEditLine(){
        return "line/editLine";
    }


    @RequestMapping("/updateLine")
    @ResponseBody
    public JSONObject updateLine(TLineInfo lineInfo,String type){
        JSONObject obj = new JSONObject();
        System.out.println(lineInfo);

        Integer typeid = null;
        if (type!=null && !"".equals(type)){
            typeid = Integer.parseInt(type);
        }
        lineInfo.setLineType(typeid);

        boolean  flag = service.updateLine(lineInfo);

        if (flag == false){
            obj.put("code",500);
            obj.put("msg","更新失败！");
        }else {
            obj.put("code",0);
            obj.put("msg","更新成功！");
        }
        return obj;
    }


    @RequestMapping("/deleteLineById")
    @ResponseBody
    public JSONObject deleteLineById(String id){
        JSONObject obj = new JSONObject();

        if (id == null || "".equals(id)){
            obj.put("code",500);
            obj.put("msg","传递的参数有问题！");
            return obj;
        }


        int count = service.deleteLineById(Integer.parseInt(id));
        if (count > 0){
            obj.put("code",0);
            obj.put("msg","删除成功！");
        }else {
            obj.put("code",500);
            obj.put("msg","删除失败！");
        }
        return obj;
    }


    /***
     * 下面是真的到数据库里面去删除数据 ，这里不再做逻辑删除了；
     * @return
     */
    @RequestMapping("/delAllLine")
    @ResponseBody
    public JSONObject delAllLine(String ids){
        JSONObject obj = new JSONObject();

        List<Integer> lineIds = new ArrayList<>();
        String[] split = ids.split(",");
        for (String idstr : split) {
            lineIds.add(Integer.parseInt(idstr));
        }

        boolean flag = false;
        int count  = service.deleteAllLine(lineIds);
        if (count < 0){
            obj.put("code",500);
            obj.put("msg","删除失败！");
        }else {
            obj.put("code",0);
            obj.put("msg","删除成功！");
        }
        return obj;
    }





}
