package com.xiyuan.template.test.designPattern.adapter.example1;

/**
 * Created by xiyuan_fengyu on 2018/7/27 17:18.
 */
public class Power220vTo5vAdapter extends Power5v {

    private Power220v from;

    public Power220vTo5vAdapter(Power220v from) {
        this.from = from;
        this.transform();
    }

    private void transform() {
        System.out.println("将" + this.from.get() + "v电源转换为5v电源");
    }

}
