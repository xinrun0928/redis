package com.zhang.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@SpringBootTest
class SpringBootRedisDataApplicationTests3 {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testBound(){
        //设置key的序列化方式
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //设置hash的key的序列化方式
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        //对字符串类型key进行绑定，之后所有操作都是基于这个key
        BoundValueOperations<String, String> name = stringRedisTemplate.boundValueOps("name");
        name.set("xiaozhang");
        name.append(" is a good person");

        String s = name.get();
        System.out.println(s);

        /**
         * 1、针对于日后处理key  value都是 String 使用 StringRedisTemplate
         * 2、针对于日后处理key  value存在对象  使用 RedisTemplate
         * 3、针对于同一个一个key多次操作可以使用boundXxxOps（） value list set zset hash的api简写
         */

        /**
         * redis应用场景
         *
         *  1、利用redis 中字符串类型完成  项目中手机验证码存储的实现
         *  2、利用redis 中字符串类型完成  具有实效性业务功能   12306   淘宝  订单还有：40分钟
         *  3、利用redis 分布式集群系统中  session共享   memcache
         */
    }
}

