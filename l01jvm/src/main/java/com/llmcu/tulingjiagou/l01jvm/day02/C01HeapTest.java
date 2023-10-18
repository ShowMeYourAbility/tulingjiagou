package com.llmcu.tulingjiagou.l01jvm.day02;

import com.llmcu.tulingjiagou.l01jvm.day03.dto.User;

import java.util.ArrayList;
import java.util.List;

public class C01HeapTest {
    // 100k
    private int[] array = new int[1024*25];

    public static void main(String[] args) throws InterruptedException {
        User user = new User();
        user.setId(1);
        List<C01HeapTest> list = new ArrayList<>();
        while(true){
            list.add(new C01HeapTest());
//            Thread.sleep(50);
        }
    }
}
