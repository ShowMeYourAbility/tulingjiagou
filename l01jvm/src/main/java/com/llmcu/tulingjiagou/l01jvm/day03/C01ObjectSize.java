package com.llmcu.tulingjiagou.l01jvm.day03;

import org.openjdk.jol.info.ClassLayout;

/**
 * 对象大小（对象头、实例数据、对齐填充）
 */
public class C01ObjectSize {
    public static void main(String[] args) {
        ClassLayout layout = ClassLayout.parseInstance(new Object());
        System.out.println(layout.toPrintable());
        System.out.println("==================================================");
        ClassLayout layout1 = ClassLayout.parseInstance(new int[]{});
        System.out.println(layout1.toPrintable());
        System.out.println("==================================================");
        ClassLayout layout2 = ClassLayout.parseInstance(new A());
        System.out.println(layout2.toPrintable());
    }
    // -XX:+UseCompressedOops           默认开启的压缩所有指针
    // -XX:+UseCompressedClassPointers  默认开启的压缩对象头里的类型指针Klass Pointer
    public static class A{
        int id;
        String name;
        byte b;
        Object o;
        int[] arr;

    }
}
