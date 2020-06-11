package com.sybinal.shop.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.sun.mail.iap.ProtocolException;
import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.protocol.IMAPProtocol;
import com.sybinal.shop.common.sends.CheckObjectIsNullUtils;
import com.sybinal.shop.model.emailDetailsWithBLOBs;


/**
 * 邮件接受测试
 * 
 */
/** 
 * 使用POP3协议接收邮件 
 */  
public class POP3ReceiveMailTest {  
	private static Logger logger = Logger.getLogger(POP3ReceiveMailTest.class); 
    public static void main(String[] args) throws Exception {  
    	// 记录info级别的信息  
    	emailDetailsWithBLOBs e=new emailDetailsWithBLOBs();
    	CheckObjectIsNullUtils c=new CheckObjectIsNullUtils();
        /**
         * 时间阶段
         */
        if (c.objCheckIsNull(e)) {
        	logger.info("adsf");
        }else {
        	logger.info("no");
        }
        
        //logger.debug("This is debug message.");
        //logger.error("This is error message.");
        //System.out.println("return ");
        //resceive();  
    }  
      
    /** 
     * 接收邮件 
     */  
    public static void resceive() throws Exception { 
    /** 
     　　* 因为现在使用的是163邮箱 而163的 pop地址是　pop3.163.com　 端口是　110　　
　　　　　* 比如使用好未来企业邮箱 就需要换成 好未来邮箱的 pop服务器地址 pop.263.net  和   端口 110  
    　　 */	
    	String duankou = "465";   // 端口号
   		String servicePath = "imap.163.com";   // 服务器地址
        // 准备连接服务器的会话信息  
        Properties props = new Properties();  
        props.setProperty("mail.store.protocol", "imap");       // 使用pop3协议  
        props.setProperty("mail.pop3.port", duankou);           // 端口  
        props.setProperty("mail.pop3.host", servicePath);       // pop3服务器  
        // 启动SSL:
    	props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    	props.put("mail.smtp.socketFactory.port", String.valueOf(duankou));
        // 创建Session实例对象  
        Session session = Session.getInstance(props);  
        Store store = session.getStore("pop3");  
        store.connect("nan77n@163.com", "yll199483"); //163邮箱程序登录属于第三方登录所以这里的密码是163给的授权密码而并非普通的登录密码
        
        System.out.println(store);
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
        /* Folder.READ_ONLY：只读权限 
         * Folder.READ_WRITE：可读可写（可以修改邮件的状态） 
         */  
        folder.open(Folder.READ_WRITE); //打开收件箱  
          
        // 由于POP3协议无法获知邮件的状态,所以getUnreadMessageCount得到的是收件箱的邮件总数  
        System.out.println("未读邮件数: " + folder.getUnreadMessageCount());  
          
        // 由于POP3协议无法获知邮件的状态,所以下面得到的结果始终都是为0  
        System.out.println("删除邮件数: " + folder.getDeletedMessageCount());  
        System.out.println("新邮件: " + folder.getNewMessageCount());  
          
        // 获得收件箱中的邮件总数  
        System.out.println("邮件总数: " + folder.getMessageCount());  
          
        // 得到收件箱中的所有邮件,并解析  
        Message[] messages = folder.getMessages();  
        parseMessage(messages);  
         
        //得到收件箱中的所有邮件并且删除邮件
        //deleteMessage(messages);  
        //释放资源  
        folder.close(true);  
        store.close();  
    }  
    
