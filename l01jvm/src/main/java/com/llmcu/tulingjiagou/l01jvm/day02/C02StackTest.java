package com.llmcu.tulingjiagou.l01jvm.day02;

public class C02StackTest {
    // 23802
    // 1087
    private static int n = 0;

    public static void method() {
        n++;
        method();
    }

    public static void main(String[] args) {
        try {
            method();
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("第" + n + "次调用");
        }
    }
}
