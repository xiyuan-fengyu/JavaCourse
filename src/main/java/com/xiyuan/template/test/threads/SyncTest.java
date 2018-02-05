package com.xiyuan.template.test.threads;

import com.xiyuan.template.util.DateUtil;

/**
 * Created by xiyuan_fengyu on 2018/2/5 15:20.
 */
public class SyncTest {

    static class SomeObj {

        public synchronized void objMethod1() {
            System.out.println(this + ".objMethod1 start: " + DateUtil.format(null));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(this + ".objMethod1 end: " + DateUtil.format(null));
        }

        public synchronized void objMethod2() {
            System.out.println(this + ".objMethod2 start: " + DateUtil.format(null));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(this + ".objMethod2 end: " + DateUtil.format(null));
        }

        public static synchronized void classMethod1() {
            System.out.println("classMethod1 start: " + DateUtil.format(null));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("classMethod1 end: " + DateUtil.format(null));
        }

        public static synchronized void classMethod2() {
            System.out.println("classMethod2 start: " + DateUtil.format(null));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("classMethod2 end: " + DateUtil.format(null));
        }

    }

    public static void main(String[] args) {
        SomeObj someObj = new SomeObj();
        new Thread(someObj::objMethod1).start();
        new Thread(someObj::objMethod2).start();
        new Thread(new SomeObj()::objMethod1).start();

        new Thread(SomeObj::classMethod1).start();
        new Thread(SomeObj::classMethod2).start();
    }

}