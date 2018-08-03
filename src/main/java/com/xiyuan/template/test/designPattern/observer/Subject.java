package com.xiyuan.template.test.designPattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiyuan_fengyu on 2018/8/3 16:44.
 */
public class Subject<T> {

    private List<Observable<T>> observers = new ArrayList<>();

    public void subscribe(Observable<T> observer) {
        observers.add(observer);
    }

    public void next(T message) {
        for (Observable<T> observer : observers) {
            observer.accept(message);
        }
    }

}
