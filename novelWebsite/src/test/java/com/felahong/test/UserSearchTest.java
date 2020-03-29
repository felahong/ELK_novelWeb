package com.felahong.test;

import com.sif.mailUtils.EsClientUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import java.util.Map;

public class UserSearchTest {

    public static void main(String[] args) {
        SearchRequestBuilder builder = EsClientUtils.getClient().prepareSearch("novel_c");
        builder.setQuery(QueryBuilders.termQuery("author","再梦里"));
//        builder.setQuery(matchQueryBuilder);
        builder.setExplain(true);

        SearchResponse searchResponse = builder.get();
        SearchHits hits = searchResponse.getHits();
        SearchHit[] hits1 = hits.getHits();

        //List<DetailNovel> novelList = new ArrayList<>();
        for (SearchHit searchHit:hits1) {

            Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();
            System.out.println(sourceAsMap.get("name").toString());

        }

    }
}
