package com.springboot2;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboot2.entity.User;
import com.springboot2.job.MyJob;
import com.springboot2.mapper.UserMapper;
import com.springboot2.util.TaskSchedulingService;
import javafx.concurrent.Task;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@SpringBootTest
class Springboot2ProjectApplicationTests {

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void testRedis() {
        User user = JSON.parseObject(JSON.toJSONString(redisTemplate.opsForValue().get("1851797497761034242")), User.class);
        log.info(JSON.toJSONString(user));

//        User user = new User();
//        user.setName("test1");
//        user.setCreateTime(LocalDateTime.now());
//        redisTemplate.opsForValue().set("test1", user);
    }

    @Test
    void testDb() {
//        List<User> users = userMapper.selectList(null);
//        log.info("users:{}", JSON.toJSONString(users));

//        User user = new User();
//        user.setAge(10);
//        user.setName("yu");
//        userMapper.insert(user);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "yu");
        int delete = userMapper.delete(queryWrapper);
        log.info("删除总数：{}", delete);
    }

}
