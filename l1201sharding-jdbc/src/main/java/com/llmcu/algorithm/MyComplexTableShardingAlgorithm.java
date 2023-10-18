package com.llmcu.algorithm;

import com.google.common.collect.Range;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.math.BigInteger;
import java.util.*;

/**
 * @author ：楼兰
 * @date ：Created in 2021/1/6
 * @description:
 **/

public class MyComplexTableShardingAlgorithm implements ComplexKeysShardingAlgorithm<Long> {
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, ComplexKeysShardingValue<Long> shardingValue) {
        Map<String, Range<Long>> columnNameAndRangeValuesMap = shardingValue.getColumnNameAndRangeValuesMap();
        if(CollectionUtils.isNotEmpty(columnNameAndRangeValuesMap.entrySet())){
            String logicTableName = shardingValue.getLogicTableName();
            return Arrays.asList(logicTableName+"_1",logicTableName+"_2",logicTableName+"_3");
        }

        // 插入数据时用shardingValueMap,分库只用cid，舍去user_id
        Collection<Long> userIdCol = shardingValue.getColumnNameAndShardingValuesMap().get("user_id");

        List<String> res = new ArrayList<>();

        for(Long userId: userIdCol){
            BigInteger userIdB = BigInteger.valueOf(userId);
            BigInteger target = (userIdB.mod(new BigInteger("3"))).add(new BigInteger("1"));

            res.add(shardingValue.getLogicTableName()+"_"+target);
        }

        return res;
    }
}
