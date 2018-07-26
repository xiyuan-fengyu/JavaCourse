package com.xiyuan.template.test.designPattern.factory;

/**
 * Created by xiyuan_fengyu on 2018/7/19 8:58.
 */
public class PcComponentFactory {

    public PcComponent create(PcComponent.Types componentType) {
        switch (componentType) {
            case Cpu:
                return new Cpu();
            case Memory:
                return new Memory();
            default:
                return null;
        }
    }

}
