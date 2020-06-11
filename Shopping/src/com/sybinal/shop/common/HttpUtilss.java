package com.sybinal.shop.common;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import com.google.common.collect.Maps;
import com.sybinal.shop.controller.admin.EnumContentType;
import com.sybinal.shop.controller.admin.SignDemo;

public class HttpUtilss {
	private static int HTTP_TIMEOUT = 10000;
	private static Logger logger=Logger.getLogger(HttpUtilss.class);
	
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();  
        sb.append("<s:Envelope xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" + 
        		"    <s:Body>\n" + 
        		"        <GetLogisticsWay xmlns=\"http://tempuri.org/\"/>\n" + 
        		"    </s:Body>\n" + 
        		"</s:Envelope>");
		try {
			String doposte=doPost2("http://open.btdair.com:8099/LogisticsService.svc?wsdl",sb.toString(),EnumContentType.XML);
			String dopso=doPost2("http://192.168.1.176:8000/parse",sb.toString(),EnumContentType.XML);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("宝通达失败",e);
		}
	}
	/**
	 * 
	 * @param xml形状的str串
	 * @return Document 对象
	 */
	public static Document StringTOXml(String str) {

		StringBuilder sXML = new StringBuilder();
		sXML.append(str);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		Document doc = null;
		try {
			InputStream is = new ByteArrayInputStream(sXML.toString().getBytes("utf-8"));
			doc = dbf.newDocumentBuilder().parse(is);
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doc;
	}

	public static String doPost2(String urlPath, String params,
			EnumContentType contentType) throws Exception {
		Map<String, String> head = Maps.newHashMap();
		head.put("SOAPAction", "http://tempuri.org/ILogisticsService/GetLogisticsWay");
		head.put("Content-Type", "text/xml;charset=utf-8");
		return doCall(urlPath, "POST", params, head, contentType, 30000);
	}
	
	public static String doPost(String urlPath, String params,String map,
			EnumContentType contentType, Integer timeout) throws Exception {
		Map<String, String> head = Maps.newHashMap();
		//9s3a61c53-e6a3-4f13-ad0b-594498a49c45 测试token
		//cs5074a1b-24c3-4895-848c-d2a7aacbe647  正式token
		head.put("SE-TOKEN", "c5074a1b-24c3-4895-848c-d2a7aacbe647");
		head.put("signature", SignDemo.getSign(urlPath,"c5074a1b-24c3-4895-848c-d2a7aacbe647",map,"POST"));
		head.put("Content-Type", "application/json");
		String method = "POST";
		return doCall(urlPath, "POST", params, head, contentType, timeout);
	}
	public static String doydPost(String urlPath, String params,
			EnumContentType contentType, Integer timeout,String string) throws Exception {
		Map<String, String> head = Maps.newHashMap();
		//yondaeeo7tfq7gnzu6p0t3naulpm7ytpz
		//ypc233783ub1rugiy39sl06banszz2qiaz1rdu618q782wtaehydzae9sa2qkhjad
		head.put("appToken", "yondaeeo7tfq7gnzu6p0t3naulpm7ytpz");
		head.put("appKey", "ypc233783ub1rugiy39sl06banszz2qiaz1rdu618q782wtaehydzae9sa2qkhjad");
		head.put("serviceMethod", string);
		head.put("paramsJson", params);
		
		return sendPost(urlPath,head,"utf-8");
	}

	public static String doczPost(String urlPath, String params,
			EnumContentType contentType, Integer timeout,String string) throws Exception {
		Map<String, String> head = Maps.newHashMap();

		head.put("Content-Type", "application/json");
		return doCall(urlPath, "POST", params, head, contentType, timeout);
	}

	public static String sendPost(String urlParam, Map<String, String> params, String charset) {
        StringBuffer resultBuffer = null;
        // 构建请求参数
        StringBuffer sbParams = new StringBuffer();
        if (params != null && params.size() > 0) {
            for (Map.Entry<String, String> e : params.entrySet()) {
                sbParams.append(e.getKey());
                sbParams.append("=");
                sbParams.append(e.getValue());
                sbParams.append("&");
            }
        }
        URLConnection con = null;
        OutputStreamWriter osw = null;
        BufferedReader br = null;
        try {
            URL realUrl = new URL(urlParam);
            // 打开和URL之间的连接
            con = realUrl.openConnection();
            // 设置通用的请求属性
            con.setRequestProperty("accept", "*/*");
            con.setRequestProperty("connection", "Keep-Alive");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            con.setDoOutput(true);
            con.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            osw = new OutputStreamWriter(con.getOutputStream(), charset);
            if (sbParams != null && sbParams.length() > 0) {
                // 发送请求参数
                osw.write(sbParams.substring(0, sbParams.length() - 1));
                // flush输出流的缓冲
                osw.flush();
            }
            // 定义BufferedReader输入流来读取URL的响应
            resultBuffer = new StringBuffer();
            int contentLength = Integer.parseInt(con.getHeaderField("Content-Length"));
            if (contentLength > 0) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream(), charset));
                String temp;
                while ((temp = br.readLine()) != null) {
                    resultBuffer.append(temp);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (osw != null) {
                try {
                    osw.close();
                } catch (IOException e) {
                    osw = null;
                    throw new RuntimeException(e);
                }
            }
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    br = null;
                    throw new RuntimeException(e);
                }
            }
        }
        return resultBuffer.toString();
    }
	public static String doPost2(String urlPath, String params,String map,
			EnumContentType contentType, Integer timeout) throws Exception {
		Map<String, String> head = Maps.newHashMap();
		head.put("Content-Type", "application/json");
		head.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36");
		String method = "POST";
		return doCall(urlPath, "POST", params, head, contentType, timeout);
	}
	
	public static String doGet(String urlPath, String params, Map<String, String> requestProperties,
			EnumContentType contentType, Integer timeout) throws Exception {
		String method = "GET";
		return doCall(urlPath, "GET", params, requestProperties, contentType, timeout);
	}

	public static String doDelete(String urlPath, String params, Map<String, String> requestProperties,
			EnumContentType contentType, Integer timeout) throws Exception {
		String method = "DELETE";
		return doCall(urlPath, "DELETE", params, requestProperties, contentType, timeout);
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
				//httpConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
				OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpConnection.getOutputStream(),
						"utf-8");
				outputStreamWriter.write(params);
				outputStreamWriter.flush();
				outputStreamWriter.close();
			}

			var16 = IOUtils.toString(httpConnection.getInputStream(),"utf-8");
		} catch (Exception var13) {
			throw var13;
		} finally {
			httpConnection.disconnect();
		}

		return var16;
	}

	public static byte[] httpDownload(String urlPath, String method, String params,
			Map<String, String> requestProperties, EnumContentType contentType) throws Exception {
		HttpURLConnection httpConnection = null;

		byte[] var9;
		try {
			URL url = new URL(urlPath);
			httpConnection = (HttpURLConnection) url.openConnection();
			httpConnection.setConnectTimeout(30000);
			httpConnection.setReadTimeout(50000);
			httpConnection.setRequestMethod(method);
			httpConnection.setRequestProperty("Content-Type", contentType.getContentType());
			if (requestProperties != null && requestProperties.keySet().size() > 0) {
				Iterator var7 = requestProperties.keySet().iterator();

				while (var7.hasNext()) {
					String key = (String) var7.next();
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

			int code = httpConnection.getResponseCode();
			if (code != 200) {
				throw new Exception(IOUtils.toString(httpConnection.getInputStream(), "utf-8"));
			}

			InputStream input = null;

			try {
				input = httpConnection.getInputStream();
				var9 = IOUtils.toByteArray(input);
			} finally {
				IOUtils.closeQuietly(input);
			}
		} catch (Exception var19) {
			throw var19;
		} finally {
			httpConnection.disconnect();
		}

		return var9;
	}

	public static int doPing(String urlPath) throws Exception {
		URL url = new URL(urlPath);
		HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
		httpConnection.setRequestMethod("GET");
		int responseCode = httpConnection.getResponseCode();
		httpConnection.disconnect();
		return responseCode;
	}

	public static String getWholeUrl(String url, Map<String, Object> params) {
		if (url == null) {
			return null;
		} else if (params == null) {
			return url;
		} else {
			Set<Entry<String, Object>> set = params.entrySet();
			if (set.size() <= 0) {
				return url;
			} else {
				url = url + "?";
				Iterator<Entry<String, Object>> it = set.iterator();
				Entry entry;
				String param;
				if (it.hasNext()) {
					entry = it.next();
					param = (String) entry.getKey() + "=" + entry.getValue();
					url = url + param;
				}

				while (it.hasNext()) {
					entry = it.next();
					param = (String) entry.getKey() + "=" + entry.getValue();
					url = url + "&" + param;
				}

				return url;
			}
		}
	}
}