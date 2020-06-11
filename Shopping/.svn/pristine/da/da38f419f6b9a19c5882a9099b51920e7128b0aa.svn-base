package com.sybinal.shop.common;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.sybinal.shop.controller.admin.EnumContentType;

public class Calculations {
	
	private static int HTTP_TIMEOUT = 10000;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*Map<String, String> head = Maps.newHashMap();
		Map<String,String> map=new HashMap<String,String>();
		map.put("weight", "300");
		map.put("country", "UK");
		map.put("shipping", "cm_hm_yg");
		Gson gson=new Gson();
		String data;
		try {
			data = doGet("http://192.168.1.172:5000/freight?weight=300&country=UK英国&shipping=cm_hm_yg", "",EnumContentType.JSON, 30000);
			System.out.println(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// get请求
*/		//String data=interfaceUtil("460","GB","cm_it_hh","");
		//System.out.println(data);
		/*Pattern pattern = Pattern.compile("\\+.{21}");
		String i="小林 < v8gpmnpf6t5ypsd+A00135411ZI2YBJ2DU9VJ@marketplace.amazon.co.jp >";
		Matcher matcher = pattern.matcher(i);
		if (matcher.find()) {
			System.out.println(matcher.group(0));
			String t=i.replace(matcher.group(0), "");
			int c=t.lastIndexOf(">");
			int d=t.indexOf("<")+1;
			System.out.println(d+""+c);
			System.out.println(t.substring(d,c).replace(" ",""));
		}*/
		try {
			System.out.println(interfaceUtil("120","JP","cm_rb_shhm"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * interfaceUtil("http://172.83.28.221:7001/NSRTRegistration/test/add.do",
		 * "id=8888888&name=99999999");
		 */// post请求
	}
	public static String los(String data1,String data2,String data3) throws Exception {
		return interfaceUtil(data1,data2,data3);
	}
	public static String doGet(String urlPath, String params, 
			EnumContentType contentType, Integer timeout) throws Exception {
		String method = "GET";
		Map<String, String> head = Maps.newHashMap();
		head.put("Content-Type", "application/json;charset=UTF-8");
		return doCall(urlPath, "GET", params, head, contentType, timeout);
	}
	/**
	 * 调用对方接口方法
	 * 
	 * @param path 对方或第三方提供的路径
	 * @param data 向对方或第三方发送的数据，大多数情况下给对方发送JSON数据让对方解析
	 * @throws Exception 
	 */
	public static String interfaceUtil(String data1,String data2,String data3) throws Exception {
		Map<String,String> params=new HashMap<String,String>();
		params.put("weight", data1);
		params.put("country", data2);
		params.put("shipping", data3);
		Gson gson=new Gson();
		String paramJson=gson.toJson(params);
		HttpURLConnection httpConnection = null;
		Map<String, String> requestProperties = Maps.newHashMap();
		requestProperties.put("Content-Type", "application/json");
		requestProperties.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36");
		Integer timeout=30000;
		String var16;
		try {
			URL url = new URL("http://192.168.1.176:5000/freight");
			httpConnection = (HttpURLConnection) url.openConnection();	
			httpConnection.setRequestMethod("POST");
			httpConnection.setRequestProperty("Content-Type", "application/json");
			if (timeout == null || timeout == 0) {
				timeout = HTTP_TIMEOUT;
			}

			httpConnection.setConnectTimeout(timeout);
			httpConnection.setReadTimeout(timeout);
			if (requestProperties != null && requestProperties.keySet().size() > 0) {
				Iterator var8 = requestProperties.keySet().iterator();

				while (var8.hasNext()) {
					String key = (String) var8.next();
					httpConnection.setRequestProperty(key, requestProperties.get(key));
				}
			}

			httpConnection.setDoOutput(true);
			httpConnection.setDoInput(true);
			if (StringUtils.isNotEmpty(paramJson)) {
				httpConnection.setRequestProperty("Content-Length", String.valueOf(paramJson.length()));
				//httpConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
				OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpConnection.getOutputStream(),
						"utf-8");
				outputStreamWriter.write(paramJson);
				outputStreamWriter.flush();
				outputStreamWriter.close();
			}

			var16 = IOUtils.toString(httpConnection.getInputStream(), "utf-8");
		} catch (Exception var13) {
			throw var13;
		} finally {
			httpConnection.disconnect();
		}

		return var16;
	}
	public static String doCall(String urlPath, String method, String params, Map<String, String> requestProperties,
			EnumContentType contentType, Integer timeout) throws Exception {
		HttpURLConnection httpConnection = null;

		String var16;
		try {
			URL url = new URL(urlPath);
			httpConnection = (HttpURLConnection) url.openConnection();
			httpConnection.setRequestMethod(method);
			httpConnection.setRequestProperty("Content-Type", contentType.getContentType());
			if (timeout == null || timeout == 0) {
				timeout = HTTP_TIMEOUT;
			}

			httpConnection.setConnectTimeout(timeout);
			httpConnection.setReadTimeout(timeout);
			if (requestProperties != null && requestProperties.keySet().size() > 0) {
				Iterator var8 = requestProperties.keySet().iterator();

				while (var8.hasNext()) {
					String key = (String) var8.next();
					httpConnection.setRequestProperty(key, requestProperties.get(key));
				}
			}

			httpConnection.setDoOutput(true);
			httpConnection.setDoInput(true);
			if (StringUtils.isNotEmpty(params)) {
				httpConnection.setRequestProperty("Content-Length", String.valueOf(params.length()));
				OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpConnection.getOutputStream(),
						"utf-8");
				outputStreamWriter.write(params);
				outputStreamWriter.flush();
				outputStreamWriter.close();
			}

			var16 = IOUtils.toString(httpConnection.getInputStream(), "utf-8");
		} catch (Exception var13) {
			throw var13;
		} finally {
			httpConnection.disconnect();
		}

		return var16;
	}
}
