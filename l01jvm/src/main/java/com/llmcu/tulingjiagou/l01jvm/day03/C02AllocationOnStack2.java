package com.llmcu.tulingjiagou.l01jvm.day03;

import com.llmcu.tulingjiagou.l01jvm.day03.converter.UserConverter;
import com.llmcu.tulingjiagou.l01jvm.day03.dto.User;
import org.springframework.beans.BeanUtils;

/**
 * 使用如下参数不会发生GC
 * -Xmx15m -Xms15m -XX:+DoEscapeAnalysis -XX:+PrintGC -XX:+EliminateAllocations
 * 使用如下参数都会发生大量GC
 * -Xmx15m -Xms15m -XX:-DoEscapeAnalysis -XX:+PrintGC -XX:-EliminateAllocations
 * -Xmx15m -Xms15m -XX:-DoEscapeAnalysis -XX:+PrintGC -XX:+EliminateAllocations
 * -Xmx15m -Xms15m -XX:+DoEscapeAnalysis -XX:+PrintGC -XX:-EliminateAllocations
 */
public class C02AllocationOnStack2 {
    public static void main(String[] args) {
//        User user = new User();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
//            allocation(user);
            allocation(new User());
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) + "ms");
    }

    public static void allocation(User user) {
        // new对象不会发生GC
//        User user2 = new User();
//        user2.setId(user.getId());

        // 静态
//        User user2 = UserConverter.convert(user);
//        user2.setId(2);

        // 实例也不会发生GC
        UserConverter converter = new UserConverter();
        User user2 = converter.convert(user);
        user2.setId(2);

        // 如果使用BeanUtils.copyProperties，会发生大量GC。因为会逃逸到栈中
//        User user2 = new User();
//        BeanUtils.copyProperties(user,user2);
//        user2.setId(1);
    }
}
