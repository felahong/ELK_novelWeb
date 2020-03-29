package com.felahong.test;

import com.sif.mailUtils.EsClientUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.sum.InternalSum;

import java.util.List;
import java.util.Map;

/**
 * @Program: NovelWebOnBigDataStructure
 * @Package: com.felahong.test
 * @Description: Created by felahong on 2020/2/28 14:43
 * TODO 测试es 复杂聚合，根据所有作品的点击量总量统计作家排行
 *      mysql: select sum(clickcount) as total, author, GROUP_CONCAT(name) novelName from novel group by author order by total desc;
 */

public class EsAuthorGroupTest {

    /*
{
	"aggs": {
		# 根据author 聚合
		"author": {
			"terms": {
				"field": "author",
				"size": 10,
				# 根据total 排序，相当于order by
				"order": {
					"total": "DESC"
				}
			},
			# 根据total 聚合
			"aggs": {
				"total": {
					# sum 操作
					"sum": {
						"field": "clickcount"
					}
				},
				# 相当于sql 里的group by
				"top": {
					"top_hits": {
						"size": 1
					}
				}
			}
		}
	}
}

     */

    /**
     * @Description TODO 代码实现上面的ES 语句
     * @Author felahong 2020/2/28 15:06
     **/
    public static void main(String[] args) {
        SearchRequestBuilder builder = EsClientUtils.getClient().prepareSearch("novel_d");

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

        builder.setQuery(QueryBuilders.rangeQuery("count").from(300000).to(500000));
        //builder.setQuery(QueryBuilders.matchQuery("status","连载中"));
        builder.addAggregation(termsAggregationBuilder);    // SN：Adds an aggregation to the search operation.

        SearchResponse searchResponse = builder.get();

        //System.out.println(searchResponse.getAggregations().toString());
        Map<String, Aggregation> aggregationMap = searchResponse.getAggregations().asMap();


            //System.out.println("key: "+ aggKey+" value: "+aggregationMap.get(aggKey).toString());
        //Aggregation authorAgg = aggregationMap.get("authorAgg");
        StringTerms stringTerms = (StringTerms)aggregationMap.get("authorAgg");
        List<StringTerms.Bucket> buckets = stringTerms.getBuckets();

        System.out.println(stringTerms.toString());

        for (StringTerms.Bucket keyValue : buckets) {
            String key = keyValue.getKey().toString();
            long docCount = keyValue.getDocCount();
            InternalSum clicksum = keyValue.getAggregations().get("clicksum");

            System.out.println("clicksum" + clicksum.getValue());
            System.out.println("key: "+key+" value: "+docCount);
        }
    }
}
