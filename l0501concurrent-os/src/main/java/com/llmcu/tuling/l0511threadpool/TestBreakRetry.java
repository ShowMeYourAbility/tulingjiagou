package com.llmcu.tuling.l0511threadpool;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/6/2 15:14
 */
public class TestBreakRetry {
    public static void main(String[] args) {
        testBreakRetry();
        testContinueRetry();
    }

    private static void testBreakRetry() {
        retry:
        for (int j = 0; j < 10; j++) {
            System.out.println("j:"+j);
            for (int i = 0; i < 10; i++) {
                if (i == 5) {
                    break retry;
                }
                System.out.println(i);
            }

        }
        System.out.println("break retry");
    }

    private static void testContinueRetry() {

        retry:
        for (int j = 0; j < 10; j++) {
            System.out.println("j:"+j);
            for (int i = 0; i < 10; i++) {
                if(j==2){
                    break retry;
                }

                if (i == 5) {
                    continue retry;
                }
                System.out.println(i);
            }

        }

        System.out.println("continue retry");
    }
}
