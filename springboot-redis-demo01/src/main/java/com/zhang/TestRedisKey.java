package com.zhang;

import com.utils.RedisUtil;
import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * @Author zhangxinrun(OS - > zhang)
 * @Date 2021/4/17 11:05
 * @Version 1.0
 */
public class TestRedisKey {
    /**
     * 测试key相关命令
     * @param args
     */
    public static void main(String[] args) {
        Jedis jedis = RedisUtil.getLocalJedis();

        String name = "name",birth = "birth",gender = "gender",age = "age",address = "address",card = "card",manager = "manager";

        //判断redis中是否存在name（key）
        boolean exists = jedis.exists(name);
        System.out.println("是否存在(" + name + ")：" + exists);

        if(exists){
            //删除单个或多个指定key，返回删除的数量
            Long delName = jedis.del(name);
            System.out.println(delName);
        }

        if(jedis.exists(birth)){
            //给指定的key设置生存时间（设置秒），设置成功则返回1
            Long birthLive = jedis.expire("birth", 1000);
            if(birthLive == 1){
                System.out.println(birth + "存活时间设置成功（秒级别）");
            }
        }

        //获得所有的key
        Set<String> allKeys = jedis.keys("*");
        allKeys.forEach(key -> System.out.print(key + ", "));
        System.out.println();

        if(jedis.exists(gender)){
            //将指定key移动到指定库
            Long moveGenderToSelect1 = jedis.move(gender, 1);
            if(moveGenderToSelect1 == 1){
                System.out.println("将" + gender + "移动到一号库成功" + moveGenderToSelect1);
            }
        }

        if(jedis.exists(age)){
            //设置存活时间，以毫秒为单位
            Long pexpire = jedis.pexpire(age, 1000000);
            if(pexpire == 1){
                System.out.println(age + "存活时间设置成功（毫秒级别）");
            }
        }

        if(jedis.exists(address)){
            //设置存活时间，以毫秒为单位
            Long aLong = jedis.pexpireAt(address, 10000000);
            if(aLong == 1){
                System.out.println(address + "存活时间设置成功（毫秒级别）");
            }
        }

        if(jedis.exists(birth)){
            //查看key存活时间（秒级别）
            Long ttlBirth = jedis.ttl(birth);
            System.out.println(birth + "生存时间剩余" + ttlBirth + "秒");
        }

        if(jedis.exists(age)){
            //查看key存活时间（毫秒级别）
            Long pttlAge = jedis.pttl(age);
            System.out.println(age + "生存时间剩余" + pttlAge + "毫秒");
        }

        //随机获取一个key
        String randomKey = jedis.randomKey();
        System.out.println("随机获得的key是：" + randomKey);

        if(jedis.exists(card)){
            //将指定key重命名
            String rename = jedis.rename("card", "newCard");
            if("OK".equals(rename)){
                System.out.println("将"+ card +"重命名之后，新名字叫newCard");
            }
        }

        if(jedis.exists(manager)){
            //查看key对应的数据类型
            String managerType = jedis.type(manager);
            System.out.println(manager + "的数据类型为：" + managerType);
        }

        //释放资源
        jedis.close();
    }
}
