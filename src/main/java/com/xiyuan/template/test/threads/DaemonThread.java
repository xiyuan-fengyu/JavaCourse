package com.xiyuan.template.test.threads;

/**
 * Created by xiyuan_fengyu on 2016/12/9.
 */
public class DaemonThread {

    public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                int index = 0;
                while (!interrupted()) {
                    System.out.println(getName() + ":" + index++);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.setDaemon(true);
        thread.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
