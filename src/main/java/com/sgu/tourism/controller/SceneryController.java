package com.sgu.tourism.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sgu.tourism.entity.Scenery;
import com.sgu.tourism.service.ISceneryService;
import javafx.scene.Scene;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Controller
public class SceneryController {

    @Autowired
    private ISceneryService sceneryService;

    @RequestMapping("/sceneryList")
    public String sceneryList(){
        return "scenery/sceneryList";
    }


    @RequestMapping("/sceneryGetAll")
    @ResponseBody
    public JSONObject sceneryGetAll(HttpServletRequest request){
        JSONObject obj = new JSONObject();
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        String key = request.getParameter("key");

        List<Scenery> sceneries = sceneryService.findAllScenerys(page,limit,key);

        int totalCount = sceneryService.getCount(key);

        Object o = JSON.toJSON(sceneries);
        obj.put("code",0);
        obj.put("data",o);
        obj.put("msg","success");
        obj.put("count",totalCount);
        return obj;
    }


    /***
     * 返回添加景点空页面；
     * @return
     */
    @RequestMapping("/sceneryAddScenery")
    public String sceneryAddScenery(){
        return "scenery/addScenery";
    }


    @RequestMapping("/sceneryAdd")
    @ResponseBody
    public JSONObject sceneryAdd(Scenery scenery){
        JSONObject obj = new JSONObject();
        System.out.println(scenery);


        boolean flag = sceneryService.insertScenery(scenery);
        if (flag == false){
            obj.put("code",0);
            obj.put("msg","success");
        }else {
            obj.put("code",0);
            obj.put("msg","success");
        }
        return obj;
    }


    @RequestMapping("/editScenery")
    public String editSceneryShow(){
        return "scenery/editScenery";
    }


    @RequestMapping("/updateScenery")
    @ResponseBody
    public JSONObject updateScenery(HttpServletRequest request, HttpServletResponse response){
        JSONObject obj = new JSONObject();
        String sceneryIdStr = request.getParameter("sceneryId");
        String sceneryCity = request.getParameter("sceneryCity");
        String sceneryTitle = request.getParameter("sceneryTitle");
        String sceneryPrice = request.getParameter("sceneryPrice");
        String sceneryOldPrice = request.getParameter("sceneryOldPrice");
        String sceneryDetile1 = request.getParameter("sceneryDetile1");
        String sceneryDetile2 = request.getParameter("sceneryDetile2");
        String sceneryDetile3 = request.getParameter("sceneryDetile3");
        String opentime = request.getParameter("opentime");
        String sceType = request.getParameter("sceType");

        Integer sceneryId = Integer.parseInt(sceneryIdStr);
        Integer sceneryType = Integer.parseInt(sceType);

        Scenery scenery = new Scenery();
        scenery.setSceneryId(sceneryId);
        scenery.setSceneryCity(sceneryCity);
        scenery.setSceneryTitle(sceneryTitle);
        scenery.setSceneryPrice(sceneryPrice);
        scenery.setSceneryOldPrice(sceneryOldPrice);
        scenery.setSceneryDetailed1(sceneryDetile1);
        scenery.setSceneryDetailed2(sceneryDetile2);
        scenery.setSceneryDetailed3(sceneryDetile3);
        scenery.setOpenTime(opentime);
        scenery.setSceneryType(sceneryType);


        int count = sceneryService.updateSceneryById(scenery);
        if (count < 1){
            obj.put("code",500);
            obj.put("msg","修改失败！");
        }else {
            obj.put("code",200);
            obj.put("msg","success");
        }
        return obj;
    }



    @RequestMapping("/deleteSceneryById")
    @ResponseBody
    public JSONObject deleteSceneryById(String id){
        JSONObject obj = new JSONObject();
        Integer sid = Integer.parseInt(id);
        Scenery scenery = sceneryService.selectSceneryById(sid);

        System.out.println("要删除的scenery:  "+scenery);
        if (scenery == null){
            obj.put("code",500);
            obj.put("msg","查无此人");
            return obj;
        }

        String imgFileName = scenery.getImgFileName();

        int count = sceneryService.deleteSceneryById(sid);
        if (count < 0){
            obj.put("code",500);
            obj.put("msg","删除失败");
        }else {
//            File file = new File("D:\\eclipseWork\\ideaWork\\upload\\scenery",imgFileName);
//            boolean delete = file.delete();
            boolean delete = true;
            if (delete){
                obj.put("code",200);
                obj.put("msg","删除成功！");
            }else{
                obj.put("code",500);
                obj.put("msg","图片没有删除成功！");
            }
        }
        return obj;
    }









//    public void Base64ToByte(String base64str,String whichOne,String sceneryId){
//        byte[] bytes = null;
//        if (base64str == null){
//            return ;
//        }
//
//        String path = null;
//        if ("1".equals(whichOne)){
//            path = "pic1";
//        }else if ("2".equals(whichOne)){
//            path = "pic2";
//        }else{
//            path = "pic3";
//        }
//
//        BASE64Decoder decoder = new BASE64Decoder();
////        base64解码
//        try {
//            byte[] b = decoder.decodeBuffer(base64str);
//            for (int i = 0; i < b.length; i++) {
//                if (b[i] < 0){
//                    b[i] += 256;
//                }
//            }
//            File file = new File("D:\\eclipseWork\\ideaWorkSpace\\tourism\\src\\main\\resources\\static\\images\\scenery\\"+sceneryId);
//            boolean exists = file.exists();
//            if (exists == false){
//                file.mkdirs();
//            }
//
//
//            OutputStream out = new FileOutputStream(file+File.separator+path+".jpg");
//            out.write(b);
//            out.flush();
//            out.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }






}
