package com.sybinal.shop.common;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sybinal.shop.model.SfPrintOrderParam;

/**
 * 生成电子面单图片工具
 * @author wu
 * @version 1.0
 * @time 2017/04/25
 * */
public class SFOrderGenerateUtil {
    
     private static final Logger logger = Logger.getLogger(SFOrderGenerateUtil.class.getSimpleName());
    
     //图片的宽度
     public static final int IMG_WIDTH = 1500;
     //图片的宽度
     public static final int IMG_HEIGHT = 1500;
     //LOGO的宽度
     public static final int LOGO_WIDTH = 240; 
     //LOGO高度
     public static final int LOGO_HEIGHT = 100; 
     //LOGO客服电话的宽度
     public static final int LOGO_TEL_WIDTH = 220; 
     //LOGO客服电话高度
     public static final int LOGO_TEL_HEIGHT = 84; 
     
     //Logo路径
     public static final String LOGO_PATH = "C:\\Users\\Administrator\\Desktop\\expressLogo\\logoSC.png";
     //Logo客服电话
     public static final String LOGO_TEL_PATH = "C:\\Users\\Administrator\\Desktop\\expressLogo\\sf_Tel.png";;
     
    
     public static BufferedImage image;  
     public static void createImage(String fileLocation) {  
         FileOutputStream fos = null;
         BufferedOutputStream bos = null;
         try {  
              fos = new FileOutputStream(fileLocation);  
              bos = new BufferedOutputStream(fos);  
              JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bos);  
              encoder.encode(image);  
         } catch (Exception e) {  
               e.printStackTrace();  
         } finally {
             try {
                 if (bos != null) bos.close();
                 if (fos != null) fos.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
         }
    }  
   public static void main( String[] args )
    {
        SfPrintOrderParam param = new SfPrintOrderParam();
        
        /*param.setMailNo("883987638272");
        param.setEarthCarryFlag("E");
        param.setDestCode("0311");
        param.setdContact("李XX");
        param.setdCompany("河北北国商城");
        param.setdMobile("13588911223");
        param.setdProvince("河北省");
        param.setdCity("石家庄市");
        param.setdCounty("桥西区");
        param.setdAddress("华润万象城");
        param.setjContact("吴XX");
        param.setjMobile("13716533121");
        param.setjCompany("北京XXX科技公司");
        param.setjProvince("北京");
        param.setjCity("北京市");
        param.setjCounty("朝阳区");
        param.setjCounty("东三环24号XXX大厦");*/
        
        
        SFOrderGenerateUtil.generateParentOrder(
                  "F:\\Foam番茄日志\\img\\",
                  param,
                  "2", 
                  false,
                  600, 
                  400);
  }
    
     
    /**
     * 插入图片 自定义图片的宽高
     * @param imgPath 
     *             插入图片的路径
     * @param imgWidth 
     *             设置图片的宽度
     * @param imgHeight 
     *             设置图片的高度
     * @param isCompress 
     *             是否按输入的宽高定义图片的尺寸，只有为true时 输入的宽度和高度才起作用<br/>
     *             为false时输入的宽高不起作用，按输入图片的默认尺寸
     * @return
     * @throws Exception
     * */
    private static Image insertImage(String imgPath, int imgWidth, int imgHeight, boolean isCompress) throws Exception {
        File fileimage = new File(imgPath); 
        Image src = ImageIO.read(fileimage);
        if (isCompress) {
            Image image = src.getScaledInstance(imgWidth, imgHeight, Image.SCALE_SMOOTH);
            return image;
        }
        return src;
    }
    
