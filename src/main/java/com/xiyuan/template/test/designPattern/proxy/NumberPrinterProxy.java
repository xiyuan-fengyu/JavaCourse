package com.xiyuan.template.test.designPattern.proxy;

import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by xiyuan_fengyu on 2018/8/2 17:29.
 */
public class NumberPrinterProxy extends CglibProxy<NumberPrinter> {

    private String printMethod;

    public NumberPrinterProxy(NumberPrinter origin) {
        super(origin);
        try {
            printMethod = NumberPrinter.class.getDeclaredMethod("print", int.class).toString();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        if (method.toString().equals(printMethod)) {
            if (((int) objects[0]) % 2 == 0) {
                return method.invoke(origin, objects);
            }
            else {
                System.out.println("call print is refused");
                return null;
            }
        }
        else return method.invoke(origin, objects);
    }

}
