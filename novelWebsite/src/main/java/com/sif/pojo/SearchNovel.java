package com.sif.pojo;

import java.util.List;

/**
 * @program: BrotherNovel
 * @description: 用于查找小说
 * @author: xifujiang
 * @create: 2019-01-08 19:17
 **/
public class SearchNovel {
    //商品列表
    private List<DetailNovel> novelList;
    //总记录数
    private long recordCount;
    //总页数
    private long pageCount;
    //当前页
    private long curPage;

    public List<DetailNovel> getNovelList() {
        return novelList;
    }

    public void setNovelList(List<DetailNovel> novelList) {
        this.novelList = novelList;
    }

    public long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(long recordCount) {
        this.recordCount = recordCount;
    }

    public long getPageCount() {
        return pageCount;
    }

    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }

    public long getCurPage() {
        return curPage;
    }

    public void setCurPage(long curPage) {
        this.curPage = curPage;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "itemList=" + novelList.size() +
                ", recordCount=" + recordCount +
                ", pageCount=" + pageCount +
                ", curPage=" + curPage +
                '}';
    }
}
