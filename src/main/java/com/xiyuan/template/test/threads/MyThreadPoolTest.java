package com.xiyuan.template.test.threads;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by xiyuan_fengyu on 2018/2/5 15:33.
 */
public class MyThreadPoolTest {

    public static class ThreadPool {

        public final int poolSize;

        public final AtomicInteger curRunning = new AtomicInteger(0);

        private AtomicBoolean running = new AtomicBoolean(true);

        private final Deque<Runnable> deque = new ArrayDeque<>();

        private final Object lock = new Object();

        public ThreadPool(int poolSize) {
            this.poolSize = poolSize;
            startLoop();
        }

        private void startLoop() {
            for (int i = 0; i < poolSize; i++) {
                new Thread(() -> {
                    while (running.get()) {
                        Runnable task = null;
                        while (running.get()) {
                            synchronized (lock) {
                                try {
                                    lock.wait();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                    break;
                                }

                                if (running.get() && !deque.isEmpty()) {
                                    task = deque.poll();
                                    break;
                                }
                            }
                        }

                        synchronized (curRunning) {
                            curRunning.incrementAndGet();
                        }
                        if (task != null) {
                            try {
                                task.run();
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        synchronized (curRunning) {
                            curRunning.decrementAndGet();
                        }
                        synchronized (lock) {
                            lock.notify();
                        }
                    }
                }).start();
            }
        }

        public void submit(Runnable task) {
            if (running.get()) {
                synchronized (lock) {
                    deque.offer(task);
                    lock.notify();
                }
            }
        }

        public void shutdown() {
            running.set(false);
            synchronized (lock) {
                lock.notifyAll();
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPool threadPool = new ThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            final int index = i;
            threadPool.submit(() -> {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + ":" + index);
            });
        }
        Thread.sleep(2000);
        threadPool.shutdown();
    }

}