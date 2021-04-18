package com.zhang;

import com.utils.RedisUtil;
import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @Author zhangxinrun(OS - > zhang)
 * @Date 2021/4/17 11:50
 * @Version 1.0
 */
public class TestRedisListType {
    /**
     * 测试Redis数据库中的List类型
     * @param args
     */
    public static void main(String[] args) {
        Jedis jedis = RedisUtil.getLocalJedis();

        String lists1 = "lists1";

        //1、将某个或多个值加入到一个key列表的头部
        Long lpush = jedis.lpush("lists1", "s1", "s2", "s3");
        System.out.println("插入之后lists1集合中的元素个数：" + lpush);

        //2、将某个或多个值加入到一个key列表的头部，但是必须保证key存在
        if(jedis.exists(lists1)){
            Long lists1Head = jedis.lpushx("lists1", "1", "2", "3", "4");
            System.out.println("插入之后lists1集合中的元素个数：" + lists1Head);
        }

        //3、将某个或多个值加入到一个key列表的末尾
        Long lists = jedis.rpush("lists", "1", "2", "3", "4", "5");
        System.out.println("插入之后lists集合中的元素个数：" + lists);

        //4、将某个或多个值加入到一个key列表的末尾，但是必须保证key存在
        Long lists2 = jedis.rpushx("lists", "5", "7", "6", "4", "2");
        System.out.println("插入之后lists集合中的元素个数：" + lists2);

        //5、返回和移除列表左边的一个元素
        String lists3 = jedis.lpop("lists");
        System.out.println("删除lists集合左边的元素是：" + lists3);

        //6、返回和移除列表右边的一个元素
        String lists4 = jedis.rpop("lists");
        System.out.println("删除lists结合右边的元素是：" + lists4);

        //7、获取某一个下标区间内的元素
        List<String> lists5 = jedis.lrange("lists", 0, -1);
        System.out.print("lists集合中的所有元素有：");
        lists5.forEach(list -> {
            System.out.print(list + ", ");
        });

        //8、获取列表元素个数
        Long list = jedis.llen("list");
        System.out.println("lists中元素的个数是：" + list);

        //9、设置某一个指定索引的值（索引必须存在）
        if(jedis.exists("lists") && jedis.llen("lists") > 50){
            String lset = jedis.lset("lists", 49, "我是替换之后的值");
            System.out.println("OK".equals(lset) ? "设置成功" : "设置失败");
        }

        //10、获取某一个指定索引位置的元素
        if(jedis.exists("lists") && jedis.llen("lists") > 100){
            String lists6 = jedis.lindex("lists", 99);
            System.out.println("获取索引为99的元素为：" + lists6);
        }

        //11、删除重复元素
        Long lists6 = jedis.lrem("lists", 30, "2");
        System.out.println("删除个数为：" + lists6);
        System.out.println(lists6 == 30 ? "已删除30个重复元素" : "删除30个元素不足");

        //12、保留列表中特定区间内的元素
        String lists7 = jedis.ltrim("lists", 0, 10000);
        System.out.println("OK".equals(lists7) ? "保留成功" : "fail");

        //13、在某一个元素之前，之后插入新元素
        Long linsert = jedis.linsert("lists", BinaryClient.LIST_POSITION.AFTER, "1", "hhh");
        System.out.println(linsert);

        jedis.close();
    }
}
