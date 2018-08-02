package com.xiyuan.template.test.designPattern.proxy;

/**
 * Created by xiyuan_fengyu on 2018/8/2 17:34.
 */
public class Test {

    public static void main(String[] args) {
        NumberPrinter numberPrinter = new NumberPrinterProxy(new NumberPrinter()).proxy();
        for (int i = 0; i < 10; i++) {
            numberPrinter.print(i);
        }
    }

}