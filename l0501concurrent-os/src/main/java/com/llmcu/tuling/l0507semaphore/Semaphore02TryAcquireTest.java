package com.llmcu.tuling.l0507semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * boolean tryAcquire(long timeout, TimeUnit unit)
 * 尝试获取许可，
 * 如果获取到了返回true,
 * 如果获取不到，则等待许可，如果超时则返回false
 * 如果被interrupt，则抛出InterruptedException
 *
 *
 * @author liuling
 * @date 2022/5/23 16:22
 */
public class Semaphore02TryAcquireTest {
    public static void main(String[] args) {
        Semaphore02TryAcquireTest test = new Semaphore02TryAcquireTest();
        test.test();
    }

    public void test() {
        Semaphore semaphore = new Semaphore(2);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    if(semaphore.tryAcquire(500, TimeUnit.MILLISECONDS)){
                        System.out.println(Thread.currentThread().getName() + "获取到了许可==========");
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName() + "释放了许可**********");
                        semaphore.release();
                    }else {
                        System.out.println(Thread.currentThread().getName() + "没有获取到许可");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }).start();
        }
    }
}

