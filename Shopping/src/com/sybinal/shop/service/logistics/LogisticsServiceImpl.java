package com.sybinal.shop.service.logistics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sybinal.shop.common.AjaxResult;
import com.sybinal.shop.controller.admin.FLogisticsController;
import com.sybinal.shop.mapper.FLogisticsMapper;
import com.sybinal.shop.mapper.UserMapper;
import com.sybinal.shop.mapper.XlogisticsMapper;
import com.sybinal.shop.mapper.countryCodeMapper;
import com.sybinal.shop.mapper.hjBaseMapper;
import com.sybinal.shop.mapper.logistictoidMapper;
import com.sybinal.shop.model.FLogistics;
import com.sybinal.shop.model.Xlogistics;
import com.sybinal.shop.model.countryCode;
import com.sybinal.shop.model.hjBase;
import com.sybinal.shop.model.logistictoid;

@Service
public class LogisticsServiceImpl implements LogisticsService {
	@Autowired
	FLogisticsMapper LogisticsMapper;
	
	@Autowired
	XlogisticsMapper xLogisticsMapper;
	
	@Autowired
	hjBaseMapper hjBasemapper;
	
	@Autowired
	logistictoidMapper logisto;

	@Autowired
	UserMapper userService;
	
	@Autowired
	countryCodeMapper countryCodes;
	@Override
	public List<FLogistics> checkTheOrder(Map<String,String>map) {
		// TODO Auto-generated method stub

		String[] split=map.get("orderNum").split(",");
        List<String> strings = Arrays.asList(split);
		return LogisticsMapper.selectAll(map,strings); 
	}
	@Override
	public List<FLogistics> selectListid(String stringss) {
		// TODO Auto-generated method stub

		String[] split=stringss.split(",");
        List<String> strings = Arrays.asList(split);
		return LogisticsMapper.selectListid(strings); 
	}
	
	@Override
	public FLogistics selectOrderByIds(FLogistics fLogistics) {
		// TODO Auto-generated method stub
		return LogisticsMapper.selectByPrimaryKey(fLogistics);
	}
	/**
	 * 查询右侧未删除的
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年3月12日 下午2:20:47   
	* @version
	 */
	@Override
	public FLogistics selectOnes(FLogistics fLogistics) {
		// TODO Auto-generated method stub
		return LogisticsMapper.selectOnes(fLogistics);
	}
	@Override
	public FLogistics selectByPrimaryKey2(FLogistics fLogistics) {
		// TODO Auto-generated method stub
		return LogisticsMapper.selectByPrimaryKey2(fLogistics);
	}

	
	@Override
	public int updateBasicInformation(FLogistics fLogistics) {
		// TODO Auto-generated method stub
		return LogisticsMapper.updateByPrimaryKeySelective(fLogistics);
	}

	@Override
	public int addLogisticsQuantity(Xlogistics xlogistics) {
		// TODO Auto-generated method stub
		return xLogisticsMapper.insertSelective(xlogistics);
	}

	@Override
	public List<Xlogistics> selLogisticsQuantity(Xlogistics xlogistics) {
		// TODO Auto-generated method stub
		return xLogisticsMapper.selectByPrimaryKey(xlogistics.getxFIds());
	}

	@Override
	public List<Xlogistics> selectSA(Integer xlogistics,String s) {
		// TODO Auto-generated method stub
		return xLogisticsMapper.selectSA(xlogistics,s);
	}

	@Override
	public int delLogisticsQuantity(Xlogistics xlogistics) {
		// TODO Auto-generated method stub
		return xLogisticsMapper.deleteByPrimaryKey(xlogistics.getId());
	}

	@Override
	public Xlogistics selsLogisticsQuantity(Xlogistics xlogistics) {
		// TODO Auto-generated method stub
		return xLogisticsMapper.selLogisticsQuantity(xlogistics.getId());
	}

	@Override
	public int editLogisticsQuantity(Xlogistics xlogistics) {
		// TODO Auto-generated method stub
		return xLogisticsMapper.updateByPrimaryKeySelective(xlogistics);
	}

	@Override
	public List<FLogistics> selStatusOrder(FLogistics fLogistics,String uks) {
		// TODO Auto-generated method stub
		List<String> strings=new ArrayList<String>();
		if(fLogistics.getfIds()!=""){
		String[] split=fLogistics.getfIds().split(",");
        	strings = Arrays.asList(split);
		}
		return LogisticsMapper.selStatusOrder(fLogistics,strings,uks);
	}

