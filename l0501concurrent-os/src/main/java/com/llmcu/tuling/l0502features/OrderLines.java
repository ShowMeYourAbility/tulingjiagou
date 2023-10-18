package com.llmcu.tuling.l0502features;

import java.util.concurrent.CountDownLatch;

/**
 * 代码重排及
 * volatile实现有序性
 *
 * @author liuling
 * @date 2022/5/15 15:03
 */
public class OrderLines {
    volatile static int a = 0;
    volatile static int b = 0;
    volatile static int x = 0;
    volatile static int y = 0;
//     static int a = 0;
//     static int b = 0;
//     static int x = 0;
//     static int y = 0;
    public static void main(String[] args) {
        boolean target;
        int times = 0;
        do{
            a = 0;
            b = 0;
            x = 0;
            y = 0;
            times++;
            CountDownLatch countDownLatch = new CountDownLatch(2);
            new Thread(() -> {
                a = 1;
                x = b;
                countDownLatch.countDown();
            }).start();
            new Thread(() -> {
                b = 1;
                y = a;
                countDownLatch.countDown();
            }).start();
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            target = (x==0) && (y==0);
            if(target){
                System.out.println("第"+times+"次："+a+","+b+","+x+","+y);
            }
        }while(!target);

    }
}
