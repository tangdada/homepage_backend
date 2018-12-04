package com.example.demo.controller;


import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.JWT;
import com.example.demo.utils.ResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("/showUser")
    @ResponseBody
    public User toIndex(HttpServletRequest request){
        int userId = Integer.parseInt(request.getParameter("id"));
        User user = this.userService.getUserById(userId);
        return user;
    }

    @GetMapping("/getInfo")
    @ResponseBody
    public ResponseData getInfo(@RequestParam String token) {
        User user = JWT.unsign(token, User.class);
        if (user != null) {
            return ResponseData.ok().putDataValue("user", user);
        }
        return ResponseData.customerError().putDataValue(ResponseData.ERRORS_KEY, new String[] { "token不合法" });
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseData login(@RequestParam String userName, @RequestParam String password) {
        User user = userService.getUserByUserName(userName);
        if (user != null && password.equals(user.getPassword())) {
            ResponseData responseData = ResponseData.ok();
            user.setPassword("");
            responseData.putDataValue("user", user);
            String token = JWT.sign(user, 30L * 24L * 3600L * 1000L);
            if (token != null) {
                responseData.putDataValue("token", token);
            }
            return responseData;
        }
        return ResponseData.customerError().putDataValue(ResponseData.ERRORS_KEY, new String[] { "用户名或者密码错误" });
    }

    @RequestMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public void toIndex(HttpServletRequest request, @RequestBody User user){
        user.setCreateTime(new Date().getTime());
        user.setUpdateTime(new Date().getTime());
        userService.addUser(user);
    }

}