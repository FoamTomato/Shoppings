package com.sybinal.shop.common.sends;


/**
 * 主题：简单邮件（不带附件的邮件）发送
 * @author 刘军/shell_liu
 * 2015-4-12
 */
public class SendMail {

     public static void main(String[] args) {
         SendMail.send_163();
    }

    //163邮箱 
    public static void send_163() {
         //这个类主要是设置邮件
        MailSenderInfo mailInfo=new MailSenderInfo();
        mailInfo.setMailServerHost("smtp.163.com");
        mailInfo.setMailServerPort("465");
        mailInfo.setValidate(true);
        mailInfo.setUserName("foamtomato@163.com");//实际发送者
        mailInfo.setPassword("foam1234");//您的邮箱密码
        mailInfo.setFromAddress("foamtomato@163.com");//设置发送人邮箱地址
        mailInfo.setToAddress("1742379947@qq.com");
        mailInfo.setSubject("e too23e3");//设置邮箱标题
        mailInfo.setContent("wlecome to beijing233");//设置邮箱内容
        System.out.println(mailInfo);
        //这个类主要是用来发送邮件
        SimpleMailSender sms=new SimpleMailSender();
        //sms.sendTextMail(mailInfo);//发送文本格式
        //sms.sendHtmlMail(mailInfo);//发送html格式:如果需要以html格式发送则需要处理好附件上传地址问题

    }

}