package com.sgu.tourism.intercetor;

import com.sgu.tourism.entity.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author huang
 * @date 2020/11/28 21:33
 */
public class BasicInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//        StringBuffer requestURL = request.getRequestURL();
//        System.out.println("     jkj;        "+requestURL);
//
//
//        String requestURI = request.getRequestURI();
//        System.out.println("请求的uri；    "+requestURI);

        /*else{
            *//*User user  = (User)request.getSession().getAttribute("user");
            if (user == null){
                response.sendRedirect(request.getContextPath()+"/login");
                System.out.println("用户没有登录 ，或者已经过期，请先登录！");
                return false;
            }else{
                return true;
            }*//*
        }*/


        return true;
    }
}
