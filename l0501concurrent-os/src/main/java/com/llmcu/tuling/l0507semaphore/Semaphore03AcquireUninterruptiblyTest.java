package com.llmcu.tuling.l0507semaphore;

import java.util.concurrent.Semaphore;

/**
 * 与acquire()相比，acquireUninterruptibly()不支持中断，即使中断也不会抛出异常。
 *
 * @author liuling
 * @date 2022/5/23 16:22
 */
public class Semaphore03AcquireUninterruptiblyTest {
    public static void main(String[] args) {
        Semaphore03AcquireUninterruptiblyTest test = new Semaphore03AcquireUninterruptiblyTest();
        test.test();
    }

    public void test() {
        Semaphore semaphore = new Semaphore(2);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquireUninterruptibly();
                    System.out.println(Thread.currentThread().getName() + "获取到了许可==========");
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + "释放了许可**********");
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }).start();
        }
    }
}

