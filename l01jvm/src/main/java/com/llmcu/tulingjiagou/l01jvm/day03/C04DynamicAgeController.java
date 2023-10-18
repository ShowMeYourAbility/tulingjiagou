package com.llmcu.tulingjiagou.l01jvm.day03;

import com.llmcu.common.dto.ResponseJson;
import com.llmcu.common.util.DateUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@RestController
public class C04DynamicAgeController {

    /**
     * -Xms300m -Xmx300m
     * -XX:-DoEscapeAnalysis -XX:+PrintGCDetails
     * -XX:SurvivorRatio=8 -XX:-UseAdaptiveSizePolicy
     * -XX:TargetSurvivorRatio=30
     * -XX:+UseSerialGC -Xms300m -Xmx300m -XX:-DoEscapeAnalysis -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:-UseAdaptiveSizePolicy -XX:TargetSurvivorRatio=30
     * @return
     * @throws InterruptedException
     */
    @GetMapping("/dynamicAge")
    public ResponseJson<Object> testDynamicAge() throws InterruptedException {
        byte[] allocation1;
        allocation1 = new byte[1*1024*1024];
        // 80m/ 4m/s = 20ss
        LocalDateTime now = LocalDateTime.now();
        int second = now.getSecond();
        TimeUnit.SECONDS.sleep(second==33?62:1);
        System.out.println(allocation1.length);
        return ResponseJson.success();
    }
}
