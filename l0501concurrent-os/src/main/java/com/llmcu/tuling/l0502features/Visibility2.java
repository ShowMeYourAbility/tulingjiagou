package com.llmcu.tuling.l0502features;

import com.llmcu.tuling.l0502features.dto.Flag;

import java.util.concurrent.TimeUnit;

/**
 * 并发编程三大特性：可见性
 *
 * @author liuling
 * @date 2022/5/15 11:45
 */
public class Visibility2 {
//    private static volatile Flag flag = new Flag();
    private static Flag flag = new Flag();
    static {
        flag.setFlag(false);
    }
    public static void main(String[] args) {
        for(int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                flag.setFlag(true);
            }).start();
            new Thread(() -> {
                while (!flag.isFlag()) {
                }
                System.out.println("flag is true");
            }).start();
        }
    }
}
