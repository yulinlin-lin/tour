package com.sgu.tourism.controller.front;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sgu.tourism.entity.User;
import com.sgu.tourism.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.File;

/**
 * @author huang
 * @date 2020/11/29 20:52
 */
@Controller
//@RequestMapping("/user")
public class UserFrontController {

    @Autowired
    private IUserService iUserService;

    @RequestMapping("/toUserCenter")
    public String toUserCenter(){
        return "user/userCenter";
    }
    
    
    

//    重新设置密码的方法
    @RequestMapping("/toReSetPasswordPage")
    public String toReSetPasswordPage(){
        return "user/passwordReset";
    }



    @ResponseBody
    @RequestMapping("/frontUserUpdateById")
    public JSONObject frontUserUpdateById(User user, HttpSession session){
        JSONObject object = new JSONObject();
        int i = iUserService.updateUserById(user);
        User user1 = iUserService.queryUserById(user.getUserId());
        Object o = JSON.toJSON(user1);


        if (i > 0){
            object.put("code",0);
            object.put("msg","修改成功！");
            object.put("data",o);
            session.removeAttribute("user");
            session.setAttribute("user",user1);
        }else {
            object.put("code",500);
            object.put("msg","修改失败！");
        }
        return object;
    }
    
    

    
    @ResponseBody
    @RequestMapping("/frontUpdateUserPass")
    public JSONObject frontUpdateUserPass(@RequestParam("userId") Integer userId,
                                      @RequestParam("oldPass") String oldPass,
                                      @RequestParam("newpass") String newpass,
                                      @RequestParam("repass") String repass){
        JSONObject object = new JSONObject();
        User user = iUserService.queryUserById(userId);

        if (!oldPass.equals(user.getPassword())){
            object.put("code",500);
            object.put("msg","原始密码不正确！");
            return object;
        }

        if (!newpass.equals(repass)){
            object.put("code",500);
            object.put("msg","两次密码不正确！");
            return object;
        }
        User user1 = new User();
        user1.setUserId(userId);
        user1.setPassword(newpass);
        int i = iUserService.updateUserById(user1);
        if (i > 0){
            object.put("code",0);
            object.put("msg","修改成功！");
        }else {
            object.put("code",500);
            object.put("msg","修改失败！修改成功的条数是0条！");
        }
        return object;
    }

    @ResponseBody
    @RequestMapping("/updateUserImg")
    public JSONObject updateUserImg(@RequestParam("userId") Integer userId,
                                    @RequestParam("url") String url,HttpSession session){
        JSONObject obj = new JSONObject();
        System.out.println(userId);
        System.out.println(url);

        //        进行判空，如果有空的，直接返回；
        if (url == null || url == ""){
            obj.put("code",500);
            obj.put("msg","上传图片失败！");
            return obj;
        }


        //        查询当前用户的图片名字；如果没有这个人直接结束 ；
        User user  = iUserService.queryUserById(userId);
        if (user == null){
            obj.put("code",500);
            obj.put("msg","查无此人");
            return obj;
        }

        String newUrl = null;
        if (url != null && !"".equals(url)){
            String imgFileNameOld = url;
            int i = imgFileNameOld.lastIndexOf("\\");
            String imgFileNameNew = imgFileNameOld.substring(i+1);
            System.out.println("截取后的字符 ：   "+imgFileNameNew);
            newUrl = imgFileNameNew;
        }

        System.out.println("要修改的图片名字："+newUrl);

        User updateUser =new User();
        updateUser.setUserId(userId);
        updateUser.setImgFileName(newUrl);
        int i = iUserService.updateUserImgFileNameById(updateUser);

        User newUser = iUserService.queryUserById(userId);


        if(i > 0){
//            如果更新成功了，那就再去删除；
            String imgFileName = user.getImgFileName();
            //        删除图片
            File file = new File("D:\\eclipseWork\\ideaWork\\upload\\user",imgFileName);
            boolean delete = file.delete();


            session.removeAttribute("user");
            session.setAttribute("user",newUser);

            obj.put("code",0);
            obj.put("msg","修改成功！");
        }else{
            obj.put("code",500);
            obj.put("msg","更新数据库图片名字失败！");
            return obj;
        }
        return obj;
    }




}
