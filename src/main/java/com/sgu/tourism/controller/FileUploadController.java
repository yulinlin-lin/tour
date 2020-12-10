package com.sgu.tourism.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author huang
 * @date 2020/11/17 20:10
 */
@Controller
@MultipartConfig
public class FileUploadController {

//    private final String  imageSavePath = "D:\\eclipseWork\\ideaWorkSpace\\tourism\\src\\main\\resources\\static\\images\\user";
    private final String  imageSavePath = "D:\\eclipseWork\\ideaWork\\upload\\user";
    private final String  sceneryImgSavePath = "D:\\eclipseWork\\ideaWork\\upload\\scenery";
    private final String  hotelImgSavePath = "D:\\eclipseWork\\ideaWork\\upload\\hotel";
    private final String  lineImgSavePath = "D:\\eclipseWork\\ideaWork\\upload\\line";




    @RequestMapping("/uploadImg")
    @ResponseBody
    public Map<String, Object> upload(HttpServletRequest servletRequest,
                                      @RequestParam("file") MultipartFile file) throws IOException {

        if (!file.isEmpty()){
            System.out.println("文件名称:  "+file.getOriginalFilename());
            String savefilename = System.currentTimeMillis()+"_"+file.getOriginalFilename();
            File filepath = new File(imageSavePath);
            if (!filepath.exists()){
                filepath.mkdirs();
            }
            File saveFile = new File(imageSavePath+File.separator+savefilename);

            file.transferTo(saveFile);

            Map<String,Object> res = new HashMap<>();
            res.put("url",saveFile);
            return res;
        } else {
            System.out.println("没有成功上传图片");
            try {
                throw new Exception("没有成功上传图片 ");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }



//    景点图片
    @RequestMapping("/uploadSceneryImg")
    @ResponseBody
    public Map<String, Object> uploadSceneryImg(HttpServletRequest servletRequest,
                                      @RequestParam("file") MultipartFile file) throws IOException {

        if (!file.isEmpty()){
            System.out.println("文件名称:  "+file.getOriginalFilename());
            String savefilename = System.currentTimeMillis()+"_"+file.getOriginalFilename();
            File filepath = new File(sceneryImgSavePath);
            if (!filepath.exists()){
                filepath.mkdirs();
            }
            File saveFile = new File(sceneryImgSavePath+File.separator+savefilename);

            file.transferTo(saveFile);

            Map<String,Object> res = new HashMap<>();
            res.put("url",saveFile);
            return res;
        } else {
            System.out.println("没有成功上传图片");
            try {
                throw new Exception("没有成功上传图片 ");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }




//    酒店图片
    @RequestMapping("/hotelUploadImg")
    @ResponseBody
    public Map<String, Object> hotelUploadImg(HttpServletRequest servletRequest,
                                                @RequestParam("file") MultipartFile file) throws IOException {

        if (!file.isEmpty()){
            System.out.println("文件名称:  "+file.getOriginalFilename());
            String savefilename = System.currentTimeMillis()+"_"+file.getOriginalFilename();
            File filepath = new File(hotelImgSavePath);
            if (!filepath.exists()){
                filepath.mkdirs();
            }
            File saveFile = new File(hotelImgSavePath+File.separator+savefilename);

            file.transferTo(saveFile);

            Map<String,Object> res = new HashMap<>();
            res.put("url",saveFile);
            return res;
        } else {
            System.out.println("没有成功上传图片");
            try {
                throw new Exception("没有成功上传图片 ");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }




//线路图片
    @RequestMapping("/lineUploadImg")
    @ResponseBody
    public Map<String, Object> lineUploadImg(HttpServletRequest servletRequest,
                                                @RequestParam("file") MultipartFile file) throws IOException {

        if (!file.isEmpty()){
            System.out.println("文件名称:  "+file.getOriginalFilename());
            String savefilename = System.currentTimeMillis()+"_"+file.getOriginalFilename();
            File filepath = new File(lineImgSavePath);
            if (!filepath.exists()){
                filepath.mkdirs();
            }
            File saveFile = new File(lineImgSavePath+File.separator+savefilename);

            file.transferTo(saveFile);

            Map<String,Object> res = new HashMap<>();
            res.put("url",saveFile);
            return res;
        } else {
            System.out.println("没有成功上传图片");
            try {
                throw new Exception("没有成功上传图片 ");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }


}
