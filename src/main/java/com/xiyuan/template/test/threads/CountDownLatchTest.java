package com.xiyuan.template.test.threads;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

/**
 * Created by xiyuan_fengyu on 2018/2/5 16:09.
 */
public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        Thread thread = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            String line;
            while ((line = scanner.nextLine()) != null && !line.equals("quit")) {
                countDownLatch.countDown();
            }
        });
        thread.setDaemon(true);
        thread.start();
        countDownLatch.await();
        System.out.println("finish");
    }

}