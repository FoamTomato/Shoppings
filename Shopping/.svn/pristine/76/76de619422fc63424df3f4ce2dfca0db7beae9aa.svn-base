package com.sybinal.shop.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.sybinal.shop.common.HttpUtilss;
import com.sybinal.shop.controller.admin.EnumContentType;
import com.sybinal.shop.controller.admin.FLogisticsController;
import com.sybinal.shop.controller.admin.MapKeyComparator;
import com.sybinal.shop.mapper.jpOrderMapper;
import com.sybinal.shop.model.jpOrder;
@Service
public class JpServiceImpl implements JpService {

	@Autowired
	jpOrderMapper jpOrderMapper1;
	@Override
	public int addd(jpOrder jporder) {
		// TODO Auto-generated method stub
		return jpOrderMapper1.insertSelective(jporder);
	}
	@Override
	public List<jpOrder> selectAll(Map <String,String> map) {
		// TODO Auto-generated method stub
		String[] split = map.get("selectText").split(",");
        List<String> strings = Arrays.asList(split);
		return jpOrderMapper1.selectAll(map,strings);
	}
	@Override
	public jpOrder selectIds(String string,String username) {
		// TODO Auto-generated method stub
		return jpOrderMapper1.selectByPrimaryKey(Integer.parseInt(string),username);
	}
	@Override
	public int dellist(String string,String string2) {
		// TODO Auto-generated method stub
		String[] split = string.split(",");
        List<String> strings = Arrays.asList(split);
		return jpOrderMapper1.deleteByPrimaryKeys(strings,string2);
	}
	@Override
	public List<jpOrder> selectPrint(Map<String, String> map) {
		// TODO Auto-generated method stub
		String[] split = map.get("idList").split(",");
        List<String> strings = Arrays.asList(split);
		return jpOrderMapper1.selectPrint(strings,map.get("usen"));
	}
	@Override
	public String postOut(String string, String standby1) {
		// TODO Auto-generated method stub
		String[] split = string.split(",");
        List<String> strings = Arrays.asList(split);
        String apiurl2=FLogisticsController.urls+"api/logistics/v1/track/dropinfo/summary";//http 请求路径
        Gson gson=new Gson();
        List<jpOrder> ordre=jpOrderMapper1.postOut(strings,standby1);
        //对map利用key排序
        Map<String,Object> map2=new HashMap<>();

        List<String> x=new ArrayList();
        //集拼单号,从”生成集拼单号”接口中获取
		 for(jpOrder result:ordre) {
			map2.put("dropNo", result.getJpLaks());
			String[] xs=result.getJpResult().split(",");
			 for(int i=0;i<xs.length;i++) {
				x.add(xs[i]);
			 }
			//集拼的重量
			map2.put("grossWeight", result.getJpRoughweight());
			//集拼的物流方式
			map2.put("shippingMethod", result.getJpLogistic());
			map2.put("referenceNoList", x);
			Map<String, Object> resMap = sortMapByKey(map2);
        	try {
				String doPost2 = HttpUtilss.doPost(apiurl2, gson.toJson(resMap), gson.toJson(resMap),EnumContentType.JSON, 30000);
				System.out.println(doPost2);
				return doPost2;
        	} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		return null;
	}
	/**
	 * 让 Map按key进行排序
	 */
	public static Map<String, Object> sortMapByKey(Map<String, Object> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}
		Map<String, Object> sortMap = new TreeMap<String, Object>(new MapKeyComparator());
		sortMap.putAll(map);
		return sortMap;
	}
}
