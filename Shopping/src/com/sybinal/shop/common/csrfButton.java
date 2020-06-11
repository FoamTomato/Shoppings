package com.sybinal.shop.common;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class csrfButton extends WebSecurityConfigurerAdapter {

	/*private static final String[] AUTH_WHITELIST = {
			// -- swagger ui
			"/swagger-resources/**","/swagger-ui.html","/v2/api-docs","/webjars/**"
	};*/
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//http.authorizeRequests().antMatchers(AUTH_WHITELIST).permitAll().antMatchers("/**/*").denyAll();
		http.csrf().disable();
	}
}