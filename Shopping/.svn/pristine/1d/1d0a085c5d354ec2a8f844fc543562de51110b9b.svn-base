package com.sybinal.shop.controller.admin;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;

/**
 * Json转换工具类
 * 
 * @author fcl
 *
 */
public final class JsonH {

	private JsonH() {
	}

	private final static ObjectMapper om = new ObjectMapper();

	static {
		om.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		om.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(Visibility.ANY));
		om.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true); // 排序转换,用于字符串比较
	}

	/**
	 * 对象转为string
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static String toJson(Object obj) throws Exception {
		return om.writeValueAsString(obj);
	}

	/**
	 * 支持大小写字段转换；匹配不上字段会赋予初始值
	 * 
	 * @param classtype
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static <T> T toObject(String jsonStr, Class<T> classtype) throws Exception {
		return om.readValue(jsonStr, classtype);
	}

}
