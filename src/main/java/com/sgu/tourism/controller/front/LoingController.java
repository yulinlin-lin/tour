package com.sgu.tourism.controller.front;

import com.alibaba.fastjson.JSONObject;
import com.sgu.tourism.entity.User;
import com.sgu.tourism.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

/**
 * @author huang
 * @date 2020/11/21 15:18
 */
@Controller
public class LoingController {

    @Autowired
    private IUserService iUserService;


    @RequestMapping("/login")
    public String login(HttpServletRequest request,HttpServletResponse response){
//        Cookie[] cookies = request.getCookies();
//        for (int i = 0; i < cookies.length; i++) {
//            if(cookies[i].getName().equals("doruser")){
//                cookies[i].setMaxAge(0);
//                String value = cookies[i].getValue();
//                int uid = Integer.parseInt(value);
//                User user = iUserService.queryUserById(uid);
//                request.getSession().setAttribute("user",user);
//                return "forward:/frontIndex";
//            }
//        }
        return "login/login";
    }

    @RequestMapping("/loginHandle")
    @ResponseBody
    public JSONObject loginPage(HttpServletRequest request,HttpServletResponse response,
                                @RequestParam("userName") String userName,
                                @RequestParam("pwd") String pwd,
                                @RequestParam("checkImg") String checkImg){
        JSONObject obj = new JSONObject();
        HttpSession session = request.getSession();
        System.out.println("前台发送过来的验证码 $$$$￥$$$$$$$$$$$>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>： "+checkImg.toUpperCase());
        System.out.println("session中获取的验证码 $$$$$$$$$$$$$$$>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>： "+session.getAttribute("servletImg"));

        String servletImg = (String)session.getAttribute("servletImg");
        if (!servletImg.equals(checkImg.toUpperCase())){
            obj.put("code",500);
            obj.put("msg","验证码错误，请再次输入！");
            return obj;
        }

        System.out.println(userName);
        System.out.println(pwd);

        User user = iUserService.findUserByNameAndPassword(userName,pwd);
        if (user == null){
            obj.put("code",500);
            obj.put("msg","用户名或者密码错误");
            return obj;
        }
//        在session域中获取user的信息；
        session.setAttribute("user",user);

        Cookie userCookie = new Cookie("doruser",user.getUserId().toString());
        userCookie.setMaxAge(60*1000);
        response.addCookie(userCookie);
        obj.put("code",0);
        obj.put("msg","登录成功！");
        return obj;
    }


    /***
     * 生成验证码图片；
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("img")
    public void getImg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int width = 120;
        int height = 40;
        // 相当于一张画质 内存创建一个图片缓冲区
        BufferedImage bufi = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);

        // 给图片缓冲区中写数据
        // 画笔
        Graphics g = bufi.getGraphics();

        // 设置画笔的颜色
        g.setColor(Color.WHITE);
        // 修改图片的背景颜色
        g.fillRect(0, 0, width, height);

        g.setColor(Color.RED);
        // 画边框
        g.drawRect(0, 0, width - 1, height - 1);

        String data = "QWERTYUPASDFGHJKLZXCVBNMqwertyupasdfghjkzxcvbnm0123456789";
        int x = 10;
        int y = 30;
        // 定义一个随机数对象
        Random r = new Random();

        //定义一个StringBuilder ，用户保存给图片上写的数据
        StringBuilder sb = new StringBuilder();
        // 生成干扰线
        for (int i = 0; i < 8; i++) {
            g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
            g.drawLine(r.nextInt(width), r.nextInt(height), r.nextInt(width),
                    r.nextInt(height));
        }
        for (int i = 0; i < 4; i++) {

            // 设置画笔的颜色，让不同的字显示不同的颜色
            g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
            // 设置字体大小
            g.setFont(new Font("宋体", Font.ITALIC, 25));

            int num = r.nextInt(data.length());
            char ch = data.charAt(num);
            sb.append(ch);
            g.drawString( ch+ "", x, y);
            x += 20;
        }
        //获取Session,把验证码图片上的数据保存在Session中
        System.out.println(sb.toString().toUpperCase()+"             $$$$$$$$$$$$$$$>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        request.getSession().setAttribute("servletImg", sb.toString().toUpperCase());
        // 把图片缓冲区中的数据写到页面
        ImageIO.write(bufi, "JPG", response.getOutputStream());
    }




}
