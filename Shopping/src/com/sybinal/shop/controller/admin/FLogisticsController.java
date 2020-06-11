package com.sybinal.shop.controller.admin;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.sybinal.shop.common.HttpUtilss;
import com.sybinal.shop.common.SrcUrlImg;
import com.sybinal.shop.common.logisticsChange;
import com.sybinal.shop.controller.admin.unit.TestwaterMark;
import com.sybinal.shop.model.Astrict;
import com.sybinal.shop.model.FLogistics;
import com.sybinal.shop.model.User;
import com.sybinal.shop.model.Xlogistics;
import com.sybinal.shop.model.countryCode;
import com.sybinal.shop.model.czBase;
import com.sybinal.shop.model.czBaseWithBLOBs;
import com.sybinal.shop.model.hjBase;
import com.sybinal.shop.model.jpOrder;
import com.sybinal.shop.model.logOutOf;
import com.sybinal.shop.model.ydBase;
import com.sybinal.shop.service.JpService;
import com.sybinal.shop.service.logOutOfService;
import com.sybinal.shop.service.logistics.LogisticsService;
import com.sybinal.shop.service.logistics.czService;
import com.sybinal.shop.service.logistics.hjService;
import com.sybinal.shop.service.order.OrderService;
import com.sybinal.shop.service.order.yidaService;
import com.sybinal.shop.service.user.UserService;

import sun.misc.BASE64Decoder;

@Controller
public class FLogisticsController{
	@Autowired
	OrderService orderService;

	@Autowired
	LogisticsService logisticsService;
	
	@Autowired
	hjService hjServices;

	@Autowired
	czService czservice;
	
	@Autowired
	JpService jpServices;

	@Autowired
	UserService userService;
	
	@Autowired
	logOutOfService logout;

	@Autowired
	yidaService yida;

	public static String urls="http://vakind.f3322.org:11008/";
	
	private static Logger logger= Logger.getLogger(FLogisticsController.class);
	/*
	 * 查询订单
	 *
	 */
	@RequestMapping(value = "/Logistics/checkTheOrder",method=RequestMethod.POST)
	@ResponseBody
	public PageInfo checkTheOrder(@RequestBody Map <String,String> map) {
		  int limit=Integer.parseInt(map.get("limit"));
		  int page=Integer.parseInt(map.get("page"));
		  map.put("username", userService.Justiactions(FLogisticsController.username()).getStandby1());
		  PageHelper.startPage(page,limit);
		  List<FLogistics> List=logisticsService.checkTheOrder(map);
		  PageInfo pageInfo = new PageInfo(List);
		return pageInfo;
	}
	@RequestMapping(value = "/Logistics/username",method=RequestMethod.POST)
	@ResponseBody
	public static String username(){
		//获取缓存的用户名
		  UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		  String names= userDetails.getUsername();
		  return names;
	}
	/*
	 * 改变打印订单状态
	 *
	 */
	@RequestMapping(value = "/Logistics/printStatus",method=RequestMethod.POST)
	@ResponseBody
	public int printStatus(@RequestBody FLogistics fLogistics) {
		fLogistics.setStandby9(userService.Justiactions(username()).getStandby1());
		return logisticsService.update(fLogistics);
	}
	/*
	 * 根据id查询订单详情selectOrderById
	 */
	@RequestMapping(value = "/Logistics/selectOrderById")
	@ResponseBody
	public ModelAndView selectOrderById(FLogistics fLogistics) {
		ModelAndView model = new ModelAndView();
		/*
		 * 获取订单详情
		 */
		User user=new User();
		user.setUserName(FLogisticsController.username());
		fLogistics.setStandby9(userService.Justiactions(username()).getStandby1());
		model.addObject("fLogistics",logisticsService.selectOrderByIds(fLogistics));
		model.addObject("jurisdiction", userService.jurisdiction(user));
		model.addObject("logoutlist", logout.selectAll());
		model.setViewName("admin/order/o_Fright");
		return model;
	}
	
	/*
	 * 获取右侧订单详情
	 */
	@RequestMapping(value = "/Logsistics/shows/ids",method=RequestMethod.POST)
	@ResponseBody
	public FLogistics shows(@RequestBody FLogistics fLogistics) {
		return logisticsService.selectOnes(fLogistics);
	}
	/*
	 * 根据id查询订单详情/Logistics/saveEdit
	 */
	@RequestMapping(value = "/Logistics/saveEdit",method=RequestMethod.POST)
	@ResponseBody
	public FLogistics saveEdit(@RequestBody FLogistics fLogistics) {
		fLogistics.setStandby9(userService.Justiactions(username()).getStandby1());
		if(logisticsService.updateBasicInformation(fLogistics)==1) {
		return logisticsService.selectOrderByIds(fLogistics);}
	return null;
	}
	/**
	 * 右侧界面状态保存orderSave
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年3月13日 下午2:27:59   
	* @version
	 */
	@RequestMapping(value = "/Logistics/orderSave",method=RequestMethod.POST)
	@ResponseBody
	public int orderSave(@RequestBody Map<String,String> map) {
		FLogistics fLogistics=new FLogistics();
		fLogistics.setId(Integer.parseInt(map.get("id")));
		fLogistics.setStandby6(map.get("standby6"));
		fLogistics.setStandby9(userService.Justiactions(username()).getStandby1());
		logisticsService.update(fLogistics);
		return logisticsService.jps(map.get("states"),map.get("id"));
	}
	/*
	 * 添加环金运单/Logistics/addLogisticsQuantity
	 */
	@RequestMapping(value = "/Logistics/addHJLogistics",method=RequestMethod.POST)
	@ResponseBody
	public int addHJLogistics(@RequestBody hjBase hjBases) {
	hjServices.defaultHj0(hjBases.getHjShipperhawbcode());
	logisticsService.default0(hjBases.getHjShipperhawbcode());
	yida.defaultHj0(hjBases.getHjShipperhawbcode());
	List<hjBase>ee=new ArrayList<hjBase>();
	ee.add(hjBases);
	czservice.defaultHj0Plus(ee);
	logisticsService.addWeight(hjBases.getHjInvoiceweight(), hjBases.getHjShipperhawbcode(),hjBases.getHjReferenceno(),userService.Justiactions(username()).getStandby1());
	
	/**
	 * 商品拿值
	 */

	//String date=new SimpleDateFormat("yyyyMMdd").format(new Date());
	hjBases.setHjReferenceno("HJ"+System.currentTimeMillis()+logisticsChange.randomNum(3));
	hjBases.setHjStandy9(userService.Justiactions(username()).getStandby1());
	hjBases.setHjStandy2("1");
	hjBases.setId(null);
	hjBases.setHjIsbattery(hjBases.getHjIscharged());
	hjBases.setHjOrderweight(hjBases.getHjInvoiceweight());
	hjBases.setHjStandy12("");
	return hjServices.addHJLogistics(hjBases);
	}
	
	/**
	 * 根据唯一id拿取商品值getLogisticParticulars
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年3月16日 上午10:46:36   
	* @version 
	 */
	@RequestMapping(value = "/Logistics/getLogisticParticulars",method=RequestMethod.POST)
	@ResponseBody
	public FLogistics getLogisticParticulars(@RequestBody FLogistics logs) {
		return logisticsService.getLogisticParticulars(logs);
	}
	/*
	 * 添加运单/Logistics/addLogisticsQuantity
	 */
	@RequestMapping(value = "/Logistics/addLogisticsQuantity",method=RequestMethod.POST)
	@ResponseBody
	public int addLogisticsQuantity(@RequestBody Xlogistics xlogistics) {
		
	return logisticsService.addLogisticsQuantity(xlogistics);
	}
	/**
	 * 修改集拼jps
	 * @param xlogistics
	 * @return
	 */
	@RequestMapping(value = "/Logistics/jps",method=RequestMethod.POST)
	@ResponseBody
	public int jps(@RequestBody Map<String,String> map) {
	return logisticsService.jps(map.get("states"),map.get("ids"));
	}
	/*
	 * 删除运单/Logistics/delLogisticsQuantity
	 */
	@RequestMapping(value = "/Logistics/delLogisticsQuantity",method=RequestMethod.POST)
	@ResponseBody
	public int delLogisticsQuantity(@RequestBody Xlogistics xlogistics) {
		
		return logisticsService.delLogisticsQuantity(xlogistics);
	}
	/*
	 * 删除HJ运单/Logistics/editHJLogistics
	 */
	
	@RequestMapping(value = "/Logistics/delHJLogistics",method=RequestMethod.POST)
	@ResponseBody
	public int delHJLogistics(@RequestBody hjBase hjBases) {
		hjBases.setHjStandy9(userService.Justiactions(username()).getStandby1());
		return hjServices.delHJLogistics(hjBases);
	}
	/*查询所有订单*/
	
	@RequestMapping(value = "/Logistics/seleHJALL",method=RequestMethod.POST)
	@ResponseBody
	public List<hjBase> seleHJALL(@RequestBody hjBase hjBases) {
		hjBases.setHjStandy9(userService.Justiactions(username()).getStandby1());
			return hjServices.selHJLogistics(hjBases);
	}
	/*
	 * 查询运单/Logistics/selLogisticsQuantity
	 */
	@RequestMapping(value = "/Logistics/selLogisticsQuantity",method=RequestMethod.POST)
	@ResponseBody
	public Xlogistics selLogisticsQuantity(@RequestBody Xlogistics xlogistics) {
			return logisticsService.selsLogisticsQuantity(xlogistics);
	}
	/*
	 * 查询环金运单/Logistics/ckHJLogistics
	 */
	@RequestMapping(value = "/Logistics/ckHJLogistics",method=RequestMethod.POST)
	@ResponseBody
	public hjBase ckHJLogistics(@RequestBody hjBase hjBases) {
		hjBases.setHjStandy9(userService.Justiactions(username()).getStandby1());
			return hjServices.ckHJLogistics(hjBases);
	}
	/*
	 * 修改运单/Logistics/editLogisticsQuantity
	 */
	@RequestMapping(value = "/Logistics/editLogisticsQuantity",method=RequestMethod.POST)
	@ResponseBody
	public int editLogisticsQuantity(@RequestBody Xlogistics xlogistics) {
		xlogistics.setxStandby9(userService.Justiactions(username()).getStandby1());
		return logisticsService.editLogisticsQuantity(xlogistics);
	}
	/*
	 * 修改HJ运单/Logistics/editHJLogistics
	 */
	@RequestMapping(value = "/Logistics/editHJLogistics",method=RequestMethod.POST)
	@ResponseBody
	public int editHJLogistics(@RequestBody hjBase hjBases) {
		hjBases.setHjStandy9(userService.Justiactions(username()).getStandby1());
		return hjServices.editHJLogistics(hjBases);
	}
	/*
	 * 修改运单/Logistics/editHJLogistics
	 */
	@RequestMapping(value = "/Logistics/selLogi",method=RequestMethod.POST)
	@ResponseBody
	public  List<Xlogistics> selLogi(@RequestBody Xlogistics xlogistics) {
		
		return logisticsService.selLogisticsQuantity(xlogistics);
	}
	
