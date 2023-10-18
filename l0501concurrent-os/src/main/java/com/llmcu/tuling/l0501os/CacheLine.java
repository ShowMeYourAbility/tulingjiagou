package com.llmcu.tuling.l0501os;

import org.springframework.util.StopWatch;

/**
 * 缓存行的空间局部性
 *
 * @author liuling
 * @date 2022/5/14 21:06
 */
public class CacheLine {
    public static void main(String[] args) {
        int[][] arr = new int[1024 * 1024][10];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j]=1;
            }
        }
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("A1");
        extracted(arr);
        stopWatch.stop();
        stopWatch.start("B1");
        extracted2(arr);
        stopWatch.stop();
        stopWatch.start("A2");
        extracted(arr);
        stopWatch.stop();
        stopWatch.start("B2");
        extracted2(arr);
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }

    private static void extracted(int[][] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                sum += arr[i][j];
            }
        }
        System.out.println(sum);
    }

    private static void extracted2(int[][] arr) {
        int sum = 0;
        for (int j = 0; j < arr[0].length; j++) {
            for (int i = 0; i < arr.length; i++) {
                sum += arr[i][j];
            }
        }
        System.out.println(sum);
    }
}
