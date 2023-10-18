package com.llmcu.tuling.l0511threadpool;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/5/28 10:51
 */
public class threadState {
    public static void main(String[] args) {
        testNewState();
        testRunnableState();
        testTerminatedState();
        testTimedWaitingState();
        testWaitingState();
        testBlockingState();
    }

    private static void testNewState() {
        Thread thread = new Thread(()->{
            System.out.println("thread start");
        });
        System.out.println("NEW:"+thread.getState());
    }

    @SneakyThrows
    private static void testRunnableState() {
        Thread thread = new Thread(()->{
            for(;;){
            }
        });
        thread.start();
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println("RUNNABLE:"+thread.getState());
    }

    @SneakyThrows
    private static void testTerminatedState(){
        Thread thread = new Thread(()->{
        });
        thread.start();
        TimeUnit.MILLISECONDS.sleep(200);
        System.out.println("TERMINATED:"+thread.getState());
    }

    @SneakyThrows
    private static void testWaitingState(){
        Thread thread = new Thread(()->{
            LockSupport.park();
        });
        thread.start();
        TimeUnit.MILLISECONDS.sleep(200);
        System.out.println("WAITING:"+thread.getState());
    }

    @SneakyThrows
    private static void testTimedWaitingState(){
        Thread thread = new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        TimeUnit.MILLISECONDS.sleep(200);
        System.out.println("TIMED_WAITING:"+thread.getState());
    }

    @SneakyThrows
    private static void testBlockingState(){
        Lock lock = new ReentrantLock();
        Thread thread = new Thread(()->{
            try {
                lock.lock();
                TimeUnit.SECONDS.sleep(10);
                lock.unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread2 = new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
                lock.lock();
                TimeUnit.SECONDS.sleep(10);
                lock.unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        thread2.start();
        TimeUnit.SECONDS.sleep(2);
        System.out.println("BLOCKING:"+thread2.getState());
    }
}
