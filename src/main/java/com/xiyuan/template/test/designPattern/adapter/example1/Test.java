package com.xiyuan.template.test.designPattern.adapter.example1;

/**
 * 两个已存在的系统无法直接合作，这时候需要一个中间人将其中一个转换为另一个需要的东西
 * 这个例子中：已存在 Mobile 和 Power220v，但是 Mobile 需要使用 Power5v 来充电, 所以需要一个适配器将 220v 转换为 5v
 * Power220vTo5vAdapter 就充当这个适配器
 * Created by xiyuan_fengyu on 2018/7/27 17:25.
 */
public class Test {

    public static void main(String[] args) {
        Mobile mobile = new Mobile();
        Power220v power220v = new Power220v();
//        mobile.charge(power220v); // 不能直接用power220v充电
        Power220vTo5vAdapter power220vTo5vAdapter = new Power220vTo5vAdapter(power220v);
        mobile.charge(power220vTo5vAdapter);
    }

}
