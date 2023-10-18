package com.llmcu.tuling.l0508CountDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/5/24 12:13
 */
public class CountDownLatchTest {
    public static void main(String[] args) {
        CountDownLatchTest test = new CountDownLatchTest();
        test.test();
    }

    private void test() {
        CountDownLatch latch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + "执行");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }

            }).start();
        }
        try {
            latch.await();
            System.out.println("所有线程执行完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}   // end class