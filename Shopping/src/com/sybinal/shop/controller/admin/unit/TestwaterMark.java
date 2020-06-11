package com.sybinal.shop.controller.admin.unit;

import java.awt.FontMetrics;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JLabel;

import org.apache.log4j.Logger;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class TestwaterMark {
	private static int interval = -5;
	private static Logger logger = Logger.getLogger(TestwaterMark.class);

	public static void waterMark(String inputFile, String outputFile, String waterMarkName, String bg) {
		try {
			// 删除已存在文件
			File filesw = new File(outputFile);
			if (filesw.exists()) {
				deleteIfExists(new File(outputFile));
			}
			PdfReader reader = new PdfReader(inputFile);
			PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(new File(outputFile)));
			BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
			//BaseFont base = BaseFont.createFont("STSong-Light", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			
			Rectangle pageRect = null;
			PdfGState gs = new PdfGState();
			// 设置透明度 
			gs.setFillOpacity(1f);
			gs.setStrokeOpacity(1f);
 
			int total = reader.getNumberOfPages() + 1;

			JLabel label = new JLabel(waterMarkName);
			FontMetrics metrics;
			int textH = 0;
			int textW = 0;
			//label.setText();
			metrics = label.getFontMetrics(label.getFont());
			textH = metrics.getHeight();
			textW = metrics.stringWidth(label.getText());

			PdfContentByte under;
			for (int i = 1; i < total; i++) {
				pageRect = reader.getPageSizeWithRotation(i);
				under = stamper.getOverContent(i);
				under.saveState();
				under.setGState(gs);
				under.beginText();

				// 设置水印颜色
				under.setColorFill(BaseColor.BLACK); 

				switch (bg) {
				case "cm_dd_sz":
					// 中邮小包/线下平邮（带电）
					under.setFontAndSize(base, 16);
					under.showTextAligned(Element.ALIGN_LEFT, waterMarkName, 240, 8, 0);
					break;
				case "cm_sx_sz":
					// 中邮小包/线下平邮（普货）
					under.setFontAndSize(base, 16);
					under.showTextAligned(Element.ALIGN_LEFT, waterMarkName, 240, 8, 0);
					break;
				case "ghxb_dd_sz":
					// 中邮小包/线下挂号（带电）
					under.setFontAndSize(base, 16);
					under.showTextAligned(Element.ALIGN_LEFT, waterMarkName, 180, 8, 0);
					break;
				case "ghxb_sg_sz":
					// 中邮小包/线下挂号（普货）
					under.setFontAndSize(base, 16);
					under.showTextAligned(Element.ALIGN_LEFT, waterMarkName, 180, 8, 0);
					break;
				case "cm_eub_szyjdd":
					// 中邮e邮宝/线下e邮宝（带电）
					under.setFontAndSize(base, 16);
					under.showTextAligned(Element.ALIGN_LEFT, waterMarkName, 180, 6, 0);
					break;
				case "cm_eub_szyj":
					// 中邮e邮宝/线下e邮宝（普货）
					under.setFontAndSize(base, 16);
					under.showTextAligned(Element.ALIGN_LEFT, waterMarkName, 180, 6, 0);
					break;
				case "cm_rb_shhm":
					// 国际专线/日本专线
					under.setFontAndSize(base, 16);
					under.showTextAligned(Element.ALIGN_LEFT, waterMarkName, 240, 10, 0);
					break;
				case "cm_yg_wbsd":
					// 英国专线
					under.setFontAndSize(base, 16);
					under.showTextAligned(Element.ALIGN_LEFT, waterMarkName, 240, 10, 0);
					break;
				case "cm_j_wbsd":
					// 欧洲经济专线挂号跟踪
					under.setFontAndSize(base, 16);
					under.showTextAligned(Element.ALIGN_LEFT, waterMarkName, 240, 10, 0);
					break;
				case "cm_py_wbsd":
					// 欧洲经济专线平邮跟踪
					under.setFontAndSize(base, 16);
					under.showTextAligned(Element.ALIGN_LEFT, waterMarkName, 240, 10, 0);
					break;
				case "cm_spain_wbsd":
					// 西班牙专线
					under.setFontAndSize(base, 16);
					under.showTextAligned(Element.ALIGN_LEFT, waterMarkName, 240, 10, 0);
					break;
				case "cm_it_hh":
					// 意大利专线
					under.setFontAndSize(base, 16);
					under.showTextAligned(Element.ALIGN_LEFT, waterMarkName, 240, 100, 0);
					break;
				case "cm_jna_ltzx":
					// 加拿大专线
					under.setFontAndSize(base, 16);
					under.showTextAligned(Element.ALIGN_LEFT, waterMarkName, 240, 10, 0);
					break;
				case "xn_fg_wbsd":
					// 法国专线
					under.setFontAndSize(base, 16);
					under.showTextAligned(Element.ALIGN_LEFT, waterMarkName, 240, 10, 0);
					break;
				case "cm_dg_shhm":
					// 德国专线
					under.setFontAndSize(base, 16);
					under.showTextAligned(Element.ALIGN_LEFT, waterMarkName, 240, 10, 0);
				case "cm_jy_jnd":
					// 加拿大专线2
					under.setFontAndSize(base, 16);
					under.showTextAligned(Element.ALIGN_LEFT, waterMarkName, 240, 10, 0);
					break;
				case "cm_jnd_jy":
					// 加拿大带电专线
					under.setFontAndSize(base, 16);
					under.showTextAligned(Element.ALIGN_LEFT, waterMarkName, 240, 10, 0);
					break;
				case "xn_fg_hm":
					// 法国带电专线
					under.setFontAndSize(base, 16);
					under.showTextAligned(Element.ALIGN_LEFT, waterMarkName, 240, 40, 0);
					break;
				case "cm_esdd_hh":
					// 西班牙带电专线
					under.setFontAndSize(base, 16);
					under.showTextAligned(Element.ALIGN_LEFT, waterMarkName, 240, 10, 0);
					break;
				case "cm_it_ft":
					// 意大利专线
					under.setFontAndSize(base, 16);
					under.showTextAligned(Element.ALIGN_LEFT, waterMarkName, 240, 10, 0);
					break;
				case "cm_ydl_ft":
					// 意大利专线平邮2
					under.setFontAndSize(base, 16);
					under.showTextAligned(Element.ALIGN_LEFT, waterMarkName, 240, 10, 0);
					break;
				case "xn_dg_hm":
					// 德国带电专线
					under.setFontAndSize(base, 16);
					under.showTextAligned(Element.ALIGN_LEFT, waterMarkName, 240, 10, 0);
					break;
				case "cm_dgdd_bx":
					// 德国带电专线挂号
					under.setFontAndSize(base, 16);
					under.showTextAligned(Element.ALIGN_LEFT, waterMarkName, 240, 40, 0);
					break;
				case "cm_hm_yg":
					// 上海黑猫英国专线
					under.setFontAndSize(base, 16);
					under.showTextAligned(Element.ALIGN_LEFT, waterMarkName, 240, 10, 0);
					break;
				case "cm_uk_hm":
					// 英国带电经济专线
					under.setFontAndSize(base, 16);
					under.showTextAligned(Element.ALIGN_LEFT, waterMarkName, 240, 10, 0);
					break;
				case "cm_alq_jc":
					// 阿联酋专线
					under.setFontAndSize(base, 16);
					under.showTextAligned(Element.ALIGN_LEFT, waterMarkName, 240, 10, 0);
					break;
				case "DY2003110001":
					// 上海黑猫日本专线带电
					under.setFontAndSize(base, 16);
					under.showTextAligned(Element.ALIGN_LEFT, waterMarkName, 240, 10, 0);
					break;
				case "DY2003180001":
					// 上海黑猫法国国际专线挂号
					under.setFontAndSize(base, 16);
					under.showTextAligned(Element.ALIGN_LEFT, waterMarkName, 240, 10, 0);
					break;
				case "DY2003190001":
					// 上海黑猫美国国际专线挂号
					under.setFontAndSize(base, 16);
					under.showTextAligned(Element.ALIGN_LEFT, waterMarkName, 240, 10, 0);
					break;
				}

				// 添加水印文字
				under.endText();
			}
			// 水印文字成30度角倾斜
			// 你可以随心所欲的改你自己想要的角度
			/*
			 * for (int height = interval + textH; height < pageRect.getHeight(); height =
			 * height + textH * 3) { for (int width = interval + textW; width <
			 * pageRect.getWidth() + textW; width = width + textW * 2) {
			 * under.showTextAligned(Element.ALIGN_LEFT, waterMarkName, width - textW,
			 * height - textH, 30); } }
			 */
			// 说三遍
			// 一定不要忘记关闭流
			// 一定不要忘记关闭流
			// 一定不要忘记关闭流
			stamper.close();
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除文件或文件夹
	 */
	public static void deleteIfExists(File file) throws IOException {
		if (file.exists()) {
			if (file.isFile()) {
				if (!file.delete()) {
					throw new IOException("Delete file failure,path:" + file.getAbsolutePath());
				}
			} else {
				File[] files = file.listFiles();
				if (files != null && files.length > 0) {
					for (File temp : files) {
						deleteIfExists(temp);
					}
				}
				if (!file.delete()) {
					throw new IOException("Delete file failure,path:" + file.getAbsolutePath());
				}
			}
		}
	}

	public static void main(String[] args) {
		waterMark("C:/Users/spor-wen10/Desktop/3.pdf",
				"C:/Users/spor-wen10/Desktop/eb392c2f15cf41cab7d5681270269af5nul.pdf", "P001", "ghxb_dd_sz");

	}
}