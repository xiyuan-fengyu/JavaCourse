package com.xiyuan.template.redis;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.PreDestroy;
import java.lang.reflect.Method;

/**
 * Created by xiyuan_fengyu on 2017/12/1 14:35.
 */
public class JedisWrapper implements FactoryBean<Jedis>, MethodInterceptor {

    private JedisPool jedisPool;

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    @Override
    public Jedis getObject() throws Exception {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Jedis.class);
        enhancer.setCallback(this);
        return (Jedis) enhancer.create();
    }

    @Override
    public Class<?> getObjectType() {
        return Jedis.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @PreDestroy
    private void destory() {
        jedisPool.destroy();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object result;
        try (Jedis jedis = jedisPool.getResource()) {
            result = method.invoke(jedis, objects);
        }
        return result;
    }

}
