package com.sybinal.shop.service.excel;

import java.beans.IntrospectionException;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lazada.lazop.api.LazopClient;
import com.lazada.lazop.api.LazopRequest;
import com.lazada.lazop.api.LazopResponse;
import com.lazada.lazop.util.ApiException;
import com.sybinal.shop.common.Calculations;
import com.sybinal.shop.common.ExcelUtil;
import com.sybinal.shop.common.Task;
import com.sybinal.shop.common.logisticsChange;
import com.sybinal.shop.controller.admin.FLogisticsController;
import com.sybinal.shop.controller.admin.LazadaController;
import com.sybinal.shop.controller.admin.unit.SnowIdUtils;
import com.sybinal.shop.mapper.DTfzyingsMapper;
import com.sybinal.shop.mapper.DTstockMapper;
import com.sybinal.shop.mapper.DTstocksMapper;
import com.sybinal.shop.mapper.EUBMapper;
import com.sybinal.shop.mapper.EubInvoiceMapper;
import com.sybinal.shop.mapper.FLogisticsMapper;
import com.sybinal.shop.mapper.GlogisticsMapper;
import com.sybinal.shop.mapper.HmLogisticsMapper;
import com.sybinal.shop.mapper.OnlineDownloadMapper;
import com.sybinal.shop.mapper.UserMapper;
import com.sybinal.shop.mapper.countryCodeMapper;
import com.sybinal.shop.mapper.freightPreviewMapper;
import com.sybinal.shop.mapper.hjBaseMapper;
import com.sybinal.shop.mapper.jpOrderMapper;
import com.sybinal.shop.mapper.logOutOfMapper;
import com.sybinal.shop.mapper.logistictoidMapper;
import com.sybinal.shop.mapper.sfGoodsMapper;
import com.sybinal.shop.mapper.sfInfoMapper;
import com.sybinal.shop.mapper.ydBaseMapper;
import com.sybinal.shop.model.DTfzyingsWithBLOBs;
import com.sybinal.shop.model.DTstock;
import com.sybinal.shop.model.EUB;
import com.sybinal.shop.model.EubInvoice;
import com.sybinal.shop.model.ExcelBean;
import com.sybinal.shop.model.Exportstock;
import com.sybinal.shop.model.FLogistics;
import com.sybinal.shop.model.Glogistics;
import com.sybinal.shop.model.HmLogistics;
import com.sybinal.shop.model.LazadaCate;
import com.sybinal.shop.model.freightPreview;
import com.sybinal.shop.model.hjBase;
import com.sybinal.shop.model.jpOrder;
import com.sybinal.shop.model.logOutOf;
import com.sybinal.shop.model.logistictoid;
import com.sybinal.shop.model.sfGoods;
import com.sybinal.shop.model.sfInfo;
import com.sybinal.shop.model.ydBase;


@Service
public class ExcelServiceImpl implements ExcelService{
	
	@Autowired
	FLogisticsMapper fLogisticsMapper;
	
	@Autowired
	GlogisticsMapper glogisticsMapper;
	
	@Autowired
	hjBaseMapper hjbaseMapper;

	@Autowired
	EUBMapper eubMapper;
	

	@Autowired
	EubInvoiceMapper eubInvoiceMapper;
	
	@Autowired
	jpOrderMapper jpMapper;
	
	@Autowired
	logistictoidMapper loMapper;

	@Autowired
	UserMapper userService;
	
	@Autowired
	freightPreviewMapper freightPrevieMapper;

	@Autowired
	DTfzyingsMapper dtfz;
	
	@Autowired
	DTstockMapper dtstock;
	
	@Autowired
	DTstocksMapper dtstocks;
	
	@Autowired
	OnlineDownloadMapper online;

	@Autowired
	logOutOfMapper logout;
	
	@Autowired
	ydBaseMapper ydBase;
	
	@Autowired
	sfInfoMapper sfBase;
	
	@Autowired
	sfGoodsMapper sfGoodsBase;
	
	@Autowired
	HmLogisticsMapper hmLogistics;
	
