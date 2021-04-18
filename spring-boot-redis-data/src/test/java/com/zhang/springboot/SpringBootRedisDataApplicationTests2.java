package com.zhang.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Date;
import java.util.UUID;

@SpringBootTest
class SpringBootRedisDataApplicationTests2 {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 操作redis
     */
    @Test
    public void testRedisTemplate(){
        User user = new User();
        //修改key序列化方案    String类型序列
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //修改hash key序列化方案   String类型序列
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        user.setId(UUID.randomUUID().toString()).setAge(12).setName("xiaozhang").setBir(new Date());
        redisTemplate.opsForValue().set("user",user);//redis进行设置，对象需要经过序列化

        User user1 = (User) redisTemplate.opsForValue().get("user");
        System.out.println(user1);

        redisTemplate.opsForList().leftPush("lists",user);

        redisTemplate.opsForSet().add("sets",user);

        redisTemplate.opsForZSet().add("zsets",user,10);

        redisTemplate.opsForHash().put("maps","user",user);
    }
}

