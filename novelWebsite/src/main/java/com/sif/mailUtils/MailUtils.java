package com.sif.mailUtils;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * @program: BrotherNovel
 * @description: 邮箱发送
 * @author: xifujiang
 * @create: 2019-01-09 16:23
 **/
public class MailUtils {
    public static void sendMail(String email, String emilMsg) throws MessagingException {
        Properties props = new Properties();

        if(email.contains("@qq")){
            //发件人地址
            InternetAddress sendMan = null;
            //发件人的邮箱
            String userName = "229694302@qq.com";
            //发件人的密码
            String password="wklcigmfigbdbicf";
            //指定协议
            props.put("mail.transport.protocol","smtp");
            //主机
            props.put("mail.smtp.host","smtp.qq.com");
            //端口 465 487 25
            props.put("mail.smtp.port",587);
            //用户密码认证
            props.put("mail.smtp.auth","true");
            //调试模式
            props.put("mail.debug","true");

            //创建地址对象
            sendMan = new InternetAddress(userName);

            //创建邮箱会话
            Session session = Session.getInstance(props);
            //创建邮箱对象
            MimeMessage msg = new MimeMessage(session);
            //设置发件人
            msg.setFrom(sendMan);
            //设置邮箱收件人
            msg.setRecipients(Message.RecipientType.TO,email);
            //标题
            msg.setSubject("user activation");
            //发送时间
            msg.setSentDate(new Date());

            String url="http://localhost:8080/brotherNovel/login?"+emilMsg;

            String content="<h1>Activation email from the Brotherhood Love Literature Network! Please click on the link below</h1><h3><a href='"+url+"'>"+url+"</a></h3>";

            msg.setContent(content,"text/html;charset=utf-8");
            //5.发送
            Transport trans = session.getTransport();
            trans.connect(userName,password);
            trans.sendMessage(msg,msg.getAllRecipients());
            trans.close();
        }

    }
//    public static void main(String[] args) throws MessagingException {
////        MailUtils.sendMail("2168965571@qq.com","method=active&uid=123456");
////        System.out.println("d87261e5-e1a7-42b0-b5d0-766856c62bd7".replace("-",""));
//    }
}
