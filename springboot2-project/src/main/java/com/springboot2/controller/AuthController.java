package com.springboot2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Administrator
 * @version 1.0
 * @title AuthController
 * @description 认证
 * @create 2025/1/6 9:21
 */

@Controller
@RequestMapping("/auth")
public class AuthController {

    /**
     * OAuth2.0 登录回调接口
     * https://api.weibo.com/oauth2/authorize?client_id=YOUR_CLIENT_ID&response_type=code&redirect_uri=YOUR_REGISTERED_REDIRECT_URI
     * https://api.weibo.com/oauth2/access_token?client_id=YOUR_CLIENT_ID&client_secret=YOUR_CLIENT_SECRET&grant_type=authorization_code&redirect_uri=YOUR_REGISTERED_REDIRECT_URI&code=CODE
     *
     * @return
     */
    @RequestMapping("/authSuccessCallBack")
    public String authSuccessCallBack(@RequestParam("code") String code) {
        // 1. 获取授权码
        // 2. 根据授权码获取access_token
        // 3. 根据access_token获取个人信息
        // 4. 成功重定向到首页，失败重定向到登录页
        return "redirect:/index.html";
    }

}
