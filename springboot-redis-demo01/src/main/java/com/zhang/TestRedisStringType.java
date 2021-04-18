package com.zhang;

import com.utils.RedisUtil;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @Author zhangxinrun(OS - > zhang)
 * @Date 2021/4/17 11:50
 * @Version 1.0
 */
public class TestRedisStringType {
    /**
     * 测试Redis数据库中的String类型
     * @param args
     */
    public static void main(String[] args) {
        Jedis jedis = RedisUtil.getLocalJedis();

        String name = "name",birth = "birth",gender = "gender",age = "age",address = "address",card = "card",manager = "manager",nember = "nember";

        //1、设置一个key/value
        String setCompany = jedis.set("company", "alibaba");
        System.out.println("OK".equals(setCompany) ? "设置成功" : "设置失败");

        //2、取得一个key对应的value
        String company = jedis.get("company");
        System.out.println("company = " + company);

        //3、一次性设置多个key/value
        String mset = jedis.mset("kpi", "指标名称", "department", "第五分公司");
        System.out.println("OK".equals(mset) ? "设置成功" : "设置失败");
        System.out.println("------------------------------------------------------------");

        //4、一次性取得多个key对应的value
        List<String> mget = jedis.mget("kpi", "department");
        mget.forEach(key -> System.out.println(key));
        System.out.println("------------------------------------------------------------");

        //5、获得原始值，设置新的值，返回原来值
        String setNewName = jedis.getSet("name", "xiaoxiaozhang");
        System.out.println(name + "原来的值：" + setNewName);
        System.out.println(name + "修改之后的值：" + jedis.get(name));
        
        //6、返回对应key值的value的长度
        Long nameLength = jedis.strlen("name");
        System.out.println(name + "的value的长度为：" + nameLength);

        //7、往对应的key追加后的长度
        Long appendName = jedis.append(name, " is a boy");
        System.out.println("追加在"+ name +"之后的长度：" + appendName);

        //8、截取value的内容
        String getrange = jedis.getrange(name, 0, -1);
        System.out.println("截取之后的值为：" + getrange);

        //9、设置一个key存活的有效期（秒）
        //String setex = jedis.setex(birth, 1000000, name);
        //System.out.println(setex);

        //10、设置一个key存活的有效期（毫秒）
        //String psetex = jedis.psetex(age, 10000000, name);
        //System.out.println(psetex);

        //11、存在则不做任何操作，如果不存在，则添加
        Long setnx = jedis.setnx("xiaozhang", "a good boy");
        System.out.println(setnx > 0 ? "设置成功" : "已存在，设置失败");

        //12、同时设置多个key只要有一个存在,则都不会保存（原子性）
        Long msetnx = jedis.msetnx("xiaohuang","xiaohuang");
        System.out.println(msetnx > 0 ? "设置成功" : "设置失败，其中存在一个已经存在");

        //13、对数值类型的进行-1操作
        Long decr = jedis.decr(nember);
        System.out.println("减一之后的值：" + decr);

        //14、根据提供的数值进行减法操作
        Long aLong = jedis.decrBy(nember, 10);
        System.out.println("减10之后的值：" + aLong);

        //15、对数值类型进行+1操作
        Long incr = jedis.incr(nember);
        System.out.println("加一之后的值：" + incr);

        //16、根据提供的数值进行加法操作
        Long aLong1 = jedis.incrBy(nember, 50);
        System.out.println("加50之后的值：" + aLong1);

        //17、根据提供的额数据加入浮点数
        Double aDouble = jedis.incrByFloat(nember, 13.14);
        System.out.println("加入浮点数之后的值：" + aDouble);

        jedis.close();
    }
}
