package com.sif.service;

import com.sif.mailUtils.EsClientUtils;
import com.sif.pojo.DetailNovel;
import com.sif.pojo.SearchNovel;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

/* @program: BrotherNovel
        * @description: es关键字索引小说
        * @author: xifujiang
        * @create: 2019-01-08 19:22
        **/
 @Service
public class SearchNovelServiceImpl implements SearchNovelService{


    @Autowired
    private HttpSolrClient client;

    /**
     * @Description 通过es 实现复杂的搜索功能。功能列表如下
     * 1. 基本搜索功能
     * 2. 分词搜索功能
     * 3. 高亮搜索功能
     * 4. 拼音搜索功能
     * @Author felahong 2020/2/26 0:36
     **/
    @Override
    public SearchNovel searchNovelList(String queryString, Integer page, Integer rows) {

        // 从指定索引中获取内容
        SearchRequestBuilder builder = EsClientUtils.getClient().prepareSearch("novel_all");
        builder.setTypes("doc");   // 设置从哪个type 获取数据。6.x 版本，type 只能设置一个type，即可一个index 对应一个type
        builder.setFrom((page-1)*rows);   // 设置当前数据从哪里开始搜索
        builder.setSize(rows);  // 当前返回rows 条结果

        // 创建一个高亮搜索的对象
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        // 添加需要高亮的字段（被搜索查询的字段就是需要高亮的字段）
        highlightBuilder.field("name.my_pinyin");
        highlightBuilder.field("category");
        highlightBuilder.field("author");
        highlightBuilder.field("novelinfo.my_pinyin");
        // 源码注释：Set the pre tags that will be used for highlighting.
        // 通过标签进行渲染，设置高亮的颜色以及不进行换行
        // 如果用span 标签，高亮字体会有下坠的偏移；
        highlightBuilder.preTags("<p style=\"color:blue; display: inline;\">");
        highlightBuilder.postTags("</p>");   // 后一半标签
        // 源码注释：Adds highlight to perform as part of the search.
        builder.highlighter(highlightBuilder);

        // 指定查询搜索的字段
        // fieldNames 必须跟es 对应，而不是跟javaBean 里的字段对应
        // 通过mapping 设置了中文分词器的字段，要是用该分词器，需要用fieldName.cn
//        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(queryString,
//                "name.pinyin", "category.pinyin", "author.pinyin", "novelinfo.pinyin");
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(queryString,
                "name.my_pinyin", "category", "author", "novelinfo.my_pinyin");

        builder.setQuery(multiMatchQueryBuilder);

        // 源码注释：Should each {@link org.elasticsearch.search.SearchHit} be returned with an explanation of the hit (ranking).
        // 每一个返回结果都会根据关联性有个评分，评分高的在前面
        builder.setExplain(true);

        // 执行
        // 源码注释：Short version of execute().actionGet().
        SearchResponse searchResponse = builder.get();

        SearchHits hits = searchResponse.getHits(); // 源码注释：The search hits，即查询结果
        long totalHits = hits.getTotalHits();   // 查询结果的总数量
        long totalPages = totalHits%rows > 0 ? totalHits/rows+1 : totalHits/rows;   // 总页数

        SearchHit[] searchHits = hits.getHits();    // 获取SearchHit 数组
        ArrayList<DetailNovel> novelList = new ArrayList<>();
        for(SearchHit searchHit : searchHits){
            DetailNovel detailNovel = new DetailNovel();
            Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();   // searchHit 返回json 格式，转为map

            // getHighlightFields() 返回highlightBuilder 中添加的字段的值，即要高亮的字段
            Map<String, HighlightField> highlightFields = searchHit.getHighlightFields();

            HighlightField highlightName = highlightFields.get("name.my_pinyin");
            // 从高亮中获取是否有相关结果，如没有，则从sourceAsMap 中获取
            if(null != highlightName){
                Text[] fragments = highlightName.fragments();   // 获取高亮片段
                StringBuilder name = new StringBuilder();
                for (Text text:fragments) {
                    name.append(text);
                }
                detailNovel.setName(name.toString());

            }
            // 在这个地方曾经报了空指针异常，debug 之后发现sourceAsMap 里，只有name，没有name.cn
            else {
                detailNovel.setName(sourceAsMap.get("name").toString());   // key 应该是es 上的field name
            }
//            detailNovel.setName(sourceAsMap.get("name.cn").toString());   // key 应该是es 上的field name

            HighlightField highlightAuthor = highlightFields.get("author");
            if(null != highlightAuthor){
                Text[] fragments = highlightAuthor.getFragments();
                StringBuilder sb = new StringBuilder();
                for (Text text : fragments) {
                    sb.append(text);
                }
                detailNovel.setAuthor(sb.toString());
            }else {
                detailNovel.setAuthor(sourceAsMap.get("author").toString());
            }

//            detailNovel.setAuthor(sourceAsMap.get("author").toString());    // 获取并设置author 字段的值

            HighlightField highlightCategory = highlightFields.get("category");
            if(null != highlightCategory){
                Text[] categoryFragments = highlightCategory.fragments();
                StringBuilder stringBuilder = new StringBuilder();
                for(Text t : categoryFragments){
                    stringBuilder.append(t);
                }
                detailNovel.setCategory(stringBuilder.toString());
            }else {
                detailNovel.setCategory(sourceAsMap.get("category").toString());

            }
//            detailNovel.setCategory(sourceAsMap.get("category.pinyin").toString());
            detailNovel.setId(sourceAsMap.get("id").toString());
            detailNovel.setDetail(sourceAsMap.get("detail").toString());
            detailNovel.setNews(sourceAsMap.get("new").toString());
            detailNovel.setStatus(sourceAsMap.get("status").toString());
            detailNovel.setCollect(Integer.valueOf(sourceAsMap.get("collect").toString()));
            detailNovel.setCount(Integer.valueOf(sourceAsMap.get("count").toString()));
            detailNovel.setLastupdate(sourceAsMap.get("lastupdate").toString());
            detailNovel.setClickcount(Integer.valueOf(sourceAsMap.get("clickcount").toString()));
            detailNovel.setMonthclick(Integer.valueOf(sourceAsMap.get("monthclick").toString()));
            detailNovel.setWeekclick(Integer.valueOf(sourceAsMap.get("weekclick").toString()));
            detailNovel.setCountreCommend(Integer.valueOf(sourceAsMap.get("countrecommend").toString()));
            detailNovel.setMonthreCommend(Integer.valueOf(sourceAsMap.get("monthrecommend").toString()));
            if(!(sourceAsMap.get("weekrecommend")==null || "".equals(sourceAsMap.get("weekrecommend")))){
                detailNovel.setWeekreCommend(Integer.valueOf(sourceAsMap.get("weekrecommend").toString()));
            }
            if(!(sourceAsMap.get("picurl")==null || "".equals(sourceAsMap.get("picurl")))){
                detailNovel.setPicUrl(sourceAsMap.get("picurl").toString());
            }

            HighlightField highlightNovelinfo = highlightFields.get("novelinfo.my_pinyin");
            if(null != highlightNovelinfo){
                Text[] fragments = highlightNovelinfo.getFragments();
                StringBuilder novelInfo = new StringBuilder();
                for (Text text:fragments) {
                    novelInfo.append(text);
                }
                detailNovel.setNovelInfo(novelInfo.toString());

            }else{
                if(null != sourceAsMap.get("novelinfo")){
                    detailNovel.setNovelInfo(sourceAsMap.get("novelinfo").toString());
                }
            }

//            detailNovel.setNovelInfo(sourceAsMap.get("novelinfo.cn").toString());

            if(null != sourceAsMap.get("novelinfo")){
                detailNovel.setLastChapter(sourceAsMap.get("lastchapter").toString());
            }

            novelList.add(detailNovel);
        }

        // 查询结果对象
        SearchNovel searchNovel = new SearchNovel();
        searchNovel.setNovelList(novelList);
        searchNovel.setPageCount(totalPages);     // 设置页数
        searchNovel.setRecordCount(totalHits);    // 设置总条数
        searchNovel.setCurPage(page); // 设置当前页数

        return searchNovel;
    }





}
