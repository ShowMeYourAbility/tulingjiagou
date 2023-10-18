package com.llmcu.tulingjiagou.l01jvm.d20practice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/5/27 17:39
 */
public class FullGcProprom {
    private static class CardInfo{
        BigDecimal price = new BigDecimal(0.0);
        String name = "张三";
        int age = 5;
        Date birthday = new Date();
        public void m(){}
    }
    private static ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(50,new ThreadPoolExecutor.DiscardPolicy());
    public static void main(String[] args) throws InterruptedException {
        executor.setMaximumPoolSize(50);
        for(;;){
            modelFit();
//            Thread.sleep(100);
        }
    }

    private static void modelFit() {
        List<CardInfo> taskList = getAllCardInfo();
        taskList.forEach(cardInfo -> {
            executor.scheduleWithFixedDelay(() -> {
                cardInfo.m();
            },2,3, TimeUnit.SECONDS);
        });
    }

    private static List<CardInfo> getAllCardInfo() {
        List<CardInfo> taskList = new ArrayList<>();
        for(int i = 0;i<100;i++){
            CardInfo cardInfo = new CardInfo();
            taskList.add(cardInfo);
        }
        return taskList;
    }
}
