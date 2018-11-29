package com.photoMapping.controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.photoMapping.constant.ErrorCode;
import com.photoMapping.service.PhotoServiceApi;
import com.photoMapping.util.Response;

@RestController
@RequestMapping(value = "photo", method = RequestMethod.GET)
public class PhotoController {

	@Autowired
	private PhotoServiceApi photoServiceApi;

	@RequestMapping(value = "provincePhoto")
	public Response currentProvincePhoto(Integer userId, String province) {
		List<String> provincePhoto = photoServiceApi.provincePhoto(userId, province);
		Response ok = Response.ok();
		ok.setBody(provincePhoto);
		return ok;
	}

	@RequestMapping(value = "uploadPhoto", method = RequestMethod.POST)
	public Response uploadPhoto(@RequestParam("photo") MultipartFile photo, String province, Integer userId)
			throws Exception {
		// 参数判断
		if (StringUtils.isEmpty(province) || userId == null) {
			return Response.error(ErrorCode.ERROR, "参数为空请确认");

		}
		// 接受文件
		String rootPath = "/usr/java/photo";
		File dir = new File(rootPath + File.separator + userId + province);
		if (!dir.exists())
			dir.mkdirs();
		// 写文件到服务器
		File serverFile = new File(dir.getAbsolutePath() + File.separator + photo.getOriginalFilename());
		photo.transferTo(serverFile);
		Response ok = Response.ok();
		return ok;
	}

}
