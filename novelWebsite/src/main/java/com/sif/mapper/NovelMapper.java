package com.sif.mapper;

import com.sif.pojo.Novel;
import com.sif.pojo.NovelExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NovelMapper {
    long countByExample(NovelExample example);

    int deleteByExample(NovelExample example);

    int deleteByPrimaryKey(String nid);

    int insert(Novel record);

    int insertSelective(Novel record);
    /*
        根据作者的名字查询小说
     */
    List<Novel> selectNovelsByAuthor(String username);

    /*
        根据小说id更新小说
    */
    void updateNovelByNid(@Param("nid") String nid);

    List<Novel> selectByExample(NovelExample example);

    Novel selectByPrimaryKey(String nid);

    int updateByExampleSelective(@Param("record") Novel record, @Param("example") NovelExample example);

    int updateByExample(@Param("record") Novel record, @Param("example") NovelExample example);

    int updateByPrimaryKeySelective(Novel record);

    int updateByPrimaryKey(Novel record);

    List<Novel> selectAllNovel();
}