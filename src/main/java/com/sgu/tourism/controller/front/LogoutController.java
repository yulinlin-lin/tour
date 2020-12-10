package com.sgu.tourism.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author huang
 * @date 2020/11/29 20:07
 */
@Controller
public class LogoutController {

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
//        session.removeAttribute("user");
        return "login/login";
    }

}
