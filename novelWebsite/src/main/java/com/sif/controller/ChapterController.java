package com.sif.controller;

import com.sif.file.FileOperation;
import com.sif.pojo.Chapter;
import com.sif.pojo.ChapterDetail;
import com.sif.pojo.DetailNovel;
import com.sif.pojo.Novel;
import com.sif.service.ChapterService;
import com.sif.service.DetailNovelService;
import com.sif.service.NovelService;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class ChapterController {
    @Autowired
    NovelService novelServiceImpl;
    @Autowired
    ChapterService chapterServiceImpl;
    @Autowired
    DetailNovelService detailNovelServiceImpl;


    /**
     * @Description 更新小说收藏数
     * 补充：尚未实现用户收藏限制：
     *      1. 用户需要登录；
     *      2. 每个用户只能收藏一次
     * @Author felahong 2020/2/26 19:10
     **/
    @RequestMapping(value="updateCollect", method= RequestMethod.GET)
    public String updateCollect(String nid, String type, Map<String,Object> map){
        //按照小说id查找该小说
        DetailNovel novel = null;
        List<Chapter> chapterList = new ArrayList<>();
        try {
            novel = novelServiceImpl.updateCollectByNid(nid, type);
        } catch (IllegalArgumentException e) {
            System.out.println("catching illegal argument exception...");
            map.put("info_msg", "developing...");
            // 如果捕获到异常，说明updateCollectByNid 未能执行，则novel 为空，需要给novel 赋值
            // 否则前端读取不到内容
            novel = novelServiceImpl.selectNovelByNid(nid);
            chapterList = chapterServiceImpl.selectChapterByNid(nid);
        }
        map.put("novel", novel);
        map.put("chapterList",chapterList);
        return "catalog";   // 返回到catalog 页面
    }

    /**
     * @Description 显示目录，根据小说id 从es 上novel_detail 索引上查找小说章节信息
     * @Author felahong 2020/2/27 15:10
     **/
    @RequestMapping(value="openCatalog", method= RequestMethod.GET)
    public String openCatalog(String nid, Map<String,Object> map){
//        System.out.println(nid);
        // 根据小说id 查找小说基本信息，对应es 上novel 索引
        DetailNovel novel = novelServiceImpl.selectNovelByNid(nid);
        // 根据小说id 查找小说章节信息，对应es 上novel_detail 索引
        List<Chapter> chapterList = chapterServiceImpl.selectChapterByNid(nid);
//        List<Chapter> chapterList = chapterServiceImpl.selectChapterByNovelName(novel.getName());

//        System.out.println(novel.toString());
        map.put("novel", novel);
        map.put("chapterList",chapterList);
        return "catalog";
    }

    /**
     * @Description 小说具体章节内容
     * @Author felahong 2020/2/27 15:12
     **/
    @RequestMapping(value="content")
    public String content(String nid, String name, String chapter, String author, Map<String,Object> map) throws Exception{
        // 按照小说id 查找该小说具体章节内容
        ChapterDetail chapterDetail = chapterServiceImpl.getChapterDetail(name, chapter, nid, author);
        map.put("content", chapterDetail);
        return "content";

    }

    /**
     * @Description 更新推荐
     * 说明：仍然需要用户限制。
     *      1. 用户需要登录
     *      2. 用户必须拥有推荐票
     *      3. 周推荐需要更新（离线任务，如每半天更新一次）
     *      4. 月推荐需要更新（离线任务）
     * @Author felahong 2020/2/26 19:38
     **/
//    @RequestMapping(value="updateRecommend", method= RequestMethod.GET)
//    public String updateRecommend(String nid, Map<String,Object> map){
//        //按照小说id查找该小说
//        DetailNovel novel = novelServiceImpl.updateCollectByNid(nid);
//
//        map.put("novel", novel);
//        return "catalog";   // 返回到catalog 页面
//    }


    /**
     * @Description: 提交到章节新建页面
     * @Param: [nid, lastchapter, map]
     * @return: java.lang.String
     * @Author: xifujiang
     * @Date: 2019/1/4
     */
    @RequestMapping(value = "insertChapter")
    public String insertChapter(String nid,Integer lastchapter, Map<String,Object> map){
//        System.out.println("nid:"+nid +"lastchapter:"+lastchapter);
        map.put("nid",nid);
        map.put("lastchapter",lastchapter);
        return "insert-chapter";
    }

    /** 
    * @Description: 提交章节
    * @Param: [nid, lastchapter, chaptername, content] 
    * @return: java.lang.String 
    * @Author: xifujiang
    * @Date: 2019/1/4 
    */ 
    @RequestMapping(value = "submitChapter",method= RequestMethod.POST)
    @ResponseBody
    public Integer submitChapter(String nid,String chaptername,String content){

        /*novel表中的最新章节+1，更新数据库数据库*/
        novelServiceImpl.updateNovelByNid(nid);

        DetailNovel novel= novelServiceImpl.selectNovelByNid(nid);
//        Integer lastchapter = novel.getLastchapter();
//        /*存放小说内容*/
//        String novelId = nid.split("-")[0];
//        String filePath ="D:\\2environment\\upload\\brotherNovel\\novel\\"+novelId+"\\"+lastchapter+".txt";
//        File file = new File(filePath);
//
//        try {
//            FileOperation.createFile(file);
//            FileOperation.writeTxtFile(content,file);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        /*添加内容到章节表*/
//        Chapter chapter = new Chapter();
//        chapter.setNid(nid);
//        chapter.setChapterid(lastchapter);
//        chapter.setChaptername(chaptername);
//        chapter.setClocation(novelId+"/"+lastchapter+".txt");
//        chapter.setCreatetime(new Date());
//        /*更新章节表*/
//        chapterServiceImpl.addChapter(chapter);
//
//        //更新所有内容到solr
//        try {
//            List<DetailNovel> detailNovels = detailNovelServiceImpl.selectDetailNovel();
//        } catch (SolrServerException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return lastchapter;
        return 12;
    }

}
