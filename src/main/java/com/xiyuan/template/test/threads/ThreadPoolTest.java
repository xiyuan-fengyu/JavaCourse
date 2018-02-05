package com.xiyuan.template.test.threads;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xiyuan_fengyu on 2018/2/5 17:15.
 */
public class ThreadPoolTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Scanner scanner = new Scanner(System.in);
        String line;
        while ((line = scanner.nextLine()) != null && !line.equals("quit")) {
            String finalLine = line;
            executorService.submit(() -> {
                System.out.println(Thread.currentThread() + ": " + finalLine);
            });
        }
        executorService.shutdown();
    }

}