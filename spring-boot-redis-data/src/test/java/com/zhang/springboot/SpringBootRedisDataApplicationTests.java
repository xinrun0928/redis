package com.zhang.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class SpringBootRedisDataApplicationTests {

    //注入StringRedisTemplate
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 操作redis中key相关
     */
    @Test
    public void testKey(){
        //删除一个key
        stringRedisTemplate.delete("name");

        //判断某个key是否存在
        Boolean name = stringRedisTemplate.hasKey("name");
        System.out.println("name是否存在：" + name);

        //判断key对应值的类型
        DataType name1 = stringRedisTemplate.type("name");
        System.out.println("name的类型为" + name1);

        //获取redis中所有key
        Set<String> keys = stringRedisTemplate.keys("*");
        keys.forEach(key -> System.out.println(key));

        //获取key超时时间
        Long name2 = stringRedisTemplate.getExpire("name");
        System.out.println(name2 == -1 ? "存在" : "不存在");

        //在redis中随机获得一个key
        String s = stringRedisTemplate.randomKey();
        System.out.println("随机获得的key：" + s);
    }

    /**
     * 操作redis中字符串 opsForValue   实际操作就是redis中String类型
     */
    @Test
    public void testString() {
        stringRedisTemplate.opsForValue().set("name","xiaozhang");
        String name = stringRedisTemplate.opsForValue().get("name");
        System.out.println("name的值为：" + name);

        //设置一个超时时间
        //第三个参数为时间，第四个参数为时间单位
        stringRedisTemplate.opsForValue().set("code","2234",120, TimeUnit.SECONDS);

        //追加指定key的value
        stringRedisTemplate.opsForValue().append("name"," is a simgle boy");
    }

    /**
     * 操作redis中list类型      opsForList就是操作redis中的list类型
     */
    @Test
    public void testList(){
        //放入一个元素到list
        stringRedisTemplate.opsForList().leftPush("names","xiaozhang");

        //放入一个或多个元素到list
        stringRedisTemplate.opsForList().leftPushAll("names","xiaohuang","xiaoliu");

        //遍历
        List<String> names = stringRedisTemplate.opsForList().range("names", 0, -1);
        names.forEach(name -> System.out.println(name));
    }

    /**
     * 操作redis中set类型    opsForSet就是操作redis中的set类型
     */
    @Test
    public void testSet(){
        //创建set 并放入元素
        stringRedisTemplate.opsForSet().add("sets","a","b","c","d","e","f","g","h","i");

        //取得所有set成员
        Set<String> sets = stringRedisTemplate.opsForSet().members("sets");
        sets.forEach(set -> System.out.println(set));
    }


    /**
     * 操作redis中Zset类型    opsForZSet就是操作redis中的Zset类型
     */
    @Test
    public void testZSet(){
        //创建zset 并放入元素
        stringRedisTemplate.opsForZSet().add("zsets","zhangsan",100);

        //指定范围查询
        Set<String> zsets = stringRedisTemplate.opsForZSet().range("zsets", 0, -1);
        zsets.forEach(zset -> System.out.println(zset));
    }

    /**
     * 操作redis中Hash类型    opsForZSet就是操作redis中的Hash类型
     */
    @Test
    public void testHash(){
        //创建zset 并放入元素
        stringRedisTemplate.opsForHash().put("map","name","zhangsan");

        //获取所有key
        Set<Object> map = stringRedisTemplate.opsForHash().keys("map");
        map.forEach(m -> System.out.println(m));

        System.out.println("--------------------------------------");

        //获取所有value
        List<Object> map1 = stringRedisTemplate.opsForHash().values("map");
        map1.forEach(map3 -> System.out.println(map3));
    }
}

