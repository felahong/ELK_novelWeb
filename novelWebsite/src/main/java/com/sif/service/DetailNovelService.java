package com.sif.service;

import com.sif.pojo.DetailNovel;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.util.List;

public interface DetailNovelService {
    List<DetailNovel> selectDetailNovel() throws IOException, SolrServerException;
}
