package com.xiyuan.template.test.designPattern.proxy;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

/**
 * Created by xiyuan_fengyu on 2018/8/2 17:21.
 */
public abstract class CglibProxy<T> implements Callback, MethodInterceptor {

    protected T origin;

    public CglibProxy(T origin) {
        this.origin = origin;
    }

    @SuppressWarnings("unchecked")
    public T proxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.origin.getClass());
        enhancer.setCallback(this);
        return (T) enhancer.create();
    }

}
