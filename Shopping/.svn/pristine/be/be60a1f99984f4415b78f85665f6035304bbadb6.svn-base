package com.sybinal.shop.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

@WebServlet("/FromAjaxservlet")
public class Http extends HttpServlet{
		
		
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			try {
				//创建默认连接
				CloseableHttpClient httpClient = HttpClients.createDefault();
				//创建HttpGet对象,处理get请求,转发到A站点
				System.out.println("请求");
				HttpPost httpGet = new HttpPost("http://vakind.f3322.org:10008/api/logistics/v1/track/create/order"); 
				httpGet.addHeader("SE-TOKEN","93a61c53-e6a3-4f13-ad0b-594498a49c45");
				httpGet.addHeader("signature","4E9937B3F3D613C8C7E707DF7399FB8C9EBE6354");
				httpGet.addHeader("Content-Type","application/json");
				//执行
				CloseableHttpResponse response = httpClient.execute(httpGet);
				int code = response.getStatusLine().getStatusCode();
				//获取状态
				System.out.println("http请求结果为:"+code);
				if(code == 200){
	                                //获取A站点返回的结果
					String result = EntityUtils.toString(response.getEntity());
					System.out.println(result);
	                                //把结果返回给B站点
					resp.getWriter().print(result);
				}
				response.close();
				httpClient.close();
			} catch (Exception e) {
			}
		}
	}