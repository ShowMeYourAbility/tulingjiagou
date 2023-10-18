package com.llmcu.tuling.l0502features;

import java.util.concurrent.TimeUnit;

/**
 * 并发编程三大特性：可见性
 *
 * @author liuling
 * @date 2022/5/15 11:45
 */
public class Visibility {
    // 0、volatile即可实现可见
    private static  boolean flag = false;
//    private static volatile  int cnt = 0;
//    private static Integer cnt = 0;
//    private static int cnt = 0;
    public static void main(String[] args) {
        for(int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                flag = true;
            }).start();
            new Thread(() -> {
                // 0、通过volatile修饰实现可见
                while (!flag) {
                    // do nothing
                    // 1、通过其他变量感知可见，如Intger、volatile int类型
//                    cnt++;
                    // 2、通过打印内容触发可见
//                    System.out.println("future is ok " );
                }
                System.out.println("flag is true");
            }).start();
        }
    }
}
