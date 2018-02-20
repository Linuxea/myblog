package com.linuxea.controller.fileupload;

import com.google.common.io.Files;
import com.linuxea.controller.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author linuxea
 * @date 2018/2/20
 */
public class FileUploadController extends BaseController {
	
	private final static Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	
	private final static String filePath = "/home/tomcat/apache-tomcat-9.0.0.M26/webapps/files/";
	
	public void upload() {
		File img = getFile("img").getFile();
		String fileName = img.getName();
		String suffix = fileName.substring(fileName.lastIndexOf("."));
		String today = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format(new Date());
		String completePath = filePath + today + suffix;
		try {
			Files.copy(img, new File(completePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("上传了文件: " + completePath);
		renderText("文件存储地址: " + completePath);
		
	}


}