    /** 
     * 解析邮件 
     * @param messages 要解析的邮件列表 
     * @throws IOException 
     * @throws MessagingException 
     * @throws FileNotFoundException 
     * @throws UnsupportedEncodingException 
     * @throws ParseException 
     */ 
    public static  emailDetailsWithBLOBs parseMessage2(HttpServletRequest request,Message ...messages) throws UnsupportedEncodingException, FileNotFoundException, MessagingException, IOException, ParseException {  
    	emailDetailsWithBLOBs tails=new emailDetailsWithBLOBs();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    	String path = request.getSession().getServletContext().getRealPath("").split("webapp")[0];
		Map<String, String> map = new HashMap<String, String>();
		path+="/resources/upload/"+sdf.format(new Date())+"/";
        if (messages == null || messages.length < 1)   
            throw new MessagingException("未找到要解析的邮件!"); 
        // 解析所有邮件  
        for (int i = 0, count = messages.length; i < count; i++) {  
            MimeMessage msg = (MimeMessage) messages[i];
            Pattern pe = Pattern.compile("@.+?amazon");
            Matcher m = pe.matcher(getFrom(msg).replace("\"", ""));
            
            if (m.find()) {
	            tails.setSpare1(getSubject(msg));
	            tails.setEmailPostman(getFrom(msg).replace("\"", ""));
	
	            tails.setEmailGetman(getReceiveAddress(msg, Message.RecipientType.TO).replace("\"\\\"", "").replace("\\\"\"", ""));
	            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//注意月份是MM
	            tails.setEmailTimes(simpleDateFormat.parse(getSentDate(msg, "yyyy/MM/dd HH:mm:ss")));
	            logger.info(tails);
	            boolean isContainerAttachment = isContainAttachment(msg);  
	            if (isContainerAttachment) {  
	                //保存附件 
	            	List<String> y=saveAttachment(msg, path);
	            	String results="";
	            	for(int r=0;r<y.size();r++) {
	            		results+=y.get(r)+",";
	            	}
	                tails.setSpare3(results.substring(0,results.length()-1));
	            }   
	            StringBuffer content = new StringBuffer(30);  
	            getMailTextContent(msg, content); 
	            if(content.indexOf("<!DOCTYPE")!=-1) {
	            	/**
	            	 * 截取<style  --  </style>
	            	 */
	            	String c=content.toString();
	            	String p=c.substring(c.indexOf("<style"),c.lastIndexOf("</style>"));
	                tails.setText(content.toString().replace(p, ""));
	            }else {
	            	tails.setText(content.toString().substring(content.toString().indexOf("<div>")+5));
	            }
            }
        }
		return tails;
    }  
    /** 
     * 解析邮件 
     * @param messages 要解析的邮件列表 
     */  
    public static void parseMessage(Message ...messages) throws MessagingException, IOException {  
        if (messages == null || messages.length < 1)   
            throw new MessagingException("未找到要解析的邮件!");  
        // 解析所有邮件  
        for (int i = 0, count = messages.length; i < count; i++) {  
            MimeMessage msg = (MimeMessage) messages[i];
            if(getSentDate(msg, "yyyy/MM/dd HH:mm:ss").compareTo("2019/12/27 16:23:12")>0) {
            System.out.println(msg.getFlags().contains(Flags.Flag.SEEN));
            System.out.println("------------------解析第" + msg.getMessageNumber() + "封邮件-------------------- ");  
            System.out.println("主题: " + getSubject(msg));  
            System.out.println("发件人: " + getFrom(msg));  
            System.out.println("收件人：" + getReceiveAddress(msg, null));  
            System.out.println("发送时间：" + getSentDate(msg, "yyyy/MM/dd HH:mm:ss"));  //"yyyy/MM/dd HH:mm:ss"
            System.out.println("是否已读：" + isSeen(msg));  
            System.out.println("邮件优先级：" + getPriority(msg));  
            System.out.println("是否需要回执：" + isReplySign(msg));  
            System.out.println("邮件大小：" + msg.getSize() * 1024 + "kb");  
            boolean isContainerAttachment = isContainAttachment(msg);  
            System.out.println("是否包含附件：" + isContainerAttachment);  
            if (isContainerAttachment) { 
                saveAttachment(msg, "F:\\don\\"+msg.getSubject() + "_"+i+"_"); //保存附件  
            }   
            StringBuffer content = new StringBuffer(30);  
            getMailTextContent(msg, content); 
            System.out.println("邮件正文：" + (content.length() > 100 ? content.substring(0,100) + "..." : content));  
            System.out.println("------------------第" + msg.getMessageNumber() + "封邮件解析结束-------------------- ");  
            System.out.println();   
            }
        }
    }  
    
    
    /** 
     * 解析邮件 
     * @param messages 要解析的邮件列表 
     */  
    public static void deleteMessage(Message ...messages) throws MessagingException, IOException {  
        if (messages == null || messages.length < 1)   
            throw new MessagingException("未找到要解析的邮件!");  
          
        // 解析所有邮件  
        for (int i = 0, count = messages.length; i < count; i++) {  

            /**
             *   邮件删除      
             */
            Message message = messages[i];
            String subject = message.getSubject();
            // set the DELETE flag to true
            message.setFlag(Flags.Flag.DELETED, true);  
       
            
        }
    } 
      
