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

public class MyComplexDSShardingAlgorithm implements ComplexKeysShardingAlgorithm<Long> {
//    SELECT  cid,cname,user_id,cstatus  FROM course
//    WHERE  cid BETWEEN ? AND ? AND user_id = ?
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, ComplexKeysShardingValue<Long> shardingValue) {
        Map<String, Range<Long>> columnNameAndRangeValuesMap = shardingValue.getColumnNameAndRangeValuesMap();
        // 1、非空说明是查询
        if(CollectionUtils.isNotEmpty(columnNameAndRangeValuesMap.entrySet())){
            return Arrays.asList("m1","m2");
        }


        // 2、插入数据时用shardingValueMap,分库只用cid，舍去user_id
        Collection<Long> cidCol = shardingValue.getColumnNameAndShardingValuesMap().get("cid");
        List<String> result = new ArrayList<>();
        for(Long cid:cidCol){
            BigInteger cidB = BigInteger.valueOf(cid);
            BigInteger target = (cidB.mod(new BigInteger("2"))).add(new BigInteger("1"));
            result.add("m"+target);
        }
        return result;
    }
}
