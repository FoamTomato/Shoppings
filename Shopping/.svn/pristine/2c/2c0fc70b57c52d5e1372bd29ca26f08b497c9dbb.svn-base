package com.sybinal.shop.common.sends;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
/**
 * ���⣺���ʼ��������������ʼ�������
 * @author ����/shell_liu
 * 2015-4-12
 */
public class MyAuthenticator extends Authenticator{

    String userName=null;
    String password=null;

    public MyAuthenticator( ) {

    }
    public MyAuthenticator(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
	protected PasswordAuthentication getPasswordAuthentication(){
        return new PasswordAuthentication(userName, password);

    }
}