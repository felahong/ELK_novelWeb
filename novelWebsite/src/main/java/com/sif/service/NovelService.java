package com.sif.service;

import com.sif.pojo.CategoryCount;
import com.sif.pojo.DetailNovel;
import com.sif.pojo.Novel;
import com.sif.pojo.TopDetail;

import java.util.List;

public interface NovelService {

    /**
     * @Description TODO 根据小说id 更新小说收藏数
     * @Author felahong 2020/2/26 19:13
     **/
    DetailNovel updateCollectByNid(String nid, String type);

    /**
     * @Description TODO 查询最近更新的小说
     * @Author felahong 2020/2/27 19:26
     **/
    List<DetailNovel> getLastedNovel();

    /**
     * @Description TODO 获取分类总数
     * @Author felahong 2020/2/27 23:32
     **/
    List<CategoryCount> getCategoryCount();

    /**
     * @Description TODO 获取月点击最高的作品
     * @Author felahong 2020/2/28 11:49
     **/
    public List<DetailNovel> getMonthBest(String type);

    /**
     * @Description TODO 获取不同状态的点击量最高的作品
     * @Author felahong 2020/2/28 11:59
     **/
    public List<DetailNovel> getStatusBest(String type);

    /**
     * @Description TODO 小说分类查询
     * @Author felahong 2020/3/9 9:42
     **/
    public List<DetailNovel> getNovelByCategory(String category);

    List<TopDetail> getTopAuthor();
    /*
        新建小说
    */
    public int updateNovel(Novel novel);

    /*
        根据作者的名字查询小说
     */
    List<DetailNovel> selectNovelsByAuthor(String username);

    /*
        根据小说id更新小说
     */
    void updateNovelByNid(String nid);

    void deleteCustomerById(String nid);

    /*查找所有的小说*/
    List<DetailNovel> selectAllBook();

    /*根据小说的id搜索小说*/
    DetailNovel selectNovelByNid(String nid);


}
