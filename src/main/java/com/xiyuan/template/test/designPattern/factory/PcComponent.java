package com.xiyuan.template.test.designPattern.factory;

/**
 * Created by xiyuan_fengyu on 2018/7/19 8:58.
 */
public interface PcComponent {

    enum Types {
        Cpu,
        Memory,
    }

    default String name() {
        return this.getClass().getSimpleName();
    }

    String description();

}
