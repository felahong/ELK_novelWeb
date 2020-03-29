package com.sif.service;

import com.sif.pojo.SearchNovel;

public interface SearchNovelService {
    SearchNovel searchNovelList(String queryString, Integer page, Integer rows);
}
