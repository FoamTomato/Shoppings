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
import com.sybinal.shop.controller.admin.EnumContentType;
import com.sybinal.shop.controller.admin.MapKeyComparator;
import com.sybinal.shop.controller.admin.unit.HJ_API_CONNECT;
import com.sybinal.shop.enums.HJApiEnum;
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
	public List<Map<String, Object>> postOut(String string, String standby1) {
		// TODO Auto-generated method stub
		String[] split = string.split(",");
        List<String> strings = Arrays.asList(split);
       // String apiurl2=FLogisticsController.urls+"api/logistics/v1/track/dropinfo/summary";//http 请求路径
        Gson gson=new Gson();
        List<jpOrder> ordre=jpOrderMapper1.postOut(strings,standby1);
        
        List<Map<String,Object>> list=  new ArrayList<Map<String,Object>>();
        
        //对map利用key排序
        ordre.forEach(x->{
            Map<String,Object> map2=new HashMap<>();
    		map2.put("dropNo", x.getJpLaks());
    		
    		// 把所有的编号都进行截取
    		String[] list_num2=x.getJpResult().split(",");
    		
    		// 数组进行list转换
    		List<String> list_num = Arrays.asList(list_num2);
    		
    		map2.put("referenceNoList", list_num);
    		map2.put("grossWeight", x.getJpRoughweight());
    		map2.put("shippingMethod", x.getJpLogistic());
    		list.add(map2);
        });
        System.err.println(list);
        
        // 结果存储
        List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
        list.forEach(y->{
            Map<String,Object> map3=new HashMap<>();
            try {
				map3.put(y.get("dropNo").toString(),HJ_API_CONNECT.doPost(HJApiEnum.SUMMARY.getUri(), y,  EnumContentType.JSON, 30000));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				map3.put(y.get("dropNo").toString(), e.getMessage());
			}
            result.add(map3);
        });
        
        System.err.println(result);
        /*List<String> x=new ArrayList();
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
        }*/
		return result;
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