    /**
     * 生成母订单
     * @param orderParam 生成订单所需的参数对象
     * @return boolean
     * */
    public static String generateParentOrder(String orderPath, SfPrintOrderParam orderParam, String printTyp, boolean isCompress, int imgWidth, int imgHeidht){
        if (null == orderParam)
            return null;
        String picPath = orderPath;
        int startHeight = 0;  //表格的起始高度
        int startWidth = 0;   //表格的起始宽度
        try {
                image = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, BufferedImage.TYPE_INT_RGB);
                Graphics2D g = image.createGraphics();
                //消除文字锯齿
               // g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                //消除画图锯齿
               // g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                // 抗锯齿 添加文字
                g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
                g.setFont(new Font("微软雅黑", Font.PLAIN, 14));
                //以运单号为名称创建存放订单的目录
                File mk = new File(picPath + orderParam.getMailNo());
                if (mk.exists()) {
                    FileUtils.deleteDirectory(mk);
                }
                FileUtils.forceMkdir(mk);
                
                picPath += orderParam.getMailNo() + "/";
                
                //设置背景色为白色
                g.setColor(Color.WHITE);
                //设置颜色区域大小
                g.fillRect(0, 0, IMG_WIDTH, IMG_HEIGHT); 
                
                /*
                 * 绘制表格 填充内容
                 * */
                //表格线条的颜色
                g.setColor(Color.BLACK);
                //边框加粗
                g.setStroke(new BasicStroke(2.0f));
                //消除文本出现锯齿现象   
                g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                //表格的四个边框
                g.drawLine(startWidth+150, startHeight, startWidth+150 + 1198, startHeight); //上边框
                
                g.drawLine(startWidth+150, startHeight, startWidth+150, startHeight + 1800); //左边框
                g.drawLine(startWidth+150, startHeight + 1799, startWidth+150 + 1198, startHeight + 1799); //下边框
                g.drawLine(startWidth+150+ 1197, startHeight, startWidth+150 + 1197, startHeight + 1800); //右边框
                
                //绘制第二行
                g.drawLine(startWidth+150, startHeight + 80, startWidth+150 + 1198, startHeight + 80);
                //g.drawLine(startWidth+150 + 750, startHeight + 155, startWidth+150 + 750, startHeight + 396);
                
                //生成code128c 条码

                BarcodeUtil.generateFile(orderParam.getMailNo(), picPath + "SFBarCoding_" + orderParam.getMailNo() + ".jpg" //图片名称
                        );
                //导入条码图片
                Image sfBarImg = insertImage(picPath + "SFBarCoding_" + orderParam.getMailNo() + ".jpg", 500, 120, true);
                g.drawImage(sfBarImg, startWidth+150 + 350, startHeight + 170, null);
                
                //设置字体  
                Font fontSfBarCode = new Font("微软雅黑",Font.BOLD,35);  
                g.setFont(fontSfBarCode);
            
                String sfBarCodeStr = orderParam.getMailNo();
                sfBarCodeStr = sfBarCodeStr.replaceAll("(.{3})", "$1 ");
                g.drawString("集拼单号  ", startWidth+150 + 530, startHeight + 140);
                
                //绘制产品类型框
                g.drawLine(startWidth+150, startHeight + 300, startWidth+150 + 1198, startHeight + 300);
                Font fontSfProtypSub = new Font("微软雅黑", Font.BOLD, 50);
                g.setFont(fontSfProtypSub);
                
                  //产品类型字符串
               // String subSfProTypStr = orderParam.getExpressTyp();
                //g.drawString(subSfProTypStr, startWidth+150 + 850, startHeight + 220);

                /*
                 * ========================================================================
                 */
                //物流渠道的绘制
                g.drawLine(startWidth+150, startHeight + 460, startWidth+150 + 1198, startHeight + 460);
                g.drawLine(startWidth+150 + 300, startHeight + 300, startWidth+150 + 300, startHeight + 460);
                
                //物流渠道填写
                Font fontDest = new Font("微软雅黑", Font.BOLD, 40);
                g.setFont(fontDest);
                //物流渠道标题
                String destTitleStr = "物流渠道";
                char[] destTitleArray = destTitleStr.toCharArray();
                int destTitleWidth = startWidth+150 + 60;
                int destTitleHeight = startHeight + 400;
                
                g.drawString(String.valueOf(destTitleArray), destTitleWidth, destTitleHeight);
                //物流渠道内容
                Font fontDestCode = new Font("微软雅黑", Font.BOLD, 50);
                g.setFont(fontDestCode);
                //物流渠道字符串
                String destCode = orderParam.getLogisticsChannel();
                g.drawString(destCode, startWidth+150 + 360, startHeight + 400);

                /*
                 * ========================================================================
                 */
                //渠道袋序表格栏
                g.drawLine(startWidth+150, startHeight + 620, startWidth+150 + 1198, startHeight + 620);
                g.drawLine(startWidth+150 + 300, startHeight + 460, startWidth+150 + 300, startHeight + 620);
                
                //设置渠道袋序标题字体
                Font fontRevicer = new Font("微软雅黑", Font.BOLD, 40);
                g.setFont(fontRevicer);
                //渠道袋序标题字符串
                String revicerTitleStr = "渠道袋序";
                char[] revicerTitleArray = revicerTitleStr.toCharArray();
                int revicerTitleWidth = startWidth+150 + 60;
                int revicerTitleHeight = startHeight + 560;
                
                g.drawString(String.valueOf(revicerTitleArray), revicerTitleWidth, revicerTitleHeight);
                //渠道袋序内容赋值
                String revicerInfo = orderParam.getChannelOrder();
                
                //渠道袋序内容
                Font fontRevicerInfo = new Font("微软雅黑", Font.BOLD, 50);
                g.setFont(fontRevicerInfo);
                g.drawString(revicerInfo, startWidth+150 + 360, startHeight + 560);
                /*
                 * ========================================================================
                 */
                //袋内件数表格
                g.drawLine(startWidth+150, startHeight + 780, startWidth+150 + 1198, startHeight +780);
                g.drawLine(startWidth+150 + 300, startHeight + 620, startWidth+150 + 300, startHeight + 780);
                
                //袋内件数标题字体
                Font fontSender = new Font("微软雅黑", Font.BOLD, 40);
                g.setFont(fontSender);
                //袋内件数标题字符串
                String senderTitleStr = "袋内件数(个)";
                char[] senderTitleArray = senderTitleStr.toCharArray();
                int senderTitleWidth = startWidth+150 + 30;
                int senderTitleHeight = startHeight + 720;

                g.drawString(String.valueOf(senderTitleArray), senderTitleWidth, senderTitleHeight);
                //袋内件数赋值
                String senderInfo = orderParam.getPackNum();
                
                //袋内件数信息字体
                Font fontSenderInfo = new Font("微软雅黑", Font.PLAIN, 50);
                g.setFont(fontSenderInfo);
                g.drawString(senderInfo, startWidth+150 + 360, startHeight + 720);

                /*
                 * ========================================================================
                 */
                //净重表格
                g.drawLine(startWidth+150, startHeight + 940, startWidth+150 + 1198, startHeight +940);
                g.drawLine(startWidth+150 + 300, startHeight + 780, startWidth+150 + 300, startHeight + 940);
                
                //净重标题字体
                Font jfontSender = new Font("微软雅黑", Font.BOLD, 40);
                g.setFont(jfontSender);
                //净重标题字符串
                String jsenderTitleStr = "净重(KG)";
                char[] jsenderTitleArray = jsenderTitleStr.toCharArray();
                int jsenderTitleWidth = startWidth+150 + 60;
                int jsenderTitleHeight = startHeight + 880;

                g.drawString(String.valueOf(jsenderTitleArray), jsenderTitleWidth, jsenderTitleHeight);
                //净重赋值
                String jsenderInfo = orderParam.getJweight();
                
                //净重字体
                Font jfontSenderInfo = new Font("微软雅黑", Font.PLAIN, 50);
                g.setFont(jfontSenderInfo);
                g.drawString(jsenderInfo, startWidth+150 + 360, startHeight + 880);
                /*
                 * ========================================================================
                 */
                //毛重表格
                g.drawLine(startWidth+150, startHeight + 1100, startWidth+150 + 1198, startHeight +1100);
                g.drawLine(startWidth+150 + 300, startHeight + 940, startWidth+150 + 300, startHeight + 1100);
                
                //毛重标题字体
                Font mfontSender = new Font("微软雅黑", Font.BOLD, 40);
                g.setFont(mfontSender);
                //毛重标题字符串
                String msenderTitleStr = "毛重(KG)";
                char[] msenderTitleArray = msenderTitleStr.toCharArray();
                int msenderTitleWidth = startWidth+150 + 60;
                int msenderTitleHeight = startHeight + 1040;

                g.drawString(String.valueOf(msenderTitleArray), msenderTitleWidth, msenderTitleHeight);
                //毛重赋值
                String msenderInfo = orderParam.getMweight();
                
                //毛重字体
                Font mfontSenderInfo = new Font("微软雅黑", Font.PLAIN, 50);
                g.setFont(mfontSenderInfo);
                g.drawString(msenderInfo, startWidth+150 + 360, startHeight + 1040);
                /*
                 * ========================================================================
                 */
                //操作员表格
                g.drawLine(startWidth+150, startHeight + 1260, startWidth+150 + 1198, startHeight +1260);
                g.drawLine(startWidth+150 + 300, startHeight + 1100, startWidth+150 + 300, startHeight + 1260);
                
                //操作员标题字体
                Font czfontSender = new Font("微软雅黑", Font.BOLD, 40);
                g.setFont(czfontSender);
                //操作员标题字符串
                String czsenderTitleStr = "操作员";
                char[] czsenderTitleArray = czsenderTitleStr.toCharArray();
                int czsenderTitleWidth = startWidth+150 + 80;
                int czsenderTitleHeight = startHeight + 1200;

                g.drawString(String.valueOf(czsenderTitleArray), czsenderTitleWidth, czsenderTitleHeight);
                //操作员赋值
                String czsenderInfo = orderParam.getAdminUser();
                
                //操作员字体
                Font czfontSenderInfo = new Font("微软雅黑", Font.PLAIN, 50);
                g.setFont(czfontSenderInfo);
                g.drawString(czsenderInfo, startWidth+150 + 360, startHeight + 1200);
               
                
                /*
                 * ========================================================================
                 */
                //操作时间表格
                g.drawLine(startWidth+150, startHeight + 1420, startWidth+150 + 1198, startHeight +1420);
                g.drawLine(startWidth+150 + 300, startHeight + 1260, startWidth+150 + 300, startHeight + 1420);
                
                //操作时间标题字体
                Font sjfontSender = new Font("微软雅黑", Font.BOLD, 40);
                g.setFont(sjfontSender);
                //操作时间标题字符串
                String sjsenderTitleStr = "操作时间";
                char[] sjsenderTitleArray = sjsenderTitleStr.toCharArray();
                int sjsenderTitleWidth = startWidth+150 + 60;
                int sjsenderTitleHeight = startHeight + 1360;

                g.drawString(String.valueOf(sjsenderTitleArray), sjsenderTitleWidth, sjsenderTitleHeight);
                //操作时间赋值
                String sjsenderInfo = orderParam.getTime();
                
                //操作时间字体
                Font sjfontSenderInfo = new Font("微软雅黑", Font.PLAIN, 50);
                g.setFont(sjfontSenderInfo);
                g.drawString(sjsenderInfo, startWidth+150 + 360, startHeight + 1360);
                /*
                 * ========================================================================
                 */
                //结束线
                
                //生成订单图片
                createImage(picPath + "SfOrderImage_" + orderParam.getMailNo() + ".jpg");
                if (isCompress) {
                    compressImg(picPath + "SfOrderImage_" + orderParam.getMailNo() + ".jpg", imgWidth, imgHeidht);
                }
                logger.info("订单生成成功. " + picPath + "SfOrderImage_" + orderParam.getMailNo() + ".jpg");
                return orderParam.getMailNo()+"/" +"SfOrderImage_" + orderParam.getMailNo() + ".jpg";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 水平翻转图像 顺时针旋转90度、左右翻转
     * @param bufferedimage 目标图像
     * @return 
     */
    private static BufferedImage rotate90DX(BufferedImage bi)
    {
        int width = bi.getWidth();
        int height = bi.getHeight();
         
        BufferedImage biFlip = new BufferedImage(height, width, bi.getType());
         
        for(int i=0; i<width; i++)
            for(int j=0; j<height; j++)
                biFlip.setRGB(height-1-j, width-1-i, bi.getRGB(i, j));
         
        return biFlip;
    }


    /**
     * 水平翻转图像 逆时针旋转90度、左右翻转
     * @param bufferedimage 目标图像
     * @return
     */
    private static BufferedImage rotate90SX(BufferedImage bi)
    {
        int width = bi.getWidth();
        int height = bi.getHeight();
         
        BufferedImage biFlip = new BufferedImage(height, width, bi.getType());
         
        for(int i=0; i<width; i++)
            for(int j=0; j<height; j++)
                biFlip.setRGB(j, i, bi.getRGB(i, j));
         
        return biFlip;
    }
    
    /**
     * 根据规定尺寸压缩图片
     * @param imgPath 图片路径
     * @param width 图片宽度
     * @param height 图片高度
     * */
    private static void compressImg(String imgPath, int width, int height){
        /**
         * 设置条码图片的尺寸
         * */
        BufferedInputStream bis = null;
        BufferedOutputStream out = null;
        FileOutputStream fis = null;
        try {
            File sfFile = new File(imgPath);
            if (sfFile.isFile() && sfFile.exists()) {
                //读取图片
                bis = new BufferedInputStream(new FileInputStream(imgPath));
                //转换成图片对象
                Image bi = ImageIO.read(bis).getScaledInstance(width, height, Image.SCALE_SMOOTH);
                //构建图片流 设置图片宽和高
                BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                //绘制改变尺寸后的图
                tag.getGraphics().drawImage(bi, 0, 0,width, height, null);
                //保存图片
                fis = new FileOutputStream(imgPath);
                JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fis);
                JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
                //设置压缩图片质量
                jep.setQuality(100f, true);
                jep.setDensityUnit(jep.DENSITY_UNIT_DOTS_INCH);
                jep.setXDensity(300);
                jep.setYDensity(300);
                encoder.encode(tag, jep);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) fis.close();
                if (out != null) out.close();
                if (bis != null) bis.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
    
    //创建目录
    private static boolean createDir(String destDirName){
        try {
            File file = new File(destDirName);
            if (destDirName.endsWith(File.separator))
                destDirName += File.separator;
            if (file.mkdir()) 
                return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    //删除目录
    public static boolean removeDir(File dir){
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                return removeDir(file);
            } else {
                return file.delete();
            }
        }
        return dir.delete();
    }
    
    //删除目录
    public static boolean deleteMk(String dirPath){
        File dirFile = new File(dirPath);
        if (dirFile.isDirectory()) {
            File[] files = dirFile.listFiles();
            for (int i = 0; i < files.length; i++) {
                files[i].delete();
            }
        }
        return dirFile.delete();
    }
    
    /**
      * 把文件转换为byte[]
      * @param filePath 文件路径
      * */
     public static byte[] getFileBytes(String filePath){  
            byte[] buffer = null;  
            FileInputStream fis = null;
            ByteArrayOutputStream bos = null;
            try {  
                File file = new File(filePath);  
                fis = new FileInputStream(file);  
                bos = new ByteArrayOutputStream(1000);
                byte[] b = new byte[1000];  
                int n;  
                while ((n = fis.read(b)) != -1) {  
                    bos.write(b, 0, n);  
                }  
                buffer = bos.toByteArray();
            } catch (FileNotFoundException e) {  
                e.printStackTrace();  
            } catch (IOException e) {  
                e.printStackTrace();  
            } finally {
                try {
                    if (bos != null) {
                        bos.close();
                    }
                    if (fis != null) {
                        fis.close();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } 
            return buffer;  
    }
}