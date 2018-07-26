package com.xiyuan.template.test.designPattern.factory;

/**
 * Created by xiyuan_fengyu on 2018/7/19 9:09.
 */
public class Test {

    public static void main(String[] args) {
        PcComponentFactory factory = new PcComponentFactory();

        PcComponent cpu = factory.create(PcComponent.Types.Cpu);
        System.out.println(cpu.name());
        System.out.println(cpu.description());

        PcComponent memory = factory.create(PcComponent.Types.Memory);
        System.out.println(memory.name());
        System.out.println(memory.description());
    }

}
