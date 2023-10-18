package com.llmcu.tuling.l0507semaphore;

import java.util.concurrent.Semaphore;

/**
 * void acquire(int permits)
 * 尝试获取许可，
 * 如果获取到了则正常返回,
 * 如果获取不到，则等待许可
 * 如果被interrupt，则抛出InterruptedException
 *
 *
 * @author liuling
 * @date 2022/5/23 16:22
 */
public class Semaphore01AcquireTest {
    public static void main(String[] args) {
        Semaphore01AcquireTest test = new Semaphore01AcquireTest();
        test.test();
    }

    public void test() {
        // 初始化state，state=4
        Semaphore semaphore = new Semaphore(4);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
//                    semaphore.acquire();
                    // 从池中拿出2个凭据
                    semaphore.acquire(2);
                    System.out.println(Thread.currentThread().getName() + "获取到了许可==========");
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + "释放了许可**********");
//                    semaphore.release();
                    // 释放2个凭据
                    semaphore.release(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }).start();
        }
    }
}

