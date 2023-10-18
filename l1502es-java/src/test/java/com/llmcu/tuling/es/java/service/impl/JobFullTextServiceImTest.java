package com.llmcu.tuling.es.java.service.impl;

import com.llmcu.tuling.es.java.dto.JobDetail;
import com.llmcu.tuling.es.java.service.JobFullTextService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/7/8 22:44
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JobFullTextServiceImTest {
    @Autowired
    private JobFullTextService jobFullTextService;

    /**
     * 准备11000条数据，用于测试分页10000条的问题
     * @throws IOException
     */
    @Test
    public void add11000() throws IOException {
        JobDetail jobDetail = new JobDetail();
        jobDetail.setId(4);
        jobDetail.setArea("北京666");
        jobDetail.setCmp("清华大学");
        jobDetail.setEdu("本科及以上");
        jobDetail.setExp("五年工作经验");
        jobDetail.setTitle("java架构师");
        jobDetail.setJob_type("全职");
        jobDetail.setPv("3000次浏览");
        jobDetail.setJd("Java架构");
        jobDetail.setSalary("60K/月");
        jobDetail.setAge(30);
        for(int i =0; i<11000;i++){
            jobDetail.setId(i);
            jobDetail.setAge(i);
            jobFullTextService.add(jobDetail);
            if(i%100==0){
                System.out.println(i);
            }
        }
    }

    @Test
    public void searchByPageTest2() throws IOException {
        Map<String, Object> resultMap = jobFullTextService.searchByPage2(10100, 1001, 10);
        System.out.println("一共查询到:" + resultMap.get("total").toString());

        ArrayList<JobDetail> content = (ArrayList<JobDetail>)resultMap.get("content");
        for (JobDetail jobDetail : content) {
            System.out.println(jobDetail);
        }
    }

    /**
     * 验证准实时会不会导致读不到。不会
     * @throws IOException
     */
    @Test
    public void addAndQuery() throws IOException {
        JobDetail jobDetail = new JobDetail();
        jobDetail.setId(6);
        jobDetail.setArea("北京666");
        jobDetail.setCmp("清华大学");
        jobDetail.setEdu("本科及以上");
        jobDetail.setExp("五年工作经验");
        jobDetail.setTitle("java架构师");
        jobDetail.setJob_type("全职");
        jobDetail.setPv("3000次浏览");
        jobDetail.setJd("Java架构");
        jobDetail.setSalary("60K/月");
        jobDetail.setAge(30);
        long startTimeMillis = System.currentTimeMillis();
        jobFullTextService.add(jobDetail);
        System.out.println(jobFullTextService.findById(6));
        long endTimeMillis = System.currentTimeMillis();
        System.out.println(endTimeMillis-startTimeMillis);
    }

    @Test
    public void addTest() throws IOException {
        JobDetail jobDetail = new JobDetail();
        jobDetail.setId(4);
        jobDetail.setArea("北京666");
        jobDetail.setCmp("清华大学");
        jobDetail.setEdu("本科及以上");
        jobDetail.setExp("五年工作经验");
        jobDetail.setTitle("java架构师");
        jobDetail.setJob_type("全职");
        jobDetail.setPv("3000次浏览");
        jobDetail.setJd("Java架构");
        jobDetail.setSalary("60K/月");
        jobDetail.setAge(30);
        jobFullTextService.add(jobDetail);
    }

        @Test
    public void getTest() throws IOException {
        System.out.println(jobFullTextService.findById(4));
    }

        @Test
    public void updateTest() throws IOException {
        JobDetail jobDetail = jobFullTextService.findById(4);
        jobDetail.setTitle("java高级架构师4");

        jobFullTextService.update(jobDetail);
    }

        @Test
    public void deleteTest() throws IOException {
        jobFullTextService.deleteById(4);
    }

        @Test
    public void searchTest() throws IOException {
        List<JobDetail> jobDetailList = jobFullTextService.searchByKeywords("java架构");
        for (JobDetail jobDetail : jobDetailList) {
            System.out.println(jobDetail);
        }
    }

        @Test
    public void searchByPageTest() throws IOException {
        Map<String, Object> resultMap = jobFullTextService.searchByPage("java架构", 1, 3);
        System.out.println("一共查询到:" + resultMap.get("total").toString());

        ArrayList<JobDetail> content = (ArrayList<JobDetail>)resultMap.get("content");
        for (JobDetail jobDetail : content) {
            System.out.println(jobDetail);
        }
    }

        @Test
    public void searchByScrollPageTest1() throws IOException {
        Map<String, Object> resultMap = jobFullTextService.searchByScrollPage("java", null, 10);
        System.out.println("scroll_id:" + resultMap.get("scroll_id").toString());

        ArrayList<JobDetail> content = (ArrayList<JobDetail>)resultMap.get("content");
        for (JobDetail jobDetail : content) {
            System.out.println(jobDetail);
        }
    }

    @Test
    public void searchByScrollPageTest2() throws IOException {
        Map<String, Object> resultMap = jobFullTextService.searchByScrollPage("java", "DXF1ZXJ5QW5kRmV0Y2gBAAAAAAAADQ0WTEpFeXQ3dy1TZ2lod2pDTDcyTndpUQ==", 10);
        System.out.println("scroll_id:" + resultMap.get("scroll_id").toString());

        ArrayList<JobDetail> content = (ArrayList<JobDetail>)resultMap.get("content");
        for (JobDetail jobDetail : content) {
            System.out.println(jobDetail);
        }
    }

}