package com.xiyuan.template.test.threads;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by xiyuan_fengyu on 2018/2/5 16:14.
 */
public class AtomicTest {

    public static void main(String[] args) throws InterruptedException {
        {
            int threadNum = 100000;
            AtomicInteger count = new AtomicInteger(0);
            CountDownLatch countDownLatch = new CountDownLatch(threadNum);
            for (int i = 0; i < threadNum; i++) {
                new Thread(() -> {
                    count.set(count.get() + 1);
                    countDownLatch.countDown();
                }).start();
            }
            countDownLatch.await();
            System.out.println(count);
        }

        int threadNum = 100000;
        AtomicInteger count = new AtomicInteger(0);
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        for (int i = 0; i < threadNum; i++) {
            new Thread(() -> {
                count.set(count.get() + 1);
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        System.out.println(count);

        count.set(0);
        CountDownLatch countDownLatch1 = new CountDownLatch(threadNum);
        for (int i = 0; i < threadNum; i++) {
            new Thread(() -> {
                synchronized (count) {
                    count.set(count.get() + 1);
                    countDownLatch1.countDown();
                }
            }).start();
        }
        countDownLatch1.await();
        System.out.println(count);

        count.set(0);
        CountDownLatch countDownLatch2 = new CountDownLatch(threadNum);
        for (int i = 0; i < threadNum; i++) {
            new Thread(() -> {
                count.incrementAndGet();
                countDownLatch2.countDown();
            }).start();
        }
        countDownLatch2.await();
        System.out.println(count);
    }

}