	@Autowired
	countryCodeMapper codes;
	private static Logger logger = Logger.getLogger(ExcelServiceImpl.class);
	/*
	 * 导入
	 * (non-Javadoc)
	 * @see com.sybinal.shop.service.excel.ExcelService#importExcelInfo(java.io.InputStream, org.springframework.web.multipart.MultipartFile)
	 */
	@Override
    public int importExcelInfo(InputStream in, MultipartFile file) {
        try{
            List<List<Object>> listob = ExcelUtil.getBankListByExcel(in,file.getOriginalFilename());
            List<FLogistics> fLogistics = new ArrayList<FLogistics>();
            //遍历listob数据，把数据放到List中listob.size()
            for (int i = 0; i < listob.size(); i++) { 
            	if(listob.get(i).toString().equals("[]")){
            		continue;
            	}
                FLogistics FLogisticsInfo =new FLogistics();
                /*
                 * id----------------------------------------------------------------------
                 * 1.获取订单*
                 * 2.进行比值判断是否为空*
                 * 3.进行s12的赋值*
                 * 4.前端添加运单进行物流调用*
                 *5
                 */
                //Number num = Float.parseFloat(String.valueOf(listob.get(i).get(0)));

                BigDecimal db = new BigDecimal(listob.get(i).get(0)+"");
                System.err.println(db.toPlainString());
                FLogisticsInfo.setfIds(db.toPlainString());
                
                /*
                                          * 旧单号
                 */
                //FLogisticsInfo.setfOldOrder(String.valueOf(listob.get(i).get(1)));
                /*
				 * 旧采购
				*/
				//FLogisticsInfo.setfOldPurchase(String.valueOf(listob.get(i).get(2)));
                /*
				* ClientOrderCode
				*/
				FLogisticsInfo.setfClientOrderCode(String.valueOf(listob.get(i).get(1)));
                /*
				* 店铺
				*/
				FLogisticsInfo.setfStore(String.valueOf(listob.get(i).get(4)));
                /*
				* PayTime
				*/
				DateFormat format = new SimpleDateFormat("yyyy/MM/dd"); 
				//FLogisticsInfo.setfPayTime(format.parse(String.valueOf(listob.get(i).get(6))));
				if(!"".equals(String.valueOf(listob.get(i).get(2)))&&String.valueOf(listob.get(i).get(2))!=null) {
					FLogisticsInfo.setfPayTime(format.parse(String.valueOf(listob.get(i).get(2))));
				}
                /*
				* Currency
				*/
				FLogisticsInfo.setfCurrency(String.valueOf(listob.get(i).get(5)));
                /*
				* TotalPrice
				*/
				FLogisticsInfo.setfTotalPrice(String.valueOf(listob.get(i).get(6)));
                /*
				* 中文简称
				*/
				FLogisticsInfo.setfChinaShort(String.valueOf(listob.get(i).get(15)));
                /*
				* FirstName
				*/
				FLogisticsInfo.setfFirstName(String.valueOf(listob.get(i).get(26)));
                /*
				* LastName
				*/
				FLogisticsInfo.setfLastName("");//String.valueOf(listob.get(i).get(24))
                /*
				* Country
				*/
				FLogisticsInfo.setfCountry(String.valueOf(listob.get(i).get(25)));
                /*
				* Province
				*/
				FLogisticsInfo.setfProvince(String.valueOf(listob.get(i).get(30)));
                /*
				* City
				*/
				FLogisticsInfo.setfCity(String.valueOf(listob.get(i).get(29)));
                /*
				* PostCode
				*/
				FLogisticsInfo.setfPostCode(String.valueOf(listob.get(i).get(31)));
                /*
				* Email
				*/
				FLogisticsInfo.setfEmail(String.valueOf(listob.get(i).get(27)));
                /*
				* Telephone
				*/
				FLogisticsInfo.setfTelephone(String.valueOf(listob.get(i).get(28)));
                /*
				* Address1
				*/
				FLogisticsInfo.setfAddress1(String.valueOf(listob.get(i).get(32)));
                /*
				* Address2
				*/
				FLogisticsInfo.setfAddress2("");//String.valueOf(listob.get(i).get(34))
                /*
				* Address3
				*/
				FLogisticsInfo.setfAddress3("");//String.valueOf(listob.get(i).get(35))
                /*
				* 物流
				*/
				FLogisticsInfo.setfLogistics(String.valueOf(listob.get(i).get(19)));
                /*
				* 渠道
				*/
				FLogisticsInfo.setfChannel(String.valueOf(listob.get(i).get(20)));
                /*
				* 运单号
				*/
				FLogisticsInfo.setfSheet(String.valueOf(listob.get(i).get(21)));
                /*
				* Fee
				*/
				FLogisticsInfo.setStandby1(String.valueOf(listob.get(i).get(7)));
                /*
				* Money
				*/
				FLogisticsInfo.setStandby2(String.valueOf(listob.get(i).get(6)));
                /*
				* Cost
				*/
				FLogisticsInfo.setStandby3(String.valueOf(listob.get(i).get(9)));
                /*
				* Profile
				*/
				FLogisticsInfo.setStandby4(String.valueOf(listob.get(i).get(10)));
                /*
				* 追踪号
				*/
				FLogisticsInfo.setStandby7(String.valueOf(listob.get(i).get(22)));
                /*
				* 备注
				*/
				FLogisticsInfo.setfRemark(String.valueOf(listob.get(i).get(24)));
                /*
				* sku
				*/
				FLogisticsInfo.setStandby8(String.valueOf(listob.get(i).get(14)));
				/*
				 * 导入的用户username
				 */

				String username=userService.Justiactions(FLogisticsController.username()).getStandby1();
				FLogisticsInfo.setStandby9(username);
                /*
                                           * 存入list列表
                 */
                /*
                 * 判断该订单号是否存在
                 * 
                 */
				logistictoid ids=loMapper.selectFids(FLogisticsInfo.getfIds());
                if(ids!=null) {
                 FLogisticsInfo.setStandby12(ids.getLogisticmethod());
                }
               fLogistics.add(FLogisticsInfo);
            }
            /*
                                 * 存入数据库
             */
           return fLogisticsMapper.insertAll(fLogistics);
        }catch (Exception e){
            e.printStackTrace();
        }
		return 0;
    }
	/*
	 * 导出
	 */
	@Override
    public XSSFWorkbook exportExcelInfo(String idList) {//String idList
        XSSFWorkbook xssfWorkbook=null;

        String username=userService.Justiactions(FLogisticsController.username()).getStandby1();
        List<logOutOf> logs=logout.selectAll();
        try {
            //根据ID查找数据
           String[] list = idList.split(",");
           List<String> strings = Arrays.asList(list);
            List<Glogistics> fLogistics = new ArrayList<Glogistics>();
           //for(int i=0;i<list.length;i++){
            	List<Glogistics> fLogisticsInfoList = glogisticsMapper.selectByPrimaryKey(strings,username);//Integer.valueOf(list[i])
            	fLogisticsMapper.updateStatues(strings);
            	for(Glogistics s:fLogisticsInfoList) {
            		if(s.getStandby12()!=null){
            		s.setStandby12(logisticsChange.pds(s.getStandby12(),logs));
                    fLogistics.add(s);
            		}
            	}
           //}
            List<ExcelBean> excel=new ArrayList<>();
            Map<Integer,List<ExcelBean>> map=new LinkedHashMap<>();
            //设置标题栏
            excel.add(new ExcelBean("ID","fids",0));
            excel.add(new ExcelBean("旧单号","foldorder",0));
            excel.add(new ExcelBean("旧采购","foldpurchase",0));
            excel.add(new ExcelBean("ClientOrderCode","fclientordercode",0));
            excel.add(new ExcelBean("店铺","fstore",0));
            excel.add(new ExcelBean("PayTime","fpaytime",0));
            excel.add(new ExcelBean("Currency","fcurrency",0));
            excel.add(new ExcelBean("TotalPrice","ftotalprice",0));
            excel.add(new ExcelBean("中文简称","fchinashort",0));
            excel.add(new ExcelBean("英文简称","fenglishshort",0));
            excel.add(new ExcelBean("FirstName","ffirstname",0));
            excel.add(new ExcelBean("LastName","flastname",0));
            excel.add(new ExcelBean("Country","fcountry",0));
            excel.add(new ExcelBean("Province","fprovince",0));
            excel.add(new ExcelBean("City","fcity",0));
            excel.add(new ExcelBean("PostCode","fpostcode",0));
            excel.add(new ExcelBean("Email","femail",0));
            excel.add(new ExcelBean("Telephone","ftelephone",0));
            excel.add(new ExcelBean("Address1","faddress1",0));
            excel.add(new ExcelBean("Address2","faddress2",0));
            excel.add(new ExcelBean("Address3","faddress3",0));
            excel.add(new ExcelBean("物流","flogistics",0));
            excel.add(new ExcelBean("渠道","fchannel",0));
            excel.add(new ExcelBean("运单号","fsheet",0));
            excel.add(new ExcelBean("物流状态","fstatue",0));
            excel.add(new ExcelBean("运费","ffreight",0));
            excel.add(new ExcelBean("海关","fcustoms",0));
            excel.add(new ExcelBean("重量G","fweight",0));
            excel.add(new ExcelBean("备注","fremark",0));
            excel.add(new ExcelBean("手续费Fee","standby1",0));
            excel.add(new ExcelBean("原价Money","standby2",0));
            excel.add(new ExcelBean("运费Cost","standby3",0));
            excel.add(new ExcelBean("利润Profile","standby4",0));
            excel.add(new ExcelBean("公司","standby5",0));
            excel.add(new ExcelBean("状态","standby6",0));
            excel.add(new ExcelBean("追踪号","standby7",0));
            excel.add(new ExcelBean("sku","standby8",0));
            excel.add(new ExcelBean("物流方式","standby12",0));
            excel.add(new ExcelBean("跟踪号","standby11",0));
            map.put(0, excel);
            Calendar cal=Calendar.getInstance();      
            int y=cal.get(Calendar.YEAR);      
            int m=cal.get(Calendar.MONTH);      
            int d=cal.get(Calendar.DATE);      
            int h=cal.get(Calendar.HOUR_OF_DAY);      
            int mi=cal.get(Calendar.MINUTE);      
            int s=cal.get(Calendar.SECOND);      
            String sheetName ="Foam_运单基本信息"+y+"年"+m+"月"+d+"日"+h+"时"+mi+"分"+s+"秒";
            xssfWorkbook = ExcelUtil.createExcelFile(Glogistics.class, fLogistics, map, sheetName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xssfWorkbook;
    }
	/*
	 * 导出集拼
	 */
	@Override
    public XSSFWorkbook exportExcelInfo2(String idList) {//String idList
        XSSFWorkbook xssfWorkbook=null;

        Date date=new Date();

        String username=userService.Justiactions(FLogisticsController.username()).getStandby1();
        SimpleDateFormat ft=new SimpleDateFormat("yyyy.MM.dd : hh:mm:ss a");
        try {
            //根据ID查找数据
           String[] list = idList.split(",");
           List<String> strings = Arrays.asList(list);
            List<Glogistics> fLogistics = new ArrayList<Glogistics>();
           //for(int i=0;i<list.length;i++){
            	List<Glogistics> fLogisticsInfoList = glogisticsMapper.selectByPrimaryKey(strings,username);//Integer.valueOf(list[i])
            	fLogisticsMapper.updateStatues(strings);
            	for(Glogistics s:fLogisticsInfoList) {
            		if(s.getStandby12()!=null){
            		s.setFcountry(logisticsChange.gj(s.getFcountry(),codes.selectAll()));
            		s.setFoldorder(ft.format(date));
                    fLogistics.add(s);
            		}
            	}
          // }
            List<ExcelBean> excel=new ArrayList<>();
            Map<Integer,List<ExcelBean>> map=new LinkedHashMap<>();
            //设置标题栏
            excel.add(new ExcelBean("物流渠道","standby12",0));
            excel.add(new ExcelBean("客户订单号","standby15",0));
            excel.add(new ExcelBean("物流跟踪号","standby11",0));
            excel.add(new ExcelBean("收货国家","fcountry",0));
            excel.add(new ExcelBean("集拼单号","standby14",0));
            excel.add(new ExcelBean("重量G","standby16",0));
            excel.add(new ExcelBean("发运时间","foldorder",0));
            map.put(0, excel);
            Calendar cal=Calendar.getInstance();      
            int y=cal.get(Calendar.YEAR);      
            int m=cal.get(Calendar.MONTH);      
            int d=cal.get(Calendar.DATE);      
            int h=cal.get(Calendar.HOUR_OF_DAY);      
            int mi=cal.get(Calendar.MINUTE);      
            int s=cal.get(Calendar.SECOND);      
            String sheetName ="Foam_集拼基本信息"+y+"年"+m+"月"+d+"日"+h+"时"+mi+"分"+s+"秒";
            xssfWorkbook = ExcelUtil.createExcelFile(Glogistics.class, fLogistics, map, sheetName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xssfWorkbook;
    }
	/*
	 * 导出中国邮政订单
	 */
	@Override
	public XSSFWorkbook exportExcelInfo3(String idList) {//String idList
        XSSFWorkbook xssfWorkbook=null;

        //DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd"); 
        hjBase hjlist=null;
        ydBase ydlist=null;
        sfInfo sflist=null;
        sfGoods goods=null;
        EUB eub=null;
        EubInvoice invoice=null;
        HmLogistics hm=null;
        SimpleDateFormat ft=new SimpleDateFormat("yyyy/MM/dd");

        String username=userService.Justiactions(FLogisticsController.username()).getStandby1();
        try {
            //根据ID查找数据
           String[] list = idList.split(",");
           List<String> strings = Arrays.asList(list);
            List<Glogistics> fLogistics = new ArrayList<Glogistics>();
           //for(int i=0;i<list.length;i++){
            	List<Glogistics> fLogisticsInfoList = glogisticsMapper.selectByPrimaryKey(strings,username);//Integer.valueOf(list[i])
            	
            	fLogisticsMapper.updateStatues(strings);
            	for(Glogistics s:fLogisticsInfoList) {
            		if(s.getFids()!=null) {
	            		// 环金导出列表
	            		hjlist=hjbaseMapper.selectByPrimaryKeyse(s.getFids(),username);
	            		// 义达导出列表
	            		ydlist=ydBase.findIds2(s.getFids());
	            		// 顺丰导出列表
	            		sflist=sfBase.selectOrders(s.getFids(),username);
	            		// eub导出列表
	            		eub=eubMapper.selectOrders(s.getFids(),username);
	            		// 黑猫导出列表
	            		hm=hmLogistics.detectOrder2(s.getFids(),username);
	            		// 环金的导出邮政
	            		if(hjlist!=null) {
		            		if(s.getStandby12()!=null){
		            			String c=s.getStandby12();
		            		s.setStandby12(logisticsChange.pd2(c));
			            		if(!"".equals(hjlist.getHjTotalprice())) {
			            		s.setFclientordercode(hjlist.getHjTotalprice());
			            		}
			            		if(!"".equals(hjlist.getHjInvoiceweight() )) {
			            		s.setStandby9(hjlist.getHjInvoiceweight());
			            		}
			            		s.setFsheet(c);
			            		s.setFoldorder("");
			            		s.setFfreight("");
			            		if(!"".equals(hjlist.getHjStandy1()) && !hjlist.getHjStandy1().equals(null)) {
			            		s.setUpdateTime(String.valueOf(ft.format(hjlist.getHjStandy1())));
			            		}
			            		fLogistics.add(s);
		            		}
	            		}
	            		
	            		// 义达的导出邮政
	            		if(ydlist!=null) {
		            		//if(s.getStandby12()!=null){
		            		s.setStandby12("义达");
			            		if(!"".equals(ydlist.getYdInvoiceUnitcharge())) {
			            		s.setFclientordercode(ydlist.getYdInvoiceUnitcharge());
			            		}
			            		if(!"".equals(ydlist.getYdOrderWeight() )) {
			            			Double d=Double.parseDouble(ydlist.getYdOrderWeight())*1000;
			            		s.setStandby9(String.valueOf(d.intValue()));
			            		}
			            		if(!"".equals(ydlist.getYdShippingMethod())) {
				            		s.setFsheet(ydlist.getYdShippingMethod());
			            		}
			            		if(!"".equals(ydlist.getYdStandy3())) {
			            				s.setFids(ydlist.getYdStandy3());
			            		}
			            		s.setFoldorder("");
			            		s.setFfreight("");
			            		if(!"".equals(ydlist.getYdShippingMethodNo())) {
			            			s.setStandby11(ydlist.getYdShippingMethodNo());
			            		}
			            		if(!"".equals(ydlist.getYdStandy5()) && !ydlist.getYdStandy5().equals(null)) {
			            		s.setUpdateTime(String.valueOf(ft.format(ydlist.getYdStandy5())));
			            		}
			            		fLogistics.add(s);
		            		//}
	            		}
	            		// 顺丰的导出邮政
	            		if(sflist!=null) {
	                		goods=sfGoodsBase.selectByPrimaryKeys(sflist.getPlatformOrderId());
	                		if(goods!=null) {
		            		//if(s.getStandby12()!=null){
		            		s.setStandby12("顺丰");
			            		if(!"".equals(goods.getAmount())) {
			            		s.setFclientordercode(goods.getAmount());
			            		}
			            		if(goods.getWeight() != null) {
			            			Double d=goods.getWeight()*1000;
			            		s.setStandby9(String.valueOf(d.intValue()));
			            		}
			            		if(!"".equals(sflist.getExpressType())) {
				            		s.setFsheet(sflist.getExpressType());
			            		}
				            		s.setFids(sflist.getOrderid());
			            		s.setFoldorder("");
			            		s.setFfreight("");
			            		if(!"".equals(sflist.getMailno())) {
			            			s.setStandby11(sflist.getMailno());
			            		}
			            		if(!"".equals(sflist.getSendstarttime()) && !sflist.getSendstarttime().equals(null)) {
			            		s.setUpdateTime(String.valueOf(ft.format(sflist.getSendstarttime())));
			            		}
			            		fLogistics.add(s);
		            		}
	            		}
	            		
	            		// EUB的导出邮政
	            		if(eub!=null) {
	            			invoice=eubInvoiceMapper.selectOrder(eub.getClno());
	                		if(invoice!=null) {
	                			s.setStandby12("广州EUB");
			            		if(invoice.getPrice() != null) {
			            			s.setFclientordercode(invoice.getPrice().toString());
			            		}
			            		if(invoice.getnWeig() != null) {
			            			Double d=invoice.getnWeig()*1000;
			            			s.setStandby9(String.valueOf(d.intValue()));
			            		}
			            		if(!"".equals(eub.getHubInCode())) {
				            		s.setFsheet(eub.getHubInCode());
			            		}
				            		s.setFids(eub.getfId());
			            		s.setFoldorder("");
			            		s.setFfreight("");
			            		if(!"".equals(eub.getStandby4())) {
			            			s.setStandby11(eub.getStandby4());
			            		}
			            		if(!"".equals(eub.getStandby7()) && !eub.getStandby7().equals(null)) {
			            			s.setUpdateTime(eub.getStandby7());
			            		}
			            		fLogistics.add(s);
		            		}
	            		}
	            		
	            		/*黑猫的导出邮政*/
	            		if(hm!=null) {
	                			s.setStandby12("黑猫物流");
			            		if(hm.getInvoice().getInvoiceUnitcharge() != null) {
			            			s.setFclientordercode(hm.getInvoice().getInvoiceUnitcharge());
			            		}
			            		if(hm.getInvoice().getNetWeight() != null) {
			            			Double d=hm.getInvoice().getNetWeight()*1000;
			            			s.setStandby9(String.valueOf(d.intValue()));
			            		}
			            		if(!"".equals(hm.getShippingMethod())) {
				            		s.setFsheet(hm.getShippingMethod());
			            		}
				            	s.setFids(hm.getBuyerId());
			            		s.setFoldorder(hm.getBackup5());
			            		s.setFfreight("");
			            		if(!"".equals(hm.getBackup6())) {
			            			s.setStandby11(hm.getBackup6());
			            		}
			            		if(!"".equals(hm.getUpdateTime()) && !hm.getUpdateTime().equals(null)) {
			            			s.setUpdateTime(String.valueOf(ft.format(hm.getUpdateTime())));
			            		}
			            		fLogistics.add(s);
	            		}
            		}
            	}
           //}
            List<ExcelBean> excel=new ArrayList<>();
            Map<Integer,List<ExcelBean>> map=new LinkedHashMap<>();
            //设置标题栏
            excel.add(new ExcelBean("订单号","fids",0));
            excel.add(new ExcelBean("发货日期","updateTime",0));
            excel.add(new ExcelBean("承运商","standby12",0));
            excel.add(new ExcelBean("运输方式","fsheet",0));
            excel.add(new ExcelBean("面单号","foldorder",0));
            excel.add(new ExcelBean("追踪号","standby11",0));
            excel.add(new ExcelBean("申报(美元)","fclientordercode",0));
            excel.add(new ExcelBean("称重(克)","standby9",0));
            excel.add(new ExcelBean("运费(人民币)","ffreight",0));
            map.put(0, excel);
            Calendar cal=Calendar.getInstance();      
            int y=cal.get(Calendar.YEAR);      
            int m=cal.get(Calendar.MONTH);      
            int d=cal.get(Calendar.DATE);      
            int h=cal.get(Calendar.HOUR_OF_DAY);      
            int mi=cal.get(Calendar.MINUTE);      
            int s=cal.get(Calendar.SECOND);      
            String sheetName ="Foam_"+y+"年"+m+"月"+d+"日"+h+"时"+mi+"分"+s+"秒";
            xssfWorkbook = ExcelUtil.createExcelFile(Glogistics.class, fLogistics, map, sheetName);
        } catch (Exception e) {
            e.printStackTrace(); 
        }
        return xssfWorkbook;
    }
	
	
    /*
     * 根据集拼id导出
     * (non-Javadoc)
     * @see com.sybinal.shop.service.excel.ExcelService#exportExcelOutof(java.lang.String)
     */
	@Override
	public XSSFWorkbook exportExcelOutof(String idList) {
		// TODO Auto-generated method stub
		XSSFWorkbook xssfWorkbook=null;

		String username=userService.Justiactions(FLogisticsController.username()).getStandby1();
        //Date date=new Date();
        SimpleDateFormat ft=new SimpleDateFormat("yyyy.MM.dd : hh:mm:ss a");
        try {
            //根据ID查找数据
           String[] list = idList.split(",");
           List<String> strings = Arrays.asList(list);
            List<hjBase> fLogistics = new ArrayList<hjBase>();
            List<hjBase> f2 = new ArrayList<hjBase>();
            List<jpOrder> jps=jpMapper.selectPrint(strings,username);
            for(jpOrder j:jps){
         	   String[] jplist= j.getJpResult().toString().split(",");
         	   List<String> string2s = Arrays.asList(jplist);
         	  f2= hjbaseMapper.seljps(string2s,username);
         	   for(hjBase s:f2){
         		   s.setHjCountrycode(logisticsChange.gj(s.getHjCountrycode(),codes.selectAll()));
         		   s.setHjStandy4(j.getJpLaks());
         		   s.setHjStandy3(String.valueOf(ft.format(s.getHjStandy1())));
         		   fLogistics.add(s);
         	   }
         	   fLogisticsMapper.updateStatue2s(string2s,username);
            }

       	  	jpMapper.updateStatue(strings,username);
            List<ExcelBean> excel=new ArrayList<>();
            Map<Integer,List<ExcelBean>> map=new LinkedHashMap<>();
            //设置标题栏
            excel.add(new ExcelBean("物流渠道","hjShippingmethod",0));
            excel.add(new ExcelBean("客户订单号","hjStandy5",0));
            excel.add(new ExcelBean("物流跟踪号","hjStandy7",0));
            excel.add(new ExcelBean("收货国家","hjCountrycode",0));
            excel.add(new ExcelBean("集拼单号","hjStandy4",0));
            excel.add(new ExcelBean("重量G","hjInvoiceweight",0));
            excel.add(new ExcelBean("发运时间","hjStandy3",0));
            excel.add(new ExcelBean("价格","hjInvoiceunitcharge",0));
            excel.add(new ExcelBean("系统单号","hjShipperhawbcode",0));
            map.put(0, excel);
            Calendar cal=Calendar.getInstance();      
            int y=cal.get(Calendar.YEAR);      
            int m=cal.get(Calendar.MONTH);      
            int d=cal.get(Calendar.DATE);
            int h=cal.get(Calendar.HOUR_OF_DAY);      
            int mi=cal.get(Calendar.MINUTE);      
            int s=cal.get(Calendar.SECOND);      
            String sheetName ="Foam_集拼基本信息"+y+"年"+m+"月"+d+"日"+h+"时"+mi+"分"+s+"秒";
            xssfWorkbook = ExcelUtil.createExcelFile(hjBase.class, fLogistics, map, sheetName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xssfWorkbook;
	}
	
	/**
	 * 运费预览
	 * (non-Javadoc)
	 * @see com.sybinal.shop.service.excel.ExcelService#importExcelInfo(java.io.InputStream, org.springframework.web.multipart.MultipartFile)
	 */
	@Override
    public int importExcelPreivew(InputStream in, MultipartFile file) {
        try{
            List<List<Object>> listob = ExcelUtil.getBankListByExcel(in,file.getOriginalFilename());
            List<freightPreview> preivew = new ArrayList<freightPreview>();
           // Calculations cal=new Calculations();
            String usname=FLogisticsController.username();
            //遍历listob数据，把数据放到List中listob.size()
            for (int i = 0; i < listob.size(); i++) { 
                List<Object> ob = listob.get(i);
                freightPreview datas =new freightPreview();
                
                /*
				* 赋值
				*/
                if("".equals(ob.get(0))) {
                	break;
                }
				SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                datas.setDeliverTime(sdf2.parse(ob.get(0).toString()));
                datas.setOddNumber(String.valueOf(ob.get(1)));
                datas.setCustomerNumber(String.valueOf(ob.get(2)));
                datas.setTrackingSingleSign(String.valueOf(ob.get(3)));
                datas.setChannelCode(String.valueOf(ob.get(4)));
                datas.setDestinationCountry(String.valueOf(ob.get(5)));
                datas.setProductType(String.valueOf(ob.get(6)));
                datas.setActualFreight(String.valueOf(ob.get(7)));
                datas.setAmountReceivable(ob.get(8).toString());
                datas.setSpare1(usname); 
                hjBase base=hjbaseMapper.selres(datas.getTrackingSingleSign());
                if(base!=null) {
                if("".equals(base.getHjStandy14())) {
                	datas.setSpare2("");// 预算运费
                	datas.setSpare2(Calculations.los(base.getHjInvoiceweight(), base.getHjCountrycode(), datas.getChannelCode()));// 预算运费
                }else {
                    datas.setSpare2(base.getHjStandy14());// 预算运费
                }
                datas.setSpare3(base.getHjInvoiceweight());// 申报重量
                datas.setSpare4(base.getHjInvoiceunitcharge());//申报金额
                preivew.add(datas);
                }
            }
            /*
                                 * 存入数据库
             */
            logger.info(preivew.size());
           return freightPrevieMapper.insertAll(preivew);
        }catch (Exception e){
            e.printStackTrace();
        }
		return 0;
    }
	/**
	 * 导出差值
	 */
	@Override
	public XSSFWorkbook exportTables(String lists) {
		// TODO Auto-generated method stub
		XSSFWorkbook xssfWorkbook=null;
        SimpleDateFormat ft=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
        try {
        	String[] split=lists.split(",");
        	List<String> strings = Arrays.asList(split);
            List<freightPreview> preview = new ArrayList<freightPreview>();
            List<freightPreview> result=freightPrevieMapper.selectAlllist(strings);
            for(freightPreview w:result) {
            	w.setSpare10(ft.format(w.getDeliverTime()));
            	preview.add(w);
            }
            List<ExcelBean> excel=new ArrayList<>();
            Map<Integer,List<ExcelBean>> map=new LinkedHashMap<>();
            //设置标题栏
            excel.add(new ExcelBean("时间","spare10",0));
            excel.add(new ExcelBean("跟踪号","trackingSingleSign",0));
            excel.add(new ExcelBean("系统单号","hjShipperHawbcode",0));
            excel.add(new ExcelBean("申报重量","hjInvoiceWeight",0));
            excel.add(new ExcelBean("实际重量","actualFreight",0));
            excel.add(new ExcelBean("差重","w",0));
            excel.add(new ExcelBean("预估运费","spare2",0));
            excel.add(new ExcelBean("实际运费","amountReceivable",0));
            excel.add(new ExcelBean("差价","p",0));
            map.put(0, excel); 
            Calendar cal=Calendar.getInstance();      
            int y=cal.get(Calendar.YEAR);      
            int m=cal.get(Calendar.MONTH);      
            int d=cal.get(Calendar.DATE);
            int h=cal.get(Calendar.HOUR_OF_DAY);      
            int mi=cal.get(Calendar.MINUTE);      
            int s=cal.get(Calendar.SECOND);      
            String sheetName ="price"+y+"年"+m+"月"+d+"日"+h+"时"+mi+"分"+s+"秒";
            xssfWorkbook = ExcelUtil.createExcelFile(freightPreview.class, preview, map, sheetName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xssfWorkbook;
	}
	/**导出扣费
	 * exportsPrices
	 */
	@Override
	public XSSFWorkbook exportsPrices(String lists) {
		// TODO Auto-generated method stub
				XSSFWorkbook xssfWorkbook=null;
		        SimpleDateFormat ft=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
		        try {
		        	String[] split=lists.split(",");
		        	List<String> strings = Arrays.asList(split);
		            List<freightPreview> preview = new ArrayList<freightPreview>();
		            List<freightPreview> result=freightPrevieMapper.selectAlllist(strings);
		            for(freightPreview w:result) {
		            	w.setSpare10(ft.format(w.getDeliverTime()));
		            	preview.add(w);
		            }
		            List<ExcelBean> excel=new ArrayList<>();
		            Map<Integer,List<ExcelBean>> map=new LinkedHashMap<>();
		            //设置标题栏
		            excel.add(new ExcelBean("erp订单编号","hjShipperHawbcode",0));
		            excel.add(new ExcelBean("物流编号","channelCode",0));
		            excel.add(new ExcelBean("物流中文","z",0));
		            excel.add(new ExcelBean("发货时间","spare10",0));
		            excel.add(new ExcelBean("客户代码","customerNumber",0));
		            excel.add(new ExcelBean("预估扣费","spare2",0));
		            excel.add(new ExcelBean("环金扣费","amountReceivable",0));
		            map.put(0, excel); 
		            Calendar cal=Calendar.getInstance();      
		            int y=cal.get(Calendar.YEAR);      
		            int m=cal.get(Calendar.MONTH);      
		            int d=cal.get(Calendar.DATE);
		            int h=cal.get(Calendar.HOUR_OF_DAY);      
		            int mi=cal.get(Calendar.MINUTE);      
		            int s=cal.get(Calendar.SECOND);      
		            String sheetName ="price"+y+"年"+m+"月"+d+"日"+h+"时"+mi+"分"+s+"秒";
		            xssfWorkbook = ExcelUtil.createExcelFile(freightPreview.class, preview, map, sheetName);
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        return xssfWorkbook;
	}
	/**
	 * 导出预估运费
	 */
	@Override
	public XSSFWorkbook Estimate(String lists) {
		// TODO Auto-generated method stub
		XSSFWorkbook xssfWorkbook=null;
       // SimpleDateFormat ft=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
        try {
        	String[] split=lists.split(",");
        	List<String> strings = Arrays.asList(split);
            List<FLogistics> preview = new ArrayList<FLogistics>();
            List<FLogistics> result=fLogisticsMapper.selectListid(strings);
            for(FLogistics w:result) {
            	w.setT(w.getUpdateTime());
            	w.setFfids(w.getfIds());
            	w.setFfCountry(w.getfCountry());
            	System.out.println(w);
            	if(w.getStandby16()!=null && !"".equals(w.getStandby16())) {
            		w.setP("");
            		w.setP(Calculations.los(w.getStandby16(), w.getfCountry(), w.getStandby12()));
            	}
            	preview.add(w);
            }
            List<ExcelBean> excel=new ArrayList<>(); 
            Map<Integer,List<ExcelBean>> map=new LinkedHashMap<>();
            //设置标题栏   
            excel.add(new ExcelBean("erp订单编号","ffids",0));
            excel.add(new ExcelBean("物流简写","standby12",0));
            excel.add(new ExcelBean("国家","ffCountry",0));
            excel.add(new ExcelBean("物流中文","z",0));
            excel.add(new ExcelBean("时间","T",0));
            excel.add(new ExcelBean("系统单号","standby15",0));
            excel.add(new ExcelBean("申报重量","standby16",0));
            excel.add(new ExcelBean("预估运费","P",0));
            map.put(0, excel); 
            Calendar cal=Calendar.getInstance();      
            int y=cal.get(Calendar.YEAR);      
            int m=cal.get(Calendar.MONTH);      
            int d=cal.get(Calendar.DATE);
            int h=cal.get(Calendar.HOUR_OF_DAY);      
            int mi=cal.get(Calendar.MINUTE);      
            int s=cal.get(Calendar.SECOND);      
            String sheetName ="price"+y+"年"+m+"月"+d+"日"+h+"时"+mi+"分"+s+"秒";
            xssfWorkbook = ExcelUtil.createExcelFile(FLogistics.class, preview, map, sheetName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xssfWorkbook;
	}
	/**
	 * 导入产品
	 * dtfzyings
	 */
	@Override
	public int importExcelupload(InputStream in, CommonsMultipartFile file,HttpServletRequest req) {
		// TODO Auto-generated method stub
		try{
            List<List<Object>> listob = ExcelUtil.getBankListByExcel(in,file.getOriginalFilename());
            List<DTfzyingsWithBLOBs> dTfzyings1 = new ArrayList<DTfzyingsWithBLOBs>();
        	
        	/**
        	 * 10最大线程数
        	 * 5最大等待数
        	 * maximumPoolSize = Integer.MAX_VALUE 无限制
        	 */
            //ThreadPoolExecutor pool=new ThreadPoolExecutor(5,10,200, TimeUnit.MILLISECONDS,  new ArrayBlockingQueue<Runnable>(5));
        	//ThreadPoolExecutor pool=new ThreadPoolExecutor(Integer.MAX_VALUE,Integer.MAX_VALUE,200, TimeUnit.MILLISECONDS,  new ArrayBlockingQueue<Runnable>(5));

            ExecutorService pool = Executors.newFixedThreadPool(30);
            CountDownLatch lat=new CountDownLatch(30);
        	/*
             * 路径
             */
            // 设置日期格式
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            
    		//存储上一个
    		String loings="";
    		//存储上一个的fid
    		String fids="";
    		int lengs=0;
            String path = req.getSession().getServletContext().getRealPath("").split("webapp")[0]+"/resources/upload/";
            for (int t = 0; t < listob.size(); t++) {
                if(t==0||! String.valueOf(listob.get(t).get(0)).equals(String.valueOf(listob.get(t-1).get(0)))) {
                UUID uuid = UUID.randomUUID();
                lengs++;
                /*
	    		 * 创建存储路径  为项目发布路径下的/resources/upload
	    		 */
	    		String loing=sdf.format(new Date())+"/"+String.valueOf(listob.get(t).get(0))+"/";
	            /*
	    		 * 获取到路径然后在该路径下面创建文件夹
	    		 */
	    		File filee = new File(path+loing);
	    		if (!filee.exists() && !filee.isDirectory()) {
	    			// 文件夹创建批量
	    			filee.mkdirs();
	    		} else {
	    			logger.info("变体创建的文件夹已存在" + filee);
	    		}
	            DTfzyingsWithBLOBs dTfzyings = new DTfzyingsWithBLOBs();
	            List<DTstock> dTstock = new ArrayList<DTstock>();
	
	            /**
	             * 主体图片
	             */
	            String pics="";
	            //添加变体
	            //for (int i = 0; i < listob.size(); i++) { 
	             //   List<Object> ob = listob.get(i);
	                DTstock DTs =new DTstock();
	                /*
	                 * 1.添加一个产品基本信息
	                 * 2.其他的迭代为变体
	                 * 3.存在线地址
	                 * 4.需要的图片点击在线地址下载到服务器本地
	                 * 5.下载了的图片进行一次更新，路径为本地路径
	                 */
	                if(!String.valueOf(listob.get(t).get(3)).equals("null")) {
	                	DTs.setFcolor(String.valueOf(listob.get(t).get(3)));
	                }else {
	                	DTs.setFcolor("");
	                }
	                DTs.setFnum(30);
	                //if(t>0 &&! String.valueOf(listob.get(t).get(0)).equals(String.valueOf(listob.get(t-1).get(0)))) {
	                //	diy=uuid.toString();
	                //}
	                if(!String.valueOf(listob.get(t).get(0)).equals("null")) {
	                	DTs.setFid(uuid.toString()+String.valueOf(listob.get(t).get(0)));
	                }
	                fids=DTs.getFid();
	                loings=loing;
	                /**
	                 * 本地图片
	                 */

	                if(!String.valueOf(listob.get(t).get(31)).equals("null")) {
		                String[] imgs=listob.get(t).get(31).toString().split("\\|");
		                String result = "";
		                for(int c=0;c<imgs.length;c++){
		                	if(imgs[c].indexOf("http")==-1) {
		                		imgs[c]="https:"+imgs[c];
		                		//logger.info(imgs[c]+"添加后");
		                	}
		                	if(imgs[c].indexOf("www.ec-sudo.com")!=-1) {
		                		result+="|" +imgs[c];
		                	}else {
		                	/**
		                	 * 用闭锁实现并发排序下载
		                	 */
		                    UUID uuids = UUID.randomUUID();
		                	Task task=new Task(imgs[c],path+loing,uuids.toString(),lat);
		                    pool.execute(task);
		                    /**
		                	 * 主图窃取第一个
		                	 */
		                	if(c==0) {
		                		pics=loing+ uuids+ ".jpg";
		                	}
		                	result+="|" +loing+ uuids + ".jpg";
		                	}
		                }
		                DTs.setFimg(result.substring(1, result.length()));
	                }
		                /**
		                 * 在线图片
		                 * \
		                 */

	                if(!String.valueOf(listob.get(t).get(31)).equals("null")) {
		                DTs.setFonline(String.valueOf(listob.get(t).get(31)));
	                }

	                if(!String.valueOf(listob.get(t).get(4)).equals("null")) {
		                DTs.setFsize(String.valueOf(listob.get(t).get(4)));
	                }else {
	                	DTs.setFsize("");
	                }
		               
		                dTstock.add(DTs);
		            //}
		            /**
		             * 添加变体
		             */
		            dtstock.insertall(dTstock);
	            /*if(t>0 &&String.valueOf(listob.get(t).get(0)).equals(String.valueOf(listob.get(t-1).get(0)))) {
	            	continue;
	            }*/
	            
	            //添加产品

	            if(!String.valueOf(listob.get(t).get(5)).equals("null")) {
	            	dTfzyings.setFbrand(String.valueOf(listob.get(t).get(5)));
	            }

                if(!String.valueOf(listob.get(t).get(6)).equals("null")) {
                	dTfzyings.setFkind(String.valueOf(listob.get(t).get(6)));
                }

                if(!String.valueOf(listob.get(t).get(7)).equals("null")) {
                	dTfzyings.setFchinese(String.valueOf(listob.get(t).get(7)));
                }

                if(!String.valueOf(listob.get(t).get(8)).equals("null")) {
                	dTfzyings.setFenglish(String.valueOf(listob.get(t).get(8)));
                }

                if(!String.valueOf(listob.get(t).get(10)).equals("null")) {
                	dTfzyings.setFcur(String.valueOf(listob.get(t).get(10)));
                }
	            if(!String.valueOf(listob.get(t).get(11)).equals("null")) {
		            dTfzyings.setFcost(Double.valueOf(listob.get(t).get(11).toString()).longValue());
	            }
	            if(!String.valueOf(listob.get(t).get(12)).equals("null")) {
	            	dTfzyings.setFfreight(Double.valueOf(listob.get(t).get(12).toString()));
	            }
	            if(!String.valueOf(listob.get(t).get(13)).equals("null")) {
	            	dTfzyings.setFshipid(Double.valueOf(listob.get(t).get(13).toString()).intValue());
	            }

	            if(!String.valueOf(listob.get(t).get(14)).equals("null")) {
	            	dTfzyings.setFprice(Double.valueOf(listob.get(t).get(14).toString()).longValue());
	            }
	            if(!String.valueOf(listob.get(t).get(15)).equals("null")) {
	            	dTfzyings.setFweight(Double.valueOf(listob.get(t).get(15).toString()));
	            }
	            if(!String.valueOf(listob.get(t).get(16)).equals("null")) {
		            String[] ckg=listob.get(t).get(16).toString().split("\\*");
		            
		            if(!"".equals(ckg[0])&&!ckg[0].equals("null")) {
		            	logger.info(ckg[0]);
		            	dTfzyings.setFlength(Double.valueOf(ckg[0]));
		                
		                dTfzyings.setFwidth(Double.valueOf(ckg[1]));
		                
		                dTfzyings.setFheight(Double.valueOf(ckg[2]));
		            }
	            }
	            
	            if(!String.valueOf(listob.get(t).get(17)).equals("null")) {
	            	dTfzyings.setFdepartment(String.valueOf(listob.get(t).get(17)));
	            }
	            if(!String.valueOf(listob.get(t).get(18)).equals("null")) {
	            	dTfzyings.setFmaterial(String.valueOf(listob.get(t).get(18)));
	            }
	            if(!String.valueOf(listob.get(t).get(20)).equals("null")) {
	            	dTfzyings.setFmetal(String.valueOf(listob.get(t).get(20)));
	            }
	            if(!String.valueOf(listob.get(t).get(21)).equals("null")) {
	            	dTfzyings.setFgem(String.valueOf(listob.get(t).get(21)));
	            }
	            if(!String.valueOf(listob.get(t).get(19)).equals("null")) {
	            	dTfzyings.setFpackages(String.valueOf(listob.get(t).get(19)));
	            }
	            dTfzyings.setFhscode("1");
	            if(!String.valueOf(listob.get(t).get(8)).equals("null")) {
	            	dTfzyings.setFenglish(String.valueOf(listob.get(t).get(8)));
	            }
	            if(!String.valueOf(listob.get(t).get(7)).equals("null")) {
	            	dTfzyings.setFchinese(String.valueOf(listob.get(t).get(7)));
	            }
	            /**
	             * 产品sku
	             */
	            if(!String.valueOf(listob.get(t).get(0)).equals("null")) {
	            	dTfzyings.setFsku(String.valueOf(listob.get(t).get(0)));
	            }
	            /**
	             * 参考网址
	             */
	            if(!String.valueOf(listob.get(t).get(32)).equals("null")) {
	            	dTfzyings.setFsource(String.valueOf(listob.get(t).get(32))); 
	            }
	            /**
	             * 传入唯一的uuid
	             */
	            if(!String.valueOf(listob.get(t).get(0)).equals("null")) {
	            	dTfzyings.setFids(uuid.toString()+String.valueOf(listob.get(t).get(0)));
	            }	            
	            /**
	             * 传中文
	             */
	            dTfzyings.setFzh(String.valueOf(listob.get(t).get(23))=="null"?String.valueOf(listob.get(t).get(23)):""
	             +"|"+String.valueOf(listob.get(t).get(24))=="null"?String.valueOf(listob.get(t).get(24)):""
	            +"|"+String.valueOf(listob.get(t).get(25))=="null"?String.valueOf(listob.get(t).get(25)):""
	             +String.valueOf(listob.get(t).get(26))=="null"?String.valueOf(listob.get(t).get(26)):""
	            +String.valueOf(listob.get(t).get(27))=="null"?String.valueOf(listob.get(t).get(27)):""
	             +String.valueOf(listob.get(t).get(28))=="null"?String.valueOf(listob.get(t).get(28)):""
	            +String.valueOf(listob.get(t).get(29))=="null"?String.valueOf(listob.get(t).get(29)):""
	             +"|"+String.valueOf(listob.get(t).get(30))=="null"?String.valueOf(listob.get(t).get(30)):"");
	            /**
	             * 展示图片
	             */
	            dTfzyings.setFpic(pics);
	            dTfzyings.setFrs1("0");
	            dTfzyings.setFrs2("0");
	            dTfzyings.setFrs3("0");
	            dTfzyings.setFrs4("0");
	            dTfzyings.setFrs5("0");
	            dTfzyings.setFrs6("0");
	            dTfzyings.setFrs7(FLogisticsController.username());
	            dTfzyings.setFrs8("0");
	            /**
	             * 添加主图
	             */

	            if(!String.valueOf(listob.get(t).get(31)).equals("null")) {
		            String[]c=String.valueOf(listob.get(t).get(31)).split("\\|");
		    		TreeSet<String> p=new TreeSet<String>();
		    		TreeSet<String> resul=new TreeSet<String>();
		    		for(int i=0;i<c.length;i++){
	                	if(c[i].indexOf("http")==-1) {
	                		c[i]="https:"+c[i];
	                		//logger.info(imgs[c]+"添加后");
	                	}
		    			if(c[i].indexOf("www.ec-sudo.com")!=-1) {
		    				resul.add(c[i]);
		    			}else {
		    				p.add(c[i]);
		    			}
		    		}
		    		p.stream().forEach(x->{
		    			/**
		            	 * 用闭锁实现并发排序下载
		            	 */
		            	UUID uui = UUID.randomUUID();
		            	Task task=new Task(x,path+loing,uui.toString(),lat);
		            	//logger.info("--->"+loing+uui.toString()+".jpg");
		            	resul.add(loing+uui.toString()+".jpg");
		                pool.execute(task);});
		    		/**
		    		 * 添加主图
		    		 */
		    		online.insertall(resul,dTfzyings.getFids());
	            }
	           /**
	            * 存入数据库
	            */
	            dTfzyings1.add(dTfzyings);
	            /**
	             * 添加产品
	             */
	           }else {
		           List<DTstock> dTstock = new ArrayList<DTstock>();
	        	   DTstock DTs =new DTstock();
	                /*
	                 * 1.添加一个产品基本信息
	                 * 2.其他的迭代为变体
	                 * 3.存在线地址
	                 * 4.需要的图片点击在线地址下载到服务器本地
	                 * 5.下载了的图片进行一次更新，路径为本地路径
	                 */
		            if(!String.valueOf(listob.get(t).get(3)).equals("null")) {
		            	DTs.setFcolor(String.valueOf(listob.get(t).get(3)));
		            }else {
		            	DTs.setFcolor("");
		            }

	                DTs.setFnum(30);
	                //if(t>0 &&! String.valueOf(listob.get(t).get(0)).equals(String.valueOf(listob.get(t-1).get(0)))) {
	                //	diy=uuid.toString();
	                //}
	                DTs.setFid(fids);
	                /**
	                 * 本地图片
	                 */

		            if(!String.valueOf(listob.get(t).get(31)).equals("null")) {
		                String[] imgs=listob.get(t).get(31).toString().split("\\|");
		                String result = "";
		                for(int c=0;c<imgs.length;c++){
		                	if(imgs[c].indexOf("http")==-1) {
		                		imgs[c]="https:"+imgs[c];
		                		//logger.info(imgs[c]+"添加后");
		                	}
		                	if(imgs[c].indexOf("www.ec-sudo.com")!=-1) {
		                		result+="|" +imgs[c];
		                	}else {
		                	/**
		                	 * 用闭锁实现并发排序下载
		                	 */
		                    UUID uuids = UUID.randomUUID();
		                	Task task=new Task(imgs[c],path+loings,uuids.toString(),lat);
		                    pool.execute(task);
		                    /**
		                	 * 主图窃取第一个
		                	 */
		                	result+="|" +loings+ uuids + ".jpg";
		                	}
		                }
		                DTs.setFimg(result.substring(1, result.length()));
		            }
	                /**
	                 * 在线图片
	                 * \
	                 */
		            if(!String.valueOf(listob.get(t).get(31)).equals("null")) {
		            	DTs.setFonline(String.valueOf(listob.get(t).get(31)));
		            }
		            if(!String.valueOf(listob.get(t).get(31)).equals("null")) {
		            	DTs.setFsize(String.valueOf(listob.get(t).get(4)));
		            }else {
		            	DTs.setFsize("");
		            }
	               
	                dTstock.add(DTs);
	            //}
	            /**
	             * 添加变体
	             */
	            dtstock.insertall(dTstock);
	           }
	        }
            pool.shutdown();
            int pd=dtfz.insertAll(dTfzyings1);
            if(pd>0) {
                return lengs;
            }
           return 0;
        }catch (Exception e){
            e.printStackTrace();
            logger.error("导入商品错误",e);
        }
		return 0;
	}
	/* (non-Javadoc) 导出产品
	 * @see com.sybinal.shop.service.excel.ExcelService#productExport(java.lang.String)
	 */
	@Override
	public XSSFWorkbook productExport(String idList) {
			logger.info(idList);
	        XSSFWorkbook xssfWorkbook=null;
	        //获取用户名
	       //String username=userService.Justiactions(FLogisticsController.username()).getStandby1();
	        try {
	            //根据ID查找数据
	           String[] list = idList.split(",");
	           List<String> strings = Arrays.asList(list);
	           List<Exportstock> exportstock = new ArrayList<Exportstock>();
	            /**
	             * 查询所有被选中的商品信息
	             */
	            dtfz.selectCheckedProduct(strings).forEach(fLogisticsInfoList->{
	            	/**
            		 * 查询该商品的变体s.getFids()
            		 */
	            	 /**
		             * 获取所有导出的商品
		             */
            		dtstocks.selectBystockSku(fLogisticsInfoList.getFids()).forEach(x->{
            			Exportstock export=new Exportstock();
            			export.setId(fLogisticsInfoList.getId());
            			export.setFsku(fLogisticsInfoList.getFsku());
            			export.setFcolor(x.getFcolor());
            			export.setFsize(x.getFsize());
            			export.setFcolor(x.getFcolor());
            			export.setFbrand(fLogisticsInfoList.getFbrand());
            			export.setFkind(fLogisticsInfoList.getFkind());
            			export.setFchinese(fLogisticsInfoList.getFchinese());
            			export.setFenglish(fLogisticsInfoList.getFenglish());
            			export.setFnum(x.getFnum());
            			export.setFcur(fLogisticsInfoList.getFcur());
            			export.setFcost(fLogisticsInfoList.getFcost());
            			export.setFfreight(fLogisticsInfoList.getFfreight());
            			export.setFshipid(fLogisticsInfoList.getFshipid());
            			export.setFprice(fLogisticsInfoList.getFprice());
            			export.setFweight(fLogisticsInfoList.getFweight());
            			export.setFlength(fLogisticsInfoList.getFlength()+"*"+fLogisticsInfoList.getFwidth()+"*"+fLogisticsInfoList.getFheight());
            			export.setFdepartment(fLogisticsInfoList.getFdepartment());
            			export.setFmaterial(fLogisticsInfoList.getFmaterial());
            			export.setFpackages(fLogisticsInfoList.getFpackages());
            			export.setFmetal(fLogisticsInfoList.getFmetal());
            			export.setFgem(fLogisticsInfoList.getFgem());
            			export.setLanguage("chinese");
            			export.setTitle(fLogisticsInfoList.getFzh().split("毰毸").length==1?fLogisticsInfoList.getFzh().split("毰毸")[0]:"");
            			export.setKeys(fLogisticsInfoList.getFzh().split("毰毸").length==4?fLogisticsInfoList.getFzh().split("毰毸")[3]:"");
            			export.setBullet(fLogisticsInfoList.getFzh().split("毰毸").length==2?fLogisticsInfoList.getFzh().split("毰毸")[1]:"");
            			export.setIntro(fLogisticsInfoList.getFzh().split("毰毸").length==3?fLogisticsInfoList.getFzh().split("毰毸")[2]:"");
            			String[] img=x.getFimg().split("\\|");
            			String fim="";
            			for(int i=0;i<img.length;i++){
            				fim+="|www.ec-sudo.com/Shopping/resources/upload/"+img[i];
            			}
            			export.setPic(fim.substring(1,fim.length()));
            			export.setFsource(fLogisticsInfoList.getFsource());
            			
            			exportstock.add(export);
            		});;
	            });;//Integer.valueOf(list[i])
	           
	            List<ExcelBean> excel=new ArrayList<>();
	            Map<Integer,List<ExcelBean>> map=new LinkedHashMap<>();
	            //设置标题栏
	            excel.add(new ExcelBean("ID","id",0));
	            excel.add(new ExcelBean("父SKU","fsku",0));
	            excel.add(new ExcelBean("SKU","fids",0));
	            excel.add(new ExcelBean("操作","k",0));
	            excel.add(new ExcelBean("颜色","fcolor",0));
	            excel.add(new ExcelBean("尺码","fsize",0));
	            excel.add(new ExcelBean("品牌","fbrand",0));
	            excel.add(new ExcelBean("分类","fkind",0));
	            excel.add(new ExcelBean("中文简称","fchinese",0));
	            excel.add(new ExcelBean("英文简称","fenglish",0));
	            excel.add(new ExcelBean("库存","fnum",0));
	            excel.add(new ExcelBean("币种","fcur",0));
	            excel.add(new ExcelBean("成本价","fcost",0));
	            excel.add(new ExcelBean("运费","ffreight",0));
	            excel.add(new ExcelBean("挂号模板","fshipid",0));
	            excel.add(new ExcelBean("分销价","fprice",0));
	            excel.add(new ExcelBean("毛重(克)","fweight",0));
	            excel.add(new ExcelBean("包装尺寸","flength",0));
	            excel.add(new ExcelBean("适用人群","fdepartment",0));
	            excel.add(new ExcelBean("材料","fmaterial",0));
	            excel.add(new ExcelBean("包装材料","fpackages",0));
	            excel.add(new ExcelBean("金属","fmetal",0));
	            excel.add(new ExcelBean("珠宝","fgem",0));
	            excel.add(new ExcelBean("语言","language",0));
	            excel.add(new ExcelBean("标题","title",0));
	            excel.add(new ExcelBean("关键字","keys",0));
	            excel.add(new ExcelBean("要点","bullet",0));
	            excel.add(new ExcelBean("简介","intro",0));
	            excel.add(new ExcelBean("在线图片","pic",0));
	            excel.add(new ExcelBean("在线网址","fsource",0));
	            map.put(0, excel);
	           /* Calendar cal=Calendar.getInstance();      
	            int y=cal.get(Calendar.YEAR);      
	            int m=cal.get(Calendar.MONTH);      
	            int d=cal.get(Calendar.DATE);      
	            int h=cal.get(Calendar.HOUR_OF_DAY);      
	            int mi=cal.get(Calendar.MINUTE);      
	            int s=cal.get(Calendar.SECOND);      
	            String sheetName ="product";*/
	            xssfWorkbook = ExcelUtil.createExcelFile(Exportstock.class, exportstock, map, "product");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return xssfWorkbook;
	    }
	/**
	 * 导出lazada分类
	 */
	@Override
	public XSSFWorkbook lazada() {
		// TODO Auto-generated method stub
        XSSFWorkbook xssfWorkbook=null;
        /*
         * *************************************************************************获取分类
         */
        LazopClient client = new LazopClient("	https://api.lazada.com.my/rest", LazadaController.appkey, LazadaController.appSecret);
        LazopRequest request = new LazopRequest();
        request.setApiName("/category/tree/get");
        request.setHttpMethod("GET");
        LazopResponse response = null;
		try {
			response = client.execute(request);
			
		} catch (ApiException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        /**
         * **********************************************************************************创建表格
         */
        List<LazadaCate> exportstock = new ArrayList<LazadaCate>();
        Gson gson=new Gson();
        Map<String,Object> maps=gson.fromJson(response.getBody(), new TypeToken<HashMap<String,Object>>() {}.getType());
        List<LazadaCate> ids=gson.fromJson(gson.toJson(maps.get("data")),new TypeToken<List<LazadaCate>>() {}.getType());
        System.out.println("================"+gson.toJson(ids.get(0)));
        /**
         * *********************************************************************************************迭代
         */
        
        exportstock.addAll(LazadaController.analysisJson(ids));
        /**
         * ****************************************************************************************存入表格
         */
        System.out.println("*****************"+gson.toJson(exportstock));
        Map<Integer,List<ExcelBean>> map=new LinkedHashMap<>();
        List<ExcelBean> excel=new ArrayList<>();
        excel.add(new ExcelBean("var","var",0));
        excel.add(new ExcelBean("名称","name",0));
        excel.add(new ExcelBean("leaf","leaf",0));
        excel.add(new ExcelBean("category_id","category_id",0));
        map.put(0, excel);

        try {
			xssfWorkbook = ExcelUtil.createExcelFile(LazadaCate.class, exportstock, map, "product");
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IntrospectionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return xssfWorkbook;
	}
	@Override
	public XSSFWorkbook Tracking(String idList4) {
		// TODO Auto-generated method stub
		if("".equals(idList4)) {
			return null;
		}
				XSSFWorkbook xssfWorkbook=null;
		       // SimpleDateFormat ft=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
		        try {
		        	String[] split=idList4.split(",");
		        	List<String> strings = Arrays.asList(split);
		            List<FLogistics> preview = new ArrayList<FLogistics>();
		            List<Map<String,String>> result=fLogisticsMapper.Tracking(strings);
		            result.forEach(x->{
		            	FLogistics w=new FLogistics();
		            	if("1".equals(x.get("yd_standy2"))&&!"".equals(x.get("yd_reference_no"))) {
		            		w.setStandby11(x.get("yd_reference_no"));
		            	}else if("1".equals(x.get("status"))&&!"".equals(x.get("platform_order_id"))) {
		            		w.setStandby12(x.get("platform_order_id"));
		            	}else if("1".equals(x.get("backup3"))&&!"".equals(x.get("reference_no"))) {
		            		w.setStandby13(x.get("reference_no"));
		            	}else if("1".equals(x.get("standby3"))&&!"".equals(x.get("clno"))) {
		            		w.setStandby14(x.get("clno"));
		            	}else if("1".equals(x.get("hj_standy2"))&&!"".equals(x.get("hj_referenceNo"))) {
		            		w.setStandby15(x.get("hj_referenceNo"));
		            	}
		            	if(!"".equals(x.get("f_ids"))){
			            	w.setFfids(x.get("f_ids"));
		            	}
		            	preview.add(w);
		            });
		            List<ExcelBean> excel=new ArrayList<>(); 
		            Map<Integer,List<ExcelBean>> map=new LinkedHashMap<>();
		            //设置标题栏   
		            excel.add(new ExcelBean("erp订单编号","ffids",0));
		            excel.add(new ExcelBean("义达","standby11",0));
		            excel.add(new ExcelBean("顺丰","standby12",0));
		            excel.add(new ExcelBean("黑猫","standby13",0));
		            excel.add(new ExcelBean("eub","standby14",0));
		            excel.add(new ExcelBean("环金","standby15",0));
		            map.put(0, excel); 
		            String sheetName ="Track "+SnowIdUtils.uniqueLong();
		            xssfWorkbook = ExcelUtil.createExcelFile(FLogistics.class, preview, map, sheetName);
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        return xssfWorkbook;
	}
}
