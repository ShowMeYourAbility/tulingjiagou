package com.llmcu.tulingjiagou.l01jvm.day05;

import java.util.ArrayList;

public class HeapTest {
    byte[] a = new byte[1024 * 100];

    public static void main(String[] args) throws InterruptedException {
        ArrayList<HeapTest> heapTests = new ArrayList<>();
        while (true) {
            heapTests.add(new HeapTest());
            Thread.sleep(10);
        }
    }
}
