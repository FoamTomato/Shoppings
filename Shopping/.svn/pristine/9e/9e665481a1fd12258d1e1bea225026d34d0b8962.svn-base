package com.sybinal.shop.controller.api;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sybinal.shop.common.AjaxResult;
import com.sybinal.shop.common.ApiResponseEnum;
import com.sybinal.shop.common.ApiResponseObject;
import com.sybinal.shop.common.SrcUrlImg;
import com.sybinal.shop.model.Comments;
import com.sybinal.shop.model.DTfzyingWithBLOBs;
import com.sybinal.shop.model.DTpic;
import com.sybinal.shop.model.DTstock;
import com.sybinal.shop.model.OnlineDownloadWithBLOBs;
import com.sybinal.shop.service.catalog.CommentsService;
import com.sybinal.shop.service.catalog.DTfzyingService;
import com.sybinal.shop.service.catalog.DTpicService;
import com.sybinal.shop.service.catalog.DTstockService;
import com.sybinal.shop.service.catalog.ProductService;
import com.sybinal.shop.service.onlineDown.OnlineDownService;

@RestController
@RequestMapping("api/v1")
public class ProductApiController extends AbstractApiController {

	@Autowired
	ProductService productService;

	@Autowired
	CommentsService commentsService;

	@Autowired
	DTstockService dTstockService;

	@Autowired
	DTfzyingService dTfzyingService;

	@Autowired
	DTpicService dtpicService;

	@Autowired
	OnlineDownService onlineDownService;

	// ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();//
	// 创建线程池
	// ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(8, 8, 10000,
	// null, null);
	// code
	@RequestMapping(value = "/code", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public int Stock(@RequestBody Map<String, Object> reqMap) throws ParseException {
		System.out.println("post这是code===》" + reqMap.get("code"));

		return 1;

	}

	@RequestMapping(value = "/code", method = RequestMethod.GET)
	@ResponseBody
	public int Stocks(@RequestParam("code") String code) throws ParseException {
		System.out.println("get这是code===》" + code);

		return 1;

	}

	/**
	 * 获取分类商品
	 * 
	 * @return
	 */
	@RequestMapping(value = "catalog/product", method = RequestMethod.POST, headers = "Accept=application/json")
	public ApiResponseObject searchProductByCategory(@RequestBody Map<String, Object> reqMap) {
		return this.reponseJSON(ApiResponseEnum.SUCCESS.getCode(), ApiResponseEnum.SUCCESS.getName(),
				productService.getProductByCategory(reqMap));
	}

	// 添加一个数据
	@RequestMapping(value = "catalog/pr", method = RequestMethod.POST, headers = "Accept=application/json")
	public ApiResponseObject productadd(@RequestBody DTfzyingWithBLOBs dtfzing, HttpServletRequest request)
			throws InterruptedException {

		// ExecutorService pool = Executors.newFixedThreadPool(10);
		// 随机数实例化
		Random rd = new Random();
		// 实例化图片的类
		SrcUrlImg img = new SrcUrlImg();
		// 實例化下載圖片的類
		OnlineDownloadWithBLOBs onlineDownload = new OnlineDownloadWithBLOBs();
		// 设置日期格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		// 设置图片名称命名格式
		SimpleDateFormat sdfs = new SimpleDateFormat("-yyyyMMddHHmmss");
		String[] t1 = null;
		String[] t2 = null;
		// 拼接文件路径
		String imageName = "F:/LibraryImage/" + sdf.format(new Date()) + "/" + dtfzing.getSku() + "/";
		if (dtfzing.getY() != null) {
			DTstock dTstock = new DTstock();
			// 获取文件路径
			File file = new File(imageName);
			if (!file.exists() && !file.isDirectory()) {
				// 文件夹创建批量
				file.mkdirs();
			} else {
				System.out.println("变体创建的文件夹已存在" + file);
			}
			for (int i = 0; i < dtfzing.getY().length; i++) {
				// 添加颜色
				dTstock.setFcolor(dtfzing.getY()[i].getColor());
				// 添加编号
				dTstock.setFno(dtfzing.getY()[i].getNo());
				// 添加价格
				dTstock.setFadd(dtfzing.getY()[i].getAdd());
				// 添加数量
				dTstock.setFnum(dtfzing.getY()[i].getNum());
				// 获取到图片
				String str = dtfzing.getY()[i].getImg();
				// 提取图片到数组
				String[] strs = str.split(",");
				String tp = "";
				// 获取路径并传输到dtstock
				for (int c = 0; c < strs.length; c++) {
					// 图片路径及名称
					String imageNames = imageName + dtfzing.getSku() + sdfs.format(new Date()) + rd.nextInt(100000)
							+ "STOCK.jpg";
					// imgTest ct = new imgTest(strs[c], imageNames);
					// ct.start();

					// Future<?> future = newCachedThreadPool.submit(new imgTest(strs[c],
					// imageNames));// 线程池中提交MyCallable
					// threadPoolExecutor.submit(new imgTest(strs[c], imageNames));
					// 判断任务是否完成
					/*
					 * boolean sc = future.isDone(); System.out.println("stock圖片第" + c + "條"); if
					 * (!sc) { System.out.println("請等待"); }
					 */
					// 把路径丢入多线程
					// pool.submit(new imgTest(strs[c], imageNames));
					// System.out.println(Thread.currentThread().getThreadGroup());

					System.out.println("變體在綫地址" + strs[c]);
					onlineDownload.setfOnlineUrl(strs[c]);
					System.out.println("變體現場地址" + imageNames);
					onlineDownload.setfImagesUrl(imageNames);
					System.out.println("變體sku" + dtfzing.getSku());
					onlineDownload.setfSku(dtfzing.getSku());
					onlineDownService.getAndset(onlineDownload);
					String[] zzyi = imageNames.split("F:/LibraryImage/");
					for (int t = 0; t < zzyi.length; t++) {
						// 把遍历出来的所有路径都存入tp数组
						tp = tp + "," + zzyi[t];
					}

				}
				// 给t1字符串截取赋值
				t1 = tp.split(",,");
				// 给stock的图片赋拼接好的路径
				dTstock.setFimg(tp);
				// 添加的变体尺码
				dTstock.setFsize(dtfzing.getY()[i].getSize());
				// 变体对应的sku
				dTstock.setFid(dtfzing.getSku());

				try {
					// 给变体传输数据
					//dTstockService.insertStock(dTstock);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					return this.reponseJSON(ApiResponseEnum.ERROR_PARAM.getCode(),
							ApiResponseEnum.ERROR_PARAM.getName(), e.getMessage());
				}
			}
		}

		if (dtfzing.getPic() != null) {
			/*
			 * String path = request.getSession().getServletContext().getRealPath("/");
			 * System.out.println("paths" + path); path = path + "resources/libraryImage/";
			 * System.out.println("path:::::" + path);
			 */
			// 服务器上面使用这一段，可以获取项目的虚拟路径
			// String imageName = path + sdf.format(new Date()) + "/" + dtfzing.getSku() +
			// "/";// 拼接文件路径
			// 获取文件路径
			File file = new File(imageName);
			if (!file.exists() && !file.isDirectory()) {
				// 文件夹创建
				file.mkdirs();
			} else {
				System.out.println("主体图创建的文件夹已存在" + file);
			}
			DTpic dTpic = new DTpic();
			// 获取自己写的图片下载转换路径方法
			String tp = "";
			for (int i = 0; i < dtfzing.getPic().length; i++) {
				// 获取sku并传输到dtpic
				dTpic.setId(dtfzing.getSku());
				// 图片路径及名称
				String imageNames = imageName + dtfzing.getSku() + sdfs.format(new Date()) + rd.nextInt(100000)
						+ "PIC.jpg";

				// imgTest ct = new imgTest(dtfzing.getPic()[i].getPic(), imageNames);
				// ct.start();
				// Future<?> future = newCachedThreadPool.submit(new
				// imgTest(dtfzing.getPic()[i].getPic(), imageNames));// 线程池中提交MyCallable
				// 判断任务是否完成

				// threadPoolExecutor.submit(new imgTest(dtfzing.getPic()[i].getPic(),
				// imageNames));
				/*
				 * boolean c = future.isDone(); System.out.println("pic第" + i + "條"); if (!c) {
				 * System.out.println("請等待"); }
				 */
				// System.out.println("返回結果給" + ct.result);
				// getSystemInfo s = new getSystemInfo();
				// pool.submit(new imgTest(dtfzing.getPic()[i].getPic(), imageNames));
				// 把路径丢入多线程
				// ct.start();
				// Thread.sleep(30);
				System.out.println("网址在綫地址" + dtfzing.getPic()[i].getPic());
				onlineDownload.setfOnlineUrl(dtfzing.getPic()[i].getPic());
				System.out.println("网址現場地址" + imageNames);
				onlineDownload.setfImagesUrl(imageNames);
				System.out.println("网址sku" + dtfzing.getSku());
				onlineDownload.setfSku(dtfzing.getSku());
				onlineDownService.getAndset(onlineDownload);
				String[] zzyi = imageNames.split("F:/LibraryImage/");
				for (int t = 0; t < zzyi.length; t++) {
					// 一个个单独存储pic的路径
					tp = tp + "," + zzyi[t];
					dTpic.setPic(zzyi[t]);
				}
				// 获取路径并传输到dtpic
				dtpicService.setinsert(dTpic);
			}
			// 给t2赋值截取数组值
			t2 = tp.split(",,");

		}
		// 进行拿图判断
		if (dtfzing.getPic().length != 0) {
			// 如果pic的值也就是长度不为空，则遍历pic的值

			dtfzing.setFpic(t2[1]);
		} else {
			// 如果pic的值为空，则获取stock的第一条的图片
			dtfzing.setFpic(t1[1]);
		}

		try {
			// 产品的数据传输threadPoolExecutor
			// newCachedThreadPool.shutdown();
			// threadPoolExecutor.shutdown();
			dTfzyingService.setinsert(dtfzing);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return this.reponseJSON(ApiResponseEnum.ERROR_PARAM.getCode(), ApiResponseEnum.ERROR_PARAM.getName(),
					e.getMessage());
		}

		return this.reponseJSON(ApiResponseEnum.SUCCESS.getCode(), ApiResponseEnum.SUCCESS.getName(),
				new AjaxResult(true));
	}

