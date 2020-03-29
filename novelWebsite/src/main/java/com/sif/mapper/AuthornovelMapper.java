package com.sif.mapper;

import com.sif.pojo.Authornovel;
import com.sif.pojo.AuthornovelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AuthornovelMapper {
    long countByExample(AuthornovelExample example);

    int deleteByExample(AuthornovelExample example);

    int deleteByPrimaryKey(Integer aid);

    int insert(Authornovel record);

    int insertSelective(Authornovel record);

    List<Authornovel> selectByExample(AuthornovelExample example);

    Authornovel selectByPrimaryKey(Integer aid);

    int updateByExampleSelective(@Param("record") Authornovel record, @Param("example") AuthornovelExample example);

    int updateByExample(@Param("record") Authornovel record, @Param("example") AuthornovelExample example);

    int updateByPrimaryKeySelective(Authornovel record);

    int updateByPrimaryKey(Authornovel record);
}