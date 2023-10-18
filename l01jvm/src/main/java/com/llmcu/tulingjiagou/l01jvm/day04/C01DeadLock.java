package com.llmcu.tulingjiagou.l01jvm.day04;

/**
 * @author llmcu
 */
public class C01DeadLock {
    private static final Object LOCK_1 = new Object();
    private static final Object LOCK_2 = new Object();
    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (LOCK_1) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread()+" get lock1...");
                synchronized (LOCK_2) {
                    System.out.println(Thread.currentThread()+" get lock2...");
                }
            }
        }).start();
        new Thread(() -> {
            synchronized (LOCK_2) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread()+" get lock2...");
                synchronized (LOCK_1) {
                    System.out.println(Thread.currentThread()+" get lock1...");
                }
            }
        }).start();

    }

}