    /** 
     * 获得邮件主题 
     * @param msg 邮件内容 
     * @return 解码后的邮件主题 
     */  
    public static String getSubject(MimeMessage msg) throws UnsupportedEncodingException, MessagingException {  
        return MimeUtility.decodeText(msg.getSubject());  
    }  
      
    /** 
     * 获得邮件发件人 
     * @param msg 邮件内容 
     * @return 姓名 <Email地址> 
     * @throws MessagingException 
     * @throws UnsupportedEncodingException  
     */  
    public static String getFrom(MimeMessage msg) throws MessagingException, UnsupportedEncodingException {  
        String from = "";  
        Address[] froms = msg.getFrom();  
        if (froms.length < 1)  
            throw new MessagingException("没有发件人!");  
          
        InternetAddress address = (InternetAddress) froms[0];  
        String person = address.getPersonal();  
        if (person != null) {  
            person = MimeUtility.decodeText(person) + " ";  
        } else {  
            person = "";  
        }  
        from = person + "<" + address.getAddress() + ">";  
          
        return from;  
    }  
      
    /** 
     * 根据收件人类型，获取邮件收件人、抄送和密送地址。如果收件人类型为空，则获得所有的收件人 
     * <p>Message.RecipientType.TO  收件人</p> 
     * <p>Message.RecipientType.CC  抄送</p> 
     * <p>Message.RecipientType.BCC 密送</p> 
     * @param msg 邮件内容 
     * @param type 收件人类型 
     * @return 收件人1 <邮件地址1>, 收件人2 <邮件地址2>, ... 
     * @throws MessagingException 
     */  
    public static String getReceiveAddress(MimeMessage msg, Message.RecipientType type) throws MessagingException {  
        StringBuffer receiveAddress = new StringBuffer();  
        Address[] addresss = null;  
        if (type == null) {  
            addresss = msg.getAllRecipients();  
        } else {  
            addresss = msg.getRecipients(type);  
        }  
          
        if (addresss == null || addresss.length < 1)  
            throw new MessagingException("没有收件人!");  
        for (Address address : addresss) {  
            InternetAddress internetAddress = (InternetAddress)address;  
            receiveAddress.append(internetAddress.toUnicodeString()).append(",");  
        }  
          
        receiveAddress.deleteCharAt(receiveAddress.length()-1); //删除最后一个逗号  
          
        return receiveAddress.toString();  
    }  
      
    /** 
     * 获得邮件发送时间 
     * @param msg 邮件内容 
     * @return yyyy年mm月dd日 星期X HH:mm 
     * @throws MessagingException 
     */  
    public static String getSentDate(MimeMessage msg, String pattern) throws MessagingException {  
        Date receivedDate = msg.getSentDate();  
        if (receivedDate == null)  
            return "";  
          
        if (pattern == null || "".equals(pattern))  
            pattern = "yyyy年MM月dd日 E HH:mm ";  
          
        return new SimpleDateFormat(pattern).format(receivedDate);  
    }  
      
