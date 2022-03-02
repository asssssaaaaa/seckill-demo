package com.xxxx.seckill;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.xxxx.seckill.utils.UUIDUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.script.RedisScript;

import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class SeckillDemoApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    RedisScript redisScript;

    @Test
    void testLock01() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        //占位，如果key不存在才能设置成功
        Boolean isLock = valueOperations.setIfAbsent("k1", "v1");
        //如果占位成功，进行正常使用
        if (isLock) {
            valueOperations.set("name", "xxxx");
            String name = (String) valueOperations.get("name");
            System.out.println(name);
            //操作结束删除锁
            redisTemplate.delete("k1");
        } else {
            System.out.println("有线程在使用，请稍后再试");
        }

    }

    @Test
    void testLock03() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String value = UUID.randomUUID().toString();
        Boolean lock = valueOperations.setIfAbsent("k1",value , 5, TimeUnit.SECONDS);
        if (lock){
            valueOperations.set("name","xxxx");
            Object name = valueOperations.get("name");
            System.out.println(name);
            System.out.println(valueOperations.get("k1"));
            Boolean k1 = (Boolean) redisTemplate.execute(redisScript, Collections.singletonList("k1"), value);
            System.out.println(k1);

        }else {
            System.out.println("有线程在使用，请稍后");
        }

    }

}
