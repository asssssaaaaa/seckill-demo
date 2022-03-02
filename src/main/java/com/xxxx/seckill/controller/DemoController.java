package com.xxxx.seckill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author QBX
 * @date 2022/2/26
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

    /**
     * 测试页面跳转
     * @param model
     * @return
     */
    @GetMapping("/hello")
    public String hello(Model model){
        model.addAttribute("name","xxxx");
        return "hello";
    }
}
