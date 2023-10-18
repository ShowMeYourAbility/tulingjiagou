package com.llmcu.tuling.es.java.service;

import com.llmcu.tuling.es.java.dto.JobDetail;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/7/7 21:30
 */
public interface JobFullTextService {
    // 添加一个职位数据
    void add(JobDetail jobDetail) throws IOException;

    // 根据ID检索指定职位数据
    JobDetail findById(long id) throws IOException;

    // 修改职位薪资
    void update(JobDetail jobDetail) throws IOException;

    // 根据ID删除指定位置数据
    void deleteById(long id) throws IOException;

    // 根据关键字检索数据
    List<JobDetail> searchByKeywords(String keywords) throws IOException;

    // 分页检索
    Map<String, Object> searchByPage(String keywords, int pageNum, int pageSize) throws IOException;
    Map<String, Object> searchByPage2(int age, int pageNum, int pageSize) throws IOException;

    // scroll分页解决深分页问题
    Map<String, Object> searchByScrollPage(String keywords, String scrollId, int pageSize) throws IOException;


    // 关闭ES连接
    void close() throws IOException;
}