	@Override
	public int update(FLogistics fLogistics) {
		// TODO Auto-generated method stub
		return LogisticsMapper.updateByPrimaryKeySelective(fLogistics);
	}
	@Override
	public int default0(String hjShipperhawbcode) {
		// TODO Auto-generated method stub
		return xLogisticsMapper.default0(hjShipperhawbcode);
	}
	/**
	 * 批量改默认
	 */
	@Override
	public int default0Plus(List<hjBase> hjList) {
		// TODO Auto-generated method stub
		return xLogisticsMapper.default0Plus(hjList);
	}
	@Override
	public int default1(Integer valueOf) {
		// TODO Auto-generated method stub
		return xLogisticsMapper.default1(valueOf);
	}

	@Override
	public List<Object> getfids(List<String> map) {
		// TODO Auto-generated method stub
		List<Object> result=new ArrayList<Object>();
		/**
		 * 1.查询账号名称
		 * 2.批量查询订单
		 * 3.去除空值
		 * 4.传值
		 * 5.返回结果
		 */
		//1
		String username = userService.Justiactions(FLogisticsController.username()).getStandby1();
		//2
		hjBasemapper.BaseList(map,username).forEach(x->{
			if(x!=null) {
				//3
				TreeMap<String, Object> params = new TreeMap<String, Object>();
				TreeMap<String, Object> params1 = new TreeMap<String, Object>();
				TreeMap<String, Object> params2 = new TreeMap<String, Object>();
				params.put("referenceNo", x.getHjReferenceno());
				params.put("shipperHawbcode", x.getHjShipperhawbcode());
				params.put("shippingMethod", x.getHjShippingmethod());
				params.put("shippingTypeCode", x.getHjStandy6());
				params.put("countryCode", x.getHjCountrycode());
				params.put("shipZip", x.getHjShipzip());
				params.put("orderWeight", (Double.parseDouble(x.getHjOrderweight())/1000));
				params.put("orderPieces", x.getHjOrderpieces());
				params.put("totalprice", x.getHjTotalprice());
				params.put("mailCargoType", x.getHjMailcargotype());
				params.put("length", x.getHjLength());
				params.put("width", x.getHjWidth());
				params.put("height", x.getHjHeight());
				params.put("isReturn", x.getHjIsreturn());
				params.put("isBattery", x.getHjIsbattery());
				params.put("consignee", params1);
				params1.put("consigneeCompany", x.getHjConsigneecompany());
				params1.put("consigneeProvince", x.getHjConsigneeprovince());
				params1.put("consigneeCity", x.getHjConsigneecity());
				params1.put("consigneeStreet", x.getHjConsigneestreet());
				params1.put("consigneeStreet2", x.getHjConsigneestreet2());
				params1.put("consigneeStreet3", x.getHjConsigneestreet3());
				params1.put("consigneePostcode", x.getHjConsigneepostcode());
				params1.put("consigneeName", x.getHjConsigneename());
				params1.put("consigneeTelephone", x.getHjConsigneetelephone());
				params1.put("consigneeMobile", x.getHjConsigneemobile());
				params1.put("consigneeEmail", x.getHjConsigneeemail());
				params1.put("consigneeCountryEname", x.getHjConsigneecountryename());
				params1.put("consigneeCountryCname", x.getHjConsigneecountrycname());
			
				Object[] intArr = new Object[1];
				intArr[0] = params2;
				params.put("itemArr",intArr);
				params2.put("invoiceEnname", x.getHjInvoiceenname());
				params2.put("invoiceCnname", x.getHjInvoicecnname());
				params2.put("invoiceWeight", (Double.parseDouble(x.getHjInvoiceweight())/1000));
				params2.put("invoiceQuantity", x.getHjInvoicequantity());
				params2.put("unitCode", x.getHjUnitcode());
				params2.put("invoiceUnitcharge", x.getHjInvoiceunitcharge());
				params2.put("invoiceCurrencycode", x.getHjInvoicecurrencycode());
				params2.put("hsCode", x.getHjHscode());
				params2.put("sku", x.getHjSku());
				params2.put("isContainsBattery", x.getHjIscontainsbattery());
				params2.put("isAneroidMarkup", x.getHjIsaneroidmarkup());
				params2.put("isOnlyBattery", x.getHjIsonlybattery());
				params2.put("isBreakable", x.getHjIsbreakable());
				params2.put("isCharged", x.getHjIscharged());
				params2.put("salePrice", x.getHjSaleprice());
				params2.put("saleCurrency", x.getHjSalecurrency());
				params2.put("categoryName", x.getHjCategoryname());
				params2.put("parentEnName", x.getHjParentenname());
				result.add(params);
			}
		});
		return result;
	}

