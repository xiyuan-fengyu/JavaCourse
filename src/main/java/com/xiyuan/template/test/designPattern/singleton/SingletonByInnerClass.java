package com.xiyuan.template.test.designPattern.singleton;

/**
 * 内部类在第一次访问 getInstance 才会被加载，内部类加载的时候才会 实例化 instance
 * 基于类加载，所以是线程安全的
 * Created by xiyuan_fengyu on 2018/7/26 16:37.
 */
public class SingletonByInnerClass {

    private static class Holder {

        private static final SingletonByInnerClass instance = new SingletonByInnerClass();

    }

    private SingletonByInnerClass() {
    }

    public static SingletonByInnerClass getInstance() {
        return Holder.instance;
    }

}
