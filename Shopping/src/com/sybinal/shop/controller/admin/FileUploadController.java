package com.sybinal.shop.controller.admin;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {
	private static Logger logger = Logger.getLogger(FileUploadController.class); 
	@RequestMapping(value = "/admin/newUploadfile", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, String> newUploadFile(@RequestParam List<MultipartFile> file, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String uuid = UUID.randomUUID().toString().replace("-", "");
		// 设置日期格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String path = request.getSession().getServletContext().getRealPath("").split("webapp")[0];
		Map<String, String> map = new HashMap<String, String>();
		path+="/resources/upload/"+sdf.format(new Date())+"/";
		//update by laoyang
		File folder = new File(path); // 创建文件夹
        if (!folder.exists() && !folder.isDirectory()){
            folder.mkdirs();
        }else {
        	logger.info("变体创建的文件夹已存在");
		}
        logger.info(path);
		FileOutputStream output = null;
		try {
			if (file != null) {
				for (MultipartFile fileObj : file) {
					String fileType = fileObj.getOriginalFilename().split("\\.")[fileObj.getOriginalFilename().split("\\.").length-1];
					output = new FileOutputStream(path + uuid + "." + fileType);
					//update by laoyang
					//File1.Copy('指定要复制的文件','为新文件指定目录和文件名'[,False | True]); //最后一个参数指定是否重写现有文件
					IOUtils.copy(fileObj.getInputStream(), output);
					map.put("errorCode", "0");
					map.put("errorMsg", "success");
					map.put("fileName", "/resources/upload/"+sdf.format(new Date())+"/"+uuid+ "." + fileType);
				}
			}
		} catch (Exception e) {
			map.put("errorCode", "-1");
			map.put("errorMsg", "error");
			logger.error("错误",e);
		} finally {
			if (output != null) {
				output.close();
			}
		}
		return map;
	}
	// 链接url下载图片
	@RequestMapping(value = "/admin/downloadimg", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
		public List<String> downloadPictures(@RequestBody List<String> list,HttpServletRequest request) {
			URL url = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			String path = request.getSession().getServletContext().getRealPath("").split("webapp")[0];
			path+="/resources/upload/"+sdf.format(new Date())+"/";
			File folder = new File(path); // 创建文件夹
	        if (!folder.exists()&& !folder.isDirectory()){
	            folder.mkdirs();
	        }
	        List<String> lists=new ArrayList<String>();
	        for(int i=0;i<list.size();i++) {
				String uuid = UUID.randomUUID().toString().replace("-", "");
				try { 
					url = new URL(list.get(i));
					DataInputStream dataInputStream = new DataInputStream(url.openStream());
					// String imageNames = imageName + Name + sdfs.format(new Date()) + ".jpg";
					FileOutputStream fileOutputStream = new FileOutputStream(path + uuid + ".jpg");
					ByteArrayOutputStream output = new ByteArrayOutputStream();
	
					byte[] buffer = new byte[1024];
					int length;
	
					while ((length = dataInputStream.read(buffer)) > 0) {
						output.write(buffer, 0, length);
					} 
					lists.add("/resources/upload/"+sdf.format(new Date())+"/" + uuid + ".jpg"); 
					//byte[] context = output.toByteArray();
					fileOutputStream.write(output.toByteArray());
					dataInputStream.close();
					fileOutputStream.close();
				} catch (MalformedURLException e) {
					logger.error("链接错误");
					e.printStackTrace();
				} catch (IOException e) {
					logger.error("io流传输异常");
					e.printStackTrace();
				}
	        }

			logger.info(lists);
			return lists;
		}
}
