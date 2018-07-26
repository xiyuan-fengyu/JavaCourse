package com.xiyuan.template.test.designPattern.builder;

/**
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
