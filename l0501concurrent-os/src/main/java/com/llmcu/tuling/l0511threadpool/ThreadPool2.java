package com.llmcu.tuling.l0511threadpool;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 *
 * @author liuling
 * @date 2022/5/30 18:27
 */
public class ThreadPool2 {
    private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int RUNNING    = -1 << COUNT_BITS;
    private static int ctlOf(int rs, int wc) { return rs | wc; }

    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

    public static void main(String[] args) {
        ThreadPool2 threadPool = new ThreadPool2();
        System.out.println(threadPool.getPoolSize());
        System.out.println(CAPACITY);
    }

    int getPoolSize() {
        return ctl.get();
    }

}