	/*
	 * 修改默认
	 */
	@RequestMapping(value = "/Logistics/defaultHj",method=RequestMethod.POST)
	@ResponseBody
	public  int defaultHj(@RequestBody hjBase hjBases) {
		hjServices.defaultHj0(hjBases.getHjShipperhawbcode());
		logisticsService.default0(hjBases.getHjShipperhawbcode());
		logger.info(hjBases.getHjShipperhawbcode());
		yida.defaultHj0(hjBases.getHjShipperhawbcode());
		List<hjBase>ee=new ArrayList<hjBase>();
		ee.add(hjBases);
		czservice.defaultHj0Plus(ee);
		if(hjBases.getHjStandy3().equals("0")) {
			/*
			 * 环金
			 */
			int i=hjServices.defaultHj1(Integer.valueOf(hjBases.getHjStandy2()));
			//更新跟踪号
			logisticsService.defaultsLO(hjBases.getHjShipperhawbcode(),username(),hjBases.getHjShippingmethod());
			return i;
		}else if(hjBases.getHjStandy3().equals("1")) {
			/*
			 * 普通
			 */
			return logisticsService.default1(Integer.valueOf(hjBases.getHjStandy2()));
		}else if(hjBases.getHjStandy3().equals("2")) {
			/*
			 * 义达
			 */
			int i=yida.default2(Integer.valueOf(hjBases.getHjStandy2()));
			//更新跟踪号
			logisticsService.defaulData(hjBases.getHjStandy4(),username(),hjBases.getHjShipperhawbcode(),hjBases.getHjShippingmethod(),hjBases.getHjConsignee());
			return i;
		} else if(hjBases.getHjStandy3().equals("3")) {
			/**
			 * 创志
			 */
			czBase cse=new czBase();
			cse.setId(Integer.valueOf(hjBases.getHjStandy2()));
			int i=czservice.default2cz(cse);
			//更新跟踪号
			logisticsService.defaulData(hjBases.getHjStandy4(),username(),hjBases.getHjShipperhawbcode(),hjBases.getHjShippingmethod(),hjBases.getHjConsignee());
		}
		return 0;
	}
	
	/*
	 * 修改默认
	 */
	@RequestMapping(value = "/Logistics/hj_logistics_post",method=RequestMethod.POST)
	@ResponseBody
	public  int hj_logistics_post(@RequestBody hjBase hjBases) {
		
		return logisticsService.postLogistics(hjBases.getHjStandy8(),userService.Justiactions(username()).getStandby1());
	}
	/**
	 * 批量修改默认hj_logistics_post_list
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年3月21日 上午10:52:57   
	* @version
	 */
	@RequestMapping(value = "/Logistics/hj_logistics_post_list",method=RequestMethod.POST)
	@ResponseBody
	public  int hj_logistics_post_list(@RequestBody List<String> hjBases) {
		
		return logisticsService.postLogisticsList(hjBases,userService.Justiactions(username()).getStandby1());
	}
	/*
	 * 根据条件查询订单/Logistics/selStatusOrder
	 */
	@RequestMapping(value = "/Logistics/selStatusOrder",method=RequestMethod.POST)
	@ResponseBody
	public PageInfo selStatusOrder(@RequestBody FLogistics fLogistics) {
		 String sr=userService.Justiactions(username()).getStandby1();
		  String uks= fLogistics.getStandby9();
		  fLogistics.setStandby9(sr);
		//指定格式化格式
		if(fLogistics.getLimit()!=null) {
			  PageHelper.startPage(fLogistics.getPage(),fLogistics.getLimit());
		}else {
			  PageHelper.startPage(fLogistics.getPage(),50); 
		} 
		  List<FLogistics> List=logisticsService.selStatusOrder(fLogistics,uks);
		  PageInfo pageInfo = new PageInfo(List);
		  
		return pageInfo;
	}
	
