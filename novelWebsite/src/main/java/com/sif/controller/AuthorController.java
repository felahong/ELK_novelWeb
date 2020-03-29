package com.sif.controller;

import com.sif.file.FileOperation;
import com.sif.pojo.*;
import com.sif.service.*;
import org.apache.commons.io.FilenameUtils;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @program: BrotherNovel
 * @description: 作者controller
 * @author: xifujiang
 * @create: 2019-01-10 09:55
 **/
@Controller
public class AuthorController {
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
     * @Description: 作者中心
     * @Param: []
     * @return: java.lang.String
     * @Author: xifujiang
     * @Date: 2019/1/4
     */
    @RequestMapping(value = "authorIndex")
    public String authorIndex(){
        return "author-index";
    }

    @RequestMapping(value= "judgeAuthor")
    @ResponseBody
    public String judgeAuthor(HttpSession session){
        User user = (User)session.getAttribute("user");
        if(user != null) {
            if(user.getUisauthor() == 1){
                return "isAuthor";
            }else{
                Integer isAuthor = userServiceImpl.selectUisAuthorByUid(user.getUid());
                if(isAuthor == 1){
                    return "isAuthor";
                }else{
                    return "notAuthor";
                }
            }
        }
        return "noUser";
    }

    @RequestMapping(value= "activeAuthor")
    public String activeAuthor(HttpSession session){

        User user = (User)session.getAttribute("user");
        userServiceImpl.updateUisAuthorByUid(user.getUid());

        return "author-index";
    }

    @RequestMapping(value = "authorMain")
    public String authorMain(){
        return "author-main";
    }

    /**
     * @Description TODO 进入小说管理页面
     * @Author felahong 2020/2/28 17:24
     **/
    @RequestMapping(value = "novelManage")
    public String novelManage(HttpSession session, Map<String,Object> map){
        // 查询该作者的小说并显示
        User user = (User)session.getAttribute("user");
        List<DetailNovel> novels = novelServiceImpl.selectNovelsByAuthor(user.getUsername());
        /*查询所有的小说类别*/
//        List<Type> types = typeServiceImpl.selectAllType();

        /*存储到map中*/
        map.put("novels", novels);
//        map.put("types", types);
        return "novelmanage";
    }

    /*
     * @Description:  创建小说
     * @Param: [novel, session, pictureFile]
     * @return: org.springframework.web.servlet.ModelAndView
     * @Author: xifujiang
     * @Date: 2019/1/4
     */
    @RequestMapping(value = "submitNovel",method= RequestMethod.POST)
    @ResponseBody
    public String submitNovel(DetailNovel novel, HttpSession session) throws IOException {

        System.out.println(novel.toString());
        User user = (User) session.getAttribute("user");
        int i = authorNovelServiceImpl.updateAuthorNovel(novel);
        System.out.println("新增的书id为：" + i);
        return "success";
//        novel.setNauthor(user.getUsername());
//        novel.setNcreatetime(new Date());
//
//        //保存图片到
//        String name = UUID.randomUUID().toString().replaceAll("-", "");
//
//        String ext = FilenameUtils.getExtension(pictureFile.getOriginalFilename());
//
//        pictureFile.transferTo(new File("D:\\2environment\\upload\\brotherNovel\\pic\\" + name + "." + ext));
//        novel.setNlogo(name + "." + ext);
//        novel.setNid(UUID.randomUUID().toString());
//        System.out.println(novel.toString());
//        //创建小说
//        if (novelServiceImpl.updateNovel(novel) > 0) {
//            //添加作者 小说表的信息
//            Authornovel authornovel = new Authornovel();
//            authornovel.setUid(user.getUid());
//            authornovel.setUsername(user.getUsername());
//            authornovel.setNid(novel.getNid());
//            authornovel.setNname(novel.getNname());
//            if (authorNovelServiceImpl.updateAuthorNovel(authornovel) > 0) {
//                /*成功*/
//                //更新所有内容到solr
//                try {
//                    List<DetailNovel> detailNovels = detailNovelServiceImpl.selectDetailNovel();
//                } catch (SolrServerException e) {
//                    e.printStackTrace();
//                }
//                return "success";
//            }
//        } else {
//            return "error";
//        }
//        return "error";
    }

    /**
     * @Description:  进入小说创建页面
     * @Param: []
     * @return: java.lang.String
     * @Author: xifujiang
     * @Date: 2019/1/4
     */
    @RequestMapping(value = "createNovel")
    public String createNovel(){
        return "create-novel";
    }

    /**
     * @Description:  删除小说√
     * @Param: [chapter]
     * @return: java.lang.String
     * @Author: xifujiang
     * @Date: 2019/1/4
     */
    @RequestMapping(value = "deleteNovel")
    public @ResponseBody
    String DeleteNovel(String nid){
        System.out.println(nid);
        /*删除与该小说的所有章节*/
        chapterServiceImpl.deleteAllChapterByNid(nid);

        /*删除磁盘中的小说*/
        String novelId = nid.split("-")[0];
        String fileName = "D:\\2environment\\upload\\brotherNovel\\novel\\"+novelId;
        File file = new File("fileName");
        FileOperation.deleteMkdirs(file);

        /*删除该小说*/
        novelServiceImpl.deleteCustomerById(nid);
        //更新所有内容到solr
        try {
            List<DetailNovel> detailNovels = detailNovelServiceImpl.selectDetailNovel();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "OK";
    }

}
