package com.zhang;

import com.utils.RedisUtil;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author zhangxinrun(OS - > zhang)
 * @Date 2021/4/17 11:51
 * @Version 1.0
 */
public class TestRedisHashType {
    /**
     * 测试Redis数据库中的Hash类型
     * @param args
     */
    public static void main(String[] args) {
        Jedis jedis = RedisUtil.getLocalJedis();

        //1、设置一个key/value键值对
        Long hset = jedis.hset("hashs", "money", "613278461");
        System.out.println(hset > 0 ? "插入成功" : "插入失败，key值已重复");

        //2、获得一个key对应的value
        String hget = jedis.hget("hashs", "money");
        System.out.println("money对应的value值是：" + hget);

        //3、获得所有的key/value键值对
        Map<String, String> map = jedis.hgetAll("hashs");
        for(String m : map.keySet()){
            String value = map.get(m);
            System.out.println(m + ":" + value);
        }

        //4、删除某一个key/value键值对
        Long hdel = jedis.hdel("hashs", "age");
        System.out.println(hdel > 0 ? "删除成功" : "删除失败");


        //5、判断一个key是否存在
        Boolean hexists = jedis.hexists("hashs", "age");
        System.out.println(hexists);

        //6、获得所有key
        Set<String> hashs = jedis.hkeys("hashs");
        hashs.forEach(hash -> System.out.print(hash + ", "));
        System.out.println();

        //7、获得所有的value
        List<String> hashs1 = jedis.hvals("hashs");
        hashs1.forEach(hh -> System.out.print(hh + ", "));
        System.out.println();

        //8、设置多个key/value
        Map<String, String> map1 = new HashMap<>();
        map1.put("age","10000");
        String hashs2 = jedis.hmset("hashs", map1);
        System.out.println("OK".equals(hashs2) ? "添加成功" : "添加失败");

        //9、获得多个key/value
        List<String> hmget = jedis.hmget("hashs", "name", "age");
        hmget.forEach(hm -> System.out.print(hm + ", "));
        System.out.println();

        //10、设置一个不存在key的值
        Long hsetnx = jedis.hsetnx("hhh", "sadf", "asdf");
        System.out.println(hsetnx > 0 ? "添加成功" : "添加失败");

        jedis.close();
    }
}
