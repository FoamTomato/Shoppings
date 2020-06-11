package com.sybinal.shop.common;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.sybinal.shop.model.ExcelBean;

public class ExcelUtil {
	private final static String excel2003L =".xls";    //2003- 鐗堟湰鐨別xcel
    private final static String excel2007U =".xlsx";   //2007+ 鐗堟湰鐨別xcel
    /**
     * Excel瀵煎叆
     */
    public static  List<List<Object>> getBankListByExcel(InputStream in, String fileName) throws Exception{
        List<List<Object>> list = null;
        //鍒涘缓Excel宸ヤ綔钖�
        Workbook work = getWorkbook(in,fileName);
        if(null == work){
            throw new Exception("鍒涘缓Excel宸ヤ綔钖勪负绌猴紒");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;
        list = new ArrayList<List<Object>>();
        //閬嶅巻Excel涓墍鏈夌殑sheet
        for (int i = 0; i < work.getNumberOfSheets(); i++) {
            sheet = work.getSheetAt(i);
            if(sheet==null){continue;}
            //閬嶅巻褰撳墠sheet涓殑鎵�鏈夎
            //鍖呮兜澶撮儴锛屾墍浠ヨ灏忎簬绛変簬鏈�鍚庝竴鍒楁暟,杩欓噷涔熷彲浠ュ湪鍒濆鍊煎姞涓婂ご閮ㄨ鏁帮紝浠ヤ究璺宠繃澶撮儴
            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
                //璇诲彇涓�琛�
                row = sheet.getRow(j);
                //鍘绘帀绌鸿鍜岃〃澶�
                if(row==null||row.getFirstCellNum()==j){continue;}
                //閬嶅巻鎵�鏈夌殑鍒�
                List<Object> li = new ArrayList<Object>();
                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                    cell = row.getCell(y);
                    if(cell!=null){
                    	int tag = cell.toString().indexOf(".");
                        if(tag>0){   //鍒ゆ柇鏄惁鍚湁灏忔暟浣�.0
                        	li.add(cell);
                        }else{
                        	li.add(getCellValue(cell));
                        }
                    }else{
                    	li.add(cell);
                    }
                }
                list.add(li);
            }
        }
        return list;
    }
    /**
     * 鎻忚堪锛氭牴鎹枃浠跺悗缂�锛岃嚜閫傚簲涓婁紶鏂囦欢鐨勭増鏈�
     */
    public static  Workbook getWorkbook(InputStream inStr, String fileName) throws Exception{
        Workbook wb = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if(excel2003L.equals(fileType)){
            wb = new HSSFWorkbook(inStr);  //2003-
        }else if(excel2007U.equals(fileType)){
            wb = new XSSFWorkbook(inStr);  //2007+
        }else{
            throw new Exception("瑙ｆ瀽鐨勬枃浠舵牸寮忔湁璇紒");
        }
        return wb;
    }
    /**
     * 鎻忚堪锛氬琛ㄦ牸涓暟鍊艰繘琛屾牸寮忓寲
     */
    public static  Object getCellValue(Cell cell){
        Object value = null;
        DecimalFormat df = new DecimalFormat("0");  //鏍煎紡鍖栧瓧绗︾被鍨嬬殑鏁板瓧
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");  //鏃ユ湡鏍煎紡鍖�
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  //鏃ユ湡鏍煎紡鍖�
        DecimalFormat df2 = new DecimalFormat("0.00");  //鏍煎紡鍖栨暟瀛�
        //鏃ユ湡鏍煎紡 
        //System.out.println(cell.getCellStyle().getDataFormatString());
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING: 
                value = cell.getRichStringCellValue().getString();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if("General".equals(cell.getCellStyle().getDataFormatString())){
                    value = df.format(cell.getNumericCellValue());
                }else if("yyyy/m/d;@".equals(cell.getCellStyle().getDataFormatString())){
                    value = sdf2.format(cell.getDateCellValue());
                }else{ 
                    value = df2.format(cell.getNumericCellValue());
                }
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
            case Cell.CELL_TYPE_BLANK:
                value = "";
                break;
            default:
                break;
        }
        return value;
    }
 
 
    /**
     * 瀵煎叆Excel琛ㄧ粨鏉�
     * 瀵煎嚭Excel琛ㄥ紑濮�
     * @param sheetName 宸ヤ綔绨垮悕绉�
     * @param clazz  鏁版嵁婧恗odel绫诲瀷
     * @param objs   excel鏍囬鍒椾互鍙婂搴攎odel瀛楁鍚�
     * @param map  鏍囬鍒楄鏁颁互鍙奵ell瀛椾綋鏍峰紡
     */
    public static XSSFWorkbook createExcelFile(Class clazz, List objs, Map<Integer, List<ExcelBean>> map, String sheetName) throws
            IllegalArgumentException,IllegalAccessException,InvocationTargetException,
            ClassNotFoundException, IntrospectionException, ParseException {
        // 鍒涘缓鏂扮殑Excel宸ヤ綔绨�
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 鍦‥xcel宸ヤ綔绨夸腑寤轰竴宸ヤ綔琛紝鍏跺悕涓虹己鐪佸��, 涔熷彲浠ユ寚瀹歋heet鍚嶇О
        XSSFSheet sheet = workbook.createSheet(sheetName);
        
        // 浠ヤ笅涓篹xcel鐨勫瓧浣撴牱寮忎互鍙奺xcel鐨勬爣棰樹笌鍐呭鐨勫垱寤猴紝涓嬮潰浼氬叿浣撳垎鏋�;
        createFont(workbook); //瀛椾綋鏍峰紡
        createTableHeader(sheet, map); //鍒涘缓鏍囬锛堝ご锛�
        createTableRows(sheet, map, objs, clazz); //鍒涘缓鍐呭
        return workbook;
    }
    private static XSSFCellStyle fontStyle;
    private static XSSFCellStyle fontStyle2;
    public static void createFont(XSSFWorkbook workbook) {
        // 琛ㄥご
        fontStyle = workbook.createCellStyle();
        XSSFFont font1 = workbook.createFont();
        font1.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        font1.setFontName("榛戜綋");
        font1.setFontHeightInPoints((short) 10);// 璁剧疆瀛椾綋澶у皬
        fontStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(156, 195, 230)));
        fontStyle.setFillPattern(fontStyle.SOLID_FOREGROUND);
        fontStyle.setFont(font1);
        fontStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN); // 涓嬭竟妗�
        fontStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);// 宸﹁竟妗�
        fontStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);// 涓婅竟妗�
        fontStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);// 鍙宠竟妗�
        fontStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 灞呬腑
        // 鍐呭
        fontStyle2=workbook.createCellStyle();
        XSSFFont font2 = workbook.createFont();
        font2.setFontName("瀹嬩綋");
        font2.setFontHeightInPoints((short) 8);// 璁剧疆瀛椾綋澶у皬
        fontStyle2.setFont(font2);
        fontStyle2.setBorderBottom(XSSFCellStyle.BORDER_THIN); // 涓嬭竟妗�
        fontStyle2.setBorderLeft(XSSFCellStyle.BORDER_THIN);// 宸﹁竟妗�
        fontStyle2.setBorderTop(XSSFCellStyle.BORDER_THIN);// 涓婅竟妗�
        fontStyle2.setBorderRight(XSSFCellStyle.BORDER_THIN);// 鍙宠竟妗�
        fontStyle2.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 灞呬腑
    }
    /**
     * 鏍规嵁ExcelMapping 鐢熸垚鍒楀ご锛堝琛屽垪澶达級
     *
     * @param sheet 宸ヤ綔绨�
     * @param map 姣忚姣忎釜鍗曞厓鏍煎搴旂殑鍒楀ご淇℃伅
     */
    public static final void createTableHeader(XSSFSheet sheet, Map<Integer, List<ExcelBean>> map) {
        int startIndex=0;//cell璧峰浣嶇疆
        int endIndex=0;//cell缁堟浣嶇疆
        for (Map.Entry<Integer, List<ExcelBean>> entry : map.entrySet()) {
            XSSFRow row = sheet.createRow(entry.getKey());
            List<ExcelBean> excels = entry.getValue();
            for (int x = 0; x < excels.size(); x++) {
                //鍚堝苟鍗曞厓鏍�
                if(excels.get(x).getCols()>1){
                    if(x==0){
                        endIndex+=excels.get(x).getCols()-1;
                        CellRangeAddress range=new CellRangeAddress(0,0,startIndex,endIndex);
                        sheet.addMergedRegion(range);
                        startIndex+=excels.get(x).getCols();
                    }else{
                        endIndex+=excels.get(x).getCols();
                        CellRangeAddress range=new CellRangeAddress(0,0,startIndex,endIndex);
                        sheet.addMergedRegion(range);
                        startIndex+=excels.get(x).getCols();
                    }
                    XSSFCell cell = row.createCell(startIndex-excels.get(x).getCols());
                    cell.setCellValue(excels.get(x).getHeadTextName());// 璁剧疆鍐呭
                    if (excels.get(x).getCellStyle() != null) {
                        cell.setCellStyle(excels.get(x).getCellStyle());// 璁剧疆鏍煎紡
                    }
                    cell.setCellStyle(fontStyle);
                }else{
                    XSSFCell cell = row.createCell(x);
                    cell.setCellValue(excels.get(x).getHeadTextName());// 璁剧疆鍐呭
                    if (excels.get(x).getCellStyle() != null) {
                        cell.setCellStyle(excels.get(x).getCellStyle());// 璁剧疆鏍煎紡
                    }
                    cell.setCellStyle(fontStyle);
                }
            }
        }
    }
    public static void createTableRows(XSSFSheet sheet, Map<Integer, List<ExcelBean>> map, List objs, Class clazz)
            throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, IntrospectionException,
            ClassNotFoundException, ParseException {
        int rowindex = map.size();
        int maxKey = 0;
        List<ExcelBean> ems = new ArrayList<>();
        for (Map.Entry<Integer, List<ExcelBean>> entry : map.entrySet()) {
            if (entry.getKey() > maxKey) {
                maxKey = entry.getKey();
            }
        }
        ems = map.get(maxKey);
        List<Integer> widths = new ArrayList<Integer>(ems.size());
        for (Object obj : objs) {
            XSSFRow row = sheet.createRow(rowindex);
            for (int i = 0; i < ems.size(); i++) {
                ExcelBean em = ems.get(i);
                // 鑾峰緱get鏂规硶
                PropertyDescriptor pd = new PropertyDescriptor(em.getPropertyName(), clazz);
                Method getMethod = pd.getReadMethod();
                //Method getMethod = pd.getWriteMethod();
                Object rtn = getMethod.invoke(obj);
                String value = "";
                // 濡傛灉鏄棩鏈熺被鍨嬭繘琛岃浆鎹�
                if (rtn != null) {
                    if (rtn instanceof Date) {
                        DateFormat format = new SimpleDateFormat("yyyy-MM-DD");
                        value = format.format(rtn);
                    } else if(rtn instanceof BigDecimal){
                        NumberFormat nf = new DecimalFormat("#,##0.00");
                        value=nf.format(rtn).toString();
                    } else if((rtn instanceof Integer) && (Integer.valueOf(rtn.toString())<0 )){
                        value="--";
                    }else {
                        value = rtn.toString();
                    }
                }
                XSSFCell cell = row.createCell(i);
                cell.setCellValue(value);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellStyle(fontStyle2);
                // 鑾峰緱鏈�澶у垪瀹�
                int width = value.getBytes().length * 300;
                // 杩樻湭璁剧疆锛岃缃綋鍓�
                if (widths.size() <= i) {
                    widths.add(width);
                    continue;
                }
                // 姣斿師鏉ュぇ锛屾洿鏂版暟鎹�
                if (width > widths.get(i)) {
                    widths.set(i, width);
                }
            }
            rowindex++;
        }
        // 璁剧疆鍒楀
        for (int index = 0; index < widths.size(); index++) {
            Integer width = widths.get(index);
            width = width < 2500 ? 2500 : width + 300;
            width = width > 10000 ? 10000 + 300 : width + 300;
            sheet.setColumnWidth(index, width);
        }
    }

}