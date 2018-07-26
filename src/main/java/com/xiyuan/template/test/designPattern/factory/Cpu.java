package com.xiyuan.template.test.designPattern.factory;

/**
 * Created by xiyuan_fengyu on 2018/7/19 9:00.
 */
public class Cpu implements PcComponent {

    Cpu() {

    }

    @Override
    public String description() {
        return "中央处理器（CPU，Central Processing Unit）是一块超大规模的集成电路，是一台计算机的运算核心（Core）和控制核心（ Control Unit）。它的功能主要是解释计算机指令以及处理计算机软件中的数据。";
    }

}
