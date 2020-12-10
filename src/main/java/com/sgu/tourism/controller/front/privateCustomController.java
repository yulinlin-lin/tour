package com.sgu.tourism.controller.front;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sgu.tourism.entity.Privatecustom;
import com.sgu.tourism.service.IPrivateCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @author huang
 * @date 2020/11/28 21:24
 */
@Controller
public class privateCustomController {

    @Autowired
    private IPrivateCustomService iPrivateCustomService;


//  返回私人定制的页面；
    @RequestMapping("/toPrivatePage")
    public String toPrivatePage(){
        System.out.println("请求成功！！！！！！！！！！！！！！！！！");
        return "private/privaPage";
    }

    //    前台页面提交过来的私人定制信息；   这个方法直接插入数据库；
    @ResponseBody
    @RequestMapping("/addPrivateCourtomerInfo")
    public JSONObject addPrivateCourtomerInfo(Privatecustom privatecustom){
        System.out.println(privatecustom);

        JSONObject obj = new JSONObject();


        System.out.println("请求添加一个---------------------------");

        int i = iPrivateCustomService.addPrivateCustom(privatecustom);

        if (i > 0){
            obj.put("code",0);
            obj.put("msg","提交成功！");
        }else{
            obj.put("code",500);
            obj.put("msg","发生了系统性的错误！");
        }
        return obj;
    }



//    后台查看所有的私人定制信息；返回多个私人定制的提交过来的信息；
    @RequestMapping("/toPrivateListPgae")
    public String toPrivateListPgae(){
        return "private/privateList";
    }


//    根据条件查询所有的私人定制信息，如果没有条件就全部查出来；
    @RequestMapping("/queryAllPrivates")
    @ResponseBody
    public JSONObject queryAllPrivates(String key, Integer page,Integer limit){
        JSONObject obj = new JSONObject();

        System.out.println("=========================================");
        System.out.println(page);
        System.out.println(limit);
        System.out.println(key);

        List<Privatecustom> privatecustoms = iPrivateCustomService.queryAllPirvateCustoms(page,limit,key);
        int totalCount = iPrivateCustomService.getCount(key);

        for (Privatecustom privatecustom : privatecustoms) {
            System.out.println(privatecustom);
        }

        Object o = JSON.toJSON(privatecustoms);
        obj.put("code",0);
        obj.put("data",o);
        obj.put("msg","success");
        obj.put("count",totalCount);
        return obj;
    }



    @RequestMapping("/editPrivateCourtom")
    public String toEditPrivateCourtomPage(){
        return "private/editPrivatePage";
    }




    @ResponseBody
    @RequestMapping("/updatePrivateCourtomerById")
    public JSONObject updatePrivateById(Privatecustom privatecustom){
        JSONObject obj = new JSONObject();
        System.out.println(privatecustom);
        int count = iPrivateCustomService.updatePrivateCourtomById(privatecustom);
        if (count > 0){
            obj.put("code",0);
            obj.put("msg","更新成功！");
        }else{
            obj.put("code",500);
            obj.put("msg","更新失败！");
        }
        return obj;
    }

    @ResponseBody
    @RequestMapping("/deleteByPrivateId")
    public JSONObject deleteByPrivateId(Integer id){
        System.out.println("要删除的id是 : "+id);
        JSONObject obj = new JSONObject();
        int count = iPrivateCustomService.deleteByPrivateId(id);
        System.out.println("删除成功的条数； "+count);
        if (count > 0){
            obj.put("code",0);
            obj.put("msg","更新成功！");
        }else{
            obj.put("code",500);
            obj.put("msg","更新失败！");
        }
        return obj;
    }


}
