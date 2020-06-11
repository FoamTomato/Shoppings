package com.sybinal.shop.common;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

class ProxyAuthenticator extends Authenticator {
	private String user, password;

	public ProxyAuthenticator(String user, String password) {
		this.user = user;
		this.password = password;
	}

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(user, password.toCharArray());
	}
}
