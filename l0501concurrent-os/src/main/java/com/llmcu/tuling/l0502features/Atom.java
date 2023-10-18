package com.llmcu.tuling.l0502features;

import java.util.concurrent.CountDownLatch;

/**
 * volatile不能实现原子性
 *
 * @author liuling
 * @date 2022/5/15 14:53
 */
public class Atom {
    static volatile int sum = 0;
    private static Object lock = new Object();
    public static void main(String[] args) throws InterruptedException {
        int cnt = 10;
        CountDownLatch countDownLatch = new CountDownLatch(cnt);
        for (int i = 0; i < cnt; i++) {
            new Thread(()->{
                for (int j = 0; j < 10000; j++) {
//                    synchronized (lock) {
//                        sum++;
//                    }
                    sum++;
                }
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        System.out.println(sum);
    }
}
