package com.llmcu.tulingjiagou.l01jvm.day01;

public class ChildTest extends ParentTest {

    public ChildTest(String name) {
//        System.out.println(3);
//        new ParentTest(name + "1");
        super(name);
        System.out.println(3);
    }

    public ChildTest() {
        System.out.println(4);
    }

    public static void main(String[] args) {
        new ChildTest("x");
    }
}