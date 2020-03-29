package com.sif.pojo;

import java.util.Date;

/**
 * @program: BrotherNovel
 * @description: 小说的详细描述
 * @author: xifujiang
 * @create: 2019-01-08 16:21
 **/
public class DetailNovel {

    private String id;  // 书id
    private String name;    // 书名
    private String author;  // 作者
    private String status;  // 书的状态
    private String category;    // 书的类别
    private String detail;  // 书的详情页地址
    private String news;    // 书的总便签页
    private Integer collect;    // 收藏数
    private Integer count;  // 书的字数
    private String lastupdate;  // 书的最新更新时间
    private Integer clickcount; // 总点击次数
    private Integer monthclick; // 月点击次数
    private Integer weekclick;  // 周点击次数
    private Integer countreCommend; // 总推荐
    private Integer monthreCommend; // 月推荐
    private Integer weekreCommend;  // 周推荐
    private String picUrl;  // 小说封面图
    private String novelInfo;   // 小说简介
    private String lastChapter; // 最近章节的名字

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public Integer getCollect() {
        return collect;
    }

    public void setCollect(Integer collect) {
        this.collect = collect;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(String lastupdate) {
        this.lastupdate = lastupdate;
    }

    public Integer getClickcount() {
        return clickcount;
    }

    public void setClickcount(Integer clickcount) {
        this.clickcount = clickcount;
    }

    public Integer getMonthclick() {
        return monthclick;
    }

    public void setMonthclick(Integer monthclick) {
        this.monthclick = monthclick;
    }

    public Integer getWeekclick() {
        return weekclick;
    }

    public void setWeekclick(Integer weekclick) {
        this.weekclick = weekclick;
    }

    public Integer getCountreCommend() {
        return countreCommend;
    }

    public void setCountreCommend(Integer countreCommend) {
        this.countreCommend = countreCommend;
    }

    public Integer getMonthreCommend() {
        return monthreCommend;
    }

    public void setMonthreCommend(Integer monthreCommend) {
        this.monthreCommend = monthreCommend;
    }

    public Integer getWeekreCommend() {
        return weekreCommend;
    }

    public void setWeekreCommend(Integer weekreCommend) {
        this.weekreCommend = weekreCommend;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getNovelInfo() {
        return novelInfo;
    }

    public void setNovelInfo(String novelInfo) {
        this.novelInfo = novelInfo;
    }

    public String getLastChapter() {
        return lastChapter;
    }

    public void setLastChapter(String lastChapter) {
        this.lastChapter = lastChapter;
    }

    @Override
    public String toString() {
        return "DetailNovel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", status='" + status + '\'' +
                ", category='" + category + '\'' +
                ", detail='" + detail + '\'' +
                ", news='" + news + '\'' +
                ", collect=" + collect +
                ", count=" + count +
                ", lastupdate='" + lastupdate + '\'' +
                ", clickcount=" + clickcount +
                ", monthclick=" + monthclick +
                ", weekclick=" + weekclick +
                ", countreCommend=" + countreCommend +
                ", monthreCommend=" + monthreCommend +
                ", weekreCommend=" + weekreCommend +
                ", picUrl='" + picUrl + '\'' +
                ", novelInfo='" + novelInfo + '\'' +
                ", lastChapter='" + lastChapter + '\'' +
                '}';
    }
}
