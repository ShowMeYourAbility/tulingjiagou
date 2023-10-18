package com.llmcu.tuling.l0510Race;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 主程序结束了，子线程还会正常运行吗？
 *
 * @author renzenggang
 */
public class CountDownLatchTest3 {

    /**
     * 运动员数量量
     */
    private static final int SPORTSMAN_COUNT = 10;

    private static final Random random = new Random();

    /**
     * ⽤用于判断发令之前运动员是否已经进⼊入准备状态，需要等待10个运动员准备就绪，占有锁，等待10个运动员完成，释放锁。
     */
    private static CountDownLatch readyLatch = new CountDownLatch(SPORTSMAN_COUNT);
    /**
     * ⽤用于判断裁判是否已经发令，占有锁，等待裁判发令完成，释放锁
     */
    private static CountDownLatch startLatch = new CountDownLatch(1);
    /**
     * 设置终点屏障，⽤用于计算成绩
     */
//    private static CountDownLatch resultLatch = new CountDownLatch(SPORTSMAN_COUNT);

    /**
     * 运动员列表(成绩单)
     */
    private static List<Sportsman> transcript = new ArrayList<>(SPORTSMAN_COUNT);

    public static void main(String[] args) {

        // ⽤用于判断发令之前运动员是否已经进⼊入准备状态，需要等待10个运动员准备就绪，占有锁，等待10个运动员完成，释放锁。
        // CountDownLatch readyLatch = new CountDownLatch(SPORTSMAN_COUNT);
        // ⽤用于判断裁判是否已经发令，占有锁，等待裁判发令完成，释放锁
        // CountDownLatch startLatch = new CountDownLatch(1);
        // 启动10个线程，也就是10个运动员，做准备⼯工作
        for (int i = 0; i < SPORTSMAN_COUNT; i++) {
            Thread t = new Thread(new RunTask((i + 1) + "号运动员", readyLatch,
                    startLatch));
            t.start();
        }
        // 当前运动员在其他运动员准备就绪前⼀一直等待，也就是说等readyLatch倒数计数器器为0之前⼀一直等待
        try {
            TimeUnit.SECONDS.sleep(3);
            System.out.println("裁判：我休息了3秒，我看下你们准备好了没有...");
            readyLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 裁判发令，释放锁
        startLatch.countDown();
        System.out.println("裁判：所有运动员准备完毕，开始跑...");

        System.out.println("裁判：主程序线束了！！！！！！！！！");

    }

    // 运动员
    static class Sportsman {
        private String name;
        private int transcript;

        public Sportsman(String name, int transcript) {
            this.name = name;
            this.transcript = transcript;
        }

        @Override
        public boolean equals(Object obj) {
            boolean result = false;
            if (obj instanceof Sportsman) {
                result = ((Sportsman) obj).getTranscript() == this.transcript;
            }
            return result;
        }

        @Override
        public String toString() {
            return this.name + ":" + this.transcript + " ms";
        }

        public String getName() {
            return name;
        }

        public int getTranscript() {
            return transcript;
        }

    }

    // 跑任务
    static class RunTask implements Runnable {

        private Lock lock = new ReentrantLock();

        private CountDownLatch ready;
        private CountDownLatch start;
        private String name;

        /**
         * 114 * (构造⽅方法)
         * 115 *
         * 116 * @param ready
         * 117 * @param start
         * 118 * @param name 运动员名称
         * 119
         */
        public RunTask(String name, CountDownLatch ready, CountDownLatch start) {
            this.ready = ready;
            this.start = start;
            this.name = name;
        }

        @Override
        public void run() {
            lock.lock();
            try {

                // 1. 写运动员准备就绪的逻辑,准备readyTime秒
                int readyTime = random.nextInt(1000);
                System.out.println(name + ":我需要" + readyTime + "秒的时间准备。");
                try {
                    Thread.sleep(readyTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(name + "我已经准备完毕！");
                // 释放锁readyLatch-1，表示⼀一个运动员已经就绪
                ready.countDown();
                try {
                    // 等待裁判发开始命令
                    start.await();
                } catch (InterruptedException e) {
                }
                System.out.println(name + "：开跑...");
                int costTime = random.nextInt(1000);
                try {
                    Thread.sleep(costTime);
                } catch (
                        InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(name + "：开跑到达终点。成绩:" + costTime + "ms");
                transcript.add(new

                        Sportsman(name, costTime));
                // 等待成绩
//                cb.await();
//                resultLatch.countDown();
            } catch (
                    Exception e) {

            } finally {
                lock.unlock();
            }

        }

    }

}