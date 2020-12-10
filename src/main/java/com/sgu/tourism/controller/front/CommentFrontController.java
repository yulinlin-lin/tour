package com.sgu.tourism.controller.front;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sgu.tourism.entity.Comment;
import com.sgu.tourism.entity.User;
import com.sgu.tourism.service.ICommentService;
import com.sgu.tourism.service.ISceneryService;
import com.sgu.tourism.service.ITHotelInfoService;
import com.sgu.tourism.service.ITLineInfoService;
import com.sgu.tourism.util.DataToos;
import com.sgu.tourism.vo.HotelCommentsVo;
import com.sgu.tourism.vo.SceneryCommentsVo;
import com.sgu.tourism.vo.UsersCommentsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author huang
 * @date 2020/11/20 22:43
 */
@Controller
@RequestMapping("/commentfront")
public class CommentFrontController {

    @Autowired
    private ICommentService iCommentService;


    @Autowired
    private ITLineInfoService tLineInfoService;


    @Autowired
    private ISceneryService iSceneryService;


    @Autowired
    private ITHotelInfoService itHotelInfoService;


    @RequestMapping("/addComment")
    @ResponseBody
    public JSONObject addComment(HttpServletRequest request, Integer goodsId, String content, Integer contentType){
        JSONObject obj = new JSONObject();
//        获取当前登录的用户的id 要从sessIon中获取 ；
        User user = (User)request.getSession().getAttribute("user");
        Integer userid = user.getUserId() ;
//        设置当前的时间；
        Comment comment = new Comment();
        comment.setUserId(userid);
        comment.setGoodsId(goodsId);
        comment.setContent(content);
        comment.setContentType(contentType);
        comment.setCreateTime(DataToos.getNowDateYYYY_MM_DD(new Date()));

        boolean flag = iCommentService.addComment(comment);
        System.out.println("添加评论成功！！！================");
        if (flag == false){
            obj.put("code",500);
            obj.put("msg","添加失败");
        }else{
            obj.put("code",0);
            obj.put("msg","");
        }
        return obj;
    }




//    线路的获取更多的评论；
    @RequestMapping("/getMoreComments")
    @ResponseBody
    public JSONObject getMoreComments(Integer goods_id,Integer startIndex){
        System.out.println("货物的id：  "+goods_id);
        System.out.println("开始的下标  "+startIndex);

        JSONObject obj = new JSONObject();
        List<UsersCommentsVo> vos = tLineInfoService.findUsersCommentsVos(goods_id, startIndex);
        for (UsersCommentsVo vo : vos) {
            System.out.println(vo);
        }

        if (vos == null){
            obj.put("code",500);
            obj.put("msg","没有更多了");
        }else{
            obj.put("code",0);
            obj.put("msg","");
            Object o = JSON.toJSON(vos);
            obj.put("data",o);
        }
        return obj;
    }



//    景点的获取更多的评论；
    @RequestMapping("/getMoreSceneryComments")
    @ResponseBody
    public JSONObject getMoreSceneryComments(Integer goods_id,Integer startIndex){

        System.out.println("景点的的id：  "+goods_id);
        System.out.println("开始的下标  "+startIndex);
        JSONObject obj = new JSONObject();
        List<SceneryCommentsVo> vos = iSceneryService.findSceneryCommentVos(goods_id, startIndex);
        for (SceneryCommentsVo vo : vos) {
            System.out.println(vo);
        }
        if (vos == null){
            obj.put("code",500);
            obj.put("msg","没有更多了");
        }else{
            obj.put("code",0);
            obj.put("msg","");
            Object o = JSON.toJSON(vos);
            obj.put("data",o);
        }
        return obj;
    }



    @RequestMapping("/getMoreHotelComments")
    @ResponseBody
    public JSONObject getMoreHotelComments(Integer goods_id,Integer startIndex){

        System.out.println("景点的的id：  "+goods_id);
        System.out.println("开始的下标  "+startIndex);
        JSONObject obj = new JSONObject();
        List<HotelCommentsVo> vos = itHotelInfoService.findHotelCommentVos(goods_id,startIndex);
        for (HotelCommentsVo vo : vos) {
            System.out.println(vo);
        }
        if (vos == null){
            obj.put("code",500);
            obj.put("msg","没有更多了");
        }else{
            obj.put("code",0);
            obj.put("msg","");
            Object o = JSON.toJSON(vos);
            obj.put("data",o);
        }
        return obj;
    }






}
