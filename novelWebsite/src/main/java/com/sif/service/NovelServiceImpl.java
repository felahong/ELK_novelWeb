package com.sif.service;

import com.sif.mailUtils.EsClientUtils;
import com.sif.mapper.NovelMapper;
import com.sif.pojo.CategoryCount;
import com.sif.pojo.DetailNovel;
import com.sif.pojo.Novel;
import com.sif.pojo.TopDetail;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequestBuilder;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.sum.InternalSum;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class NovelServiceImpl implements NovelService{
    @Autowired
    NovelMapper novelMapper;


    /**
     * @Description TODO 根据小说的id 从ES 搜索小说
     * @Author felahong 2020/2/28 17:23
     **/
    public DetailNovel selectNovelByNid(String nid){
//        return novelMapper.selectByPrimaryKey(nid);

        // 根据id 获取documents
        GetResponse response = EsClientUtils.getClient()
                // 源码：Gets the document that was indexed from an index with a type (optional) and id.
                .prepareGet(EsClientUtils.getNovelIndex(), "doc", nid)
                .execute().actionGet();

        Map<String, Object> sourceAsMap = response.getSourceAsMap();    // 源码注释：The source of the document (As a map)
        DetailNovel detailNovel = new DetailNovel();
        detailNovel.setName(sourceAsMap.get("name").toString());
        detailNovel.setId(sourceAsMap.get("id").toString());
        detailNovel.setDetail(sourceAsMap.get("detail").toString());
        detailNovel.setNovelInfo(sourceAsMap.get("novelinfo").toString());
        detailNovel.setAuthor(sourceAsMap.get("author").toString());
        detailNovel.setCategory(sourceAsMap.get("category").toString());

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

        detailNovel.setWeekreCommend(Integer.valueOf(sourceAsMap.get("weekrecommend").toString()));
        detailNovel.setPicUrl(sourceAsMap.get("picurl").toString());
        return detailNovel;

    }

    /**
     * @Description TODO 通过id 和type 更新es 上对应小说的tpye 值，并返回新的一个DetailNovel
     * @Author felahong 2020/2/26 19:28
     **/
    @Override
    public DetailNovel updateCollectByNid(String nid, String type) {
        // 根据id 获取documents
        GetResponse response = EsClientUtils.getClient()
                // 源码：Gets the document that was indexed from an index with a type (optional) and id.
                .prepareGet(EsClientUtils.getNovelIndex(), "doc", nid)
                .execute().actionGet();

        Map<String, Object> sourceAsMap = response.getSourceAsMap();    // 源码注释：The source of the document (As a map)
        // 获取自增一后的数据
        if("collect".equals(type)){
            Integer collect = Integer.valueOf(sourceAsMap.get("collect").toString()) + 1;
            sourceAsMap.put("collect", collect);
        }else if ("recommend".equals(type)){
            // 更新推荐总数
            Integer countrecommend = Integer.valueOf(sourceAsMap.get("countrecommend").toString()) + 1;
            sourceAsMap.put("countrecommend", countrecommend);
            // 更新周推荐数
            Integer weekrecommend = Integer.valueOf(sourceAsMap.get("weekrecommend").toString()) + 1;
            sourceAsMap.put("weekrecommend", weekrecommend);
            // 更新月推荐数
            Integer monthrecommend = Integer.valueOf(sourceAsMap.get("monthrecommend").toString()) + 1;
            sourceAsMap.put("monthrecommend", monthrecommend);
        }else{
            // 如果不是“收藏” 和“推荐”，则报参数异常
            throw new IllegalArgumentException("Error: current type " + type + "is not found");
        }

        // 更新es 上的收藏数
        UpdateRequestBuilder updateBuilder = EsClientUtils.getClient().prepareUpdate(EsClientUtils.getNovelIndex(), "doc", nid);
        // 通过UpdateRequestBuilder 更新es 上的局部内容（收藏数）
        updateBuilder.setDoc(sourceAsMap).execute().actionGet();

        // 获取新的detailNovel, 因为是先把加一后的collect put 到sourceAsMap 上，然后再通过setDoc(sourceAsMap) 去更新es 上的内容
        // 因此新的detailNovel 直接从sourceAsMap 上获取即可
        DetailNovel detailNovel = new DetailNovel();
        detailNovel.setName(sourceAsMap.get("name").toString());
        detailNovel.setId(sourceAsMap.get("id").toString());
        detailNovel.setDetail(sourceAsMap.get("detail").toString());
        detailNovel.setNovelInfo(sourceAsMap.get("novelinfo").toString());
        detailNovel.setAuthor(sourceAsMap.get("author").toString());
        detailNovel.setCategory(sourceAsMap.get("category").toString());

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

        detailNovel.setWeekreCommend(Integer.valueOf(sourceAsMap.get("weekrecommend").toString()));
        detailNovel.setPicUrl(sourceAsMap.get("picurl").toString());
        return detailNovel;

    }

    /**
     * @Description TODO 获取最新的小说
     * @Author felahong 2020/2/27 23:33
     **/
    @Override
    public List<DetailNovel> getLastedNovel() {

        SearchRequestBuilder builder = EsClientUtils.getClient().prepareSearch("novel_all");
//        SearchRequestBuilder builder = EsClientUtils.getClient().prepareSearch("novelIndex");
        builder.setTypes("doc");
//        builder.setFrom(0);     // Source Note: From index to start the search from. Defaults to <tt>0</tt>.
        builder.setSize(10);    // Source Note: The number of search hits to return. Defaults to <tt>10</tt>.
        builder.addSort("lastupdate", SortOrder.DESC);  // 获取最近更新的小说，自然需要排序。按更新时间，降序排列
        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();// 源码注释：A query that matches on all documents.
        builder.setQuery(matchAllQueryBuilder);
        builder.setExplain(true);   // 设置关联性评分，高的在前
        SearchResponse searchResponse = builder.get();  // Source Note: Short version of execute().actionGet().
        SearchHits searchHits = searchResponse.getHits();   // 获取查询结果
        SearchHit[] hits = searchHits.getHits();

        List<DetailNovel> detailNovelList = new ArrayList<>();
        for(SearchHit hit : hits){
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            DetailNovel detailNovel = new DetailNovel();
            detailNovel.setName(sourceAsMap.get("name").toString());
            detailNovel.setId(sourceAsMap.get("id").toString());
            detailNovel.setAuthor(sourceAsMap.get("author").toString());
            detailNovel.setCategory(sourceAsMap.get("category").toString());
            detailNovel.setLastupdate(sourceAsMap.get("lastupdate").toString());
            detailNovelList.add(detailNovel);

        }

        return detailNovelList;
    }

    /**
     * @Description TODO 查找所有的小说，根据点击数量排序
     * @Author felahong 2020/2/27 19:58
     **/
    @Override
    public List<DetailNovel> selectAllBook(){
        SearchRequestBuilder builder = EsClientUtils.getClient().prepareSearch("novel_all");
//        SearchRequestBuilder builder = EsClientUtils.getClient().prepareSearch("novelIndex");

        builder.setTypes("doc");
        //我们当前的数据从哪里开始搜索
        builder.setFrom(0);
        //当前返回几条结果
        builder.setSize(240);
        builder.addSort("clickcount", SortOrder.DESC);

        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
        builder.setQuery(matchAllQueryBuilder);
        //根据关联性返回评分高的在前面
        builder.setExplain(true);
        SearchResponse searchResponse = builder.get();
        SearchHits searchHits = searchResponse.getHits();

        List<DetailNovel> detailNovelList = new ArrayList<>();
        SearchHit[] hits = searchHits.getHits();
        for (SearchHit searchHit : hits) {
            Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();
            DetailNovel detailNovel = new DetailNovel();
            detailNovel.setName(sourceAsMap.get("name").toString());
            detailNovel.setId(sourceAsMap.get("id").toString());
            detailNovel.setAuthor(sourceAsMap.get("author").toString());
            detailNovel.setCategory(sourceAsMap.get("category").toString());
            detailNovelList.add(detailNovel);
        }
        return detailNovelList;
    }

    /**
     * @Description TODO 获取不同小说分类的总数
     * @Author felahong 2020/2/28 17:42
     **/
    @Override
    public List<CategoryCount> getCategoryCount() {
//        SearchRequestBuilder builder = EsClientUtils.getClient().prepareSearch("novel_c");
        SearchRequestBuilder builder = EsClientUtils.getClient().prepareSearch("novel_all");
        /**
         * Source Notes
         *      AggregationBuilders：Utility class to create aggregations.
         *      terms(): Create a new {@link Terms} aggregation with the given name.
         */
        // category_count，相当于对结果取的别名
        TermsAggregationBuilder termAgg = AggregationBuilders.terms("category_count").field("category");
        builder.addAggregation(termAgg);
        SearchResponse searchResponse = builder.execute().actionGet();
        Map<String, Aggregation> aggregationMap = searchResponse.getAggregations().asMap();

        List<CategoryCount> categoryCountsList = new ArrayList<>();
        for (String aggKey:aggregationMap.keySet()) {
            //System.out.println("key: "+ aggKey+" value: "+aggregationMap.get(aggKey).toString());

            // 聚合的解析需要强转为StringTerms 类型
            StringTerms stringTerms = (StringTerms)aggregationMap.get(aggKey);
            List<StringTerms.Bucket> buckets = stringTerms.getBuckets();
            for (StringTerms.Bucket keyValue : buckets) {

                CategoryCount categoryCount = new CategoryCount();

                String key = keyValue.getKey().toString();
                long docCount = keyValue.getDocCount();
                categoryCount.setNovelCategory(key);
                categoryCount.setDocCount(docCount);

                categoryCountsList.add(categoryCount);
            }
        }
        return categoryCountsList;
    }

    /**
     * @Description TODO 根据月/周点击数查询小说
     * @Author felahong 2020/2/28 11:51
     **/
    @Override
    public List<DetailNovel> getMonthBest(String type) {
//        SearchRequestBuilder builder = EsClientUtils.getClient().prepareSearch("novel_c");
        SearchRequestBuilder builder = EsClientUtils.getClient().prepareSearch("novel_all");
        // 根据月点击数来进行查询而不是根据某一个具体字段，所以要查询所有的数据
        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
        // SortOrder是一个枚举类，所以直接可以把排序方式给点出来
        if("month".equals(type)){
            builder.addSort("monthclick", SortOrder.DESC);
        }else {
            builder.addSort("weekclick", SortOrder.DESC);
        }

        builder.setFrom(0);     // Source Note：From index to start the search from. Defaults to <tt>0</tt>.
        builder.setSize(8);     // Source Note：The number of search hits to return. Defaults to <tt>10</tt>.
        builder.setQuery(matchAllQueryBuilder);     // Source Note：Constructs a new search source builder with a search query.
        builder.setExplain(true);   // 设置关联性评分

        SearchResponse searchResponse = builder.get();
        SearchHits searchHits = searchResponse.getHits();     // 获取hit 结果
        SearchHit[] hits = searchHits.getHits();     // hit 结果转数组

        List<DetailNovel> novelList = new ArrayList<>();
        for (SearchHit searchHit : hits) {
            DetailNovel detailNovel = new DetailNovel();
            Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();   // 把结果包装成map，否则是一长串的json
            detailNovel.setPicUrl(sourceAsMap.get("picurl").toString());
            detailNovel.setName(sourceAsMap.get("name").toString());
            detailNovel.setMonthclick(Integer.valueOf(sourceAsMap.get("monthclick").toString()));
            detailNovel.setWeekclick(Integer.valueOf(sourceAsMap.get("weekclick").toString()));

            novelList.add(detailNovel);
        }
        return novelList;
    }

    /**
     * @Description TODO 连载榜及完结榜后台实现，通过点击量实现榜单排序
     * @Author felahong 2020/2/28 12:32
     **/
    @Override
    public List<DetailNovel> getStatusBest(String type) {
//        SearchRequestBuilder builder = EsClientUtils.getClient().prepareSearch("novel_c");
        SearchRequestBuilder builder = EsClientUtils.getClient().prepareSearch("novel_all");
        MatchQueryBuilder matchQueryBuilder = null;
        if("serial".equals(type)){
            matchQueryBuilder = QueryBuilders.matchQuery("status", "连载");

        }else{
            matchQueryBuilder = QueryBuilders.matchQuery("status", "完结");
        }
        builder.addSort("countrecommend",SortOrder.DESC);
        builder.setFrom(0);
        builder.setSize(8);
        builder.setQuery(matchQueryBuilder);
        builder.setExplain(true);

        SearchResponse searchResponse = builder.get();
        SearchHits hits = searchResponse.getHits();
        SearchHit[] hits1 = hits.getHits();

        List<DetailNovel> novelList = new ArrayList<>();
        for (SearchHit searchHit:hits1) {
            DetailNovel detailNovel = new DetailNovel();
            Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();
            detailNovel.setPicUrl(sourceAsMap.get("picurl").toString());
            detailNovel.setName(sourceAsMap.get("name").toString());
            detailNovel.setCountreCommend(Integer.valueOf(sourceAsMap.get("countrecommend").toString()));
            novelList.add(detailNovel);
        }
        return novelList;
    }

    /**
     * @Description TODO 新书榜的后台实现方法
     *                  mysql 里的实现：select sum(clickcount) as total, author, group_contact(name) novelName
     *                  from novel group by author order by total desc;
     *                  sql 转es：www.ischoolbar.com/EsParser
     * @Author felahong 2020/2/28 13:32
     **/
    @Override
    public List<TopDetail> getTopAuthor() {

//        SearchRequestBuilder builder = EsClientUtils.getClient().prepareSearch("novel_d");
        SearchRequestBuilder builder = EsClientUtils.getClient().prepareSearch("novel_all");

        // ES 语法通过AggregationBuilders “翻译”
        TermsAggregationBuilder termsAggregationBuilder = AggregationBuilders
                .terms("authorAgg")     // 聚合，并取别名
                .field("author")    // 根据author 聚合
                .order(BucketOrder.aggregation("clicksum",false))   // 排序
                .subAggregation(    // 子聚合
                        AggregationBuilders     // 再一个AggregationBuilders
                                .sum("clicksum")    // 对clickcount 求和并取别名clicksum
                                .field("clickcount")
                                .subAggregation(
                                        AggregationBuilders     // 第三层AggregationBuilders
                                                .topHits("top"))
                );

        /**
         * 新书榜其实和作家榜差不多，只不过多了一些条件
         *      1.新书，字数要有限制   30-50万内的叫新书
         *      2.要是在连载中
         */
        builder.setPostFilter(QueryBuilders.matchQuery("status", "连载中"));
        //builder.setQuery(QueryBuilders.matchQuery("status","连载中"));
        // 限制字数 在30-50万内
        builder.setQuery(QueryBuilders.rangeQuery("count").from(300000).to(500000));

        // 这里是进行聚合的内容，我们聚合之前应该先进行对应的过滤操作
        builder.addAggregation(termsAggregationBuilder);

        SearchResponse searchResponse = builder.get();

        //System.out.println(searchResponse.getAggregations().toString());
        Map<String, Aggregation> aggregationMap = searchResponse.getAggregations().asMap();

        StringTerms stringTerms = (StringTerms)aggregationMap.get("authorAgg");
        List<StringTerms.Bucket> buckets = stringTerms.getBuckets();
        List<TopDetail>  topList = new ArrayList<>();
        for (StringTerms.Bucket keyValue:buckets) {
            TopDetail topDetail = new TopDetail();
            String key = keyValue.getKey().toString();
            long docCount = keyValue.getDocCount();
            InternalSum clicksum = keyValue.getAggregations().get("clicksum");
            topDetail.setClickCount(clicksum.getValue());
            topDetail.setAuthor(key);
            topDetail.setSumBooks(docCount);
            topDetail.setPic("https://www.23us.so/files/article/image/29/29582/29582s.jpg");
            topList.add(topDetail);
        }
        return topList;
    }

    /**
     * @Description TODO 新建小说
     * @Author felahong 2020/3/9 9:44
     **/
    @Override
    public int updateNovel(Novel novel){
        return novelMapper.insertSelective(novel);
    }

    /**
     * @Description TODO 根据作者的名字查询小说
     * @Author felahong 2020/2/28 17:22
     **/
    @Override
    public List<DetailNovel> selectNovelsByAuthor(String username) {
        SearchRequestBuilder builder = EsClientUtils.getClient().prepareSearch("novel_all");
//        SearchRequestBuilder builder = EsClientUtils.getClient().prepareSearch("novel_c");
        builder.setQuery(   // SN：Constructs a new search source builder with a search query.
                QueryBuilders.termQuery("author", username));  // termQuery 属于完全匹配查询

        builder.setExplain(true);
        SearchResponse searchResponse = builder.get();
        SearchHits hits = searchResponse.getHits();

        List<DetailNovel> detailNovelList = new ArrayList<>();
        SearchHit[] searchHits = hits.getHits();
        for (SearchHit searchHit : searchHits) {
            Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();
            DetailNovel detailNovel = new DetailNovel();
            detailNovel.setName(sourceAsMap.get("name").toString());
            System.out.println(sourceAsMap.get("name").toString());
            detailNovel.setPicUrl(sourceAsMap.get("picurl").toString());
            detailNovel.setId(sourceAsMap.get("id").toString());
            detailNovel.setLastChapter(sourceAsMap.get("lastchapter").toString());
            detailNovel.setLastupdate(sourceAsMap.get("lastupdate").toString());
            detailNovel.setAuthor(sourceAsMap.get("author").toString());
            detailNovel.setCategory(sourceAsMap.get("category").toString());
            detailNovelList.add(detailNovel);
        }

        return detailNovelList;
    }

    /**
     * @Description TODO 根据小说分类查询小说列表
     * @Author felahong 2020/3/9 10:02
     **/
    @Override
    public List<DetailNovel> getNovelByCategory(String category) {
        SearchRequestBuilder builder = EsClientUtils.getClient().prepareSearch(EsClientUtils.getNovelIndex());
        // 设置查询内容
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("category", category);
        builder.setQuery(termQueryBuilder);
        builder.setTypes("doc");

        // 获取查询结果
        SearchResponse searchResponse = builder.get();
        SearchHits hits = searchResponse.getHits();
        SearchHit[] categoryHits = hits.getHits();

        // 查询结果封装
        List<DetailNovel> categoryList = new ArrayList<>();
        DetailNovel detailNovel = null;
        for(SearchHit hit : categoryHits){
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            detailNovel = new DetailNovel();
            detailNovel.setId(sourceAsMap.get("id").toString());
            detailNovel.setName(sourceAsMap.get("name").toString());
            detailNovel.setAuthor(sourceAsMap.get("author").toString());
            detailNovel.setCategory(category);
            detailNovel.setNovelInfo(sourceAsMap.get("novelInfo").toString());
            detailNovel.setLastChapter(sourceAsMap.get("lastchapter").toString());
            detailNovel.setLastupdate(sourceAsMap.get("lastupdate").toString());
            categoryList.add(detailNovel);

        }

        return categoryList;
    }

    /*
        根据小说id更新小说
        */
    @Override
    public void updateNovelByNid(String nid) {
        novelMapper.updateNovelByNid(nid);
    }

    /*根据id删除小说*/
    @Override
    public void deleteCustomerById(String nid) {
        novelMapper.deleteByPrimaryKey(nid);
    }

}
