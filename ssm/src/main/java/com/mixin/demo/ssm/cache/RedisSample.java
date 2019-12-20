package com.mixin.demo.ssm.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisSample {

    @Autowired
    private RedisTemplate redisTemplate;

    public void set() {
        redisTemplate.opsForValue().set("test:set1", "testValue1");
        redisTemplate.opsForSet().add("test:set2", "asdf");
        redisTemplate.opsForHash().put("hash1", "name1", "lms1");
        redisTemplate.opsForHash().put("hash1", "name2", "lms2");
        redisTemplate.opsForHash().put("hash1", "name3", "lms3");
        System.out.println(redisTemplate.opsForValue().get("test:set"));
        System.out.println(redisTemplate.opsForHash().get("hash1", "name1"));
    }

}
