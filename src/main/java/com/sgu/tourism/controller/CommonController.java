package com.sgu.tourism.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author huang
 * @date 2020/11/21 13:41
 */
@Controller
@RequestMapping("/getSuccess")
public class CommonController {

    @RequestMapping("/")
    public String getSuccess(){
        return "success";
    }

}
