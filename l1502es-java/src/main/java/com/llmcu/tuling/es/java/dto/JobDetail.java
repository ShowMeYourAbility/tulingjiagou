package com.llmcu.tuling.es.java.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.SneakyThrows;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/7/7 21:31
 */
@Data
public class JobDetail {

    @JSONField(serialize = false)
    private long id;
    private String area;
    private String exp;
    private String edu;
    private String salary;
    private String job_type;
    private String cmp;
    private String pv;
    private String title;
    private String jd;
    private int age;


    @SneakyThrows
    @Override
    public String toString() {
//        return JSON.toJSONString(this);
        return new ObjectMapper().writeValueAsString(this);
    }
}
