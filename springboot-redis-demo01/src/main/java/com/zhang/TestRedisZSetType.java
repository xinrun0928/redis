package com.zhang;

import com.utils.RedisUtil;
import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * @Author zhangxinrun(OS - > zhang)
 * @Date 2021/4/17 11:50
 * @Version 1.0
 */
public class TestRedisZSetType {
    /**
     * 测试Redis数据库中的ZSet类型
     * @param args
     */
    public static void main(String[] args) {
        Jedis jedis = RedisUtil.getLocalJedis();

        //1、向集合中添加元素
        Long zsets = jedis.zadd("zsets", 12, "32");
        System.out.println(zsets > 0 ? "添加成功" : "添加失败，此元素已存在");

        //2、返回集合的元素个数
        Long zsets1 = jedis.zcard("zsets");
        System.out.println("集合中元素的个数为：" + zsets1);

        //3、返回一个范围内的元素
        Set<String> zsets2 = jedis.zrange("zsets", 0, -1);
        zsets2.forEach(s -> System.out.print(s + ", "));
        System.out.println();

        //4、按照分数查找一个范围内的元素
        Set<String> strings = jedis.zrangeByScore("zsets", 10, 100);
        strings.forEach(ss -> System.out.print(ss + ", "));
        System.out.println();

        //5、返回排名
        Long zrank = jedis.zrank("zsets", "ruanjian");
        System.out.println("ruanjian的排名为：" + zrank);

        //6、倒序排名
        Long zrank1 = jedis.zrevrank("zsets", "ruanjian");
        System.out.println("ruanjian的倒序排名为：" + zrank1);

        //7、显示某一个元素的分数
        Double zscore = jedis.zscore("zsets", "ruanjian");
        System.out.println("ruanjian的分数为：" + zscore);

        //8、移除某个元素
        Long zrem = jedis.zrem("zsets", "ruanjian");
        System.out.println(zrem > 0 ? "移除成功" : "移除失败");

        //9、给特定元素加分
        Double zincrby = jedis.zincrby("zsets", 100, "huazhuangping");
        System.out.println("给huazhuangping加完分之后的分数是：" + zincrby);


        jedis.close();
    }
}
