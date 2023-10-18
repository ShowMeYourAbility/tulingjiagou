package com.llmcu.tuling.es.aggs;

import com.llmcu.common.util.DateUtils;
import com.llmcu.tuling.es.aggs.dto.Car;

import java.util.Arrays;
import java.util.List;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/6/30 10:51
 */
public class CarGenerateUtil {
    public static List<Car> carList() {
        List cars = Arrays.asList(
                Car.builder().price(258_000).color("金色").brand("大众").model("大众迈腾").sold_date(DateUtils.strToDate("2021-10-28", DateUtils.COMMON_DATE)).remark("大众中档车").build(),
                Car.builder().price(123_000).color("金色").brand("大众").model("大众速腾").sold_date(DateUtils.strToDate("2021-11-05", DateUtils.COMMON_DATE)).remark("大众神车").build(),
                Car.builder().price(239_800).color("白色").brand("标志").model("标志508").sold_date(DateUtils.strToDate("2021-05-18", DateUtils.COMMON_DATE)).remark("标志品牌全球上市车型").build(),
                Car.builder().price(148_800).color("白色").brand("标志").model("标志408").sold_date(DateUtils.strToDate("2021-07-02", DateUtils.COMMON_DATE)).remark("比较大的紧凑型车").build(),
                Car.builder().price(1_998_000).color("黑色").brand("大众").model("大众辉腾").sold_date(DateUtils.strToDate("2021-08-19", DateUtils.COMMON_DATE)).remark("大众最让人肝疼的车").build(),
                Car.builder().price(218_000).color("红色").brand("奥迪").model("奥迪A4").sold_date(DateUtils.strToDate("2021-11-05", DateUtils.COMMON_DATE)).remark("小资车型").build(),
                Car.builder().price(489_000).color("黑色").brand("奥迪").model("奥迪A6").sold_date(DateUtils.strToDate("2022-01-01", DateUtils.COMMON_DATE)).remark("政府专用？").build(),
                Car.builder().price(1_899_000).color("黑色").brand("奥迪").model("奥迪A8").sold_date(DateUtils.strToDate("2022-02-12", DateUtils.COMMON_DATE)).remark("很贵的大A6。。。").build()
        );
        return cars;
    }
}
