package com.springboot2.service;

import com.alibaba.fastjson2.JSON;
import com.springboot2.entity.User;
import com.springboot2.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @version 1.0
 * @title WebServiceImpl
 * @description
 * @create 2024/10/30 15:28
 */
@Slf4j
@Service
public class UserServiceImpl {

    @Autowired
    UserMapper userMapper;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    RedissonClient redissonClient;
    @Value("${spring.datasource.url}")
    String jdbcUrl;

    @Cacheable(cacheNames = "user",key = "#id")
    public User getUserById(Long id) {
        log.info("查询数据库。。。。。。");
        User user = userMapper.selectById(id);
        return user;
    }

    @CachePut(cacheNames = "user" ,key = "#result.id")
    public User saveUser() {
        User user = new User();
        user.setName("张三");
        user.setAge(20);
        userMapper.insert(user);
        return user;
    }

    public List<User> getUsers() {
        Object redisUser = redisTemplate.opsForValue().get("user");
        List<User> userList = new ArrayList<>();
        if (redisUser != null) {
            // 读取缓存
            return JSON.parseArray(JSON.toJSONString(redisUser),User.class);
        }

        RLock userLock = redissonClient.getLock("userLock");
        try {
            boolean tryLock = userLock.tryLock(100, 30, TimeUnit.SECONDS);
            if (tryLock) {
                userList = getUsersByDb();
                Thread.sleep(30000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (userLock.isHeldByCurrentThread()) {
                userLock.unlock();
            }
        }

        return userList;
    }

    public List<User> getUsersByDb() {
        Object redisUser = redisTemplate.opsForValue().get("user");
        if (redisUser != null) {
            // 读取缓存
            return JSON.parseArray(JSON.toJSONString(redisUser),User.class);
        }
        log.info("查询数据。。。。。。");
        List<User> userList = new ArrayList<>();
        userList = userMapper.selectList(null);
        redisTemplate.opsForValue().set("user", userList);
        return userList;
    }
}
