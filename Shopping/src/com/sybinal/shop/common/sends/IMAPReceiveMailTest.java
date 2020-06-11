package com.sybinal.shop.common.sends; 
 
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.sun.mail.iap.ProtocolException;
import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPMessage;
import com.sun.mail.imap.protocol.IMAPProtocol;
import com.sybinal.shop.common.POP3ReceiveMailTest;
import com.sybinal.shop.model.emailDetailsWithBLOBs;
import com.sybinal.shop.model.userEmail; 
 
/**
 * <b>使用IMAP协议接收邮件</b><br/>
 * <p>POP3和IMAP协议的区别:</p>
 * <b>POP3</b>协议允许电子邮件客户端下载服务器上的邮件,但是在客户端的操作(如移动邮件、标记已读等)，不会反馈到服务器上，<br/>
 * 比如通过客户端收取了邮箱中的3封邮件并移动到其它文件夹，邮箱服务器上的这些邮件是没有同时被移动的。<br/>
 * <p><b>IMAP</b>协议提供webmail与电子邮件客户端之间的双向通信，客户端的操作都会同步反应到服务器上，对邮件进行的操作，服务
 * 上的邮件也会做相应的动作。比如在客户端收取了邮箱中的3封邮件，并将其中一封标记为已读，将另外两封标记为删除，这些操作会
 * 即时反馈到服务器上。</p>
 * <p>两种协议相比，IMAP 整体上为用户带来更为便捷和可靠的体验。POP3更易丢失邮件或多次下载相同的邮件，但IMAP通过邮件客户端
 * 与webmail之间的双向同步功能很好地避免了这些问题。</p>
 */ 
public class IMAPReceiveMailTest { 

	private static Logger logger = Logger.getLogger(IMAPReceiveMailTest.class);  
    public  List<emailDetailsWithBLOBs> getEmailMessage(HttpServletRequest req,userEmail list2) throws MessagingException, IOException, ParseException{ 
        // 准备连接服务器的会话信息 
        Properties props = new Properties(); 
        props.setProperty("mail.store.protocol", "imap"); 
        props.setProperty("mail.imap.host", list2.getSpare2()); 
        props.setProperty("mail.imap.port", list2.getPort2()); 
        // 启动SSL:
    	props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    	props.put("mail.smtp.socketFactory.port", String.valueOf(list2.getPort2()));
        // 创建Session实例对象 
        Session session = Session.getInstance(props); 
         
        // 创建IMAP协议的Store对象 
        Store store = null;
		try {
			store = session.getStore("imap");
		} catch (NoSuchProviderException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		} 
         
        // 连接邮件服务器 
        try {
			store.connect(list2.getUserEmail(), list2.getPwdEmail());
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
         
        // 获得收件箱 
        Folder folder = store.getFolder("INBOX"); 

        IMAPFolder imapFolder = (IMAPFolder)folder;
        //javamail中使用id命令有校验checkOpened, 所以要去掉id方法中的checkOpened();
        imapFolder.doCommand(new IMAPFolder.ProtocolCommand() {
            @Override
			public Object doCommand(IMAPProtocol p) throws ProtocolException {

            	p.id("FUTONG");
                return null;
            }
        });
        // 以读写模式打开收件箱 
        folder.open(Folder.READ_WRITE); 
         
        // 获得收件箱的邮件列表 
        Message[] messages = folder.getMessages(folder.getMessageCount()-folder.getUnreadMessageCount()+1,folder.getMessageCount()); 
        // 打印不同状态的邮件数量 
        System.out.println("收件箱中共" + folder.getMessages().length + "封邮件!"); 
        System.out.println("收件箱中共" + folder.getUnreadMessageCount() + "封未读邮件!"); 
        System.out.println("收件箱中共" + folder.getNewMessageCount() + "封新邮件!"); 
        System.out.println("收件箱中共" + folder.getDeletedMessageCount() + "封已删除邮件!"); 
        System.out.println("------------------------开始解析邮件----------------------------------"); 
        List<emailDetailsWithBLOBs> list=new ArrayList<emailDetailsWithBLOBs>();
        /**
         * 判断对象是否为空
         */
        CheckObjectIsNullUtils c=new CheckObjectIsNullUtils();
        // 解析邮件 
        for (Message message : messages) { 
            IMAPMessage msg = (IMAPMessage) message; 
            //String subject = MimeUtility.decodeText(msg.getSubject()); 
            //System.out.println("[" + subject + "]未读，是否需要阅读此邮件（yes/no）？");
            //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
            // String answer = reader.readLine();   //加上这四行可以选择是否需要阅读
            //if ("yes".equalsIgnoreCase(answer)) { 
            emailDetailsWithBLOBs dat=POP3ReceiveMailTest.parseMessage2(req,msg);
            if(!c.objCheckIsNull(dat)) {
            	list.add(dat);
            }
            
             //list.add(msg);  // 解析邮件 
                // 第二个参数如果设置为true，则将修改反馈给服务器。false则不反馈给服务器 
                msg.setFlag(Flag.SEEN, true);   //设置已读标志 
            //} 
        } 
         
        // 关闭资源 
        folder.close(false); 
        store.close();
		return list; 
    } 
} 