package com.sybinal.shop.service.excel;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public interface ExcelService {

    public int importExcelInfo(InputStream in, MultipartFile file);

	XSSFWorkbook exportExcelInfo(String idList);//String idList
	//导出集拼
	XSSFWorkbook exportExcelInfo2(String idList);//String idList
	//导出邮政
	XSSFWorkbook exportExcelInfo3(String idList);
	//导出集拼（根据集拼的id导出）
	XSSFWorkbook exportExcelOutof(String idList);
	/**
	 * 运费预览
	 * @param in
	 * @param file
	 */
	public int importExcelPreivew(InputStream in, MultipartFile file);
	/**
	 * 导出差值
	 * @param list
	 * @return
	 */
	public XSSFWorkbook exportTables(String list);
	/**
	 * 导出扣费
	 * @param idList
	 * @return
	 */
	public XSSFWorkbook exportsPrices(String idList);
	/**
	 * 导出预估
	 * @param idList
	 * @return
	 */
	public XSSFWorkbook Estimate(String idList);
	/**
	 * 导入产品
	 * @param in
	 * @param file
	 * @return
	 */
	public int importExcelupload(InputStream in, CommonsMultipartFile file,HttpServletRequest req);

	/**  
	 * @user: spor-wen10
	 * @Title: productExport  
	 * @Description: TODO 导出产品
	 * @date: 2020年2月17日 下午7:17:27
	 * @param: @param idList
	 * @param: @return    参数  
	 * @return: XSSFWorkbook    返回类型  
	 * @throws  
	 */
	public XSSFWorkbook productExport(String idList);
	/**
	 * 导出lazada分类
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年6月20日 下午6:34:12   
	* @version
	 */
	public XSSFWorkbook lazada();
	/**
	 * 
	* @Title Shopping   
	* @Package com.sybinal.shop.service.excel
	* @Description: TODO 导出跟踪号
	* @author PC1  
	* @date 2021年2月20日 上午10:42:31   
	* @version V1.0
	 */
	public XSSFWorkbook Tracking(String idList4); 
}
