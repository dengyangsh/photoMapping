package com.photoMapping.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.photoMapping.util.Response;

public interface PhotoServiceApi {

	List<String> provincePhoto(Integer userId, String province);

	void savePhoto(String url, Integer userId, String province);
	
	public Response savePhoto(MultipartFile[] photos , Integer userId, String province) ;

}
