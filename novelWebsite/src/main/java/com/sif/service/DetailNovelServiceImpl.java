package com.sif.service;

import com.sif.conversion.DateChange;
import com.sif.mapper.DetailNovelMapper;
import com.sif.pojo.DetailNovel;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @program: BrotherNovel
 * @description: solr存储小说
 * @author: xifujiang
 * @create: 2019-01-08 16:40
 **/
@Service
public class DetailNovelServiceImpl implements DetailNovelService{
    @Autowired
    DetailNovelMapper detailNovelMapper;
    @Autowired
    HttpSolrClient client;


    @Override
    public List<DetailNovel> selectDetailNovel() throws IOException, SolrServerException {

        client.deleteByQuery("*:*");  //把删除的条件设置为"*:*"就可以了
        client.commit();

        System.out.println(client);
//        查询所有的item
        List<DetailNovel> detailNovels = detailNovelMapper.selectDetailNovel();
        for (DetailNovel detailNovel : detailNovels) {
//            System.out.println(detailNovel.toString());
            //创建一个SolrInputDocument对象
            SolrInputDocument document = new SolrInputDocument();
//            document.setField("id", detailNovel.getNid());
//            document.setField("nname", detailNovel.getNname());
//            document.setField("nauthor", detailNovel.getNauthor());
//            document.setField("nstatic", detailNovel.getNstatic());
//            document.setField("tname", detailNovel.getTname());
//            document.setField("nlabel", detailNovel.getNlabel());
//            document.setField("nlogo", detailNovel.getNlogo());
//            document.setField("nintro", detailNovel.getNintro());
//            document.setField("lastchapter", detailNovel.getLastchapter());
//            document.setField("ncreatetime", DateChange.dateToString(detailNovel.getNcreatetime()));
//            document.setField("chaptername", detailNovel.getChaptername());
//            document.setField("clocation", detailNovel.getClocation());
//            document.setField("createtime", DateChange.dateToString(detailNovel.getCreatetime()));
            //写入索引库
            client.add(document);

    }
        client.commit();

        return detailNovels;
//        return null;
    }

}
