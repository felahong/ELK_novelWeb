package com.sif.controller;

import com.sif.pojo.CategoryCount;
import com.sif.pojo.DetailNovel;
import com.sif.pojo.Novel;
import com.sif.pojo.TopDetail;
import com.sif.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class NovelController {
    @Autowired
    UserService userServiceImpl;
    @Autowired
    NovelService novelServiceImpl;
    @Autowired
    AuthorNovelService authorNovelServiceImpl;
    @Autowired
    TypeService typeServiceImpl;
    @Autowired
    ChapterService chapterServiceImpl;
    @Autowired
    DetailNovelService detailNovelServiceImpl;

    /** 
    * @Description: 小说界面
    * @Param: [httpSession] 
    * @return: java.lang.String 
    * @Author: xifujiang
    * @Date: 2019/1/4 
    */ 
    @RequestMapping(value = "index")
    public String novel(){
        return "index";
    }

    @RequestMapping(value = "main")
    public String model(HttpSession httpSession, Map<String,Object> map){
        // 获取最近更新的小说
        List<DetailNovel> lastedNovel = novelServiceImpl.getLastedNovel();

        // 将作品分类排序展示（首页）
        List<DetailNovel> allNovel = novelServiceImpl.selectAllBook();
        // 作品分类聚合
        List<CategoryCount> categoryCount = novelServiceImpl.getCategoryCount();
        // 作品周精选
        List<DetailNovel> weekBest = novelServiceImpl.getMonthBest("week");
        // 作品月精选
        List<DetailNovel> monthBest = novelServiceImpl.getMonthBest("month");
        // 小说状态榜单，连载or完结
        List<DetailNovel>  serialBest = novelServiceImpl.getStatusBest("serial");
        List<DetailNovel> endBest = novelServiceImpl.getStatusBest("end");
        // top10作家榜
        List<TopDetail> topAuthor = novelServiceImpl.getTopAuthor();

        map.put("lastedNovel", lastedNovel);
        map.put("allNovel", allNovel);
        map.put("categoryCount", categoryCount);
        map.put("weekBest", weekBest);
        map.put("monthBest", monthBest);
        map.put("serialBest", serialBest);
        map.put("endBest", endBest);
        map.put("topAuthor", topAuthor);

        return "main";
    }

    /** 
    * @Description:  个人中心
    * @Param: [] 
    * @return: java.lang.String 
    * @Author: xifujiang
    * @Date: 2019/1/4 
    */ 
    @RequestMapping(value = "personIndex")
    public String personIndex(){
        return "person-index";
    }

    @RequestMapping(value = "personMain")
    public String personMain(){
        return "person-main";
    }

}
