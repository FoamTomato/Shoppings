package com.sybinal.shop.common;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 顺丰速运条码生成工具<br/>
 * 采用 code128c编码规则<br/>
 * <pre>
 *    CODE128C:[00]-[99]的数字对集合,共100个
 *    即只能表示偶数位长度的数字
 * </pre>
 * @author wu
 * @version 1.0
 * @Time 2017/03/29
 * */
public class SFBarCodeGenerateUtil {
    
    private static final Logger logger = Logger.getLogger(SFBarCodeGenerateUtil.class.getSimpleName());
    
    /**图片格式  jpg 格式*/
    public static final String PICTURE_JPG = "JPG";
    /**图片格式  png 格式*/
    public static final String PICTURE_PNG = "PNG";
    /**图片格式  gif 格式*/
    public static final String PICTURE_GIF = "GIF";
     
    /** code128编码字符集  二维数组*/
    private static String[][] code128 = {
            { " ", " ", "00", "212222", "11011001100" },
            { "!", "!", "01", "222122", "11001101100" },
            { "\"", "\"", "02", "222221", "11001100110" },
            { "#", "#", "03", "121223", "10010011000" },
            { "$", "$", "04", "121322", "10010001100" },
            { "%", "%", "05", "131222", "10001001100" },
            { "&", "&", "06", "122213", "10011001000" },
            { "'", "'", "07", "122312", "10011000100" },
            { "(", "(", "08", "132212", "10001100100" },
            { ")", ")", "09", "221213", "11001001000" },
            { "*", "*", "10", "221312", "11001000100" },
            { "+", "+", "11", "231212", "11000100100" },
            { ",", ",", "12", "112232", "10110011100" },
            { "-", "-", "13", "122132", "10011011100" },
            { ".", ".", "14", "122231", "10011001110" },
            { "/", "/", "15", "113222", "10111001100" },
            { "0", "0", "16", "123122", "10011101100" },
            { "1", "1", "17", "123221", "10011100110" },
            { "2", "2", "18", "223211", "11001110010" },
            { "3", "3", "19", "221132", "11001011100" },
            { "4", "4", "20", "221231", "11001001110" },
            { "5", "5", "21", "213212", "11011100100" },
            { "6", "6", "22", "223112", "11001110100" },
            { "7", "7", "23", "312131", "11101101110" },
            { "8", "8", "24", "311222", "11101001100" },
            { "9", "9", "25", "321122", "11100101100" },
            { ":", ":", "26", "321221", "11100100110" },
            { ";", ";", "27", "312212", "11101100100" },
            { "<", "<", "28", "322112", "11100110100" },
            { "=", "=", "29", "322211", "11100110010" },
            { ">", ">", "30", "212123", "11011011000" },
             { "?", "?", "31", "212321", "11011000110" },
            { "@", "@", "32", "232121", "11000110110" },
            { "A", "A", "33", "111323", "10100011000" },
            { "B", "B", "34", "131123", "10001011000" },
            { "C", "C", "35", "131321", "10001000110" },
            { "D", "D", "36", "112313", "10110001000" },
            { "E", "E", "37", "132113", "10001101000" },
            { "F", "F", "38", "132311", "10001100010" },
            { "G", "G", "39", "211313", "11010001000" },
            { "H", "H", "40", "231113", "11000101000" },
            { "I", "I", "41", "231311", "11000100010" },
            { "J", "J", "42", "112133", "10110111000" },
            { "K", "K", "43", "112331", "10110001110" },
            { "L", "L", "44", "132131", "10001101110" },
            { "M", "M", "45", "113123", "10111011000" },
            { "N", "N", "46", "113321", "10111000110" },
            { "O", "O", "47", "133121", "10001110110" },
            { "P", "P", "48", "313121", "11101110110" },
            { "Q", "Q", "49", "211331", "11010001110" },
            { "R", "R", "50", "231131", "11000101110" },
            { "S", "S", "51", "213113", "11011101000" },
            { "T", "T", "52", "213311", "11011100010" },
            { "U", "U", "53", "213131", "11011101110" },
            { "V", "V", "54", "311123", "11101011000" },
            { "W", "W", "55", "311321", "11101000110" },
            { "X", "X", "56", "331121", "11100010110" },
            { "Y", "Y", "57", "312113", "11101101000" },
            { "Z", "Z", "58", "312311", "11101100010" },
            { "[", "[", "59", "332111", "11100011010" },
            { "\\", "\\", "60", "314111", "11101111010" },
            { "]", "]", "61", "221411", "11001000010" },
            { "^", "^", "62", "431111", "11110001010" },
            { "_", "_", "63", "111224", "10100110000" },
            { "NUL", "`", "64", "111422", "10100001100" },
            { "SOH", "a", "65", "121124", "10010110000" },
            { "STX", "b", "66", "121421", "10010000110" },
            { "ETX", "c", "67", "141122", "10000101100" },
            { "EOT", "d", "68", "141221", "10000100110" },
            { "ENQ", "e", "69", "112214", "10110010000" },
            { "ACK", "f", "70", "112412", "10110000100" },
            { "BEL", "g", "71", "122114", "10011010000" },
            { "BS", "h", "72", "122411", "10011000010" },
            { "HT", "i", "73", "142112", "10000110100" },
            { "LF", "j", "74", "142211", "10000110010" },
            { "VT", "k", "75", "241211", "11000010010" },
            { "FF", "I", "76", "221114", "11001010000" },
            { "CR", "m", "77", "413111", "11110111010" },
            { "SO", "n", "78", "241112", "11000010100" },
            { "SI", "o", "79", "134111", "10001111010" },
            { "DLE", "p", "80", "111242", "10100111100" },
            { "DC1", "q", "81", "121142", "10010111100" },
            { "DC2", "r", "82", "121241", "10010011110" },
            { "DC3", "s", "83", "114212", "10111100100" },
            { "DC4", "t", "84", "124112", "10011110100" },
            { "NAK", "u", "85", "124211", "10011110010" },
            { "SYN", "v", "86", "411212", "11110100100" },
            { "ETB", "w", "87", "421112", "11110010100" },
            { "CAN", "x", "88", "421211", "11110010010" },
            { "EM", "y", "89", "212141", "11011011110" },
            { "SUB", "z", "90", "214121", "11011110110" },
            { "ESC", "{", "91", "412121", "11110110110" },
            { "FS", "|", "92", "111143", "10101111000" },
            { "GS", "},", "93", "111341", "10100011110" },
            { "RS", "~", "94", "131141", "10001011110" },
            { "US", "DEL", "95", "114113", "10111101000" },
            { "FNC3", "FNC3", "96", "114311", "10111100010" },
            { "FNC2", "FNC2", "97", "411113", "11110101000" },
            { "SHIFT", "SHIFT", "98", "411311", "11110100010" },
            { "CODEC", "CODEC", "99", "113141", "10111011110" },
            { "CODEB", "FNC4", "CODEB", "114131", "10111101110" },
            { "FNC4", "CODEA", "CODEA", "311141", "11101011110" },
            { "FNC1", "FNC1", "FNC1", "411131", "11110101110" },
            { "StartA", "StartA", "StartA", "211412", "11010000100" },
            { "StartB", "StartB", "StartB", "211214", "11010010000" },
            { "StartC", "StartC", "StartC", "211232", "11010011100" },
            { "Stop", "Stop", "Stop", "2331112", "1100011101011" }, 
    };
    
