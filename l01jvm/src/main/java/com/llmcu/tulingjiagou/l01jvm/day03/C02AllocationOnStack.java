package com.llmcu.tulingjiagou.l01jvm.day03;

import com.llmcu.tulingjiagou.l01jvm.day03.dto.User;

import java.util.concurrent.TimeUnit;

/**
 * 使用如下参数不会发生GC
 * -Xmx15m -Xms15m -XX:+DoEscapeAnalysis -XX:+PrintGC -XX:+EliminateAllocations
 * 推荐使用下方
 * java -XX:-UseAdaptiveSizePolicy -Xss1M -Xmx30m -Xms30m -XX:+DoEscapeAnalysis -XX:+PrintGCDetails -XX:+EliminateAllocations com.llmcu.tulingjiagou.l01jvm.day03.C02AllocationOnStack
 *
 * 使用如下参数都会发生大量GC
 * -Xmx15m -Xms15m -XX:-DoEscapeAnalysis -XX:+PrintGC -XX:+EliminateAllocations
 * -Xmx15m -Xms15m -XX:+DoEscapeAnalysis -XX:+PrintGC -XX:-EliminateAllocations
 * 推荐使用下方
 * java -XX:-UseAdaptiveSizePolicy -Xss1M -Xmx30m -Xms30m -XX:-DoEscapeAnalysis -XX:+PrintGCDetails -XX:-EliminateAllocations com.llmcu.tulingjiagou.l01jvm.day03.C02AllocationOnStack
 *
 * 1、idea栈上分配不成功
 * 2、开启jvisualvm后，对-XX:+PrintGCDetails展示的结果有影响
 */
public class C02AllocationOnStack {
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 2000000000; i++) {
            allocation();
        }
        for (int i = 0; i < 2000000000; i++) {
            allocation();
        }

        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) + "ms");
    }

    public static void allocation() throws InterruptedException {
        User user = new User();
        user.setId(1);
    }

}
