package com.llmcu.tuling.l0501os;

import lombok.SneakyThrows;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 内核线程模型感受：多创建几个线程，操作系统可以感知即可证明
 *
 * @author liuling
 * @date 2022/5/14 22:29
 */
public class KernelMode {
    public static void main(String[] args) {
        int cnt = 200;
        CountDownLatch countDownLatch = new CountDownLatch(cnt);
        // 创建线程
        for(int i = 0; i < cnt; i++) {
            new Thread(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    TimeUnit.SECONDS.sleep(5);
                    countDownLatch.countDown();
                }
            }).start();
        }
        try {
            countDownLatch.await();
            System.out.println("所有线程执行完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