    /**
    * 生产Code128的条形码的code
    * @param barCode 生成条码的数字号码
    * @return
    */
    private static String getCode(String barCode) {
        String rtnCode = "";// 返回的参数
        List<Integer> rtnCodeNumb = new ArrayList<Integer>();// 2截取位的组合
        int examine = 105; // 首位
        // 编码不能是奇数
        if (!((barCode.length() & 1) == 0))
            return "";
        while (barCode.length() != 0) {
            int temp = 0;
            try {
               // Code128 编码必须为数字
               temp = Integer.valueOf(barCode.substring(0, 2));
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
            // 获得条纹
            rtnCode += getValue(barCode, barCode.substring(0, 2), temp);
            rtnCodeNumb.add(temp);
            // 条码截取2个就需要去掉用过的前二位
            barCode = barCode.substring(2);
        }
        if (rtnCodeNumb.size() == 0) {
           return "";
        }
        rtnCode = getValue(examine) + rtnCode; // 获取开始位
        for (int i = 0; i != rtnCodeNumb.size(); i++) {
          examine += rtnCodeNumb.get(i) * (i + 1);
        }
        examine = examine % 103; // 获得校验位
        rtnCode += getValue(examine); // 获取校验位
        rtnCode += "1100011101011"; // 结束位
        return rtnCode;
    }
    
    /**
    * 根据编号获得条纹
    * 
    * @param encode
    * @param p_Value
    * @param p_SetID
    * @return
    */
    private static String getValue(String encode, String p_Value, int p_SetID) {
       return code128[p_SetID][4];
    }
    
    /**
     * 根据编号获得条纹
     * @param p_CodeId
     * @return
     */
    private static String getValue(int p_CodeId) {
        return code128[p_CodeId][4];
    }

    // 条码的高度像素数
    private static int m_nImageHeight = 40; 
    
    /** 
     * 生成条码
     * @param barString 条码模式字符串
     * @param path 生成条码图片的路径
     */
    private static boolean kiCode128C(String barString, String path) {
        OutputStream out = null;
        try {
                File myPNG = new File(path);
                out = new FileOutputStream(myPNG);
                int nImageWidth = 0;
                char[] cs = barString.toCharArray();
                for (int i = 0; i != cs.length; i++) {
                    nImageWidth = cs.length;
                }
                BufferedImage bi = new BufferedImage(nImageWidth, m_nImageHeight,
                BufferedImage.TYPE_INT_RGB);
                Graphics g = bi.getGraphics();
                for (int i = 0; i < cs.length; i++) {
                    if ("1".equals(cs[i] + "")) {
                        g.setColor(Color.BLACK);
                        g.fillRect(i, 0, 1, m_nImageHeight);
                    } else {
                        g.setColor(Color.WHITE);
                        g.fillRect(i, 0, 1, m_nImageHeight);
                    }
                }
                JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
                encoder.encode(bi);
                return true;
        } catch (FileNotFoundException fe) {
            logger.error("系统找不到指定路径！！" + path, fe);
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }
 
    /**
     * 生成条形码<br/>
     * 条码是图片格式（JPG、PNG、GIF）
     * @param wayBillNo 
     *            运单号
     * @param generatePathName 
     *            生成条形码路径及名称
     * @param width 
     *            条码图片宽度
     * @param height
     *            条码图片高度
     * @param picTyp
     *            图片格式<br/>
     *            JPG、PNG、GIF三种图片类型选择
     * @return boolean类型值  
     *            true 生成成功，false 生成失败
     * */
    public static boolean generateBarCode(String wayBillNo, String generatePathName, int width, 
        int height, String picTyp){
        //只能是数字
        if (!Pattern.matches("[0-9]+", wayBillNo)) {
            logger.error("生成CODE128C条码只能是0~9的数字不能有其他字符！！");
        //运单号长度只能是偶数
        } else if (wayBillNo.length() % 2 != 0) {
            logger.error("生成CODE128C条码的长度只能是偶数！！");
        //生成条码
        } else if (kiCode128C(getCode(wayBillNo), generatePathName)){
            /**
             * 设置条码图片的尺寸
             * */
            BufferedInputStream bis = null;
            BufferedOutputStream out = null;
            try {
                File sfFile = new File(generatePathName);
                if (sfFile.isFile() && sfFile.exists()) {
                    //读取图片
                    bis = new BufferedInputStream(new FileInputStream(generatePathName));
                    //转换成图片对象
                    Image bi = ImageIO.read(bis);
                    //构建图片流 设置图片宽和高
                    BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                    //绘制改变尺寸后的图
                    tag.getGraphics().drawImage(bi, 0, 0,width, height, null);
                    //保存图片
                    out = new BufferedOutputStream(new FileOutputStream(generatePathName));
                    ImageIO.write(tag, picTyp,out);
                    out.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (out != null) 
                        out.close();
                    if (bis != null)
                        bis.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            logger.info("条码生成成功. " + generatePathName + " 像素：" + width + "*" + height);
            return true;
        } 
        logger.error("条码生成失败！");
        return false;
    }
    
}