	@Override
	public int postLogistics(String id,String username) {
		return LogisticsMapper.postLogistics(id,username);
	}

	@Override
	public int insertLableKey(String id, String lab,String num1,String id2,String method,String username) {//, String num2
		// TODO Auto-generated method stub
		LogisticsMapper.insertLableKey(id2,lab,num1,method,username);
		return hjBasemapper.insertLableKey(id,lab,num1,username);//,num2
	}
	/**
	 * 批量添加标签
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年3月18日 下午5:33:30   
	* @version
	 */
	@Override
	public int insertLableKeyList(List<Map<String,Object>> list,String username) {
		// TODO Auto-generated method stub
		LogisticsMapper.insertLableKeyList(list,username);
		return hjBasemapper.insertLableKeyList(list,username);
	}
	@Override
	public FLogistics selHJLogistics2(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updatajp(String jpResult,String ordlis,String nos,String username) {
		// TODO Auto-generated method stub
		return LogisticsMapper.updatajp(jpResult,ordlis,nos,username);
	}

	
	@Override
	public int batdel(List<String> map) {
		// TODO Auto-generated method stub
		return LogisticsMapper.batdel(map);
	}

	@Override
	public void addWeight(String hjInvoiceweight,String string,String nos,String username) {
		// TODO Auto-generated method stub
		LogisticsMapper.addWeight(hjInvoiceweight,string,nos,username);
	}
	/**
	 * 批量添加重量
	 */
	@Override
	public int addWeightPlus(List<hjBase> hjList, String standby1) {
		// TODO Auto-generated method stub
		return LogisticsMapper.addWeightPlus(hjList,standby1);
	}
	@Override
	public int updataCn(String string, String string2) {
		// TODO Auto-generated method stub
		return LogisticsMapper.updataCn(string,string2);
	}
 
	@Override
	public AjaxResult LogisticToId(List<logistictoid> list) {
		// TODO Auto-generated method stub
		int c = logisto.BatchAll(list);
		if (c != 0) {
			return new AjaxResult(true, "订单批量添加成功!共添加了"+c+"条订单物流方式");
		}

		return new AjaxResult(false, "订单批量添加失败!");
	}

	@Override
	public int defaultsLO(String valueOf,String username,String username2) {
		// TODO Auto-generated method stub
		return LogisticsMapper.defaultsLO(valueOf,username,username2);
	}
	/**
	 * 修改集拼
	 */
	@Override
	public int jps(String states,String ids) {
		// TODO Auto-generated method stub
		hjBasemapper.jps(states,ids);
		return LogisticsMapper.jps(states,ids);
	}
	/**
	 * 根据亚马逊编号查找
	 */
	@Override
	public List<FLogistics> stream(String poststream) {
		// TODO Auto-generated method stub
		return LogisticsMapper.stream(poststream);
	}
	/**
	 * 根据邮箱查找
	 */
	@Override
	public List<FLogistics> streamEmail(String replace) {
		// TODO Auto-generated method stub
		return LogisticsMapper.streamEmail(replace);
	}
	/**
	 * 根据系统单号查询
	 * @param parseInt
	 * @return List<hjBase>
	 */
	@Override
	public List<hjBase> logticslist(int parseInt) {
		// TODO Auto-generated method stub
		return hjBasemapper.logticslist(parseInt);
	}
	/**
	 * 更新义达
	 */
	@Override
	public void defaulData(String hjStandy1, String username,String integer,String standy7, String string8) {
		// TODO Auto-generated method stub
		LogisticsMapper.defaulData(hjStandy1,username,integer,standy7,string8);
	}
	@Override
	public List<String> selectIDList(List<String> list) {
		// TODO Auto-generated method stub
		return LogisticsMapper.selectIDList(list);
	}
	@Override
	public FLogistics getLogisticParticulars(FLogistics logs) {
		// TODO Auto-generated method stub
		return LogisticsMapper.selectOnes(logs);
	}
	/**
	 * 批量添加订单
	 */
	@Override
	public int updataCnList(List<Map<String, Object>> map) {
	// TODO Auto-generated method stub
	return LogisticsMapper.updataCnList(map);
	}
	/**
	 * 批量改变状态
	 */
	@Override
	public int postLogisticsList(List<String> hjBases, String standby1) {
		// TODO Auto-generated method stub
		return LogisticsMapper.postLogisticsList(hjBases,standby1);
	}
	/**
	 * 获取国家二字码
	 * 
	 */
	@Override
	public List<countryCode> countryCodes() {
		// TODO Auto-generated method stub
		return countryCodes.selectAll();
	}
	
}
