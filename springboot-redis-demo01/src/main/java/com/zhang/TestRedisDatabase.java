package com.zhang;

import com.utils.RedisUtil;
import redis.clients.jedis.Jedis;

/**
 * @Author zhangxinrun(OS - > zhang)
 * @Date 2021/4/17 10:50
 * @Version 1.0
 */
public class TestRedisDatabase {
    /**
     * 测试数据库操作指令
     * @param args
     */
    public static void main(String[] args) {
        // 创建jedis客户端对象
        Jedis jedis = RedisUtil.getLocalJedis();

        //选择0号库
        jedis.select(1);

        //清空当前数据库
        String flushDB = jedis.flushDB();
        System.out.println("flushDB = " + flushDB);
        if("OK".equals(flushDB)){
            System.out.println("清空当前数据库成功！！！");
        }

        //清空所有数据库
        String flushAll = jedis.flushAll();
        System.out.println("flushAll = " + flushAll);
        if("OK".equals(flushAll)){
            System.out.println("清空所有数据库成功！！！");
        }

        //释放资源
        jedis.close();
    }
}
