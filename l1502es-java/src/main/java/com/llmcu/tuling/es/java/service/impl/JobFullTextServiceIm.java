package com.llmcu.tuling.es.java.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.llmcu.tuling.es.java.dto.JobDetail;
import com.llmcu.tuling.es.java.service.JobFullTextService;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchScrollRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/7/8 22:44
 */
@Service
public class JobFullTextServiceIm implements JobFullTextService {
    /**
     //     * 索引库的名字
     //     */
    private static final String JOB_IDX = "job_index";
    @Autowired
    @Qualifier("elasticsearchRestHighLevelClient")
    private RestHighLevelClient client;
    @Override
    public void add(JobDetail jobDetail) throws IOException {
//1.	构建IndexRequest对象，用来描述ES发起请求的数据。
        IndexRequest indexRequest = new IndexRequest(JOB_IDX);

        //2.	设置文档ID。
        indexRequest.id(jobDetail.getId() + "");

        //3.	使用FastJSON将实体类对象转换为JSON。
        String json = JSONObject.toJSONString(jobDetail);

        //4.	使用IndexRequest.source方法设置文档数据，并设置请求的数据为JSON格式。
        indexRequest.source(json, XContentType.JSON);



        //5.	使用ES High level client调用index方法发起请求，将一个文档添加到索引中。
        client.index(indexRequest, RequestOptions.DEFAULT);
    }

        @Override
    public JobDetail findById(long id) throws IOException {
        // 1.	构建GetRequest请求。
        GetRequest getRequest = new GetRequest(JOB_IDX, id + "");

        // 2.	使用RestHighLevelClient.get发送GetRequest请求，并获取到ES服务器的响应。
        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);

        // 3.	将ES响应的数据转换为JSON字符串
        String json = getResponse.getSourceAsString();

        // 4.	并使用FastJSON将JSON字符串转换为JobDetail类对象
        JobDetail jobDetail = JSONObject.parseObject(json, JobDetail.class);

        // 5.	记得：单独设置ID
        jobDetail.setId(id);

