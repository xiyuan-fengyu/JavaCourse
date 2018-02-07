package com.xiyuan.template.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.PreDestroy;
import java.lang.reflect.Method;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by xiyuan_fengyu on 2017/12/1 14:35.
 */
public class JedisWrapper implements FactoryBean<Jedis>, MethodInterceptor {

    private final Logger logger = LoggerFactory.getLogger(JedisWrapper.class);

    private JedisPool jedisPool;

    private final ConcurrentHashMap<ThreadAndJedisProxy, JedisCache> jedisCaches = new ConcurrentHashMap<>();

    private final AtomicBoolean jedisReleaseLock = new AtomicBoolean(true);

    private final long jedisReleaseAfter = 2000;

    private final Timer jedisReleaseTimer = new Timer(true);

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
        jedisReleaseLock.set(false);
        jedisPool.destroy();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object result;
        JedisCache jedisCache = jedisFromCaches(o);
        try {
            logger.debug(String.format("jedis method proxy: %s, %s", jedisCache.jedis, method));
            result = method.invoke(jedisCache.jedis, objects);
        }
        finally {
            jedisCache.releaseAfter(jedisReleaseAfter);
        }
        return result;
    }

    private JedisCache jedisFromCaches(final Object o) {
        return jedisCaches.compute(new ThreadAndJedisProxy(Thread.currentThread(), o), (key, oldCache) -> {
            if (oldCache != null) {
                oldCache.keep();
                return oldCache;
            }
            else return new JedisCache(jedisPool.getResource(), key);
        });
    }

    class ThreadAndJedisProxy {

        final Thread thread;

        final Object proxy;

        ThreadAndJedisProxy(Thread thread, Object proxy) {
            this.thread = thread;
            this.proxy = proxy;
        }

        @Override
        public int hashCode() {
            return thread.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof ThreadAndJedisProxy)) return false;
            if (this == obj) return true;
            ThreadAndJedisProxy temp = (ThreadAndJedisProxy) obj;
            return temp.thread == thread && temp.proxy == proxy;
        }
    }

    class JedisCache {

        Jedis jedis;

        ThreadAndJedisProxy threadAndJedisProxy;

        final AtomicLong releaseAt = new AtomicLong(-1);

        final AtomicBoolean scheduled = new AtomicBoolean(false);

        final AtomicBoolean closed = new AtomicBoolean(false);

        JedisCache(Jedis jedis, ThreadAndJedisProxy threadAndJedisProxy) {
            this.jedis = jedis;
            this.threadAndJedisProxy = threadAndJedisProxy;
        }

        void keep() {
            this.releaseAt.set(-1);
        }

        void releaseAfter(long time) {
            synchronized (scheduled) {
                this.releaseAt.set(System.currentTimeMillis() + time);
                if (!scheduled.get()) {
                    jedisReleaseTimer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            synchronized (scheduled) {
                                while (true) {
                                    long releaseTime = releaseAt.get();
                                    if (releaseTime != -1 && releaseTime <= System.currentTimeMillis()) {
                                        jedisCaches.remove(threadAndJedisProxy);
                                        close();
                                        break;
                                    }
                                    else {
                                        try {
                                            Thread.sleep(releaseTime == -1 ? jedisReleaseAfter : releaseTime - System.currentTimeMillis());
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                                scheduled.set(false);
                            }
                        }
                    }, jedisReleaseAfter);
                    scheduled.set(true);
                }
            }
        }

        void close() {
            closed.set(true);
            jedis.close();
            logger.debug(String.format("jedis(%s) released", jedis));
            jedis = null;
            threadAndJedisProxy = null;
        }

    }

}
