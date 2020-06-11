package com.sybinal.shop.controller.admin;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpRequest;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sybinal.shop.service.logOutOfService;
import com.sybinal.shop.service.excel.ExcelService;
import com.sybinal.shop.service.user.UserService;

@Controller
public class ExcelController {

	@Autowired
	ExcelService excelService;
	
	@Autowired
	UserService userService;

	@Autowired
	logOutOfService logout;
	private static Logger logger=Logger.getLogger(ExcelController.class);
	/*
	 * 导入
	 */
	@RequestMapping(value= "/admin/import", method = RequestMethod.POST)
    @ResponseBody
    public  int impotr(@RequestParam("file")CommonsMultipartFile file){
		ModelAndView model = new ModelAndView();
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            InputStream in = file.getInputStream();
 
           int i= excelService.importExcelInfo(in,file);
            in.close();
            map.put("error", "ok"); // 成功
			map.put("data", 200); // 数据
			return i;
        }catch (Exception e){
            map.put("error", "err"); // 成功 
			map.put("data", 500); // 数据
        }
		/*User user=new User();
		user.setUserName(FLogisticsController.username());
		model.addObject("jurisdiction", userService.jurisdiction(user));
		model.addObject("data",map);
		model.addObject("logoutlist", logout.selectAll());
        model.setViewName("admin/order/orderInfoMain");*/
        return 0;
    }
	/**
	 * 导入运费预览
	 */
	@RequestMapping(value= "/preivew/preivew/import", method = RequestMethod.POST)
    @ResponseBody
    public  int preivew(@RequestParam("file")CommonsMultipartFile file){
		ModelAndView model = new ModelAndView();
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            InputStream in = file.getInputStream();
 
            int i =excelService.importExcelPreivew(in,file);
            in.close();
            map.put("error", "ok"); // 成功
			map.put("data", 200); // 数据
			return i;
        }catch (Exception e){
            map.put("error", "err"); // 成功
			map.put("data", 500); // 数据
        }
        return 0;
    }
	/**
	 * 导入产品
	 * admin/upload/import
	 */
	@RequestMapping(value= "/admin/upload/import", method = RequestMethod.POST)
    @ResponseBody
    public  int uploads(@RequestParam("file")CommonsMultipartFile file,HttpServletRequest req){
		ModelAndView model = new ModelAndView();
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            InputStream in = file.getInputStream();
 
            int i =excelService.importExcelupload(in,file,req);
            in.close();
            map.put("error", "ok"); // 成功
			map.put("data", 200); // 数据
			return i;
        }catch (Exception e){
            map.put("error", "err"); // 成功
			map.put("data", 500); // 数据
        }
        return 0;
    }
	/**
	 * 图片下载
	 */
	@RequestMapping(value= "/image/import", method = RequestMethod.POST)
    @ResponseBody
    public  int imagedown(@RequestParam("file")CommonsMultipartFile file){
		System.out.println(file);
        return 0;
    }
	/*
	 * 导出
	 */
	@RequestMapping(value= "/admin/export", method = RequestMethod.POST)
	@ResponseBody
    public void export(HttpServletResponse response,String idList) {
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
        fileName +=y+m+d+h+mi+s;
        response.setHeader("Content-disposition", "attachment;filename=FoamOrderBase-"+fileName+".xlsx;charset=UTF-8");
        XSSFWorkbook workbook = excelService.exportExcelInfo(idList);//idList
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
	
	/*
	 * 导出2集拼
	 */
	@RequestMapping(value= "/admin/export2", method = RequestMethod.POST)
	@ResponseBody
    public void export2(HttpServletResponse response,String idList2) {
		System.out.println(idList2);
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
        fileName +=y+m+d+h+mi+s;
        response.setHeader("Content-disposition", "attachment;filename=FoamOrderBase-"+fileName+".xlsx;charset=UTF-8");
        XSSFWorkbook workbook = excelService.exportExcelInfo2(idList2);//idList
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
	/*
	 * 导出2邮政 
	 */
	@RequestMapping(value= "/admin/export3", method = RequestMethod.POST)
	@ResponseBody
    public void export3(HttpServletResponse response,String idList3) {
		logger.info(idList3);

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
        fileName +=y+m+d+h+mi+s;
        response.setHeader("Content-disposition", "attachment;filename=FoamOrderBase-"+fileName+".xlsx;charset=UTF-8");
        XSSFWorkbook workbook = excelService.exportExcelInfo3(idList3);//idList
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
	 * 导出产品
	 * /admin/upload/export?
	 */
	@RequestMapping(value = "/admin/upload/export",method=RequestMethod.POST)
	@ResponseBody
	public void productExport(String idList,HttpServletResponse response) {
	        response.setContentType("application/vnd.ms-excel");
	        logger.info(idList);
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
	        
	        response.setHeader("Content-disposition", "attachment;filename=product.xlsx;charset=UTF-8");
	        XSSFWorkbook workbook = excelService.productExport(idList);//idList
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
	 * 导出预估
	 * admin/export/Estimate
	 */
	@RequestMapping(value = "/admin/export/Estimate",method=RequestMethod.POST)
	@ResponseBody
	public void Estimate(String idList4,HttpServletResponse response) {
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
	        fileName +=y+m+d+h+mi+s;
	        response.setHeader("Content-disposition", "attachment;filename=price-"+fileName+".xlsx;charset=UTF-8");
	        XSSFWorkbook workbook = excelService.Estimate(idList4);//idList
	        /*XSSFSheet sheet=null;
	        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {//获取每个Sheet表
	             sheet=workbook.getSheetAt(i);
	             for (int j = 0; j < sheet.getPhysicalNumberOfRows(); j++) {//获取每行
	                XSSFRow row=sheet.getRow(j);
	                for (int k = 0; k < row.getPhysicalNumberOfCells(); k++) {//获取每个单元格
	                    System.out.print(row.getCell(k)+"\t");
	                }
	                System.out.println("---Sheet表"+i+"处理完毕---");
	            }}*/
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
	 * 导出数据
	 */
	@RequestMapping(value = "/preivew/preivew/exports",method=RequestMethod.POST)
	@ResponseBody
	public void exports(String idList,HttpServletResponse response) {
	        response.setContentType("application/vnd.ms-excel");
	        System.out.println(idList);
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
	        fileName +=y+m+d+h+mi+s;
	        response.setHeader("Content-disposition", "attachment;filename=price-"+fileName+".xlsx;charset=UTF-8");
	        XSSFWorkbook workbook = excelService.exportTables(idList);//idList
	        /*XSSFSheet sheet=null;
	        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {//获取每个Sheet表
	             sheet=workbook.getSheetAt(i);
	             for (int j = 0; j < sheet.getPhysicalNumberOfRows(); j++) {//获取每行
	                XSSFRow row=sheet.getRow(j);
	                for (int k = 0; k < row.getPhysicalNumberOfCells(); k++) {//获取每个单元格
	                    System.out.print(row.getCell(k)+"\t");
	                }
	                System.out.println("---Sheet表"+i+"处理完毕---");
	            }}*/
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
	 * 导出扣费exportsPrice
	 */
	@RequestMapping(value = "/preivew/preivew/exportsPrice",method=RequestMethod.POST)
	@ResponseBody
	public void exportsPrice(String idList,HttpServletResponse response) {
	        response.setContentType("application/vnd.ms-excel");
	        System.out.println(idList);
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
	        fileName +=y+m+d+h+mi+s;
	        response.setHeader("Content-disposition", "attachment;filename=price-"+fileName+".xlsx;charset=UTF-8");
	        XSSFWorkbook workbook = excelService.exportsPrices(idList);//idList
	        /*XSSFSheet sheet=null;
	        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {//获取每个Sheet表
	             sheet=workbook.getSheetAt(i);
	             for (int j = 0; j < sheet.getPhysicalNumberOfRows(); j++) {//获取每行
	                XSSFRow row=sheet.getRow(j);
	                for (int k = 0; k < row.getPhysicalNumberOfCells(); k++) {//获取每个单元格
	                    System.out.print(row.getCell(k)+"\t");
	                }
	                System.out.println("---Sheet表"+i+"处理完毕---");
	            }}*/
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
}