        return jobDetail;
    }

        @Override
    public void update(JobDetail jobDetail) throws IOException {
        // 1.	判断对应ID的文档是否存在
        // a)	构建GetRequest
        GetRequest getRequest = new GetRequest(JOB_IDX, jobDetail.getId() + "");

        // b)	执行client的exists方法，发起请求，判断是否存在
        boolean exists = client.exists(getRequest, RequestOptions.DEFAULT);

        if(exists) {
            // 2.	构建UpdateRequest请求
            UpdateRequest updateRequest = new UpdateRequest(JOB_IDX, jobDetail.getId() + "");

            // 3.	设置UpdateRequest的文档，并配置为JSON格式
            updateRequest.doc(JSONObject.toJSONString(jobDetail), XContentType.JSON);

            // 4.	执行client发起update请求
            client.update(updateRequest, RequestOptions.DEFAULT);
        }
    }

        @Override
    public void deleteById(long id) throws IOException {
        // 1.	构建delete请求
        DeleteRequest deleteRequest = new DeleteRequest(JOB_IDX, id + "");

        // 2.	使用RestHighLevelClient执行delete请求
        client.delete(deleteRequest, RequestOptions.DEFAULT);

    }

        @Override
    public List<JobDetail> searchByKeywords(String keywords) throws IOException {
        // 1.构建SearchRequest检索请求
        // 专门用来进行全文检索、关键字检索的API
        SearchRequest searchRequest = new SearchRequest(JOB_IDX);

        // 2.创建一个SearchSourceBuilder专门用于构建查询条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        // 3.使用QueryBuilders.multiMatchQuery构建一个查询条件（搜索title、jd），并配置到SearchSourceBuilder
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(keywords, "title", "jd");

        // 将查询条件设置到查询请求构建器中
        searchSourceBuilder.query(multiMatchQueryBuilder);

        // 4.调用SearchRequest.source将查询条件设置到检索请求
        searchRequest.source(searchSourceBuilder);

        // 5.执行RestHighLevelClient.search发起请求
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHit[] hitArray = searchResponse.getHits().getHits();

        // 6.遍历结果
        ArrayList<JobDetail> jobDetailArrayList = new ArrayList<>();

        for (SearchHit documentFields : hitArray) {
            // 1)获取命中的结果
            String json = documentFields.getSourceAsString();

            // 2)将JSON字符串转换为对象
            JobDetail jobDetail = JSONObject.parseObject(json, JobDetail.class);

            // 3)使用SearchHit.getId设置文档ID
            jobDetail.setId(Long.parseLong(documentFields.getId()));

            jobDetailArrayList.add(jobDetail);
        }

        return jobDetailArrayList;
    }

        @Override
    public Map<String, Object> searchByPage(String keywords, int pageNum, int pageSize) throws IOException {
        // 1.构建SearchRequest检索请求
        // 专门用来进行全文检索、关键字检索的API
        SearchRequest searchRequest = new SearchRequest(JOB_IDX);

        // 2.创建一个SearchSourceBuilder专门用于构建查询条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        // 3.使用QueryBuilders.multiMatchQuery构建一个查询条件（搜索title、jd），并配置到SearchSourceBuilder
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(keywords, "title", "jd");

        // 将查询条件设置到查询请求构建器中
        searchSourceBuilder.query(multiMatchQueryBuilder);

        // 每页显示多少条
        searchSourceBuilder.size(pageSize);
        // 设置从第几条开始查询
        searchSourceBuilder.from((pageNum - 1) * pageSize);

        // 4.调用SearchRequest.source将查询条件设置到检索请求
        searchRequest.source(searchSourceBuilder);

        // 5.执行RestHighLevelClient.search发起请求
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHit[] hitArray = searchResponse.getHits().getHits();

        // 6.遍历结果
        ArrayList<JobDetail> jobDetailArrayList = new ArrayList<>();

        for (SearchHit documentFields : hitArray) {
            // 1)获取命中的结果
            String json = documentFields.getSourceAsString();

            // 2)将JSON字符串转换为对象
            JobDetail jobDetail = JSONObject.parseObject(json, JobDetail.class);

            // 3)使用SearchHit.getId设置文档ID
            jobDetail.setId(Long.parseLong(documentFields.getId()));

            jobDetailArrayList.add(jobDetail);
        }

        // 8.	将结果封装到Map结构中（带有分页信息）
        // a)	total -> 使用SearchHits.getTotalHits().value获取到所有的记录数
        // b)	content -> 当前分页中的数据
        long totalNum = searchResponse.getHits().getTotalHits().value;
        HashMap<String,Object> hashMap = new HashMap<>(2);
        hashMap.put("total", totalNum);
        hashMap.put("content", jobDetailArrayList);


        return hashMap;
    }

    @Override
    public Map<String, Object> searchByPage2(int age, int pageNum, int pageSize) throws IOException {
        // 1.构建SearchRequest检索请求
        // 专门用来进行全文检索、关键字检索的API
        SearchRequest searchRequest = new SearchRequest(JOB_IDX);

        // 2.创建一个SearchSourceBuilder专门用于构建查询条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        // 3.使用QueryBuilders.multiMatchQuery构建一个查询条件（搜索title、jd），并配置到SearchSourceBuilder
        RangeQueryBuilder multiMatchQueryBuilder = QueryBuilders.rangeQuery("age").from(0).to(age);

        // 将查询条件设置到查询请求构建器中
        searchSourceBuilder.query(multiMatchQueryBuilder);

        // 每页显示多少条
        searchSourceBuilder.size(pageSize);
        // 设置从第几条开始查询
        searchSourceBuilder.from((pageNum - 1) * pageSize);

        searchSourceBuilder.sort("age", SortOrder.DESC);

        // 4.调用SearchRequest.source将查询条件设置到检索请求
        searchRequest.source(searchSourceBuilder);

        // 5.执行RestHighLevelClient.search发起请求
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHit[] hitArray = searchResponse.getHits().getHits();

        // 6.遍历结果
        ArrayList<JobDetail> jobDetailArrayList = new ArrayList<>();

        for (SearchHit documentFields : hitArray) {
            // 1)获取命中的结果
            String json = documentFields.getSourceAsString();

            // 2)将JSON字符串转换为对象
            JobDetail jobDetail = JSONObject.parseObject(json, JobDetail.class);

            // 3)使用SearchHit.getId设置文档ID
            jobDetail.setId(Long.parseLong(documentFields.getId()));

            jobDetailArrayList.add(jobDetail);
        }

        // 8.	将结果封装到Map结构中（带有分页信息）
        // a)	total -> 使用SearchHits.getTotalHits().value获取到所有的记录数
        // b)	content -> 当前分页中的数据
        long totalNum = searchResponse.getHits().getTotalHits().value;
        HashMap<String,Object> hashMap = new HashMap<>(2);
        hashMap.put("total", totalNum);
        hashMap.put("content", jobDetailArrayList);


        return hashMap;
    }

        @Override
    public Map<String, Object> searchByScrollPage(String keywords, String scrollId, int pageSize) throws IOException {
        SearchResponse searchResponse ;

        if(scrollId == null) {
            // 1.构建SearchRequest检索请求
            // 专门用来进行全文检索、关键字检索的API
            SearchRequest searchRequest = new SearchRequest(JOB_IDX);

            // 2.创建一个SearchSourceBuilder专门用于构建查询条件
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

            // 3.使用QueryBuilders.multiMatchQuery构建一个查询条件（搜索title、jd），并配置到SearchSourceBuilder
            MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(keywords, "title", "jd");

            // 将查询条件设置到查询请求构建器中
            searchSourceBuilder.query(multiMatchQueryBuilder);

            // 设置高亮
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            highlightBuilder.field("title");
            highlightBuilder.field("jd");
            highlightBuilder.preTags("<font color='red'>");
            highlightBuilder.postTags("</font>");

            // 给请求设置高亮
            searchSourceBuilder.highlighter(highlightBuilder);

            // 每页显示多少条
            searchSourceBuilder.size(pageSize);

            // 4.调用SearchRequest.source将查询条件设置到检索请求
            searchRequest.source(searchSourceBuilder);

            //--------------------------
            // 设置scroll查询
            //--------------------------
            searchRequest.scroll(TimeValue.timeValueMinutes(5));

            // 5.执行RestHighLevelClient.search发起请求
            searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        }
        // 第二次查询的时候，直接通过scroll id查询数据
        else {
            SearchScrollRequest searchScrollRequest = new SearchScrollRequest(scrollId);
            searchScrollRequest.scroll(TimeValue.timeValueMinutes(5));

            // 使用RestHighLevelClient发送scroll请求
            searchResponse = client.scroll(searchScrollRequest, RequestOptions.DEFAULT);
        }

        //--------------------------
        // 迭代ES响应的数据
        //--------------------------

        SearchHit[] hitArray = searchResponse.getHits().getHits();

        // 6.遍历结果
        ArrayList<JobDetail> jobDetailArrayList = new ArrayList<>();

        for (SearchHit documentFields : hitArray) {
            // 1)获取命中的结果
            String json = documentFields.getSourceAsString();

            // 2)将JSON字符串转换为对象
            JobDetail jobDetail = JSONObject.parseObject(json, JobDetail.class);

            // 3)使用SearchHit.getId设置文档ID
            jobDetail.setId(Long.parseLong(documentFields.getId()));

            jobDetailArrayList.add(jobDetail);

            // 设置高亮的一些文本到实体类中
            // 封装了高亮
            Map<String, HighlightField> highlightFieldMap = documentFields.getHighlightFields();
            HighlightField titleHl = highlightFieldMap.get("title");
            HighlightField jdHl = highlightFieldMap.get("jd");

            if(titleHl != null) {
                // 获取指定字段的高亮片段
                Text[] fragments = titleHl.getFragments();
                // 将这些高亮片段拼接成一个完整的高亮字段
                StringBuilder builder = new StringBuilder();
                for(Text text : fragments) {
                    builder.append(text);
                }
                // 设置到实体类中
                jobDetail.setTitle(builder.toString());
            }

            if(jdHl != null) {
                // 获取指定字段的高亮片段
                Text[] fragments = jdHl.getFragments();
                // 将这些高亮片段拼接成一个完整的高亮字段
                StringBuilder builder = new StringBuilder();
                for(Text text : fragments) {
                    builder.append(text);
                }
                // 设置到实体类中
                jobDetail.setJd(builder.toString());
            }
        }

        // 8.	将结果封装到Map结构中（带有分页信息）
        // a)	total -> 使用SearchHits.getTotalHits().value获取到所有的记录数
        // b)	content -> 当前分页中的数据
//        long totalNum = searchResponse.getHits().getTotalHits();
        HashMap<String,Object> hashMap = new HashMap<>(2);
        hashMap.put("scroll_id", searchResponse.getScrollId());
        hashMap.put("content", jobDetailArrayList);

        return hashMap;
    }





    @Override
    public void close() throws IOException {

    }
}