	/*
	 * 根据唯一id查询跟踪
	 */
	@RequestMapping(value = "/Logistics/ij",method=RequestMethod.POST)
	@ResponseBody
	public String ij(@RequestBody Map <String,String> map) {
		
		  Gson gson=new Gson();
		return gson.toJson(hjServices.selalls2(String.valueOf(map.get("ij")),userService.Justiactions(username()).getStandby1()));
	}
	/*
	 * 获取到订单fid/Logistics/getPostId
	 */
	@RequestMapping(value = "/Logistics/getPostId",method=RequestMethod.POST)
	@ResponseBody
	public  List<Object> getPostId(@RequestBody List<String> map) {
		return logisticsService.getfids(map);
	}
	/*
	 * 发送同步请求
	 */
	@RequestMapping(value = "/Logistics/getPostYu",method=RequestMethod.POST)
	@ResponseBody
	public  List<Object> getPosts(@RequestBody List<Map<String,Object>> map) {
		Gson gson=new Gson();
		/**
		 * 1.获取到需要发送的列表
		 * 2.进行不合格筛选
		 * 3.发送
		 * 4.获取到结果集
		 * 5.结果存入结果集返回
		 */
		//存储结果
		List<Object> result=new ArrayList<Object>();
		//查询所有的物流及限制
		List<logOutOf> lists=logout.selectAll();
		//查询拦截
		List<Astrict> listsew= hjServices.astrict();
		//查询
		//1
		map.forEach(x->{
			//2
			Map<String,Object> maps=new HashMap<String,Object>();
			if(Integer.parseInt(x.get("totalprice").toString())>11){
				maps.put("code", "0");
				maps.put("data", "");
				maps.put("message", "价格大于11，不上传");
				maps.put("orderId", x);
				result.add(maps);
				return ;
			};
			final String[] resulte = new String[1];
			resulte[0]="";
			lists.forEach(y->{
				if(x.get("shippingMethod").toString().equals(y.getShortName())||x.get("shippingMethod").toString().equals(y.getStandy5())){
					if(Double.parseDouble(x.get("orderWeight").toString())>=Double.parseDouble(y.getStandy4())){
						resulte[0]="重量超过"+y.getStandy4()+"kg";
						return;
					};
				}
			});
			if(!resulte[0].equals("")){
				maps.put("code", "0");
				maps.put("data", "");
				maps.put("message", resulte[0]);
				maps.put("orderId", x);
				result.add(maps);
				return ;
			}
			//拦截收货人
			JSONObject jsonObject = new JSONObject(gson.toJson(x.get("consignee")));
			JSONObject jsonObject2 = new JSONObject(gson.toJson(x.get("itemArr")).substring(1, gson.toJson(x.get("itemArr")).length()-1));
			String namese=logout.namese(listsew,jsonObject.getString("consigneeName"),jsonObject2.getString("invoiceCnname"),jsonObject2.getString("invoiceEnname"));
			if(namese!="0"){
				maps.put("code", "0");
				maps.put("data", "");
				maps.put("message", namese);
				maps.put("orderId", x);
				result.add(maps);
				return ;
			}
			String apiurl=urls+"api/logistics/v1/track/sync/create/order";//http 请求路径
			//http请求请求
			try {
				//3
				Map<String,Object> map2=new HashMap<String,Object>();
				map2.putAll(x);
				map2.put("shipperHawbcode", x.get("referenceNo").toString());
				String doPost = HttpUtilss.doPost(apiurl, gson.toJson(map2), gson.toJson(map2),EnumContentType.JSON, 30000);
				logger.info(gson.toJson(map2)); 
				maps.put("orderId", x);
				maps.put("code", "1");
				maps.put("data", doPost);
				//4
				maps.put("message", "发送成功");
				result.add(maps);
				return ;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				maps.put("code", "0");
				maps.put("data", "");
				maps.put("message", "发送错误"+e);
				maps.put("orderId", x);
				result.add(maps);
				logger.error("发送错误",e);
			}
		});
		//5
		return result;
		/*if(Integer.parseInt(map.get("totalprice").toString())>11){
			logger.info("价格大于11，不上传");
			return "价格大于11，不上传";
		};
		//查询所有的物流及限制
		List<logOutOf> lists=logout.selectAll();
		final String[] result = new String[1];
		result[0]="";
		lists.forEach(x->{
			if(map.get("shippingMethod").toString().equals(x.getShortName())||map.get("shippingMethod").toString().equals(x.getStandy5())){
				if(Double.parseDouble(map.get("orderWeight").toString())>=Double.parseDouble(x.getStandy4())){
					result[0]="重量超过"+x.getStandy4()+"kg";
					return;
				};
			}
		});
		if(!result[0].equals("")){
			return gson.toJson(result[0]);
		}
		JSONObject jsonObject = new JSONObject(gson.toJson(map.get("consignee")));
		JSONObject jsonObject2 = new JSONObject(gson.toJson(map.get("itemArr")).substring(1, gson.toJson(map.get("itemArr")).length()-1));
		*//**
		 * 拦截收件人名称
		 *//*
		
		String namese=logout.namese(jsonObject.getString("consigneeName"),jsonObject2.getString("invoiceCnname"),jsonObject2.getString("invoiceEnname"));
		if(namese!="0"){
			return gson.toJson(namese);
		}
		
		String apiurl=urls+"api/logistics/v1/track/sync/create/order";//http 请求路径
		
		//http请求请求
		try {
			map.put("shipperHawbcode", map.get("referenceNo").toString());
			logger.info(gson.toJson(map));
			String doPost = HttpUtilss.doPost(apiurl, gson.toJson(map), gson.toJson(map),EnumContentType.JSON, 30000);
			logger.info(doPost);
			return doPost;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("发送错误",e);
		}*/
		//return null;
	}
	/*
	 * 获取跟踪号traceNumber
	 */
	@RequestMapping(value = "/Logistics/traceNumber",method=RequestMethod.POST)
	@ResponseBody
	public  String traceNumber(@RequestBody Map<String,Object> map) {
		Gson gson=new Gson();
		String apiurl=urls+"api/logistics/v1/track/list";//http 请求路径
		//http请求请求
		try {
			String doPost = HttpUtilss.doPost(apiurl, gson.toJson(map), gson.toJson(map),EnumContentType.JSON, 30000);
			return doPost;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("获取跟踪号失败",e);
			e.printStackTrace();
		}
		return null;
	}
	//跟踪号赋值setTrack
	@RequestMapping(value = "/Logistics/setTrack",method=RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object> setTrack(@RequestBody  List<Map<String,Object>> map) {
		//运单添加跟踪号
		//批量修改
		Map<String,Object>lists=new HashMap<String,Object>();
		lists.put("hj", hjServices.updataList(map));
		lists.put("lo", logisticsService.updataCnList(map));
		//订单添加
		return lists;
	}
	/*
	 * 发送异步请求asyncPostYu
	 */
	@RequestMapping(value = "/Logistics/asyncPostYu",method=RequestMethod.POST)
	@ResponseBody
	public  List<Object> asyncPostYu(@RequestBody List<Map<String,Object>> map) {
		Gson gson=new Gson();
		/**
		 * 1.获取到需要发送的列表
		 * 2.进行不合格筛选
		 * 3.发送
		 * 4.获取到结果集
		 * 5.结果存入结果集返回
		 */
		//存储结果
		List<Object> result=new ArrayList<Object>();
		//查询所有的物流及限制
		List<logOutOf> lists=logout.selectAll();
		//查询拦截
		List<Astrict> listsew= hjServices.astrict();
		//查询
		//1
		map.forEach(x->{
			//2
			Map<String,Object> maps=new HashMap<String,Object>();
			if(Integer.parseInt(x.get("totalprice").toString())>11){
				maps.put("code", "0");
				maps.put("data", "");
				maps.put("message", "价格大于11，不上传");
				maps.put("orderId", x);
				result.add(maps);
				return ;
			};
			final String[] resulte = new String[1];
			resulte[0]="";
			lists.forEach(y->{
				if(x.get("shippingMethod").toString().equals(y.getShortName())||x.get("shippingMethod").toString().equals(y.getStandy5())){
					if(Double.parseDouble(x.get("orderWeight").toString())>=Double.parseDouble(y.getStandy4())){
						resulte[0]="重量超过"+y.getStandy4()+"kg";
						return;
					};
				}
			});
			if(!resulte[0].equals("")){
				maps.put("code", "0");
				maps.put("data", "");
				maps.put("message", resulte[0]);
				maps.put("orderId", x);
				result.add(maps);
				return ;
			}
			//拦截收货人
			JSONObject jsonObject = new JSONObject(gson.toJson(x.get("consignee")));
			JSONObject jsonObject2 = new JSONObject(gson.toJson(x.get("itemArr")).substring(1, gson.toJson(x.get("itemArr")).length()-1));
			String namese=logout.namese(listsew,jsonObject.getString("consigneeName"),jsonObject2.getString("invoiceCnname"),jsonObject2.getString("invoiceEnname"));
			if(namese!="0"){
				maps.put("code", "0");
				maps.put("data", "");
				maps.put("message", namese);
				maps.put("orderId", x);
				result.add(maps);
				return ;
			}
			String apiurl=urls+"api/logistics/v1/track/create/order";//http 请求路径
			//http请求请求
			try {
				//3
				Map<String,Object> map2=new HashMap<String,Object>();
				map2.putAll(x);
				map2.put("shipperHawbcode", x.get("referenceNo").toString());
				String doPost = HttpUtilss.doPost(apiurl, gson.toJson(map2), gson.toJson(map2),EnumContentType.JSON, 30000);

				maps.put("orderId", x);
				maps.put("code", "1");
				maps.put("data", doPost);
				//4
				maps.put("message", "发送成功");
				result.add(maps);
				return ;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				maps.put("code", "0");
				maps.put("data", "");
				maps.put("message", "发送错误"+e);
				maps.put("orderId", x);
				result.add(maps);
				logger.error("发送错误",e);
			}
		});
		//5
		return result;
		/*if(Integer.parseInt(map.get("totalprice").toString())>11){
			logger.info("价格大于11，不上传");
			return "价格大于11，不上传";
		};
		//查询所有的物流及限制
		List<logOutOf> lists=logout.selectAll();
		final String[] result = new String[1];
		result[0]="";
		lists.forEach(x->{
			if(map.get("shippingMethod").toString().equals(x.getShortName())||map.get("shippingMethod").toString().equals(x.getStandy5())){
				if(Double.parseDouble(map.get("orderWeight").toString())>=Double.parseDouble(x.getStandy4())){
					result[0]="重量超过"+x.getStandy4()+"kg";
					return;
				};
			}
		});
		if(!result[0].equals("")){
			return gson.toJson(result[0]);
		}
		String apiurl=urls+"api/logistics/v1/track/create/order";//http 请求路径
		
		//http请求请求
		try {
			String doPost = HttpUtilss.doPost(apiurl, gson.toJson(map), gson.toJson(map),EnumContentType.JSON, 30000);
			return doPost;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("异步发送失败",e);
			e.printStackTrace();
		}
		return null;*/
	}
	/*
	 * 批量删除/Logsistics/BatchDel
	 */
	@RequestMapping(value = "/Logsistics/BatchDel",method=RequestMethod.POST)
	@ResponseBody
	public  int BatchDel(@RequestBody List<String> map,HttpServletRequest request){
	
		return logisticsService.batdel(map);
	}
	/**
	 * 义达批量删除
	*    yddel
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年3月16日 下午11:50:32   
	* @version
	 */
	@RequestMapping(value = "/Logsistics/ydDel",method=RequestMethod.POST)
	@ResponseBody
	public  List<Object> ydDel(@RequestBody List<Object> map){
		logger.info(map); 
		List<Object> data=new ArrayList<Object>();
		yida.selectYdsId(data).forEach(x->{
			Map<String,Object> maps=new HashMap<String,Object>();
			maps.put("reference_no", x.getYdReferenceNo());
			try {
				data.add(yidaLogistics(maps,"removeorder"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		return data;
	}
	/*
	 * 根据id获取全部图片
	 */
	@RequestMapping(value = "/Logistics/getpdfall",method=RequestMethod.POST)
	@ResponseBody
	public  String getpdfall(@RequestBody List<String> map,HttpServletRequest request) throws JSONException, IOException {
		/*
		 * 获取所有编号
		 */
		FLogistics cww=new FLogistics();
		String das="";
		for(int i=0;i<map.size();i++) {
			
			hjBase bs=hjServices.selalls(map.get(i),userService.Justiactions(username()).getStandby1());
			if(bs!=null) {
				das+=bs.getHjReferenceno()+",";
			}
		}
		das=das.substring(0, das.length()-1);
		List<hjBase> hi=hjServices.selectallOrid(das,userService.Justiactions(username()).getStandby1());
		Map<String,Object> maps=new HashMap<>();
		/*
		 * 获取路径
		 */

		String path3 = request.getSession().getServletContext().getRealPath("").split("webapp")[0];
		//String path3 = request.getSession().getServletContext().getRealPath("/");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		//path+="/resources/upload/"+sdf.format(new Date())+"/";
        String tempPath = "resources/upload/"+sdf.format(new Date())+"/";
		PDFMergerUtility mergePdf = new PDFMergerUtility();
		/*
		 * 拼接pdf
		 */
		
		String username=userService.Justiactions(username()).getStandby1();
		for(hjBase bjbase:hi) {
			logisticsService.postLogistics(bjbase.getHjShipperhawbcode(),username);
			maps.put("lableKey", bjbase.getHjStandy5());
			String c=setLableKey(maps);
			JSONObject jsonObj = new JSONObject(c);
			Object jsonsI=jsonObj.get("result");
			if(jsonsI.equals(null) || jsonsI =="") {
				continue;
			}
				JSONObject result = (JSONObject) jsonsI;
				//循环添加要合并的pdf存放的路径，path是进行路径生成
				mergePdf.addSource(path(result.get("lableData").toString(), request,bjbase.getHjShippingmethod()));
			
		}

		/*
		 * 随机名称
		 */
		String uuid = UUID.randomUUID().toString().replace("-", "");
		//设置合并生成pdf文件名称
		logger.info(path3+ tempPath + uuid+".pdf");
        mergePdf.setDestinationFileName(path3+ tempPath + uuid+".pdf"); 
        //合并pdf
        mergePdf.mergeDocuments(); 
        Gson gson=new Gson();
		return gson.toJson(tempPath + uuid+".pdf");
	}
	/*
	 * 根据id获取跟踪号getTracking
	 */
	@RequestMapping(value = "/Logistics/getTracking",method=RequestMethod.POST)
	@ResponseBody
	public  String getTracking(@RequestBody Map<String,Object> map) {
		Gson gson=new Gson();
		
		String apiurl=urls+"api/logistics/v1/track/list";//http 请求路径
		try {
			String doPost = HttpUtilss.doPost(apiurl, gson.toJson(map), gson.toJson(map),EnumContentType.JSON, 30000);
			JsonObject jsonObject = new JsonParser().parse(gson.toJson(doPost)).getAsJsonObject();
			return doPost;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * 根据id获取跟踪号setLableKey
	 */
	@RequestMapping(value = "/Logistics/setLableKey",method=RequestMethod.POST)
	@ResponseBody
	public  String setLableKey(@RequestBody Map<String,Object> map) {
		/*
		 * 添加运单的标签号
		 */
		Gson gson=new Gson();
		
		String apiurl=urls+"api/logistics/v1/track/sync/lable/download";//http 请求路径
		//System.out.println(SignDemo.getSign(apiurl,"93a61c53-e6a3-4f13-ad0b-594498a49c45",gson.toJson(map),"POST"));
		
		Map<String,Object> maps=new HashMap<String,Object>();
		maps.put("lableKey", map.get("lableKey"));
		try {
			String doPost = HttpUtilss.doPost(apiurl, gson.toJson(maps), gson.toJson(map),EnumContentType.JSON, 30000);
			logger.info(gson.toJson(maps));
			//System.out.println("标签获取到的："+doPost);
			return doPost;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
     *   byte 转文件 下载到本地
     * @param fileName
     * @param
     */
	/*
	 * 根据id获取跟踪号setLableKey
	 */
	@RequestMapping(value = "/Logistics/conserveFile",method=RequestMethod.POST)
	@ResponseBody
    public String conserveFile(@RequestBody Map<String,Object> map,HttpServletRequest request) {
		/*
		 * 随机名称
		 */
		String uuid = UUID.randomUUID().toString().replace("-", "");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		/*
		 * 把map转字符串
		 */
		String bytes=map.get("bytes").toString();
		/* 
		 * 获取服务器项目路径
		 */
		String path = request.getSession().getServletContext().getRealPath("").split("webapp")[0];
		path+="resources/upload/"+sdf.format(new Date())+"/";
        InputStream inputStream = null;
        InputStream inputStreams = null;
        try {
            String tempPath = "resources/upload/";
            String filePath = path+uuid+map.get("fileName")+".pdf";
            // 相对路径
            String relativePath =tempPath+sdf.format(new Date())+"/"+uuid+map.get("fileName")+"1.pdf";
            inputStream = new ByteArrayInputStream(bytes.getBytes());
            // 进行解码
            BASE64Decoder base64Decoder = new BASE64Decoder();
            byte[] byt = base64Decoder.decodeBuffer(inputStream);
            inputStreams = new ByteArrayInputStream(byt);
            File filee = new File(path);
    		if (!filee.exists() && !filee.isDirectory()) {
    			// 文件夹创建批量
    			filee.mkdirs();
    		} else {
    			logger.info("变体创建的文件夹已存在" + filee);
    		}
            /*File file = new File(filePath);
            if (file.exists()) {
                //如果文件存在，则删除文件
                file.delete();
            }*/
            Files.copy(inputStreams, Paths.get(filePath));
            Gson gson=new Gson();
            //添加水印
            TestwaterMark.waterMark(filePath, path+uuid+map.get("fileName")+"1.pdf", "P001",map.get("metho").toString());
            return gson.toJson(relativePath);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (inputStream != null && inputStreams != null) {
                try {
                    inputStream.close();
                    inputStreams.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    /*
	 * 添加LableKey
	 */
	@RequestMapping(value = "/Logistics/setLableid",method=RequestMethod.POST)
	@ResponseBody
    public int setLableid(@RequestBody List<Map<String,Object>> list) {
		logger.info(list);
		return logisticsService.insertLableKeyList(list,userService.Justiactions(username()).getStandby1());
        /*return logisticsService.insertLableKey(map.get("id").toString(),map.get("lab").toString(),map.get("num1").toString(),map.get("id2").toString(),map.get("methosd").toString()
        		,userService.Justiactions(username()).getStandby1());//,y
*/    }
	/*
	 * 解密
	 */
	 public String path(String t,HttpServletRequest request,String methon) {
			/*
			 * 随机名称
			 */
			String uuid = UUID.randomUUID().toString().replace("-", "");
			/*
			 * 把map转字符串
			 */
			String bytes=t;
			/*
			 * 获取服务器项目路径
			 */
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

			String path = request.getSession().getServletContext().getRealPath("").split("webapp")[0];
			path+="/resources/upload/"+sdf.format(new Date())+"/";
			logger.info(path);
	        InputStream inputStream = null;
	        InputStream inputStreams = null;
	        try {
	            String tempPath = "resources/upload/";
	            String filePath = path+uuid+".pdf";
	            // 相对路径
	            String relativePath =path+uuid+"1.pdf";
	            inputStream = new ByteArrayInputStream(bytes.getBytes());
	            // 进行解码
	            BASE64Decoder base64Decoder = new BASE64Decoder();
	            byte[] byt = base64Decoder.decodeBuffer(inputStream);
	            inputStreams = new ByteArrayInputStream(byt);
	            File folder = new File(path); // 创建文件夹
	            if (!folder.exists()&& !folder.isDirectory()){
	                folder.mkdirs();
	            }else {
	    			logger.info("变体创建的文件夹已存在" + filePath);
	    		}
	            Files.copy(inputStreams, Paths.get(filePath));
	            File folder2 = new File(filePath); // 创建文件夹
	            //添加水印
	            if(!folder2.exists()&& !folder2.isDirectory()) {
	            	logger.info(folder2+"不存在");
	            }else {
		            TestwaterMark.waterMark(filePath, path+uuid+"1.pdf", "P001",methon);
	            }
	            return relativePath;
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {

	            if (inputStream != null && inputStreams != null) {
	                try {
	                    inputStream.close();
	                    inputStreams.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	        return null;
	    }
	 /*
	  * dropinfo 生成集拼单号
	  */
	@RequestMapping(value = "/Logistics/dropinfo",method=RequestMethod.POST)
	@ResponseBody
	public String dropinfo(@RequestBody Map<String,Object> map,HttpServletRequest req) {
		//1s1008正式1s0008测试
		String apiurl=urls+"api/logistics/v1/track/dropinfo";//http 请求路径

		String apiurl2=urls+"api/logistics/v1/track/dropinfo/summary";//http 请求路径
		Gson gson=new Gson();
		Map<String,Object> result=new HashMap<String,Object>();
		
		try {
			String doPost = HttpUtilss.doPost(apiurl, "", "",EnumContentType.JSON, 30000);
			Map<String,String> mapSummary=gson.fromJson(doPost, Map.class);
			Map<String,Object> map2=new HashMap<>();
			logger.info(doPost);
			//集拼单号,从”生成集拼单号”接口中获取
			map2.put("dropNo", mapSummary.get("result"));
			
			//传入唯一编号
			List<hjBase> y=hjServices.getNoList(map.get("referenceNoList").toString(),userService.Justiactions(username()).getStandby1());
			if(y.size()==0){
				return gson.toJson("未找到订单"+map.get("referenceNoList").toString());
			}
			for(hjBase d:y){
				if(d.getHjStandy12().equals("1")) {
					return gson.toJson(d.getHjShipperhawbcode()+"包含已集拼订单");
				}
			}
			List<String> x=new ArrayList();
			//存储编号
			String bianh="";
			for(hjBase d:y){
				hjServices.addStandy12(d.getHjReferenceno());
				x.add(d.getHjReferenceno());
				logisticsService.updatajp(d.getHjShipperhawbcode(),mapSummary.get("result"),d.getHjReferenceno(),userService.Justiactions(username()).getStandby1());
				bianh+=d.getHjReferenceno()+",";
			}
			//查找
			map2.put("referenceNoList", x);
			map2.put("grossWeight", map.get("S_weight2"));
			map2.put("shippingMethod", map.get("S_channel"));
			
			//存储编号和集拼单号
			jpOrder ords=new jpOrder();
			
			ords.setJpLaks(mapSummary.get("result"));
			ords.setJpResult(bianh.substring(0, bianh.length()-1));
			ords.setJpLength(map.get("S_num").toString());
			ords.setJpLogistic(map.get("S_channel").toString());
			ords.setJpAdmin(map.get("S_admin").toString());
			ords.setJpRoughweight(map.get("S_weight2").toString());
			ords.setJpSuttle(map.get("S_weights").toString());
			SimpleDateFormat datt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ords.setJpTime(datt.parse(map.get("S_time").toString()));
			
			//对map利用key排序
			Map<String, Object> resMap = sortMapByKey(map2);
			
			String doPost2 = HttpUtilss.doPost(apiurl2, gson.toJson(resMap), gson.toJson(resMap),EnumContentType.JSON, 30000);
			//String s="{\"status\":\"1\",\"errormsg\":null,\"errorcode\":null,\"spenttime\":162,\"result\":{\"succList\":[\"4_1189145774647377922\",\"4_1189157442131558402\",\"4_1189158008123523073\",\"4_1189160009481814017\",\"4_1189160318211948546\",\"4_1189161485075709953\"]},\"errorlevel\":null}";

			Map<Object,Object> mapSummary2=gson.fromJson(doPost2, Map.class);
			String s2=gson.toJson(mapSummary2.get("result"));
			Map<Object,Object> mapSummary3=gson.fromJson(s2, Map.class);
			//所有编号
			result.put("succList",mapSummary3.get("succList"));
			//集拼单号
			result.put("dropNo", mapSummary.get("result"));
			/*
			 * 修改提交的编号
			 */
			//String[] p=map.get("referenceNoList").toString().substring(1, map.get("referenceNoList").toString().length()-1).split(",");
			//if(!mapSummary2.get("status").equals("0")){
			ords.setJpStandby2(username());
			jpServices.addd(ords);
			//}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gson.toJson(result); 
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
	/**
	 * 测试国外接口
	 * @throws Exception 
	 */
	@RequestMapping(value = "/Logistics/test",method=RequestMethod.POST)
	@ResponseBody
		public static String test() throws Exception {
			String apiurl="https://app.rakuten.co.jp/services/api/IchibaItem/Search/20170706?applicationId=[アプリID]&keyword=%E7%A6%8F%E8%A2%8B&sort=%2BitemPrice";
			
			Map<String, String> head = Maps.newHashMap();
			
			String doPost;
			doPost= HttpUtilss.doPost(apiurl, "", "",EnumContentType.JSON, 30000);
			logger.info(doPost);
			Gson gson=new Gson();
			return gson.toJson(doPost);
	}
	/**
	 * 获取国家二字码
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年3月21日 上午11:18:42   
	* @version
	 */
	@RequestMapping(value = "/Logistics/countryCode",method=RequestMethod.POST)
	@ResponseBody
    public List<countryCode> countryCodes(){
		return logisticsService.countryCodes();
	}
	/*
	 * /Logistics/ALLbatch 批量添加运单
	 */
	@RequestMapping(value = "/Logistics/ALLbatch",method=RequestMethod.POST)
	@ResponseBody
    public int ALLbatch(@RequestBody Map<String,String> map){
			//拿到列表
			String str=map.get("List");
			String[] str1=str.split(",");

			//String date=new SimpleDateFormat("yyyyMMdd").format(new Date());
			FLogistics fLogistics = new FLogistics();
			List<hjBase> hjList = new ArrayList<hjBase>();
			List<countryCode> codes=countryCodes();
			String usernames=userService.Justiactions(username()).getStandby1();
			//获取到id并传值
			for(int i=0;i<str1.length;i++) {
				fLogistics.setId(Integer.parseInt(str1[i]));
				fLogistics.setStandby9(usernames);
				fLogistics=logisticsService.selectByPrimaryKey2(fLogistics);
				hjBase hjBases=new hjBase();
				hjBases.setHjReferenceno("HJ"+System.currentTimeMillis()+logisticsChange.randomNum(3));

				hjBases.setHjShipperhawbcode(fLogistics.getfIds());
				//如果为0 则获取自带
				if(map.get("bat_shippingMethod")=="0"){
					hjBases.setHjShippingmethod(fLogistics.getStandby12());
				}else{
					hjBases.setHjShippingmethod(map.get("bat_shippingMethod"));
				}
				hjBases.setHjCountrycode(fLogistics.getfCountry());
				hjBases.setHjShipzip(fLogistics.getfPostCode());
				hjBases.setHjOrderweight(map.get("bat_orderWeight"));
				hjBases.setHjOrderpieces(map.get("bat_orderPieces"));
				hjBases.setHjTotalprice(map.get("bat_invoiceUnitcharge"));
				hjBases.setHjMailcargotype(map.get("bat_mailCargoType"));
				hjBases.setHjLength(map.get("bat_length"));
				hjBases.setHjWidth(map.get("bat_width"));
				hjBases.setHjHeight(map.get("bat_height"));
				hjBases.setHjIsreturn(map.get("bat_isReturn"));
				hjBases.setHjIsbattery(map.get("bat_isBreakable"));
				hjBases.setHjConsigneecompany(map.get("bat_COMPANY"));
				//System.out.println(fLogistics.getfProvince());
				if(!map.get("bat_invoicePrivan").equals("")){
					hjBases.setHjConsigneeprovince(map.get("bat_invoicePrivan"));
				}else if(fLogistics.getfProvince().equals("")) {
					hjBases.setHjConsigneeprovince(fLogistics.getfCity());
				}else {
					hjBases.setHjConsigneeprovince(fLogistics.getfProvince());
				}
				//System.out.println(hjBases.getHjConsigneeprovince());
				if(!map.get("bat_invoiceCity").equals("")){
					hjBases.setHjConsigneecity(map.get("bat_invoiceCity"));
				}else{
					hjBases.setHjConsigneecity(fLogistics.getfCity());
				}
				hjBases.setHjConsignee("1");
				hjBases.setHjConsigneestreet(fLogistics.getfAddress1());
				hjBases.setHjConsigneestreet2(fLogistics.getfAddress2());
				hjBases.setHjConsigneestreet3(fLogistics.getfAddress3());
				hjBases.setHjConsigneepostcode(fLogistics.getfPostCode());
				hjBases.setHjConsigneename(fLogistics.getfFirstName()+fLogistics.getfLastName());
				hjBases.setHjConsigneetelephone(fLogistics.getfTelephone());
				hjBases.setHjConsigneemobile(fLogistics.getfTelephone());
				hjBases.setHjConsigneeemail(fLogistics.getfEmail());
				String pi=fLogistics.getfCountry();
				String data=logisticsChange.pd(pi,codes);
				if(data!=null) {
					String[] y=data.split(",");
					hjBases.setHjConsigneecountryename(y[1]);
					hjBases.setHjConsigneecountrycname(y[0]);
				}
				hjBases.setHjInvoiceenname(map.get("bat_invoiceEnname"));
				hjBases.setHjInvoicecnname(map.get("bat_invoiceCnname"));
				hjBases.setHjInvoiceweight(map.get("bat_orderWeight"));
				hjBases.setHjInvoicequantity(map.get("bat_invoiceQuantity"));
				hjBases.setHjUnitcode(map.get("bat_unitCode"));
				hjBases.setHjInvoiceunitcharge(map.get("bat_invoiceUnitcharge"));
				hjBases.setHjInvoicecurrencycode(map.get("bat_invoiceCurrencycode"));
				hjBases.setHjHscode(map.get("bat_hsCode"));
				
				
				hjBases.setHjSku("B07"+logisticsChange.randomNum(7).toUpperCase());
				
				
				hjBases.setHjIscontainsbattery(map.get("bat_isContainsBattery"));
				hjBases.setHjIsaneroidmarkup(map.get("bat_isAneroidMarkup"));
				hjBases.setHjIsonlybattery(map.get("bat_isOnlyBattery"));
				hjBases.setHjIsbreakable(map.get("bat_isBreakable"));
				hjBases.setHjIscharged(map.get("bat_isCharged"));
				hjBases.setHjStandy2("1"); 
				hjBases.setHjSaleprice(map.get("bat_invoiceUnitcharge"));
				hjBases.setHjSalecurrency(map.get("bat_saleCurrency"));
				hjBases.setHjCategoryname("家具装饰");
				hjBases.setHjParentenname("Furniture decoration");
				if (map.get("bat_shippingMethod").indexOf("cm")!= -1){
						hjBases.setHjStandy6("cm");//根据客服添加配送方式
					}else if(map.get("bat_shippingMethod").indexOf("ghxb")!= -1){
						hjBases.setHjStandy6("ghxb");//根据客服添加配送方式
					}else if(map.get("bat_shippingMethod").indexOf("kd")!= -1){
						hjBases.setHjStandy6("kd");//根据客服添加配送方式
					}else if(map.get("bat_shippingMethod").indexOf("zx")!= -1){
						hjBases.setHjStandy6("zx");//根据客服添加配送方式
					}else if(map.get("bat_shippingMethod").indexOf("xn")!= -1){
						hjBases.setHjStandy6("xn");//根据客服添加配送方式法国专线
					}
				hjBases.setHjStandy9(usernames);
				hjBases.setHjStandy10(str1[i]);
				hjBases.setHjStandy11("");
				hjBases.setHjStandy12("");
				hjBases.setHjStandy13("");
				hjBases.setHjStandy3("");
				hjBases.setHjStandy4("");
				hjBases.setHjStandy5("");
				hjBases.setHjStandy7("");
				hjBases.setHjStandy8("");
				hjList.add(hjBases);
			}
			logisticsService.addWeightPlus(hjList,userService.Justiactions(username()).getStandby1());
			hjServices.defaultHj0Plus(hjList);
			logisticsService.default0Plus(hjList);
			yida.defaultHj0Plus(hjList);
			hjServices.insertSelective2Plus(hjList);
			czservice.defaultHj0Plus(hjList);
/*待优化
				hjServices.insertSelective2(hjBases);*
			logisticsService.addWeight(hjBases.getHjInvoiceweight(),hjBases.getHjShipperhawbcode(),hjBases.getHjReferenceno(),userService.Justiactions(username()).getStandby1());
			hjServices.defaultHj0(hjBases.getHjShipperhawbcode());
			logisticsService.default0(hjBases.getHjShipperhawbcode());
			yida.defaultHj0(hjBases.getHjShipperhawbcode());*/
			return 1;
	}
	/**
	 * 发送义达订单
	 * yidaLogistics
	 * @param string 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/Logistics/yidaLogistics",method=RequestMethod.POST)
	@ResponseBody
    public String yidaLogistics(@RequestBody Map<String,Object> map, String string) throws Exception{
		String apiurl2="http://customer.ydhex.com/webservice/PublicService.asmx/ServiceInterfaceUTF8";
		Gson gson=new Gson();
		logger.info(gson.toJson(map));
		/*Map<String,Object> maps=new HashMap<String,Object>();

		maps.put("paramsJson", map);
		maps.put("appToken", "yondaeeo7tfq7gnzu6p0t3naulpm7ytpz");
		maps.put("appKey", "ypc233783ub1rugiy39sl06banszz2qiaz1rdu618q782wtaehydzae9sa2qkhjad");
		maps.put("serviceMethod", string);
		logger.info(gson.toJson(maps));*/
		String doPost=HttpUtilss.doydPost(apiurl2, gson.toJson(map),EnumContentType.URLEncoder, 30000,string);
		return doPost;
	}
	/**
	 * 发送创志订单
	 * yidaLogistics
	 * @param string 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/Logistics/cz/postOrder",method=RequestMethod.POST)
	@ResponseBody
    public String czpostOrder(@RequestBody Map<String,Object> map, String string) throws Exception{
		String apiurl2="http://saczex.nextsls.com/api/v4/shipment/"+string;
		Gson gson=new Gson();
		//logger.info(gson.toJson(map));
		String doPost=HttpUtilss.doczPost(apiurl2, gson.toJson(map),EnumContentType.URLEncoder, 30000,string);
		return doPost;
	}
	/**
	 * 添加义达
	 */
	@RequestMapping(value = "/Logistics/addyida",method=RequestMethod.POST)
	@ResponseBody
    public int addyida(@RequestBody ydBase base) throws Exception{
		base.setYdShipperName("数独科技");
  	  	base.setYdShipperCountrycode("CN");
  	  	base.setYdShipperStreet("深圳龙岗坂田");
  	  	base.setYdShipperTelephone("18911520803");
  	  	base.setYdShipperMobile("18911520803");

		base.setYdStandy2("1");
		
		hjServices.defaultHj0(base.getYdStandy3());
		logisticsService.default0(base.getYdStandy3());
		yida.defaultHj0(base.getYdStandy3());

		List<hjBase>ee=new ArrayList<hjBase>();
		hjBase hj=new hjBase();
		hj.setHjShipperhawbcode(base.getYdStandy3());
		ee.add(hj);
		czservice.defaultHj0Plus(ee);
		//base.setYdReferenceNo("YD"+System.currentTimeMillis()+logisticsChange.randomNum(3));
		return yida.addOrder(base);
	}
	/**
	 * 批量添加义达addyidas
	 */
	@RequestMapping(value = "/Logistics/addyidas",method=RequestMethod.POST)
	@ResponseBody
    public int addyidas(@RequestBody Map<String,Object>map) throws Exception{
		//批量添加
		List<ydBase> lists=new ArrayList<ydBase>();
		List<hjBase> hjbasee=new ArrayList<hjBase>();
		//列表
        logisticsService.selectListid(map.get("checkedCities").toString()).forEach(x->{
    		ydBase base=new ydBase();
    		base.setYdInvoiceEnname(map.get("invoiceEnname").toString());
    		base.setYdInvoiceCnname(map.get("invoiceCnname").toString());
    		base.setYdConsigneeCity(map.get("city").toString());
    		base.setYdConsigneeProvince(map.get("province").toString());
    		base.setYdUnitCode(map.get("unitCode").toString());
    		base.setYdInvoiceQuantity(map.get("invoiceQuantity").toString());
    		base.setYdShippingMethod(map.get("shippingMethod").toString());
    		base.setYdOrderWeight(map.get("orderWeight").toString());
    		base.setYdOrderPieces(map.get("orderPieces").toString());
    		base.setYdMailCargoType(map.get("mailCargoType").toString());
    		base.setYdCargotype(map.get("cargotype").toString());
    		if(map.containsKey("orderInfo")) {
    			base.setYdOrderInfo(map.get("orderInfo").toString());
    		}
    		base.setYdReturnSign(map.get("isReturn").toString());
    		//base.setYdOrderInfo(map.get("checkedCities").toString());
    		
    		base.setYdShipperName("数独科技");
      	  	base.setYdShipperCountrycode("CN");
      	  	base.setYdShipperStreet("深圳龙岗坂田");
      	  	base.setYdShipperTelephone("18911520803");
      	  	base.setYdShipperMobile("18911520803");
    		//base.setYdReferenceNo("YD"+System.currentTimeMillis()+logisticsChange.randomNum(3));
    		base.setYdReferenceNo(x.getfIds());
        	base.setYdSku("B07"+logisticsChange.randomNum(7).toUpperCase());
        	if(base.getYdShippingMethod().equals("FRDB")) {
        		base.setYdInvoiceUnitcharge("18");//如果为欧洲大包就默认18美元
			}else {
				base.setYdInvoiceUnitcharge(String.valueOf(sj()));
			}
			base.setYdConsigneeName(x.getfFirstName()+x.getfLastName());
			base.setYdConsigneeCountrycode(x.getfCountry());
			base.setYdConsigneeProvince(x.getfProvince());
			base.setYdConsigneeCity(x.getfCity());
			base.setYdConsigneeStreet(x.getfAddress1()+x.getfAddress2()+x.getfAddress3());
			//base.setYdConsigneeStreet(x.getfAddress1());
			base.setYdConsigneePostcode(x.getfPostCode());
			base.setYdConsigneeTelephone(x.getfTelephone());
			base.setYdConsigneeMobile(x.getfTelephone());
			base.setYdConsigneeEmail(x.getfEmail());
			base.setYdStandy7("");
			base.setYdStandy3(x.getfIds());
			base.setYdStandy2("1");
			hjBase hj=new hjBase();
			hj.setHjShipperhawbcode(x.getfIds());
			hjbasee.add(hj);
			
			lists.add(base);
        });
		hjServices.defaultHj0Plus(hjbasee);
		logisticsService.default0Plus(hjbasee);
		yida.defaultHj0Plus(hjbasee);
		czservice.defaultHj0Plus(hjbasee);
		return yida.addOrders(lists);
	}
	/**
	 * 随机价格
	 */
	public int sj() {
	    //x上限，y下限     
	    int x = 11;
	    int y = 3;
	    int rand = (int) (Math.random() * (x - y + 1) + y);
		return rand;
	}
	/**
	 * 跳转查看跟踪号/Logistics/tail
	 */
	@RequestMapping(value = "/Logistics/tail")
	@ResponseBody
    public ModelAndView tail() throws Exception{
		ModelAndView model = new ModelAndView();
		
		model.setViewName("admin/tail/ydTail");
		return model;
	}
	/**
	 * 查看跟踪号tail/orderList
	 */
	@RequestMapping(value = "/Logistics/tail/orderList")
	@ResponseBody
    public List<Object> tailOrderList(@RequestBody Map<String,String>map) throws Exception{
		//拿编号
		String[] tailList=map.get("selectText").split(",");
		Map<String,Object> tails=new HashMap<String,Object>();
		//存入列表
		List<Object> result=new ArrayList<Object>();
		for(int i=0;i<tailList.length;i++) {
			tails.put("tracking_number", tailList[i]);
			result.add(yidaLogistics(tails,"gettrack"));
		}
		return result;
	}
	/**
	 * 修改义达ydUpdateOrder
	 */
	@RequestMapping(value = "/Logistics/ydUpdateOrder",method=RequestMethod.POST)
	@ResponseBody
    public int ydUpdateOrder(@RequestBody ydBase base) throws Exception{
		
		return yida.ydUpdateOrder(base);
	}
    /**
	 * 添加义达跟踪号
	 */
	@RequestMapping(value = "/Logistics/updateYd",method=RequestMethod.POST)
	@ResponseBody
    public int updateYd(@RequestBody ydBase base) throws Exception{
		//更新跟踪号
		logger.info(base);
		logisticsService.defaulData(base.getYdShippingMethodNo(),username(),base.getYdStandy3(),base.getYdStandy7(),"");
		return yida.updateYd(base);
	}
	/**
	 * ydDelOrder删除义达
	 */
	@RequestMapping(value = "/Logistics/ydDelOrder",method=RequestMethod.POST)
	@ResponseBody
    public String ydDelOrder(@RequestBody ydBase base) throws Exception{
		yida.ydDelOrder(base);
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("reference_no", base.getYdReferenceNo());
		return yidaLogistics(map,"removeorder");
	}
	/**
	 * 批量发送义达postYds
	 */
	@RequestMapping(value = "/Logistics/postYds",method=RequestMethod.POST)
	@ResponseBody
    public List<Object> postYds(@RequestBody List<String> list) throws Exception{
		Map<String,Object>map=new HashMap<String,Object>();
		List<Object>liste=new ArrayList<Object>();
		
		yida.selectYds(logisticsService.selectIDList(list)).forEach(base2->{
			map.put("reference_no", base2.getYdReferenceNo());
			map.put("shipping_method", base2.getYdShippingMethod());
			map.put("order_weight", base2.getYdOrderWeight());
			map.put("order_pieces", base2.getYdOrderPieces());
			map.put("cargotype", base2.getYdCargotype());
			map.put("mail_cargo_type", base2.getYdMailCargoType());
			map.put("return_sign", base2.getYdReturnSign());
			map.put("order_info", base2.getYdOrderInfo());
			//发件人信息
			Map<String,Object>shipper=new HashMap<String,Object>();
			shipper.put("shipper_name", base2.getYdShipperName());
			shipper.put("shipper_countrycode", base2.getYdShipperCountrycode());
			shipper.put("shipper_street", base2.getYdShipperStreet());
			shipper.put("shipper_telephone", base2.getYdShipperTelephone());
			shipper.put("shipper_mobile", base2.getYdShipperMobile());
			//收件人信息
			Map<String,Object>consignee=new HashMap<String,Object>();
			consignee.put("consignee_name", base2.getYdConsigneeName());
			consignee.put("consignee_countrycode", base2.getYdConsigneeCountrycode());
			consignee.put("consignee_province", base2.getYdConsigneeProvince());
			consignee.put("consignee_city", base2.getYdConsigneeCity());
			consignee.put("consignee_street", base2.getYdConsigneeStreet());
			consignee.put("consignee_postcode", base2.getYdConsigneePostcode());
			consignee.put("consignee_telephone", base2.getYdConsigneeTelephone());
			consignee.put("consignee_mobile", base2.getYdConsigneeMobile());
			consignee.put("consignee_tariff", base2.getYdConsigneeTariff());
			//海关申报信息
			Map<String,Object>invoice=new HashMap<String,Object>();
			List<Object> invoice2=new ArrayList<Object>();
			invoice2.add(invoice);
			invoice.put("invoice_enname", base2.getYdInvoiceEnname());
			invoice.put("invoice_cnname", base2.getYdInvoiceCnname());
			invoice.put("invoice_quantity", base2.getYdInvoiceQuantity());
			invoice.put("unit_code", base2.getYdUnitCode());
			invoice.put("invoice_unitcharge", base2.getYdInvoiceUnitcharge());
			map.put("shipper", shipper);
			map.put("consignee", consignee);
			map.put("invoice", invoice2);
			try {
				
				Map<String,Object>result=new HashMap<String,Object>();
				result.put("data", yidaLogistics(map,"createorder"));
				result.put("map", map);
				result.put("fid", base2.getYdStandy3());
				result.put("method", base2.getYdShippingMethod());
				liste.add(result);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		return liste;
	}
	/**
	 * 发送义达postYd
	 */
	@RequestMapping(value = "/Logistics/postYd",method=RequestMethod.POST)
	@ResponseBody
    public List<Object> postYd(@RequestBody ydBase base) throws Exception{
		Map<String,Object>map=new HashMap<String,Object>();

		List<Object>liste=new ArrayList<Object>();
		ydBase base2=yida.selectYd(base.getId().toString());
		map.put("reference_no", base2.getYdReferenceNo());
		map.put("shipping_method", base2.getYdShippingMethod());
		map.put("order_weight", base2.getYdOrderWeight());
		map.put("order_pieces", base2.getYdOrderPieces());
		map.put("cargotype", base2.getYdCargotype());
		map.put("mail_cargo_type", base2.getYdMailCargoType());
		map.put("return_sign", base2.getYdReturnSign());
		map.put("order_info", base2.getYdOrderInfo());
		//发件人信息
		Map<String,Object>shipper=new HashMap<String,Object>();
		shipper.put("shipper_name", base2.getYdShipperName());
		shipper.put("shipper_countrycode", base2.getYdShipperCountrycode());
		shipper.put("shipper_street", base2.getYdShipperStreet());
		shipper.put("shipper_telephone", base2.getYdShipperTelephone());
		shipper.put("shipper_mobile", base2.getYdShipperMobile());
		//收件人信息
		Map<String,Object>consignee=new HashMap<String,Object>();
		consignee.put("consignee_name", base2.getYdConsigneeName());
		consignee.put("consignee_countrycode", base2.getYdConsigneeCountrycode());
		consignee.put("consignee_province", base2.getYdConsigneeProvince());
		consignee.put("consignee_city", base2.getYdConsigneeCity());
		consignee.put("consignee_street", base2.getYdConsigneeStreet());
		consignee.put("consignee_postcode", base2.getYdConsigneePostcode());
		consignee.put("consignee_telephone", base2.getYdConsigneeTelephone());
		consignee.put("consignee_mobile", base2.getYdConsigneeMobile());
		consignee.put("consignee_tariff", base2.getYdConsigneeTariff());
		//海关申报信息
		Map<String,Object>invoice=new HashMap<String,Object>();
		List<Object> invoice2=new ArrayList<Object>();
		invoice2.add(invoice);
		invoice.put("invoice_enname", base2.getYdInvoiceEnname());
		invoice.put("invoice_cnname", base2.getYdInvoiceCnname());
		invoice.put("invoice_quantity", base2.getYdInvoiceQuantity());
		invoice.put("unit_code", base2.getYdUnitCode());
		invoice.put("invoice_unitcharge", base2.getYdInvoiceUnitcharge());
		
		map.put("shipper", shipper);
		map.put("consignee", consignee);
		map.put("invoice", invoice2);
		
		Map<String,Object>result=new HashMap<String,Object>();
		result.put("data", yidaLogistics(map,"createorder"));
		result.put("fid", base2.getYdStandy3());
		result.put("map", map);
		result.put("method", base2.getYdShippingMethod());
		liste.add(result);
		return liste;
	}
	
	/**
	 * 查询所有列表
	 */
	@RequestMapping(value = "/Logistics/orderlist",method=RequestMethod.POST)
	@ResponseBody
    public Map<String, Object> orderlist(@RequestBody Map<String,Object> maps) throws Exception{
		Map<String,Object>map=new HashMap<String,Object>();
		/*
		 * 获取云单详情
		 */
		/*model.addObject("XLogistics",logisticsService.selectSA(fLogistics.getId(),fLogistics.getfIds()));
		
		 * 获取hj云单详情
		 * */
		map.put("hj",hjServices.selectHjLogice(maps.get("fid").toString(),userService.Justiactions(username()).getStandby1(),maps.get("id").toString()));
		map.put("yd",yida.selectOrder(maps.get("fid").toString(),userService.Justiactions(username()).getStandby1(),maps.get("id").toString()));
		map.put("cz",czservice.selectOrder(maps.get("fid").toString(),userService.Justiactions(username()).getStandby1()));
		//map.put("logoutlist", logout.selectAll());查询物流
		//map.put("XLogistics",logisticsService.selectSA(fLogistics.getId(),fLogistics.getfIds()))自己的物流
		return map;
	}
    
    /**
	 * 根据id查询单个
	 */
	@RequestMapping(value = "/Logistics/yidaSelectOne",method=RequestMethod.POST)
	@ResponseBody
    public ydBase yidaSelectOne(@RequestBody  ydBase base) throws Exception{
		
		return yida.yidaSelectOne(base);
	}
	/**
	 * 修改义达
	 */
	@RequestMapping(value = "/Logistics/yidaUpdate",method=RequestMethod.POST)
	@ResponseBody
    public void yidaUpdate() throws Exception{
		ExecutorService pool = Executors.newFixedThreadPool(30);
		CountDownLatch lat=new CountDownLatch(30);
		yida.selectAll().forEach(x->{
			try {
				//Task tast=new Task(null, null, null, lat, objToMap(x));
				//pool.execute(tast);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
	/**
	 * 对象转map
	 * @param obj
	 * @return
	 */
	private static Map<String, Object> objToMap(Object obj) {

		Map<String, Object> map = new HashMap<String, Object>();
		Field[] fields = obj.getClass().getDeclaredFields();	// 获取f对象对应类中的所有属性域
		for (int i = 0, len = fields.length; i < len; i++) {
			String varName = fields[i].getName();
			varName = varName.toLowerCase();					// 将key置为小写，默认为对象的属性
			try {
				boolean accessFlag = fields[i].isAccessible();	// 获取原来的访问控制权限
				fields[i].setAccessible(true);					// 修改访问控制权限
				Object o = fields[i].get(obj);					// 获取在对象f中属性fields[i]对应的对象中的变量
				if (o != null){
					map.put(varName, o.toString());
				}
				fields[i].setAccessible(accessFlag);			// 恢复访问控制权限
			} catch (IllegalArgumentException ex) {
				ex.printStackTrace();
			} catch (IllegalAccessException ex) {
				ex.printStackTrace();
			}
		}
		return map;
	}
	/**
	 * 义达打印lable
	 */
	@RequestMapping(value = "/Logistics/lable",method=RequestMethod.POST)
	@ResponseBody
    public String lable(@RequestBody Map<String,Object> map) throws Exception{
		
		return yidaLogistics(map,"getnewlabel");
	}
	/**
	 * 义达批量打印
	 */
	@RequestMapping(value = "/Logistics/ydPrint",method=RequestMethod.POST)
	@ResponseBody
    public String ydPrint(@RequestBody List<String> list) throws Exception{
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("lable_file_type","2");
    	map.put("lable_paper_type","1");
	    map.put("lable_content_type","1");
	    Map<String,Object> map2=new HashMap<String,Object>();
	    map2.put("lable_print_invoiceinfo","Y");
	    map2.put("lable_print_buyerid","N");
	    map2.put("lable_print_datetime","Y");
	    map2.put("customsdeclaration_print_actualweight","N");
	    map.put("additional_info", map2);
		List<Object> listorder=new ArrayList<Object>();
		//yida.selectIDList(list);
		yida.selectIDList(logisticsService.selectIDList(list)).forEach(sx->{
		    Map<String,Object> map4=new HashMap<String,Object>();
		    map4.put("reference_no",sx);
		    listorder.add(map4);
		});
		Map<String,Object> map3=new HashMap<String,Object>();
		map3.put("configInfo", map);
		map3.put("listorder", listorder);
		return yidaLogistics(map3,"getnewlabel");
	}
	
	/**
	 * 创志根据id查找
	 */
	@RequestMapping(value = "/Logistics/cz/selectId",method=RequestMethod.POST)
	@ResponseBody
    public czBase czSelectId(@RequestBody czBase czbase) throws Exception{
		return czservice.SelectAll(czbase);
	}
	/**
	 * Logistics/cz/del创志删除
	 */
	@RequestMapping(value = "Logistics/cz/del",method=RequestMethod.POST)
	@ResponseBody
    public Map<String, Object> czdel(@RequestBody czBase czbase) throws Exception{
		Map<String,Object>map3=new HashMap<String,Object>();
		Map<String,Object>map=new HashMap<String,Object>();
		Map<String,Object>map1=new HashMap<String,Object>();
		Map<String,Object>map2=new HashMap<String,Object>();
		//创志默认token
		map1.put("access_token", "5e82a8df3aff972da61d29225e82a8df2a7736220");
	    map2.put("shipment_id", czservice.selectID(czbase.getId()).getFsr4());
		map.put("validation", map1);
		map.put("shipment", map2);
		map3.put("data",czpostOrder(map,"void"));
		czservice.dels(czbase);
		return map3;
	}
	/**
	 * Logistics/cz/label创志打印
	 */
	@RequestMapping(value = "/Logistics/cz/label",method=RequestMethod.POST)
	@ResponseBody
    public String czPrint(@RequestBody List<String> list,HttpServletRequest req) throws Exception{

		Map<String,Object>map=new HashMap<String,Object>();
		Map<String,Object>map1=new HashMap<String,Object>();
		Map<String,Object>map2=new HashMap<String,Object>();
		List<Object> lists=new ArrayList<Object>();
		//创志默认token
		//logisticsService.postLogisticsList(hjBases,userService.Justiactions(username()).getStandby1())
		Gson gson=new Gson();

        //ExecutorService pool = Executors.newFixedThreadPool(30);
       // CountDownLatch lat=new CountDownLatch(30);
        String path = req.getSession().getServletContext().getRealPath("").split("webapp")[0]+"/resources/upload/";
		map1.put("access_token", "5e82a8df3aff972da61d29225e82a8df2a7736220");
		czservice.selectIDList(logisticsService.selectIDList(list)).forEach(x->{
			if(!StringUtils.isEmpty(x)) {
			    map2.put("shipment_id", x.getFsr4());
				map.put("validation", map1);
				map.put("shipment", map2);
				try {
					lists.add(czpostOrder(map,"get_labels"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					logger.error("获取创志标签失败",e);
					e.printStackTrace();
				}
			}
		});
		/**
		 * 存pdf列表
		 */
		List<String> listpdfs=new ArrayList<String>();
		PDFMergerUtility mergePdf = new PDFMergerUtility();
		/**
		 * 批量下载JSON.parse(result.body[da1]).data.shipment.single_pdf
		 */
		SrcUrlImg src=new SrcUrlImg();
		lists.forEach(x->{
	        /**
	    	 * 用闭锁实现并发排序下载
	    	 */
	        UUID uuids = UUID.randomUUID(); 
			Map<String,Object> data1=gson.fromJson(x.toString(), new TypeToken<Map<String,Object>>(){}.getType());
			if(!data1.get("data").toString().equals("[]")) {
				Map<String,Object> data2=gson.fromJson(gson.toJson(data1.get("data")), new TypeToken<Map<String,Object>>(){}.getType());
				Map<String,Object> data3=gson.fromJson(gson.toJson(data2.get("shipment")), new TypeToken<Map<String,Object>>(){}.getType());
				/**
				 * 添加拼接地址
				 */
				listpdfs.add(src.downloadPDF(path, uuids.toString(), data3.get("single_pdf").toString()));
			}
		});
		logisticsService.postLogisticsList(list,userService.Justiactions(username()).getStandby1());

		/**
		 * 循环拼接
		 */
		listpdfs.forEach(x->{
	      //循环添加要合并的pdf存放的路径，path是进行路径生成
			try {
				mergePdf.addSource(path+x);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error("拼接失败",e);
			}
		});
		/*
		 * 随机名称
		 */
		String uuid = UUID.randomUUID().toString().replace("-", "");
		//设置合并生成pdf文件名称
		mergePdf.setDestinationFileName(path + uuid+".pdf"); 
		//合并pdf
		mergePdf.mergeDocuments(); 
		logger.info(path + uuid+".pdf");
		return gson.toJson(uuid+".pdf");
	}
	/**
	 * 添加创志Logsistics/cz/addOrder
	 */
	@RequestMapping(value = "/Logistics/cz/addOrder",method=RequestMethod.POST)
	@ResponseBody
    public int addOrder(@RequestBody Map<String,Object> map) throws Exception{
		/**
		 * 1.批量添加
		 * 2.修改
		 * 3.单独添加
		 */
		int numbers=0;
		Gson gson=new Gson();
		if(map.get("type").equals("1")||map.get("type").equals("3")) {
			//添加
			//List<String> ids=gson.fromJson(map.get("ids").toString(),new TypeToken<List<String>>(){}.getType());
			/**
			 * 查询订单列表
			 */
			List<czBaseWithBLOBs> czlist=new ArrayList<czBaseWithBLOBs>();
			String users=userService.Justiactions(username()).getStandby1();
			List<hjBase> hjbasee=new ArrayList<hjBase>();
			logisticsService.selectListid(map.get("ids").toString().substring(1, map.get("ids").toString().length()-1)).forEach(x->{
				czBaseWithBLOBs base2=gson.fromJson(gson.toJson(map.get("cz")), czBaseWithBLOBs.class);
				base2.setClientReference(x.getfIds());
				base2.setExportScc("0");
				base2.setImportScc("0");
				/**
				 * 发件人信息
				 */
				base2.setToName("数独科技");
				base2.setToTel("18911520803");
				base2.setToAddress1("深圳龙岗坂田");
				base2.setToCity("广东省深圳市");
				base2.setToCountry("CN");
				base2.setToPostcode("518100");
				
				/**
				 * 发件人信息
				 */
				base2.setFromName(x.getfFirstName()+x.getfLastName());
				base2.setFormTel(x.getfTelephone());
				base2.setFormAddress1(x.getfAddress1());
				base2.setFormAddress2(x.getfAddress2());
				base2.setFormAddress3(x.getfAddress3());
				base2.setFormCity(x.getfProvince()+x.getfCity());
				base2.setFormCountry(x.getfCountry());
				base2.setFormEmail(x.getfEmail());
				base2.setFormPostcode(x.getfPostCode());
				base2.setFormState(x.getfProvince());
				base2.setFormStateCode(x.getfProvince());
				base2.setWeight(Double.valueOf(base2.getWeight())/1000+"");
				/**
				 * 申报信息
				 */
				base2.setSku("B07"+logisticsChange.randomNum(7).toUpperCase());
				//随机3-11
				String prices=String.valueOf(sj());
				base2.setUnitValue(prices);
				base2.setMaterial("无纺布");
				base2.setSalePrice(prices);
				/**
				 * 需要信息
				 */
				base2.setFsr2(x.getfIds());
				//默认
				base2.setFsr3("1");
				base2.setFsr5(users);
				/**
				 * 需要清空默认的订单
				 */
				hjBase hj=new hjBase();
				hj.setHjShipperhawbcode(x.getfIds());
				hjbasee.add(hj);
				czlist.add(base2);
			});

			hjServices.defaultHj0Plus(hjbasee);
			logisticsService.default0Plus(hjbasee);
			yida.defaultHj0Plus(hjbasee);
			czservice.defaultHj0Plus(hjbasee);
			numbers= czservice.addOrder(czlist);
		}else if(map.get("type").equals("2")) {
			//修改
			logger.info(gson.toJson(map.get("cz")));
			czBaseWithBLOBs base2=gson.fromJson(gson.toJson(map.get("cz")), czBaseWithBLOBs.class);
			base2.setWeight(Double.valueOf(base2.getWeight())/1000+"");
			logger.info(base2.getFsr1());
			numbers= czservice.updateOrder(base2);
		}
		return numbers;
	}
	/**
	 * Logistics/cz/post
	 */
	@RequestMapping(value = "/Logistics/cz/post",method=RequestMethod.POST)
	@ResponseBody
	public List<Object> czpost(@RequestBody List<String> list) throws Exception{
		List<Object>liste=new ArrayList<Object>();
		Map<String,Object>map1=new HashMap<String,Object>();
		//创志默认token
		map1.put("access_token", "5e82a8df3aff972da61d29225e82a8df2a7736220");
		czservice.selectIDList(logisticsService.selectIDList(list)).forEach(base2->{
			/**
			 * 第一级
			 */
			Map<String,Object>map=new HashMap<String,Object>();
			/**
			 * 第二级
			 */
			Map<String,Object>map2=new HashMap<String,Object>();
			map2.put("service", base2.getService());
			map2.put("client_reference", base2.getClientReference());
			map2.put("parcel_count", base2.getParcelCount());
			map2.put("export_scc", base2.getExportScc());
			map2.put("import_scc", base2.getImportScc());

			/**
			 * 发件人from_address
			 */
			/*Map<String,Object>map3=new HashMap<String,Object>();
			map3.put("name", base2.getToName());
			map3.put("tel", base2.getToTel());
			map3.put("address_1", base2.getToAddress1());
			map3.put("city", base2.getToCity());
			map3.put("country", base2.getToCountry());
			map3.put("postcode", base2.getToPostcode());
*/
			/**
			 * 收件人to_address
			 */
			Map<String,Object>map4=new HashMap<String,Object>();
			map4.put("name", base2.getFromName());
			map4.put("tel", base2.getFormTel());
			map4.put("address_1", base2.getFormAddress1());
			map4.put("address_2", base2.getFormAddress2());
			map4.put("address_3", base2.getFormAddress3());
			map4.put("city", base2.getFormCity());
			map4.put("country", base2.getFormCountry());
			map4.put("postcode", base2.getFormPostcode());
			map4.put("email", base2.getFormEmail());
			map4.put("state", base2.getFormState());
			map4.put("state_code", base2.getFormStateCode());
			
			/**
			 * parcels箱子
			 */
			List<Object>listcz=new ArrayList<Object>();
			Map<String,Object>map5=new HashMap<String,Object>();
			//俄罗斯套娃
			List<Object>listcz2=new ArrayList<Object>();
			Map<String,Object>map6=new HashMap<String,Object>();
			map6.put("sku", base2.getSku());
			map6.put("name_zh", base2.getNameZh());
			map6.put("name_en", base2.getNameEn());
			map6.put("unit_value", base2.getUnitValue());
			map6.put("qty", base2.getQty());
			map6.put("material", base2.getMaterial());
			map6.put("sale_price", base2.getSalePrice());
			map6.put("weight", base2.getWeight());
			map6.put("size", base2.getLength()+"*"+base2.getWidth()+"*"+base2.getHeight());
			map6.put("duty_rate", "0");
			map6.put("is_battery", base2.getIsBattery());
			
			/**
			 * 根节点
			 */

			listcz2.add(map6);
			map5.put("declarations", listcz2);
			map5.put("number", base2.getNumber());
			map5.put("client_weight", base2.getWeight());
			map5.put("client_length", base2.getLength());
			map5.put("client_height", base2.getHeight());
			map5.put("client_width", base2.getWidth());
			listcz.add(map5);
			map2.put("parcels", listcz);
			//map2.put("from_address", map3);
			map2.put("to_address", map4);
			map.put("validation", map1);
			map.put("shipment", map2);
			map.put("remark", base2.getRemark());
			Gson gson=new Gson();
			try {
				logger.info(gson.toJson(map));
				Map<String,Object>result=new HashMap<String,Object>();
				result.put("data", czpostOrder(map,"create"));
				result.put("fid", base2.getFsr2());
				result.put("map", map);
				liste.add(result);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error("发送超时",e);
				e.printStackTrace();
			}
		});
		
		return liste;
	}
	/**
	 * 添加创志跟踪号
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年4月1日 下午7:20:29   
	* @version
	 */
	@RequestMapping(value = "/Logistics/cz/tarack",method=RequestMethod.POST)
	@ResponseBody
    public int tarack(@RequestBody czBase base) throws Exception{
		//更新跟踪号
		logger.info(base);
		logisticsService.defaulData(base.getFsr4(),username(),base.getFsr2(),base.getService(),"");
		return czservice.updateCz(base);
	}
	/**
	 * 宝通达
	 * 
	 */
	@RequestMapping(value = "/Logistics/btd/add",method=RequestMethod.POST)
	@ResponseBody
    public String btdadd() throws Exception{
		StringBuilder sb = new StringBuilder();  
	    sb.append("<s:Envelope xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" + 
	    		"    <s:Body>\n" + 
	    		"        <GetLogisticsWay xmlns=\"http://tempuri.org/\"/>\n" + 
	    		"    </s:Body>\n" + 
	    		"</s:Envelope>");
	    String doposte = null;
	    Gson gson=new Gson();
		try {
			doposte=HttpUtilss.doPost2("http://open.btdair.com:8099/LogisticsService.svc?wsdl",sb.toString(),EnumContentType.XML);
			return gson.toJson(doposte);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("宝通达失败",e);
		}
		return doposte;
	}
}