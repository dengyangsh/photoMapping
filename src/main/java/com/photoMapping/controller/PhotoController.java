package com.photoMapping.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.photoMapping.constant.ErrorCode;
import com.photoMapping.model.User;
import com.photoMapping.service.PhotoServiceApi;
import com.photoMapping.util.Response;

@RestController
@RequestMapping(value = "photo", method = RequestMethod.POST)

public class PhotoController extends BaseController {

	@Autowired
	private PhotoServiceApi photoServiceApi;

	@RequestMapping(value = "provincePhoto")
	public Response currentProvincePhoto(HttpServletRequest request, String province) {
		Response ok = Response.ok();
		User loginUser = getLoginUser(request);
		if (loginUser == null) {
			ok.setBody("请先登录");
			return ok;
		}
		List<String> provincePhoto = photoServiceApi.provincePhoto(loginUser.getId(), province);
		ok.setBody(provincePhoto);
		return ok;
	}

	@RequestMapping(value = "uploadPhoto", method = RequestMethod.POST)
	public Response uploadPhoto(@RequestParam("photo") MultipartFile photo, String province, HttpServletRequest request)
			throws Exception {
		Response ok = Response.ok();
		User loginUser = getLoginUser(request);
		if (loginUser == null) {
			ok.setBody("请先登录");
			return ok;
		}
		// 参数判断
		if (StringUtils.isEmpty(province)) {
			return Response.error(ErrorCode.ERROR, "参数为空请确认");
		}
		String rootPath = "/usr/java/photo";
		// String rootPath = "f:";
		String url = rootPath + File.separator + loginUser.getId() + File.separator + province;

		// 接受文件
		File dir = new File(url);
		if (!dir.exists())
			dir.mkdirs();

		// 数据库存储链接地址
		String path = dir.getAbsolutePath() + File.separator + photo.getOriginalFilename();
		photoServiceApi.savePhoto(path, loginUser.getId(), province);
		// 写文件到服务器
		File serverFile = new File(path);
		photo.transferTo(serverFile);
		return ok;
	}

}
