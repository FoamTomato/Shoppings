package com.sybinal.shop.controller.admin;

import java.util.Comparator;

public class MapKeyComparator implements Comparator<String> {

	//实现一个比较器类
	@Override
	public int compare(String s1, String s2) {
		return s1.compareTo(s2);  //从小到大排序
	}
}
