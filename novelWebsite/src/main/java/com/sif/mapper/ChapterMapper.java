package com.sif.mapper;

import com.sif.pojo.Chapter;
import com.sif.pojo.ChapterExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChapterMapper {
    long countByExample(ChapterExample example);

    int deleteByExample(ChapterExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(Chapter record);

    int insertSelective(Chapter record);

    /*删除章节by 小说编号*/
    void deleteAllChapterByNid(String nid);

  /*  根据小说的id查找章节*/
    List<Chapter> selectChapterByNid(String nid);

    /*查找一个章节*/
    Chapter selectChapterByChapter(@Param("nid") String nid, @Param("chapterid") Integer chapterid);

    List<Chapter> selectByExample(ChapterExample example);

    Chapter selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") Chapter record, @Param("example") ChapterExample example);

    int updateByExample(@Param("record") Chapter record, @Param("example") ChapterExample example);

    int updateByPrimaryKeySelective(Chapter record);

    int updateByPrimaryKey(Chapter record);


}