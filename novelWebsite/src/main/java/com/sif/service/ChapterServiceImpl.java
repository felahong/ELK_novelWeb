package com.sif.service;

import com.sif.mailUtils.EsClientUtils;
import com.sif.mailUtils.HBaseUtils;
import com.sif.mailUtils.MD5Util;
import com.sif.mapper.ChapterMapper;
import com.sif.pojo.Chapter;
import com.sif.pojo.ChapterDetail;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    ChapterMapper chapterMapper;


    /**
     * @Description 根据小说的id 从es 中novel_detail 索引查找章节
     * @Author felahong 2020/2/27 17:49
     **/
    public List<Chapter> selectChapterByNid(String nid) {
        SearchRequestBuilder builder = EsClientUtils.getClient().prepareSearch(EsClientUtils.getNovelDetailIndex());
        // 源码注释：* @param name  The name of the field
        //          * @param value The value of the term
        // 类似于rdb，field 是字段名称，term 是要query 的值
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("novel_id", nid);
        builder.setQuery(termQueryBuilder); // 源码注释：Constructs a new search source builder with a search query.
        builder.setTypes("doc");

        SearchResponse searchResponse = builder.get();  // 源码注释：Short version of execute().actionGet().
        SearchHits hits = searchResponse.getHits();     // 获取查询结果
        List<Chapter> chaptersList = new ArrayList<>();
        SearchHit[] chapterHits = hits.getHits();       // 查询结果转数组

        // 循环获取章节信息，添加到列表里
        for (SearchHit hit : chapterHits) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();     // 源码注释：The source of the document as a map
            Chapter chapter = new Chapter();
            chapter.setId(Integer.valueOf(sourceAsMap.get("id").toString()));
            chapter.setChapterName(sourceAsMap.get("chapter_name").toString());
            chapter.setNovelId(Integer.valueOf(sourceAsMap.get("novel_id").toString()));
            chaptersList.add(chapter);
        }
        // 要对章节信息进行排序，调用比较器，对chaptersList 的id 进行排序
        Collections.sort(chaptersList, new Comparator<Chapter>() {
            @Override
            public int compare(Chapter o1, Chapter o2) {
                return o1.getId() - o2.getId();     // 升序排列
            }
        });
        return chaptersList;
    }

    /**
     * @Description TODO 根据小说名获取小说目录信息
     * @Author felahong 2020/3/9 10:46
     **/
    public List<Chapter> selectChapterByNovelName(String nname) {
        SearchRequestBuilder builder = EsClientUtils.getClient().prepareSearch(EsClientUtils.getNovelDetailIndex());
        // 源码注释：* @param name  The name of the field
        //          * @param value The value of the term
        // 类似于rdb，field 是字段名称，term 是要query 的值
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("novel_name", nname);
        builder.setQuery(termQueryBuilder); // 源码注释：Constructs a new search source builder with a search query.
        builder.setTypes("doc");

        SearchResponse searchResponse = builder.get();  // 源码注释：Short version of execute().actionGet().
        SearchHits hits = searchResponse.getHits();     // 获取查询结果
        List<Chapter> chaptersList = new ArrayList<>();
        SearchHit[] chapterHits = hits.getHits();       // 查询结果转数组

        // 循环获取章节信息，添加到列表里
        for (SearchHit hit : chapterHits) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();     // 源码注释：The source of the document as a map
            Chapter chapter = new Chapter();
            chapter.setId(Integer.valueOf(sourceAsMap.get("id").toString()));
            chapter.setChapterName(sourceAsMap.get("chapter_name").toString());
//            chapter.setNovelId(Integer.valueOf(sourceAsMap.get("novel_id").toString()));
            chaptersList.add(chapter);
        }
        // 要对章节信息进行排序，调用比较器，对chaptersList 的id 进行排序
        Collections.sort(chaptersList, new Comparator<Chapter>() {
            @Override
            public int compare(Chapter o1, Chapter o2) {
                return o1.getId() - o2.getId();     // 升序排列
            }
        });
        return chaptersList;
    }


    /**
     * @Description 从HBase 上获取具体章节内容的方法
     *              需要从hbase 上获取的内容只有章节内容的url，所以其它字段字需要赋值，url 从hbase 里get
     * @Author felahong 2020/2/27 17:48
     **/
    public ChapterDetail getChapterDetail(String name, String chapter, String nid, String author) throws IOException {
        ChapterDetail chapterDetail = new ChapterDetail();
        chapterDetail.setChapterName(chapter);
        chapterDetail.setNovelName(name);
        chapterDetail.setNovelId(nid);
        chapterDetail.setAuthorName(author);

        // 获取hbase 连接客户端
        Connection connection = HBaseUtils.client();
        Table table = connection.getTable(TableName.valueOf("novel_detail_test"));
        String rowKey = MD5Util.md5(name+chapter);  // hbase 里的rowkey 为小说名+章节名取md5
        // hbase 查询数据用get，根据rowkey 进行查询效率最高
        Get get = new Get(rowKey.getBytes());

        Result result = table.get(get);
        for (KeyValue keyValue : result.raw()) {
            String key = new String(keyValue.getQualifier());
            if("chapterUrl".equals(key)){
//                System.out.println(new String(keyValue.getValue()));
                chapterDetail.setChapterUrl(new String(keyValue.getValue()));
            }
        }
        System.out.println(chapterDetail.getChapterUrl());
        return chapterDetail;
    }

    /**
     * @Description: 添加章节
     * @Param: [chapter]
     * @return: void
     * @Author: xifujiang
     * @Date: 2019/1/4
     */
    @Override
    public void addChapter(Chapter chapter) {
        chapterMapper.insert(chapter);
    }

    /**
     * @Description: 删除小说时删除所有的章节
     * @Param: [nid]
     * @return: void
     * @Author: xifujiang
     * @Date: 2019/1/4
     */
    @Override
    public void deleteAllChapterByNid(String nid) {
        chapterMapper.deleteAllChapterByNid(nid);
    }


    /** 
    * @Description: 查询一本章节
    * @Param: [nid, lastchapter]
    * @return: com.sif.pojo.Chapter 
    * @Author: xifujiang
    * @Date: 2019/1/6 
    */ 
    @Override
    public Chapter selectChapterByChapter(String nid, Integer chapterid) {
        return chapterMapper.selectChapterByChapter(nid,chapterid);
    }

}
