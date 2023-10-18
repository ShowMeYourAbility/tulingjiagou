package com.llmcu.tuling.l0504synchronized;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * synchronized锁升级
 *
 * @author liuling
 * @date 2022/5/16 16:34
 */
public class SynchronizedUpdate {

    public static void main(String[] args) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        Object obj = new Object();
        // 00000001
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());

        synchronized (obj) {
            System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        }

        new Thread(() -> {
            synchronized (obj) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(ClassLayout.parseInstance(obj).toPrintable());
            }
        }).start();

        new Thread(() ->{
            synchronized (obj){
                System.out.println(ClassLayout.parseInstance(obj).toPrintable());
            }
        }).start();
        TimeUnit.SECONDS.sleep(3);

    }
}
