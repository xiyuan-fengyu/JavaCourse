package com.xiyuan.template.test.threads;

import java.util.concurrent.CompletableFuture;

/**
 * Created by xiyuan_fengyu on 2018/1/17 13:36.
 */
public class CompletableFutureTest {

    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "timeout";
        });
        future.thenAcceptAsync(System.out::println);
        Thread.sleep(2000);
        future.complete("specialResult");
    }

}