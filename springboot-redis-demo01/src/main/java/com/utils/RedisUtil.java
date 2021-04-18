package com.utils;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author zhangxinrun(OS - > zhang)
 * @Date 2021/4/17 10:55
 * @Version 1.0
 */
public class RedisUtil {

    /**
     * 获取redis连接的工具类
     * @return  jedis客户端对象
     */
    public static Jedis getLocalJedis(){
        Jedis jedis = new Jedis("localhost", 6379);

        //在redis创建初始化数据,20,"ruanjian",100,"yaoxue",90,"huazhuangping",30,"shiping",203,"shengwujishu",80,"guojijiaoliu",60,"faln"
        jedis.mset("name","xiaozhang","birth","1999-09","gender","nan","age","18","address","guangdong","card","445221","manager","boss","nember","10");

        jedis.rpush("lists","zhangsan","lisi","wangwu","zhaoliu","dahuang","zhangsan","xiaohang","xiaoxin","ahuan","lisi","qiqi","weiqian","datou","xiaotou");

        jedis.sadd("sets","aaa","bbb","ccc","ddd","eee","fff","ggg","hhh");

        jedis.zadd("zsets",10,"jianshe");
        jedis.zadd("zsets",20,"ruanjian");
        jedis.zadd("zsets",30,"huazhuangping");

        Map<String, String> map = new HashMap<>();
        map.put("name","xiaozhang");
        map.put("age","18");
        map.put("sex","nan");
        map.put("card","123456");
        jedis.hmset("hashs",map);

        return jedis;
    }
}