    /** 
     * 判断邮件中是否包含附件 
     * @param msg 邮件内容 
     * @return 邮件中存在附件返回true，不存在返回false 
     * @throws MessagingException 
     * @throws IOException 
     */  
    public static boolean isContainAttachment(Part part) throws MessagingException, IOException {  
        boolean flag = false;  
        if (part.isMimeType("multipart/*")) {  
            MimeMultipart multipart = (MimeMultipart) part.getContent();  
            int partCount = multipart.getCount();  
            for (int i = 0; i < partCount; i++) {  
                BodyPart bodyPart = multipart.getBodyPart(i);  
                String disp = bodyPart.getDisposition();  
                if (disp != null && (disp.equalsIgnoreCase(Part.ATTACHMENT) || disp.equalsIgnoreCase(Part.INLINE))) {  
                    flag = true;  
                } else if (bodyPart.isMimeType("multipart/*")) {  
                    flag = isContainAttachment(bodyPart);  
                } else {  
                    String contentType = bodyPart.getContentType();  
                    if (contentType.indexOf("application") != -1) {  
                        flag = true;  
                    }    
                      
                    if (contentType.indexOf("name") != -1) {  
                        flag = true;  
                    }   
                }  
                  
                if (flag) break;  
            }  
        } else if (part.isMimeType("message/rfc822")) {  
            flag = isContainAttachment((Part)part.getContent());  
        }  
        return flag;  
    }  
      
    /**  
     * 判断邮件是否已读  
     * @param msg 邮件内容  
     * @return 如果邮件已读返回true,否则返回false  
     * @throws MessagingException   
     */  
    public static boolean isSeen(MimeMessage msg) throws MessagingException {  
        return msg.getFlags().contains(Flags.Flag.SEEN);  
    }  
      
    /** 
     * 判断邮件是否需要阅读回执 
     * @param msg 邮件内容 
     * @return 需要回执返回true,否则返回false 
     * @throws MessagingException 
     */  
    public static boolean isReplySign(MimeMessage msg) throws MessagingException {  
        boolean replySign = false;  
        String[] headers = msg.getHeader("Disposition-Notification-To");  
        if (headers != null)  
            replySign = true;  
        return replySign;  
    }  
      
    /** 
     * 获得邮件的优先级 
     * @param msg 邮件内容 
     * @return 1(High):紧急  3:普通(Normal)  5:低(Low) 
     * @throws MessagingException  
     */  
    public static String getPriority(MimeMessage msg) throws MessagingException {  
        String priority = "普通";  
        String[] headers = msg.getHeader("X-Priority");  
        if (headers != null) {  
            String headerPriority = headers[0];  
            if (headerPriority.indexOf("1") != -1 || headerPriority.indexOf("High") != -1)  
                priority = "紧急";  
            else if (headerPriority.indexOf("5") != -1 || headerPriority.indexOf("Low") != -1)  
                priority = "低";  
            else  
                priority = "普通";  
        }  
        return priority;  
    }   
      
    /** 
     * 获得邮件文本内容 
     * @param part 邮件体 
     * @param content 存储邮件文本内容的字符串 
     * @throws MessagingException 
     * @throws IOException 
     */  
    public static void getMailTextContent(Part part, StringBuffer content) throws MessagingException, IOException {  
        //如果是文本类型的附件，通过getContent方法可以取到文本内容，但这不是我们需要的结果，所以在这里要做判断  
        boolean isContainTextAttach = part.getContentType().indexOf("name") > 0;   
        if (part.isMimeType("text/*") && !isContainTextAttach) {
            content.append(part.getContent().toString());  
        } else if (part.isMimeType("message/rfc822")) {   
            getMailTextContent((Part)part.getContent(),content);  
        } else if (part.isMimeType("multipart/*")) {  
            Multipart multipart = (Multipart) part.getContent();  
            int partCount = multipart.getCount();  
            for (int i = 0; i < partCount; i++) {  
                BodyPart bodyPart = multipart.getBodyPart(i);  
                getMailTextContent(bodyPart,content);  
            }  
        }  
    }  
      
