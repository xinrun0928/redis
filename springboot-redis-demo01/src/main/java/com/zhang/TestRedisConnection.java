package com.zhang;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * @Author zhangxinrun(OS - > zhang)
 * @Date 2021/4/17 10:51
 * @Version 1.0
 */
public class TestRedisConnection {
    /**
     * 测试redis连接
     * @param args
     */
    public static void main(String[] args) {
        // 创建jedis客户端对象（连接windows本地redis数据库，默认端口号6379）
        Jedis jedis = new Jedis("localhost", 6379);
        // 选择使用一个库，默认使用  0   号库
        jedis.select(0);

        // 获取redis中所有的key信息
        Set<String> keys = jedis.keys("*");
        keys.forEach((key) -> {
            System.out.println("key = " + key);
        });

        //释放资源
        jedis.close();
    }
}
