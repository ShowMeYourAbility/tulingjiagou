package com.llmcu.algorithm;

import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author ：楼兰
 * @date ：Created in 2021/1/6
 * @description:
 **/

public class MyHintTableShardingAlgorithm implements HintShardingAlgorithm<Integer> {
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, HintShardingValue<Integer> shardingValue) {
        String logicTableName = shardingValue.getLogicTableName();
        return Arrays.asList(logicTableName + "_" + shardingValue.getValues().toArray()[0]);
    }
}
