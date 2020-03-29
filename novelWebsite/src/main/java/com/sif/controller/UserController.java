package com.sif.controller;

import com.sif.mailUtils.MailUtils;
import com.sif.pojo.User;
import com.sif.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.UUID;

@Controller
public class UserController {

    @Autowired
    private UserService userServiceImpl;

    /** 
    * @Description:  跳转到用户登录界面
    * @Param: [] 
    * @return: java.lang.String 
    * @Author: xifujiang
    * @Date: 2019/1/4 
    */ 
    @RequestMapping(value = "regist")
    public String regist(){
        return "regist";
    }
    
    /** 
    * @Description:  判断注册是否成功
    * @Param: [user] 
    * @return: java.lang.String 
    * @Author: xifujiang
    * @Date: 2019/1/4 
    */ 
    @RequestMapping(value="userRegister",method= RequestMethod.POST)
    public String userRegister(User user) {
        // 如果数据库中没有该邮箱没有被注册过，可以注册，跳转登录页面，否则跳转注册页面，给出提示
        if(user!=null) {
            String umail = user.getUmail();
            User u = userServiceImpl.selectUserByUmail(umail);
            User u1 = userServiceImpl.selectUserByUsername(user.getUsername());
            if(u != null || u1 != null){
                return "regist";
            }else{
                String uid=UUID.randomUUID().toString();
                user.setUid(uid);

                String code = uid.replace("-","");
                user.setUcode(code);
                //增加用户
                userServiceImpl.insertUserSelective(user);

                //短信验证码
                try {
                    MailUtils.sendMail(umail,"method=active&code="+code);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
                return "login";
            }
        }
        return "regist";
    }

    /** 
    * @Description: 进入登录界面 
    * @Param: [] 
    * @return: java.lang.String 
    * @Author: xifujiang
    * @Date: 2019/1/4 
    */ 
    @RequestMapping(value="login",method = RequestMethod.GET)
    public String login(String method,String code,Map<String,Object> map){
        if("active".equals(method)) {
            User activeUser = userServiceImpl.selectUserByCode(code);
            userServiceImpl.updateUstateByUid(activeUser.getUid());
            map.put("activeUser",activeUser);
        }
        return "login";
    }

    /** 
    * @Description:  用户登录
    * @Param: [user, session, request] 
    * @return: java.lang.String 
    * @Author: xifujiang
    * @Date: 2019/1/4 
    */
    @RequestMapping(value="/userLogin")
    @ResponseBody
    public String userLogin(User user, HttpSession session, HttpServletRequest request) {
        //在登陆时验证验证码是否正确
        String code = request.getParameter("code");
        if (!(code.equalsIgnoreCase(session.getAttribute("code").toString()))) {  //忽略验证码大小写
            /*验证码错误*/
            System.out.println("errCode");
            return "errCode";
        } else {
            //判断账号密码是否输入成功，若成功，跳转主页
            if (user.getUsername() != null && user.getUpassword() != null) {
                User u = userServiceImpl.selectUserByIntro(user);
                System.out.println(u.getUsername());
                if (u.getUstate() == 0) {
                    /*未激活*/
                    return "notActive";
                }
                if (u == null) {
                    /*邮箱密码未匹配*/
                    return "errUser";
                }
                if (u != null && u.getUstate() == 1) {
                    session.setAttribute("user", u);
                    User u1 = (User) session.getAttribute("user");
                    return "succ";
                }
            }
        }
            return "defectInfo";
    }

    /** 
    * @Description: 退出登录 
    * @Param: [session] 
    * @return: java.lang.String 
    * @Author: xifujiang
    * @Date: 2019/1/7 
    */ 
    @RequestMapping(value="/logout")
    @ResponseBody
    public String logout( HttpSession session) {
        System.out.println("hhhh");
        session.removeAttribute("user");
        return "success";
    }
}
