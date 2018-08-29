package com.xiyuan.template.test.thread;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by xiyuan_fengyu on 2018/8/23 9:36.
 */
public class ThreadPool {

    private final int maxThreadNum;

    private final Queue<Runnable> queue;

    private final Thread[] pools;

    private int curThreadNum = 0;

    private volatile boolean running = true;

    public ThreadPool(int maxThreadNum, Queue<Runnable> queue) {
        this.maxThreadNum = maxThreadNum;
        this.queue = queue;

        this.pools = new Thread[maxThreadNum];
    }

    public void submit(Runnable task) {
        if (queue.size() >= curThreadNum && curThreadNum < maxThreadNum) {
            Thread thread = new Thread(() -> {
                while (running) {
                    Runnable newTask = null;
                    while (running) {
                        synchronized (queue) {
                            if (queue.isEmpty()) {
                                try {
                                    queue.wait();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                    break;
                                }
                            } else {
                                newTask = queue.poll();
                                break;
                            }
                        }
                    }

                    if (newTask != null) {
                        newTask.run();
                    }
                }
            });
            pools[curThreadNum++] = thread;
            thread.start();
        }

        synchronized (queue) {
            queue.add(task);
            queue.notify();
        }
    }

    public void shutdown() {
        synchronized (queue) {
            queue.notifyAll();
        }
        running = false;
    }

    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(10, new LinkedBlockingQueue<>());
        for (int i = 0; i < 20; i++) {
            final int taskIndex = i;
            threadPool.submit(() -> {
                System.out.println("task " + taskIndex + " start");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("task " + taskIndex + " end");
            });
        }
    }

}