	/**
	 * 查询商品
	 * 
	 * @return
	 */
	@RequestMapping(value = "catalog/search/product", method = RequestMethod.POST, headers = "Accept=application/json")
	public ApiResponseObject searchProduct(@RequestBody Map<String, Object> reqMap) {
		return this.reponseJSON(ApiResponseEnum.SUCCESS.getCode(), ApiResponseEnum.SUCCESS.getName(),
				productService.getProductRelation(reqMap));
	}

	/**
	 * 查询商品新
	 * 
	 * @return
	 */
	@RequestMapping(value = "catalog/search/producttu", headers = "Accept=application/json")
	public ApiResponseObject searchProductNew(@RequestBody Map<String, Object> reqMap) {
		return this.reponseJSON(ApiResponseEnum.SUCCESS.getCode(), ApiResponseEnum.SUCCESS.getName(),
				dTfzyingService.selectAll());
	}

	/**
	 * 查询商品
	 * 
	 * @return
	 */
	@RequestMapping(value = "catalog/product/hot", method = RequestMethod.POST, headers = "Accept=application/json")
	public ApiResponseObject searchProductByHot(@RequestBody Map<String, Object> reqMap) {
		return this.reponseJSON(ApiResponseEnum.SUCCESS.getCode(), ApiResponseEnum.SUCCESS.getName(),
				productService.getProduct(reqMap));
	}

	/**
	 * 商品详情页
	 * 
	 * @return
	 */
	@RequestMapping(value = "catalog/product/details", method = RequestMethod.POST, headers = "Accept=application/json")
	public ApiResponseObject productDetails(@RequestBody Map<String, Object> reqMap) {
		Object re = null;
		if ((re = productService.getProductDetailsById(reqMap)) != null)
			return this.reponseJSON(ApiResponseEnum.SUCCESS.getCode(), ApiResponseEnum.SUCCESS.getName(), re);
		return this.reponseJSON(ApiResponseEnum.ERROR_PARAM.getCode(), ApiResponseEnum.ERROR_PARAM.getName(), re);
	}

	/**
	 * 添加商品评论
	 * 
	 * @return
	 */
	@RequestMapping(value = "catalog/product/add/review", method = RequestMethod.POST, headers = "Accept=application/json")
	public ApiResponseObject addComments(@RequestBody Comments comments) {
		if (!commentsService.saveValidation(comments)) {
			return this.reponseJSON(ApiResponseEnum.ERROR_PARAM.getCode(), ApiResponseEnum.ERROR_PARAM.getName(),
					new AjaxResult(false));
		}
		commentsService.saveComments(comments);
		return this.reponseJSON(ApiResponseEnum.SUCCESS.getCode(), ApiResponseEnum.SUCCESS.getName(),
				new AjaxResult(true));
	}

	/**
	 * 获取商品评论
	 * 
	 * @return
	 */
	@RequestMapping(value = "catalog/product/reviews", method = RequestMethod.POST, headers = "Accept=application/json")
	public ApiResponseObject getComments(@RequestBody Map<String, Object> reqMap) {
		return this.reponseJSON(ApiResponseEnum.SUCCESS.getCode(), ApiResponseEnum.SUCCESS.getName(),
				commentsService.getCommentsByProductId(reqMap));
	}
}