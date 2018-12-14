package com.photoMapping.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aliyuncs.exceptions.ClientException;
import com.photoMapping.constant.ErrorCode;
import com.photoMapping.dao.PhotoDao;
import com.photoMapping.service.PhotoServiceApi;
import com.photoMapping.util.ConfigComponent;
import com.photoMapping.util.FileUpload;
import com.photoMapping.util.Response;

@Service
public class PhotoServiceImpl implements PhotoServiceApi {
	
	private static final Logger log = LoggerFactory.getLogger(PhotoServiceImpl.class);
	
	@Autowired
	private PhotoDao photoDao;

	@Override
	public List<String> provincePhoto(Integer userId, String province) {
		return photoDao.findPhotoUrlByUserIdAndProvince(userId, province);
	}

	@Override
	public void savePhoto(String url, Integer userId, String province) {
		photoDao.save(url, userId, province);
	}
	
	/**
	 * @Title: 将文件上传到oss  
	* @Date: 2018年12月14日
	* @Author: 温家祥
	 */
	@Override
	public Response savePhoto(MultipartFile[] photos , Integer userId, String province) {
		if(photos == null || photos.length <= 0) 
			return Response.error(ErrorCode.ERROR, "未上传图片");
		
		String uploadErrorImages = null;
		String ossEndpoint = ConfigComponent.getInstance().getOssEndpoint();
		String bucketName = ConfigComponent.getInstance().getBucketName();
		String imagePath = ConfigComponent.getInstance().getImagePath();
		
		for (MultipartFile multipartFile : photos) {
			String originalFileName = multipartFile.getName();
			String fileNamePrefix = UUID.randomUUID().toString().replaceAll("-", "");
			//存储的文件名称
			String fileName = fileNamePrefix +"_"+ originalFileName ;
			//出的路径+名称
			String storePath = imagePath + "/" + userId + "/" + fileName ;
			try {
				FileUpload.fileUpload(multipartFile.getInputStream(), storePath);
				String url = ossEndpoint + "/" + bucketName +"/" + storePath;
				photoDao.save(url, userId, province);
			} catch (ClientException | IOException e) {
				log.error("上传图片时出错,{}" ,fileName,e);
				uploadErrorImages = uploadErrorImages + ","+originalFileName ;
			}
		}
		if(uploadErrorImages != null) {
			return Response.error(ErrorCode.ERROR, uploadErrorImages + "上传错误");
		}
		return Response.ok();
	}

}
