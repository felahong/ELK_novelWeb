package com.sif.service;

import com.sif.pojo.Chapter;
import com.sif.pojo.ChapterDetail;

import java.io.IOException;
import java.util.List;

public interface ChapterService {

    ChapterDetail getChapterDetail(String name, String chapter, String nid, String author) throws IOException;

    void addChapter(Chapter chapter);

    void deleteAllChapterByNid(String nid);

    List<Chapter> selectChapterByNid(String nid);

    public List<Chapter> selectChapterByNovelName(String nname);

    Chapter selectChapterByChapter(String nid, Integer lastchapter);
}
