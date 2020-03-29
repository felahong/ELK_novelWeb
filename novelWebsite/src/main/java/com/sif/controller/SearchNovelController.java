package com.sif.controller;

import com.sif.pojo.DetailNovel;
import com.sif.pojo.SearchNovel;
import com.sif.service.SearchNovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @program: BrotherNovel
 * @description: 关键字查询小说
 * @author: xifujiang
 * @create: 2019-01-08 19:21
 **/
@Controller
public class SearchNovelController {

    @Autowired
    private SearchNovelService searchNovelServiceImpl;

    /** 
    * @Description: 搜索关键字
    * @Param: [queryString, page, rows, map] 
    * @return: java.lang.String 
    * @Author: xifujiang
    * @Date: 2019/1/8 
    */ 
    @RequestMapping(value = "/solr/novelList", method= RequestMethod.GET)
    public String searchList(String queryString,
                             @RequestParam(defaultValue="1")Integer page,
                             @RequestParam(defaultValue="10")Integer rows, Map<String,Object> map){
        System.out.println(queryString);
        /*查询对象，封装了detialNovel*/
        SearchNovel searchNovel = null;
        try {

            searchNovel = searchNovelServiceImpl.searchNovelList(queryString, page, rows);

        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("queryString",queryString);
        map.put("searchNovel",searchNovel);
        return "novelList";
    }

    /** 
    * @Description: 查看某作者信息
    * @Param: [queryString, page, rows, map] 
    * @return: java.lang.String 
    * @Author: xifujiang
    * @Date: 2019/1/8 
    */ 
    @RequestMapping(value="/solr/openAuthor")
    public String openAuthor(String nauthor,
                             @RequestParam(defaultValue="1")Integer page,
                             @RequestParam(defaultValue="8")Integer rows, Map<String,Object> map){

        System.out.println(nauthor);
        /*查询对象，封装了detialNovel*/
        SearchNovel searchNovel = null;
        try {

            searchNovel = searchNovelServiceImpl.searchNovelList(nauthor, page, rows);

        } catch (Exception e) {
            e.printStackTrace();
        }
        DetailNovel detailNovel =searchNovel.getNovelList().get(0);
//        System.out.println(searchNovel.getNovelList().size());
        map.put("nauthor",nauthor);
        map.put("newNovel",detailNovel);
        map.put("searchNovel",searchNovel);

        return "author";
    }
}
