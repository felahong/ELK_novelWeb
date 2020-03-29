package com.sif.controller;

import com.sif.pojo.DetailNovel;
import com.sif.service.DetailNovelService;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @program: BrotherNovel
 * @description: 小说的详细描述操作
 * @author: xifujiang
 * @create: 2019-01-08 16:38
 **/
@Controller
@RequestMapping("/solr")
public class DetailNovelController {
    @Autowired
    DetailNovelService detailNovelServiceImpl;

    @RequestMapping("/importAll")
    @ResponseBody
    public String importAllItems(Map<String,Object> map) throws IOException, SolrServerException {
        /*更新所有内容到solr*/
        List<DetailNovel> detailNovels = detailNovelServiceImpl.selectDetailNovel();

        return "";
    }
}
