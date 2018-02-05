package com.xiyuan.template.test.threads;

import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by xiyuan_fengyu on 2018/2/5 16:39.
 */
public class BlockQueueTest {

    public static void main(String[] args) {
        AtomicBoolean isWorking = new AtomicBoolean(true);
        BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>();
        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            String line;
            while ((line = scanner.nextLine()) != null && !line.equals("quit")) {
                blockingQueue.add(line);
            }
            isWorking.set(false);
        }).start();
        new Thread(() -> {
            while (isWorking.get()) {
                try {
                    String msg = blockingQueue.take();
                    System.out.println(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}