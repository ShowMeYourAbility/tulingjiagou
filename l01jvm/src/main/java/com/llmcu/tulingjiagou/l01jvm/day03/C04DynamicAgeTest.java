package com.llmcu.tulingjiagou.l01jvm.day03;


public class C04DynamicAgeTest {
    private static Integer _1MB = 1024 * 1024;

    /**
     * jvm参数：
     * -verbose:gc -XX:+UseSerialGC -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15
     */
    public static void main(String[] args) {
        byte[] a1, a2, a3, a4, a5, a6;
        a1 = new byte[_1MB / 4];
        //先造成一次minorgc，让a1的年龄加一
        a2 = new byte[_1MB * 4];
        a3 = new byte[_1MB * 4];
        // a1 + a4 等于 survivor区一半
        a4 = new byte[_1MB / 4];
        //第二次minorgc，a1年龄=2，a4年龄=1
        a5 = new byte[_1MB * 4];
    }
}