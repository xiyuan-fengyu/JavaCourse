package com.xiyuan.template.test.designPattern.observer;

/**
 * Created by xiyuan_fengyu on 2018/8/3 16:49.
 */
public class Test {

    public static void main(String[] args) {
        Subject<String> stringSubject = new Subject<>();

        stringSubject.subscribe(message -> {
            System.out.println("observer0 get message: " + message);
        });
        stringSubject.subscribe(message -> {
            System.out.println("observer1 get message: " + message);
        });
        stringSubject.subscribe(message -> {
            System.out.println("observer2 get message: " + message);
        });

        stringSubject.next("just test");
    }

}
