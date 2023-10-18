package com.llmcu.tuling.es.aggs.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/6/30 9:31
 */
@Data
@Builder
public class Car {
    private long price;
    private String color;
    private String brand;
    private String model;
    private Date sold_date;
    private String remark;
}
