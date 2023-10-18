package com.llmcu.tuling.es.aggs;

import com.alibaba.fastjson.JSON;
import com.llmcu.tuling.es.aggs.dto.Car;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/6/30 9:55
 */
@Slf4j
public class C01GroupByColor {
    public static void main(String[] args) {

        List<Car> cars = CarGenerateUtil.carList();
//        不同color车辆销售数量
        Map<String, Long> collect = cars.stream().collect(Collectors.groupingBy(Car::getColor, Collectors.counting()));
        log.info("1、不同color车辆销售数量:{}", collect);
//        统计不同color车辆的平均价格
        Map<String, Double> collect1 = cars.stream().collect(Collectors.groupingBy(Car::getColor, Collectors.averagingLong(Car::getPrice)));
        log.info("2、统计不同color车辆的平均价格:{}", collect1);
//        统计不同color,不同brand分组下的平均价格
        Map<String, Map<String, Double>> collect2 = cars.stream().collect(Collectors.groupingBy(Car::getColor, Collectors.groupingBy(Car::getBrand, Collectors.averagingLong(Car::getPrice))));
        log.info("3、统计不同color,不同brand分组下的平均价格:{}", collect2);
//        统计不同color分组下的最大值，最小值，和值
        Map<String, LongSummaryStatistics> collect3 = cars.stream().collect(Collectors.groupingBy(Car::getColor, Collectors.summarizingLong(Car::getPrice)));
        log.info("4、统计不同color分组下的统计值：{}", collect3);

        Map<String, Car> collect4 = cars.stream().collect(Collectors.groupingBy(Car::getBrand, Collectors.collectingAndThen(
                Collectors.maxBy(Comparator.comparingLong(Car::getPrice)),
                Optional::get)
        ));

// 组内排序
        Map<String, List<Car>> brandMap = cars.stream().collect(
                Collectors.groupingBy(
                        Car::getBrand
                )
        );
        Map<String, List<Car>> sortedBrandMap = new HashMap<>();
        brandMap.forEach((brand, list) ->
                sortedBrandMap.put(brand, Collections.singletonList(list.stream().sorted(Comparator.comparingLong(Car::getPrice).reversed()).findFirst().get()))
        );

//        sortedBrandMap.forEach((k,v)->{
//            k=k+"aa";
//            v.get(0).setPrice(999);
//        });
        log.info("5、品牌分组内排序：{}", JSON.toJSONString(sortedBrandMap));

        Map<Long, Double> priceLevelMap = cars.stream().collect(
                Collectors.groupingBy(
                        car -> car.getPrice() / 1_000_000*1_000_000,
                        Collectors.averagingLong(Car::getPrice)
                )
        );
        log.info("6、价格区间分组：{}",JSON.toJSONString(priceLevelMap));


    }


}
