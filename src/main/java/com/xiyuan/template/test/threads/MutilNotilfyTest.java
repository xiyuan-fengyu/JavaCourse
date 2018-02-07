package com.xiyuan.template.test.threads;

/**
 * Created by xiyuan_fengyu on 2018/2/7 9:47.
 */
public class MutilNotilfyTest {

    public static void main(String[] args) throws InterruptedException {
        final Object lock = new Object();
        new Thread(() -> {
            while (Thread.currentThread().isAlive()) {
                synchronized (lock) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("afterWait");
            }
        }).start();

        Thread.sleep(100);
        synchronized (lock) {
            lock.notify();
            System.out.println("notify");
            Thread.sleep(1000);
            lock.notify();
            System.out.println("notify");
            Thread.sleep(2000);
            lock.notify();
            System.out.println("notify");
        }

        Thread.sleep(1000);
        synchronized (lock) {
            lock.notify();
            System.out.println("notify");
        }
    }

}