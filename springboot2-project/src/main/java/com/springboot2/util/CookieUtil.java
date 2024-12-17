package com.springboot2.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

    // 默认缓存时间,单位/秒
    public static final int COOKIE_MAX_AGE = 300;
    // 保存路径,根路径
    private static final String COOKIE_PATH = "/";


    /**
     * 添加cookie,保存登录状态
     *
     * @param name  cookie name
     * @param value cookie value
     */
    public static void addCookie(String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath(COOKIE_PATH);
        cookie.setMaxAge(COOKIE_MAX_AGE);
        getResponse().addCookie(cookie);
    }

    /**
     * 通过cookieKey获取value
     *
     * @param key 查询cookie的key
     * @return
     */
    public static String getValue(String key) {
        Cookie[] cookies = getRequest().getCookies();
        if (cookies == null) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(key)) {
                return cookie.getValue();
            }
        }
        return null;
    }

    /**
     * 获取request
     *
     * @return
     */
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取request
     *
     * @return
     */
    public static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }
}
