package com.xiyuan.template.rx;

import io.reactivex.Observable;

/**
 * Created by xiyuan_fengyu on 2018/11/2 13:52.
 */
public class HelloWorld {

    public static void main(String[] args) {
        Observable.fromArray(1, 2, 3, 4).subscribe(item -> {
            System.out.println(item);
        });
        System.out.println("finish");
    }

}
