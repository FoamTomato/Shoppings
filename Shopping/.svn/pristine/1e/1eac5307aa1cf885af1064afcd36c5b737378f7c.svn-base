package com.sybinal.shop.controller.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/***
 * java抓取网络图片
 * 
 * @author ITWANG
 * 
 */
public class imgClass {

	// 抓去网页地址
	private static final String URL = "https://images-na.ssl-images-amazon.com/images/I/51z5YjC9nhL._SL1024_.jpg";
	// 转换编码
	private static final String ECODING = "UTF-8";
	// 图片后缀
	private static final String[] picstuffix = { "jpg", "JPG", "gif", "GIF", "png", "PNG" };
	// 开启线程数
	private static int Threadcount = 3;
	// 超时时间
	private static int timeout = 4000;

	public static void main(String[] args) throws Exception {
		imgClass cm = new imgClass();
		// 获得html文本内容
		String HTML = cm.getHTML(URL);
		List<String> imgSrc = cm.getttr(HTML, picstuffix);
		List<String> pList = cm.picFilter(imgSrc);
		cm.TOThreadDownload(pList, "E:\\Imagesave" + saveDiff(), Threadcount, timeout);
	}

	/**
	 * 过滤http:\/\/xxxxxx\/xxxx\/xxx.jpg为http://xxxxxx/xxxx/xxx.jpg
	 * 
	 * @param picurl 图片列表
	 * @return 返回过滤后的图片列表
	 */
	public List<String> picFilter(List<String> picurl) {
		List<String> list = new ArrayList<>();
		for (String string : picurl) {
			list.add(string.replace("\\/", "/"));
		}
		return list;
	}

	/**
	 * 获取但网页图片
	 * 
	 * @param htmlsource html的string数据源
	 * @param picstuffix 后缀数组
	 * @return 返回图片地址
	 */
	public List<String> getttr(String htmlsource, String[] picstuffix) {
		List<String> listpic = new ArrayList<>();
		String[] htmlarray1 = htmlsource.split("\"");
		String[] htmlarray2 = htmlsource.split("\'");
		System.out.println("双引号分割：" + htmlarray1.length);
		for (int i = 0; i < htmlarray1.length; i++) {
			for (int j = 0; j < picstuffix.length; j++) {
				if (htmlarray1[i].startsWith("http") && htmlarray1[i].endsWith(picstuffix[j])) {
					listpic.add(htmlarray1[i]);
				}
			}
		}
		System.out.println("单引号分割：" + htmlarray2.length);
		for (int i = 0; i < htmlarray2.length; i++) {
			for (int j = 0; j < picstuffix.length; j++) {
				if (htmlarray2[i].startsWith("http") && htmlarray2[i].endsWith(picstuffix[j])) {
					listpic.add(htmlarray2[i]);
				}
			}
		}
		System.out.println(listpic.size());
		for (String string : listpic) {
			System.out.println(string);
		}
		return listpic;
	}

	/***
	 * 获取HTML内容,并且转为String
	 * 
	 * @param url 网页地址
	 * @return 返回字符串
	 * @throws Exception 连接网络失败
	 */
	private String getHTML(String url) throws Exception {
		URL uri = new URL(url);
		URLConnection connection = uri.openConnection();
		InputStream in = connection.getInputStream();
		byte[] buf = new byte[1024];
		int length = 0;
		StringBuffer sb = new StringBuffer();
		while ((length = in.read(buf, 0, buf.length)) > 0) {
			sb.append(new String(buf, ECODING));
		}
		in.close();
		return sb.toString();
	}

	/**
	 * 多线程超时下载
	 * 
	 * @param listImgSrc 图片地址列表
	 * @param savedir    保存文件夹
	 * @param tnum       开启线程数
	 * @param timeout    下载超时时间
	 */
	private void TOThreadDownload(List<String> listImgSrc, String savedir, int tnum, int timeout) {
		for (int i = 0; i < listImgSrc.size(); i += tnum) {
			new TODThread(savedir, tnum, listImgSrc, i, timeout).start();
		}
	}

	/**
	 * 
	 * 2014-4-3上午10:52:07 Describe: 超时方式下载照片线程
	 * 
	 * @author: ITWANG
	 */
	class TODThread extends Thread {
		private String savedir = null;
		private int tnum;
		private List<String> listImgSrc;
		private int bunm;
		private int timeout = 3000;

		public TODThread(String savedir, int tnum, List<String> listImgSrc, int bnum, int timeout) {
			this.savedir = savedir;
			this.tnum = tnum;
			this.listImgSrc = listImgSrc;
			this.bunm = bnum;
			this.timeout = timeout;
		}

		@Override
		public void run() {
			for (int i = 0; i < tnum; i++) {
				String url = listImgSrc.get(bunm + i);
				String sps = url.substring(url.lastIndexOf("."), url.length());
				String imageName = UUID.randomUUID().toString() + sps;
				try {
					if (getPic(url, savedir, imageName, timeout)) {
						System.out.println("*^_^*");
					} else {
						System.out.println("-_-!");
					}
				} catch (Exception e) {
					System.out.println("下载异常：" + e);
				}
			}
		}
	}

	/**
	 * GET方式下载照片
	 * 
	 * @param purl     图片路径
	 * @param folder   保存文件夹
	 * @param filename 保存文件名
	 * @param timeout  超时时间
	 * @return 返回保存状态
	 * @throws Exception
	 */
	public boolean getPic(String purl, String folder, String filename, int timeout) throws Exception {
		URL url = new URL(purl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(timeout);
		conn.setRequestMethod("GET");
		conn.setRequestProperty("User-Agent",
				"Mozilla/5.0 (Linux; U; Android 4.0.2; en-us; Galaxy Nexus Build/ICL53F) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30");
		conn.setDoOutput(true);
		conn.setDoInput(true);
		if (conn.getResponseCode() == 200) {
			InputStream is = conn.getInputStream();
			byte[] bs = new byte[1024];
			int len;
			File sf = new File(folder);
			if (!sf.exists()) {
				sf.mkdirs();
			}
			OutputStream os = new FileOutputStream(sf.getPath() + "\\" + filename);
			while ((len = is.read(bs)) != -1) {
				os.write(bs, 0, len);
			}
			os.close();
			is.close();
			System.out.println("成功：" + url);
			return true;
		}
		System.out.println("失败：" + url);
		return false;
	}

	/**
	 * 时间文件夹
	 * 
	 * @return 返回当前时间
	 */
	public static String saveDiff() {
		SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		return "\\" + formate.format(System.currentTimeMillis()) + "\\";
	}
}