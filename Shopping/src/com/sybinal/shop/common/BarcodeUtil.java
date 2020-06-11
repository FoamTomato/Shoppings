package com.sybinal.shop.common;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.lang.StringUtils;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;
 
/**
 * 条形码工具类
 *
 * @author tangzz
 * @createDate 2015年9月17日
 *
 */
public class BarcodeUtil {
    /**
     * 生成文件
     *
     * @param msg
     * @param path
     * @return
     */
    public static File generateFile(String msg, String path) {
        File file = new File(path);
        try {
            generate(msg, new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return file;
    }
 
    /**
     * 生成字节
     *
     * @param msg
     * @return
     */
    public static byte[] generate(String msg) {
        ByteArrayOutputStream ous = new ByteArrayOutputStream();
        generate(msg, ous);
        return ous.toByteArray();
    }
 
    /**
     * 生成到流
     *
     * @param msg
     * @param ous
     */
    public static void generate(String msg, OutputStream ous) {
        if (StringUtils.isEmpty(msg) || ous == null) {
            return;
        }

        // 如果想要其他类型的条码(CODE 39, EAN-8...)直接获取相关对象Code39Bean...等等
        //Code39Bean bean = new Code39Bean();
        Code128Bean bean = new Code128Bean();
        
        // 精细度// 分辨率：值越大条码越长，分辨率越高。
        final int dpi = 400;
        // module宽度
        // 设置条码每一条的宽度
        // UnitConv 是barcode4j 提供的单位转换的实体类，用于毫米mm,像素px,英寸in,点pt之间的转换
        final double moduleWidth = UnitConv.in2mm(10.0f / dpi);
 
        // 配置对象
        bean.setModuleWidth(moduleWidth);
        //bean.setWideFactor(3);
        // 设置两侧是否加空白
        bean.doQuietZone(true);
        // 设置图片格式
        String format = "image/png";
        try {
 
            // 输出到流
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(ous, format, dpi,
                    BufferedImage.TYPE_BYTE_BINARY, false, 0);
            
            // 生成条形码
            bean.generateBarcode(canvas, msg);
 
            // 结束绘制
            canvas.finish();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
 
    public static void main(String[] args) {
        String msg = "70255403";
        String path = "D:/barcode.png";
        generateFile(msg, path);
    }
}