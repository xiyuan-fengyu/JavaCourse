package com.xiyuan.template.test.designPattern.singleton;

/**
 * 第一次加载这个类的时候就会实例化 Instance
 * 基于类加载，所以是线程安全的
 * Created by xiyuan_fengyu on 2018/7/26 16:39.
 */
public enum SingletonByEnum {
    Instance;

}
