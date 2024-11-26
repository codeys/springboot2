package com.springboot2.controller;

import com.springboot2.entity.User;
import com.springboot2.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @title WebContrller
 * @description
 * @create 2024/10/30 15:27
 */

@RestController
public class WebController {

    @Autowired
    UserServiceImpl userService;
    @Value("${spring.datasource.url}")
    String jdbcUrl;

    @RequestMapping("/getUserById/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        return user;
    }

    @RequestMapping("/saveUser")
    public String saveUser() {
        userService.saveUser();
        return "success";
    }

    @RequestMapping("/getUsers")
    public List<User> getUsers(@Valid @RequestBody User user,BindingResult bindingResult) {
        return userService.getUsers();
    }
}
