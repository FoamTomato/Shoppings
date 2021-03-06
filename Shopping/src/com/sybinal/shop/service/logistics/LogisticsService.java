package com.sybinal.shop.service.logistics;

import java.util.List;
import java.util.Map;

import com.sybinal.shop.common.AjaxResult;
import com.sybinal.shop.model.FLogistics;
import com.sybinal.shop.model.Xlogistics;
import com.sybinal.shop.model.countryCode;
import com.sybinal.shop.model.hjBase;
import com.sybinal.shop.model.logistictoid;


public interface LogisticsService {
	/*
	 * 查询所有列表
	 */
	List<FLogistics> checkTheOrder(Map<String, String> map);
	/*
	 * 根据id查询列表
	 */
	FLogistics selectOrderByIds(FLogistics fLogistics);
	/*
	 * 修改基本信息
	 */
	int updateBasicInformation(FLogistics fLogistics);
	/*
	 * 添加运单
	 */
	int addLogisticsQuantity(Xlogistics xlogistics);
	/*
	 * 查询运单的信息
	 */
	List<Xlogistics> selLogisticsQuantity(Xlogistics xlogistics);
	/*
	 * 查询运单的信息
	 */
	List<Xlogistics>  selectSA(Integer xlogistics, String string);
	/*
	 * 删除运单
	 */
	int delLogisticsQuantity(Xlogistics xlogistics);
	/*
	 * 查询单独运单
	 */
	Xlogistics selsLogisticsQuantity(Xlogistics xlogistics);
	/*
	 * 修改运单
	 */
	int editLogisticsQuantity(Xlogistics xlogistics);
	/*
	 * 根据条件查询订单
	 */
	List<FLogistics> selStatusOrder(FLogistics fLogistics, String uks);
	/*
	 * 改变状态
	 */
	int update(FLogistics fLogistics);
	/*
	 * 默认状态
	 */
	int default0(String hjShipperhawbcode);
	/*
	 * 默认状态
	 */
	int default1(Integer valueOf);
	/*
	 * 查找fid
	 */
	List<Object> getfids(List<String> map);
	/*
	 * 已发件状态
	 */
	int postLogistics(String id, String string);
	/*
	 * 添加标签
	 */
	int insertLableKey(String id, String lab,String num1,String id2,String method,String username);//, String num2
	/*
	 * 根据id查询所有的订单
	 */
	FLogistics selectByPrimaryKey2(FLogistics fLogistics);
	/*
	 * 查找fid
	 */
	FLogistics selHJLogistics2(String string);
	/*
	 * 修改集拼编号
	 */
	int updatajp(String jpResult, String string,String nos,String username);
	/*
	 * 批量删除
	 */
	int batdel(List<String> map);
	//添加重量
	void addWeight(String hjInvoiceweight, String string, String string2,String username);
	//添加跟踪号
	int updataCn(String string, String string2);
	/*
	 * list添加订单号和物流方式
	 */
	AjaxResult LogisticToId(List<logistictoid> list);
	/**
	 * 更新跟踪号
	 * @param valueOf
	 * @return
	 */
	int defaultsLO(String valueOf,String username,String username2);
	/**
	 * 修改集拼状态
	 * @param states
	 * @param string 
	 * @return
	 */
	int jps(String states, String string);
	
	/**
	 * 根据亚马逊编号查找
	 * @param poststream
	 * @return
	 */
	List<FLogistics> stream(String poststream);
	/**
	 * 根据邮箱查找
	 * @param replace
	 * @return
	 */
	List<FLogistics> streamEmail(String replace);
	/**
	 * 根据系统单号查询
	 * @param parseInt
	 * @return List<hjBase>
	 */
	List<hjBase> logticslist(int parseInt);
	/**
	*    
	* 项目名称：Shopping   
	* 类描述：   更新义达的跟踪号
	* 创建人：PC1   
	* 创建时间：2020年3月6日 下午5:56:17   
	* @version
	 * @param integer 
	 * @param string 
	 */
	void defaulData(String hjStandy1, String username, String integer, String string, String string2);
	/**
	 * 批量查询订单
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年3月9日 下午4:05:52   
	* @version
	 */
	List<FLogistics> selectListid(String stringss);
	/**
	 * 获取编号
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年3月9日 下午5:46:37   
	* @version
	 */
	List<String> selectIDList(List<String> list);
	/**
	 * 查询详情（单个）
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年3月12日 下午2:21:00   
	* @version
	 */
	FLogistics selectOnes(FLogistics fLogistics);
	/**
	 * 根据id查找产品信息
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年3月16日 上午10:48:07   
	* @version
	 */
	FLogistics getLogisticParticulars(FLogistics id);
	/**
	 * 批量修改默认
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年3月18日 下午5:26:53   
	* @version
	 */
	int default0Plus(List<hjBase> hjList);
	/**
	 * 批量添加重量
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年3月18日 下午5:33:30   
	* @version
	 */
	int addWeightPlus(List<hjBase> hjList, String standby1);
	/**
	 * 批量添加标签
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年3月18日 下午5:33:30   
	* @version
	 */
	int insertLableKeyList(List<Map<String,Object>> list,String username);
	/**
	 * 批量添加订单
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年3月20日 下午3:32:47   
	* @version
	 */
	int updataCnList(List<Map<String, Object>> map);
	/**
	 * 批量修改默认
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年3月21日 上午10:53:45   
	* @version
	 */
	int postLogisticsList(List<String> hjBases, String standby1);
	/**
	 * 获取所有的国家
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年3月21日 上午11:23:10   
	* @version
	 */
	List<countryCode> countryCodes() ;

}
