package com.sybinal.shop.common.sends;
import java.util.Arrays;
import java.util.Properties;
/**
 * 主题：简单邮件（不带附件的邮件）发送
 * @author 刘军/shell_liu
 * 2015-4-12
 */
public class MailSenderInfo {

    //发送邮件的服务器的IP和端口
    private String mailServerHost;
    private String mailServerPort="25";
    //邮件发送者的地址
    private String fromAddress;
    //邮件接收者的地址
    private String toAddress;
    //登陆邮件发送服务器的用户名和密码
    private String userName;
    private String password;
    //是否需要身份验证
    private boolean validate=false;
    //邮件发送主题
    private String subject;
    //邮件的文本内容
    private String content;
    //邮件附件的文件名
    private String[] attachFileNames;
    /**
     * 获得邮件会话属性
     */
    /**
     * 关键代码，
     * 出现554 DT:SPM 163 smtp5,D9GowACHO7RNWNdXmXs1Bw--.9035S2 1473730639,
     * please see http://mail.163.com/help/help_spam_16.htm?ip=124.251.36.10
     * &hostid=smtp5&time=1473730639
        原因是：ip与域名不匹配。
     */
    public  Properties getProperties(){
        Properties p=new Properties();// 创建Properties对象
        p.setProperty("mail.transport.protocol", "smtp");// 设置传输协议
        p.put("mail.smtp.host", this.mailServerHost);// 设置发信邮箱的smtp地址
        p.put("mail.smtp.port", this.mailServerPort);
        p.put("mail.smtp.auth", validate?"true":"false");// 验证
       p.put("mail.smtp.ssl.enable", true); 
        p.put("mail.debug", "true");//便于调试
        return p;}
    public String getMailServerHost() {
        return mailServerHost;
    }
    public void setMailServerHost(String mailServerHost) {
        this.mailServerHost = mailServerHost;
    }
    public String getMailServerPort() {
        return mailServerPort;
    }
    public void setMailServerPort(String mailServerPort) {
        this.mailServerPort = mailServerPort;
    }
    public String getFromAddress() {
        return fromAddress;
    }
    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }
    public String getToAddress() {
        return toAddress;
    }
    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean isValidate() {
        return validate;
    }
    public void setValidate(boolean validate) {
        this.validate = validate;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String[] getAttachFileNames() {
        return attachFileNames;
    }
    public void setAttachFileNames(String[] attachFileNames) {
        this.attachFileNames = attachFileNames;
    }
	@Override
	public String toString() {
		return "MailSenderInfo [mailServerHost=" + mailServerHost + ", mailServerPort=" + mailServerPort
				+ ", fromAddress=" + fromAddress + ", toAddress=" + toAddress + ", userName=" + userName + ", password="
				+ password + ", validate=" + validate + ", subject=" + subject + ", content=" + content
				+ ", attachFileNames=" + Arrays.toString(attachFileNames) + "]";
	}


}
