package com.xiyuan.template.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Method;

/**
 * Created by xiyuan_fengyu on 2018/2/5 15:10.
 */
@Component
public class JedisTest {

    private final Logger logger = LoggerFactory.getLogger(JedisTest.class);

    @Autowired
    private Jedis jedis;

    @Autowired
    private Jedis jedis2;

    public void execute() {
        jedis.set("test", "just");
        System.out.println(jedis.get("test"));

        jedis.set("test", "just");
        System.out.println(jedis.get("test"));

        jedis.set("test", "just");
        System.out.println(jedis.get("test"));

        jedis2.set("test", "just2");
        System.out.println(jedis2.get("test"));

        try {
            Thread.sleep(5000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "spring/applicationContext.xml",
                "spring/springServlet.xml"
        );
        context.getBean(JedisTest.class).execute();
    }

}
