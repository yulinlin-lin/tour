package com.sgu.tourism.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author huang
 * @date 2020/11/25 19:26
 */
@Controller
public class RegisteredController {



    @RequestMapping("/")
    public String toRegisterPage(){
        return "registered";
    }

}
