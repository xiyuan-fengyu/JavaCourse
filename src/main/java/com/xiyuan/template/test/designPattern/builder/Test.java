package com.xiyuan.template.test.designPattern.builder;

/**
 * 建造者模式
 * 对象的生产流程是确定的，但是创建过程比较复杂灵活，参数也是可选多变的
 * 这时候可以通过Builder模式
 * Created by xiyuan_fengyu on 2018/7/26 17:21.
 */
public class Test {

    public static void main(String[] args) {
        Pc pc = new Pc.Builder()
                .setCpu("i7 7700k")
                .setMemory("GTX 1080")
                .build();
        System.out.println(pc);
    }

}
