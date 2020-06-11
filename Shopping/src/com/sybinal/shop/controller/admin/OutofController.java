package com.sybinal.shop.controller.admin;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sybinal.shop.model.User;
import com.sybinal.shop.model.hjBase;
import com.sybinal.shop.model.jpOrder;
import com.sybinal.shop.service.JpService;
import com.sybinal.shop.service.logOutOfService;
import com.sybinal.shop.service.excel.ExcelService;
import com.sybinal.shop.service.logistics.hjService;
import com.sybinal.shop.service.user.UserService;

@Controller
public class OutofController {
	

	@Autowired
	JpService jpServices;
	
	@Autowired
	hjService hjservices;

	@Autowired
	ExcelService excelService;

	@Autowired
	UserService userService;

	@Autowired
	logOutOfService logout;
	@RequestMapping(value = "/order/Outof", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView model = new ModelAndView();
		User user=new User();

		 
		user.setUserName(FLogisticsController.username());
		model.addObject("logoutlist", logout.selectAll());
		model.addObject("jurisdiction", userService.jurisdiction(user));
		model.setViewName("admin/outOf/outofMain");
		return model;
	}
	
	/*
	 * /Logistics/outoflist
	 * 查询所有集拼的数据
	 */
	@RequestMapping(value = "/Logistics/outoflist",method=RequestMethod.POST)
	@ResponseBody
	public PageInfo outoflist(@RequestBody Map <String,String> map) {
		  int limit=Integer.parseInt(map.get("limit"));
		  int page=Integer.parseInt(map.get("page"));
		  String reus=userService.Justiactions(FLogisticsController.username()).getStandby1();
		  PageHelper.startPage(page,limit);

			 
		  map.put("jpStandby2",reus);
		  List<jpOrder> List=jpServices.selectAll(map);
		  PageInfo pageInfo = new PageInfo(List);
		  
		return pageInfo;
	}
	/*
	 * /Logistics/outoflists
	 * 查询所有需要打印的数据
	 */
	@RequestMapping(value = "/Logistics/outoflists",method=RequestMethod.POST)
	@ResponseBody
	public List<jpOrder> outoflists(@RequestBody Map <String,String> map) {

		 
		map.put("usen",  userService.Justiactions(FLogisticsController.username()).getStandby1());
		return jpServices.selectPrint(map);
	}
	/*outofLogicsticlist
	 * 根据前端传来的id查询集拼单的所有参考编号
	 */
	@RequestMapping(value = "/Logistics/outofLogicsticlist",method=RequestMethod.POST)
	@ResponseBody
	public List<hjBase> outofLogicsticlist(@RequestBody Map <String,String> map) {
		//根据id查询所有的参考编号

		 
		String username= userService.Justiactions(FLogisticsController.username()).getStandby1();
		jpOrder jporder=jpServices.selectIds(map.get("id").toString(),username);
		//传入所有的参考编号，并在servlet进行list截取存储
		String usernames= userService.Justiactions(FLogisticsController.username()).getStandby1();
		List<hjBase> hjbase=hjservices.selectallOrid(jporder.getJpResult(),usernames);
		//把获取到的list对象传入前端
		return hjbase;
	}
	/*
	 * 删除集合dellist
	 */
	@RequestMapping(value = "/Logistics/dellist",method=RequestMethod.POST)
	@ResponseBody
	public int dellist(@RequestBody Map <String,String> map) {

		 
		return jpServices.dellist(map.get("idList").toString(), userService.Justiactions(FLogisticsController.username()).getStandby1());
	}
	/*
	 * 导出集拼单outofleft
	 */
	@RequestMapping(value= "/Logistics/outofleft", method = RequestMethod.POST)
	@ResponseBody
    public void outofleft(HttpServletResponse response,String idList) {
	//	String idList=map.get("idList");
        response.setContentType("application/vnd.ms-excel");

        Calendar cal=Calendar.getInstance();      
        int y=cal.get(Calendar.YEAR);      
        int m=cal.get(Calendar.MONTH);      
        int d=cal.get(Calendar.DATE);      
        int h=cal.get(Calendar.HOUR_OF_DAY);      
        int mi=cal.get(Calendar.MINUTE);      
        int s=cal.get(Calendar.SECOND);   
        String fileName = "";
        try {
        	fileName = new String(fileName.getBytes(),"ISO8859-1");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        fileName +=y+"/"+m+"/"+d+"/"+h+"/"+mi+"/"+s+"/";
        response.setHeader("Content-disposition", "attachment;filename=FoamOrderBase-"+fileName+".xlsx;charset=UTF-8");
        XSSFWorkbook workbook = excelService.exportExcelOutof(idList);//idList
        try {
            OutputStream output  = response.getOutputStream();
            BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
            workbook.write(bufferedOutPut);
            bufferedOutPut.flush();
            bufferedOutPut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	/**
	 * 批量发送集拼
	 */
	@RequestMapping(value = "/Logistics/postOut",method=RequestMethod.POST)
	@ResponseBody
	public String postOut(@RequestBody Map <String,String> map) {
		return jpServices.postOut(map.get("idList").toString(), userService.Justiactions(FLogisticsController.username()).getStandby1());
	}
}
