package com.xiyuan.template.test.threads;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by xiyuan_fengyu on 2017/3/16.
 */
public class CyclicBarrierTest {

    public static void main(String[] args) {
        final CyclicBarrier barrier = new CyclicBarrier(10);
        for (int i = 0; i < 9; i++) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep((long) (Math.random() * 3000));
                        System.out.println(getName());
                        barrier.await();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }

        try {
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }

}
