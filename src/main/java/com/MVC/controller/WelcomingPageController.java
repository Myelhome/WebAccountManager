package com.MVC.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/home")
public class WelcomingPageController {

    @GetMapping("/entity")
    public ResponseEntity showHome(@RequestParam(value = "param", required = false) String param){
        String response = "Home page in json";
        if(param != null) response += ", CONTENT: " + param;

        try {
            return ResponseEntity.ok(response);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getStackTrace());
        }
    }

    @GetMapping("/html")
    public String showHomeHtml(){
        return "home/home_page.html";
    }

    @GetMapping("/cool-html")
    public String showCoolHtml(Model model) {

        model.addAttribute("message", "Nu real'no cool!");

        return "home/cool_home_page.html";
    }
}
