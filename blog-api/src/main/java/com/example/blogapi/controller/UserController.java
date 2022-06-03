package com.example.blogapi.controller;

import com.example.blogapi.common.lang.Result;
import com.example.blogapi.entity.User;
import com.example.blogapi.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {

    @Autowired
    UserService userService;


    @RequiresAuthentication
    @GetMapping("index")
    public Result index() {
        User user = userService.getById(1L);
        return Result.succ(user);
    }


    @GetMapping("/save")
    public Result save(@Validated @RequestBody User user) {
        return Result.succ(user);
    }

}
