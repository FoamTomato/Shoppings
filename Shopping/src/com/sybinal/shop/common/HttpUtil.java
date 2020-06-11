package com.sybinal.shop.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.IOUtils;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;

import com.google.common.collect.Maps;

/**
 * HTTP请求工具类
 * 
 * @author xiezq
 */
public class HttpUtil {

	private final static long Time_out = 10000;

	public static Map<String ,String> HttpPostJson(String url, String json, Map<String, String> headMap, Long timeOut) {
		Map<String ,String> result=Maps.newHashMap();
//	        CloseableHttpClient httpClient = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).build();

		CloseableHttpClient httpClient = HttpClients.createDefault();
		try { // 第一步：创建HttpClient对象
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(timeOut.intValue())
					.setConnectionRequestTimeout(timeOut.intValue()).setSocketTimeout(timeOut.intValue()).build();
			// 第二步：创建httpPost对象
			HttpPost httpPost = new HttpPost(url);
			httpPost.setConfig(requestConfig);
			if (headMap != null && headMap.size() > 0) {
				for (Map.Entry<String, String> head : headMap.entrySet()) {
					httpPost.addHeader(new BasicHeader(head.getKey(), head.getValue()));
				}
			}
			// 第三步：给httpPost设置JSON格式的参数
			StringEntity requestEntity = new StringEntity(json, "utf-8");
			requestEntity.setContentEncoding("UTF-8");
			httpPost.setHeader("Content-type", "application/json");
			httpPost.setEntity(requestEntity);
		
			// 第四步：发送HttpPost请求，获取返回值
			InputStream responseParams = null;
			InputStreamReader responseRead = null;
			BufferedReader bufferReader = null;
			try {
				HttpResponse httpResponse = httpClient.execute(httpPost);
				HttpEntity entity = httpResponse.getEntity();
				responseParams = entity.getContent();
				responseRead = new InputStreamReader(responseParams);
				bufferReader = new BufferedReader(responseRead);
				StringBuilder sb = new StringBuilder();
				String line;
				while (null != (line = bufferReader.readLine())) {
					sb.append(line);
				}
				result.put("result", sb.toString());
				result.put("status", String.valueOf(httpResponse.getStatusLine().getStatusCode()));
			} catch (IOException IOe) {
				IOe.printStackTrace();
			} finally {
				try {
					if (null != responseParams) {
						responseParams.close();
					}
					if (null != responseRead) {
						responseRead.close();
					}
					if (null != bufferReader) {
						bufferReader.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return result;
		
			// return httpClient.execute(httpPost);
			// //调接口获取返回值时，必须用此方法
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;

	}

}
