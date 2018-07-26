package com.xiyuan.template.test.designPattern.factory;

/**
 * Created by xiyuan_fengyu on 2018/7/19 9:00.
 */
public class Memory implements PcComponent {

    Memory() {

    }

    @Override
    public String description() {
        return "内存(Memory)也被称为内存储器，其作用是用于暂时存放CPU中的运算数据，以及与硬盘等外部存储器交换的数据。";
    }

}
