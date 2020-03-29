package com.felahong.test;

import com.sif.mailUtils.EsClientUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;

import java.util.List;
import java.util.Map;

/**
 * @Program: NovelWebOnBigDataStructure
 * @Package: com.felahong.test
 * @Description: Created by felahong on 2020/2/27 22:50
 * TODO 测试ES 分类聚合展示功能
 */

public class EsAggTest {

    /**
     * @Description TODO ES aggregation test
     * @Author felahong 2020/2/27 22:56
     **/
    public static void main(String[] args) {

        SearchRequestBuilder builder = EsClientUtils.getClient().prepareSearch("novel_c");

        /**
         * Source Notes
         *      AggregationBuilders：Utility class to create aggregations.
         *      terms(): Create a new {@link Terms} aggregation with the given name.
         */
        // category_count，相当于对结果取的别名
        TermsAggregationBuilder termAgg = AggregationBuilders.terms("category_count").field("category");

        builder.addAggregation(termAgg);    // Source Note: Adds an aggregation to the search operation.

        SearchResponse searchResponse = builder.get();

        Map<String, Aggregation> aggregationMap = searchResponse.getAggregations().asMap();

        for (String aggKey : aggregationMap.keySet()) {
//             System.out.println("key: "+ aggKey+" value: "+aggregationMap.get(aggKey).toString());
            /**
             * 上面的输出结果会把value 划分成一个一个字，如把“武侠修真”，划分成四个字
             * 因为category 是text，会对它进行分词，分开聚合
             * 因为es 默认把keyword 看成一个整体，所以不会进行分词再聚合
             * 因此要实现中文不分词的聚合，需要重建mapping，把text 换为keyword
             *
             * key: category_count value: {"category_count":{
             * "doc_count_error_upper_bound":0,
             * "sum_other_doc_count":0,
             * "buckets":[{"key":"网游竞技","doc_count":1406},.....
             */

            // 聚合的解析需要强转为StringTerms 类型
            StringTerms stringTerms = (StringTerms)aggregationMap.get(aggKey);

            List<StringTerms.Bucket> buckets = stringTerms.getBuckets();    // 获取bucket 的list

            for (StringTerms.Bucket keyValue : buckets) {
                String key = keyValue.getKey().toString();
                long docCount = keyValue.getDocCount();
                System.out.println("key: "+key+" value: "+docCount);
            }
            /*
                key: 网游竞技 value: 1406
                key: 武侠仙侠 value: 1372
                key: 武侠修真 value: 926
                key: 都市言情 value: 557
                key: 恐怖灵异 value: 556
                key: 女频频道 value: 548
                key: 历史军事 value: 208
                key: 科幻灵异 value: 128
             */
        }

    }
}