package com.sgu.tourism.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sgu.tourism.service.ICommentService;
import com.sgu.tourism.vo.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.List;

/**
 * @author huang
 * @date 2020/11/19 14:58
 */
@Controller
public class CommentController {


    @Autowired
    private ICommentService iCommentService;

    @RequestMapping("/showCommentList")
    public String showCommentList(){
        return "comments/commentsList";
    }


    @RequestMapping("/findAllCOmmet")
    @ResponseBody
    public JSONObject getAllComments(Integer page,Integer limit,String key){
        JSONObject obj = new JSONObject();
        System.out.println("页数：   "+page);
        System.out.println("页数：   "+limit);
        System.out.println("页数：   "+key);

        List<CommentVo> comments = iCommentService.findAllCOmments(page,limit,key);

        int totalCount = iCommentService.getCount(key);

        Object o = JSON.toJSON(comments);
        obj.put("code",0);
        obj.put("data",o);
        obj.put("msg","");
        obj.put("count",totalCount);
        return obj;
    }



    @RequestMapping("/deleteCommentById")
    @ResponseBody
    public JSONObject deleteCommentById(@RequestParam("id") Integer id){
        JSONObject obj = new JSONObject();
        System.out.println("删除的id： "+id);
        int count = 0;
        if (id != null){
            count = iCommentService.deleteCommentById(id);
        }
        if (count <= 0){
            obj.put("code",500);
            obj.put("msg","数据库数据 删除失败");
        }else {
            obj.put("code",0);
            obj.put("msg","删除成功！");
        }
        return obj;
    }



}
