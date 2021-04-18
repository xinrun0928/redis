package com.zhang;

import com.utils.RedisUtil;
import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * @Author zhangxinrun(OS - > zhang)
 * @Date 2021/4/17 11:50
 * @Version 1.0
 */
public class TestRedisSetType {
    /**
     * 测试Redis数据库中的String类型
     * @param args
     */
    public static void main(String[] args) {
        Jedis jedis = RedisUtil.getLocalJedis();

        //1、向set集合中添加元素
        Long sadd = jedis.sadd("sets", "xiaohei", "xiaobai", "xiaohuang");
        System.out.println("添加的元素个数：" + sadd);

        //2、显示集合中的所有元素（无序）
        Set<String> sets = jedis.smembers("sets");
        sets.forEach(set -> System.out.print(set + ", "));
        System.out.println();

        //3、返回set集合中元素的个数
        Long sets1 = jedis.scard("sets");
        System.out.println("set结合中元素的个数" + sets1);

        //4、随机返回一个元素，并将元素在集合中删除
        String sets2 = jedis.spop("sets");
        System.out.println("删除的元素：" + sets2);

        //5、删除指定元素
        Long ddd = jedis.srem("sets","ddd");
        System.out.println(ddd > 0 ? "删除成功" : "删除失败");

        //6、判断一个集合中是否含有某个元素
        jedis.sismember("sets","xxx");

        //7、随机返回元素
        String sets3 = jedis.srandmember("sets");
        System.out.println("随机返回的元素：" + sets3);

        jedis.close();
    }
}
