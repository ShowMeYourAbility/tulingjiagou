package com.llmcu.tuling.l0509CyclicBarrier;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/5/24 18:06
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        CyclicBarrierTest test = new CyclicBarrierTest();
        test.test();
    }

    private void test() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3,() -> System.out.println("所有人到达了"));
        for(int i = 0; i < 3; i++) {
            new Thread(()->{
                try {
                    System.out.println(Thread.currentThread().getName() + " begin");
                    cyclicBarrier.await();
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName() + " end");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }
}