    /**  
     * 保存附件  
     * @param part 邮件中多个组合体中的其中一个组合体  
     * @param destDir  附件保存目录  
     * @throws UnsupportedEncodingException  
     * @throws MessagingException  
     * @throws FileNotFoundException  
     * @throws IOException  
     */  
    public static List<String> saveAttachment(Part part, String destDir) throws UnsupportedEncodingException, MessagingException,  
            FileNotFoundException, IOException {  
    	List<String> list=new ArrayList<String>();

    	/*
		 * 随机生成文件名
		 */
		String uuid = UUID.randomUUID().toString().replace("-", "");
        if (part.isMimeType("multipart/*")) {  
            Multipart multipart = (Multipart) part.getContent();    //复杂体邮件  
            //复杂体邮件包含多个邮件体  
            int partCount = multipart.getCount();  
            for (int i = 0; i < partCount; i++) { 
                //获得复杂体邮件中其中一个邮件体  
                BodyPart bodyPart = multipart.getBodyPart(i);  
                //某一个邮件体也有可能是由多个邮件体组成的复杂体  
                String disp = bodyPart.getDisposition();  
                if (disp != null && (disp.equalsIgnoreCase(Part.ATTACHMENT) || disp.equalsIgnoreCase(Part.INLINE))) {  
                    InputStream is = bodyPart.getInputStream();
                    //路径存入list
                    list.add(destDir.split("/resources/upload/")[1]+uuid+decodeText(bodyPart.getFileName()));
                    logger.info("正在下载："+decodeText(bodyPart.getFileName()));
                  //下载文件到路径
                    saveFile(is, destDir, uuid+decodeText(bodyPart.getFileName()));  
                } else if (bodyPart.isMimeType("multipart/*")) {  
                    saveAttachment(bodyPart,destDir);  
                } else {  
                    String contentType = bodyPart.getContentType();  
                    if (contentType.indexOf("name") != -1 || contentType.indexOf("application") != -1) {
                        logger.info("正在下载："+decodeText(bodyPart.getFileName()));
                    	//路径存入list
                    	list.add(destDir.split("/resources/upload/")[1]+uuid+decodeText(bodyPart.getFileName()));
                    	//下载文件到路径
                        saveFile(bodyPart.getInputStream(), destDir, uuid+decodeText(bodyPart.getFileName()));  
                    }  
                }  
            }  
        } else if (part.isMimeType("message/rfc822")) {  
            saveAttachment((Part) part.getContent(),destDir);  
        }
        
        return list;
    }  
      
    /**  
     * 读取输入流中的数据保存至指定目录  
     * @param is 输入流  
     * @param fileName 文件名  
     * @param destDir 文件存储目录  
     * @throws FileNotFoundException  
     * @throws IOException  
     */  
    private static void saveFile(InputStream is, String destDir, String fileName)  
            throws FileNotFoundException, IOException {  
        BufferedInputStream bis = new BufferedInputStream(is); 
        File folder = new File(destDir); // 创建文件夹
        if (!folder.exists()){
            folder.mkdir();
        }
        BufferedOutputStream bos = new BufferedOutputStream(  
                new FileOutputStream(destDir+fileName));  
        int len = -1;  
        while ((len = bis.read()) != -1) {  
            bos.write(len);  
            bos.flush();  
        }  
        bos.close();  
        bis.close();  
    }  
      
    /** 
     * 文本解码 
     * @param encodeText 解码MimeUtility.encodeText(String text)方法编码后的文本 
     * @return 解码后的文本 
     * @throws UnsupportedEncodingException 
     */  
    public static String decodeText(String encodeText) throws UnsupportedEncodingException {  
        if (encodeText == null || "".equals(encodeText)) {  
            return "";  
        } else {  
            return MimeUtility.decodeText(encodeText);  
        }  
    }
    
    
}