package com.sybinal.shop.controller.admin;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Consumer;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Maps;

public class SignDemo {


	public static final String HMAC_SHA1 = "HmacSHA1";
	public static final String CHARSET_NAME_UTF8 = "UTF-8";
	public static final char[] digital = "0123456789ABCDEF".toCharArray();
	public static final String DEFAULT_DATA_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
	
	/**
	 * 程序入口
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		String apiurl="http://vakind.f3322.org:10008/api/logistics/v1/track/create/order";//http 请求路径
	
		String paramesr="{\"test\":\"123\"}";
		//Map<String, Object> paramer = Maps.newHashMap();
		//paramer.put("test", "123");
		Map<String, String> head = Maps.newHashMap();
		head.put("SE-TOKEN", "93a61c53-e6a3-4f13-ad0b-594498a49c45");
		head.put("signature", getSign(apiurl,"93a61c53-e6a3-4f13-ad0b-594498a49c45",paramesr,"POST"));
		System.out.println(getSign(apiurl,"93a61c53-e6a3-4f13-ad0b-594498a49c45",paramesr,"POST"));
		head.put("Content-Type", "application/json");
		Long i=(long) 30000;
		//Gson gson=new Gson();
		//http请求请求
		//String doPost = HttpUtilss.doPost(apiurl, paramesr, head,EnumContentType.JSON, 30000);
		//System.out.println(doPost);
	}
	
	/**
	 * 签名
	 * @param url　　httpＵＲＬ
	 * @param token token
	 * @param content json 签名字符串
	 * @param method　　http　请求方法类型
	 * @return
	 */
	public static String getSign(String url,String token, String content,String method) {
		final StringBuilder sbuilder = new StringBuilder();
		if (method.equals("POST")) {
			if (null != content && content.length() > 0) {
				try {
					final JsonNode jnode = JsonH.toObject(content, JsonNode.class);
					if (jnode.isArray()) {
						int i=0;
						Iterator<JsonNode> iters = jnode.iterator();
						while(iters.hasNext()){
							sbuilder.append(i+iters.next().toString());
							i++;
						}
					} else {
						Map bodymap = JsonH.toObject(content, Map.class);
						bodymap.keySet().stream().sorted().forEach(new Consumer<Object>() {
							@Override
							public void accept(Object p) {
								sbuilder.append(p);
								if (jnode.get(String.valueOf(p)) != null
										&& jnode.get(String.valueOf(p)).asText() != "null") {
									if (jnode.get(String.valueOf(p)).isObject()
											|| jnode.get(String.valueOf(p)).isArray())
										sbuilder.append(jnode.get(String.valueOf(p)).toString());
									else
										sbuilder.append(jnode.get(String.valueOf(p)).asText());
								}
							}
						});
					}
				} catch (Exception ex) {
					sbuilder.append(content);

//					logger.error("get body data error: ", ex);
				}
			}
		}
		String urlpath = url.substring(url.indexOf("/api") + 1);
		return hmacSha1ToHexStr(urlpath + sbuilder.toString(), token);
	}
	
	

	public static String hmacSha1ToHexStr(String str, String key) {
		try {
			byte[] data = str.getBytes(CHARSET_NAME_UTF8);
			return hmacSha1ToHexStr(data, key.getBytes(CHARSET_NAME_UTF8), 0, data.length);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

    public static String encodeHexStr(final byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        char[] result = new char[bytes.length * 2];
        for (int i = 0; i < bytes.length; i++) {
            result[i * 2] = digital[(bytes[i] & 0xf0) >> 4];
            result[i * 2 + 1] = digital[bytes[i] & 0x0f];
        }
        return new String(result);
    }

	public static String hmacSha1ToHexStr(byte[] data, byte[] key, int offset, int len) {
		byte[] rawHmac = hmacSha1(data, key, offset, len);
		return encodeHexStr(rawHmac);
	}
	
	/**
	 * HMAC
	 */
	public static byte[] hmacSha1(byte[] data, byte[] key, int offset, int len) {
		SecretKeySpec signingKey = new SecretKeySpec(key, HMAC_SHA1);
		Mac mac = null;
		try {
			mac = Mac.getInstance(HMAC_SHA1);
			mac.init(signingKey);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (InvalidKeyException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		mac.update(data, offset, len);
		return mac.doFinal();
	}

}