package com.llmcu.dataobject;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import javax.annotation.Generated;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/8/17 13:35
 */
@Data
public class CourseUserDO {
    private Long cid;

    private String cname;

    private Long userId;

    private String cstatus;

    private String username;

    private String ustatus;

    private Integer uage;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
