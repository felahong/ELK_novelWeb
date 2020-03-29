package com.sif.service;

import com.sif.mailUtils.EsClientUtils;
import com.sif.mapper.AuthornovelMapper;
import com.sif.pojo.Authornovel;
import com.sif.pojo.DetailNovel;
import org.elasticsearch.action.index.IndexResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthorNovelServiceImpl implements AuthorNovelService{
    @Autowired
    AuthornovelMapper authornovelMapper;

    /**
     * @Description TODO 添加小说
     * @Author felahong 2020/2/28 18:46
     **/
    public int updateAuthorNovel(DetailNovel novel){

        Map<String ,Object> novelMap = new HashMap<>();
        novelMap.put("name", novel.getName());
        novelMap.put("status", novel.getStatus());
        novelMap.put("category", novel.getCategory());
        novelMap.put("detail", novel.getDetail());
        novelMap.put("picurl", novel.getPicUrl());
        novelMap.put("novelinfo", novel.getNovelInfo());
        novelMap.put("author", novel.getAuthor());
        novelMap.put("id", 5707);
        novelMap.put("lastchapter", "暂无章节");
        novelMap.put("lastupdate", "2018-12-31 23:45:31");
        novelMap.put("count", 0);
        novelMap.put("clickcount", 0);
        novelMap.put("monthclick", 0);
        novelMap.put("countrecommend", 0);
        novelMap.put("monthrecommend", 0);
        novelMap.put("weekrecommend", 0);

        // ES 的put 操作
        IndexResponse indexResponse = EsClientUtils.getClient()
                .prepareIndex("novel_all", "doc")     // SN：Index a document associated with a given index and type.
                .setSource(novelMap)    // SN：Index the Map as a JSON.
                .setId("12121212")  // SN：Sets the id to index the document under. Optional, and if not set, one will be automatically generated.
                .get();     // SN：Short version of execute().actionGet().

        return Integer.valueOf(indexResponse.getId());
    }
}
