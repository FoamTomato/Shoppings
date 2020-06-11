package com.sybinal.shop.common.sends;

import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
/**
 * 主题：简单邮件（不带附件的邮件）发送
 * @author 刘军/shell_liu
 * 2015-4-12
 */
public class SimpleMailSender {
    /**
     * 以文本格式发送邮件
     *   @param mailInfo
     *   代发送的邮件的信息
     */

    public boolean sendTextMail(MailSenderInfo mailInfo){
        //判断是否需要身份认证
        MyAuthenticator authenticator=null;
        Properties pro=mailInfo.getProperties();
         if(mailInfo.isValidate()){
             //如果需要身份认证；则创建一个密码验证器
             authenticator=new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
         }
        //根据邮件会话属性和密码验证器构造一个邮件发送的session
         Session sendMailSession=Session.getDefaultInstance(pro, authenticator);
         try {
           //    根据session 创建一个邮件消息
         Message mailMessage=new MimeMessage(sendMailSession);
          //创建邮件发送者地址
         Address from =new InternetAddress(mailInfo.getFromAddress());
          //设置邮件消息的发送者
         mailMessage.setFrom(from);
          //创建邮件的接受者地址；并设置到邮件消息中
         Address to=new InternetAddress(mailInfo.getToAddress());
         mailMessage.setRecipient(Message.RecipientType.TO, to);
         //设置邮件消息的主题
         mailMessage.setSubject(mailInfo.getSubject());
         //设置邮件消息发送的时间
         mailMessage.setSentDate(new Date());
         //设置邮件消息的主要内容
        // System.out.println(pro);
         mailMessage.addRecipients(Message.RecipientType.CC, InternetAddress.parse("<foamtomato@163.com>"));
         String mailContent =mailInfo.getContent();
         mailMessage.setText(mailContent);

         //发送邮件
         Transport.send(mailMessage);
         System.out.println("发送成功");
         return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /***
     * 以HTML格式发送邮件
     * @param mailInfo
     *     待发送的邮件信息
     */
    public boolean sendHtmlMail(MailSenderInfo mailInfo,String addre){
        //判断是否需要身份认证
        MyAuthenticator authenticator=null;
        Properties pro=mailInfo.getProperties();
         //如果需要身份认证；则创建一个密码验证器
         if(mailInfo.isValidate()){
             authenticator=new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
         }
        //根据邮件会话属性和密码验证器构造一个邮件发送的session
         Session sendMailSession=Session.getDefaultInstance(pro, authenticator);
         try {
               //    根据session 创建一个邮件消息
             Message mailMessage=new MimeMessage(sendMailSession);
              //创建邮件发送者地址
             Address from =new InternetAddress(mailInfo.getFromAddress());
              //设置邮件消息的发送者
             mailMessage.setFrom(from);
              //创建邮件的接受者地址；并设置到邮件消息中
             Address to=new InternetAddress(mailInfo.getToAddress());
               //Message.RecipientType.TO表示接收者的类型为TO
             mailMessage.setRecipient(Message.RecipientType.TO, to);
	         if(!addre.equals("")){
	             Address froms =new InternetAddress(addre);
	             
	             mailMessage.setRecipient(Message.RecipientType.CC, froms);
             }
             //设置邮件消息的主题
             mailMessage.setSubject(mailInfo.getSubject());
             //设置邮件消息发送的时间
             mailMessage.setSentDate(new Date());
             //设置邮件消息的主要内容

             //MiniMultipart 类是一个容器 包含MimeBodyPart类型的对象
             Multipart mailPart =new MimeMultipart("mixed");//混合的组合关系
              //创建一个包含HTNL内容的MimeBodyPart
             BodyPart html=new MimeBodyPart();
             //设置HTML内容
             html.setContent(mailInfo.getContent(),"text/html;charset=utf-8");
             mailPart.addBodyPart(html);

             //s设置信件的附件（用本地上的文件作为附件）
             String[] mails=mailInfo.getAttachFileNames();
             if(mails[mails.length-1]!=""){
	             for(int i=0;i<mails.length;i++){
	            	 MimeBodyPart htmls=new MimeBodyPart();
	            	 htmls.setContent("","text/html;charset=UTF-8");
	            	 //可能需要截取/
	                 FileDataSource fds=new FileDataSource(mails[i]);
	                 DataHandler dh=new DataHandler(fds);
	                 htmls.setFileName(mails[i]);
	                 htmls.setDataHandler(dh); 
	                 mailPart.addBodyPart(htmls);
	             }
             }
             //将MiniMultipart对象设置为邮件内容
             mailMessage.setContent(mailPart);
             mailMessage.saveChanges();

             //发送邮件
             Transport.send(mailMessage);
             System.out.println("发送成功");
             return true;
            } catch (Exception e) {
                e.printStackTrace();
            }

        return false;
    }












}