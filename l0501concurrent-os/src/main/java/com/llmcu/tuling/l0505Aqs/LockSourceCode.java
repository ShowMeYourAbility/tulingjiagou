package com.llmcu.tuling.l0505Aqs;

import java.util.concurrent.locks.ReentrantLock;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/5/17 12:06
 */
public class LockSourceCode {
    ReentrantLock lock = new ReentrantLock(true);
    public static void main(String[] args) {
        LockSourceCode lockSourceCode = new LockSourceCode();
        // 线程
        new Thread(() -> lockSourceCode.doTheWork()).start();
//        new Thread(() -> lockSourceCode.doTheWork()).start();

    }

    private void doTheWork() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread()+" do the work start");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread()+" do the work end");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
