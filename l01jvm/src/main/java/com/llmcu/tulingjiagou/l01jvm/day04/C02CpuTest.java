package com.llmcu.tulingjiagou.l01jvm.day04;

public class C02CpuTest {
    public static void main(String[] args) {
        C02CpuTest test = new C02CpuTest();
        while(true) {
            test.test();
        }
    }

    private int test() {
        int a = 1;
        int b = 2;
        int c = (a+b)*10;
        return c;
    }

}
