package com.llmcu.algorithm;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.math.BigInteger;
import java.util.Collection;

/**
 * @author ：楼兰
 * @date ：Created in 2021/1/6
 * @description:
 **/

public class MyPreciseTableShardingAlgorithm implements PreciseShardingAlgorithm<Long> {
    //select * from course where cid = ? or cid in (?,?)
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
        String logicTableName = shardingValue.getLogicTableName();
        String userId = shardingValue.getColumnName();
        Long userIdValue = shardingValue.getValue();
        //实现 course_$->{cid%2+1)
        BigInteger shardingValueB = BigInteger.valueOf(userIdValue);
        BigInteger resB = (shardingValueB.mod(new BigInteger("3"))).add(new BigInteger("1"));
        String key = logicTableName+"_"+resB;
        if(availableTargetNames.contains(key)){
            return key;
        }
        //couse_1, course_2
        throw new UnsupportedOperationException("route "+ key +" is not supported ,please check your config");
    }
}
