package com.sif.pojo;

import java.util.Date;

public class Chapter {

    private Integer id;    // 用于排序
    private String chapterName; // 主要的查找内容
    private Integer novelId;    // 用于搜索

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public Integer getNovelId() {
        return novelId;
    }

    public void setNovelId(Integer novelId) {
        this.novelId = novelId;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "id=" + id +
                ", chapterName='" + chapterName + '\'' +
                ", novelId=" + novelId +
                '}';
    }
}