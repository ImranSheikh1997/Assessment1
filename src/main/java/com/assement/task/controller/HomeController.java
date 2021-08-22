package com.assement.task.controller;

import com.assement.task.dto.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;

@Controller
@Slf4j
public class HomeController {

    @Autowired
    private UserResponse response;

    @GetMapping("")
    public String showHomePage(
    ){
        return "index";
    }

    @GetMapping("/dashboard")
    public String dashboard(){
        return "dashboard";
    }

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users",response.findAllUsers());
        return "users";
    }

    @GetMapping("/profile")
    public String profile(){
        return "profile";
    }

    @GetMapping("/edit")
    public String edit(
            @RequestParam("email") String email,
            Model model
    ){
            model.addAttribute("users", response.findUserByEmail(email));
        return "edit";
    }

    @GetMapping("/header")
    public String header(){
        return "header";
    }
}
