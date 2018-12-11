package com.photoMapping.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.photoMapping.constant.ErrorCode;
import com.photoMapping.model.User;
import com.photoMapping.service.PhotoServiceApi;
import com.photoMapping.util.Response;

@Controller
@RequestMapping(value = "photo", method = RequestMethod.POST)
public class PhotoController extends BaseController {

	@Autowired
	private PhotoServiceApi photoServiceApi;

	@RequestMapping(value = "provincePhoto")
	@ResponseBody
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
	@ResponseBody
	public Response uploadPhoto(@RequestParam("photos") MultipartFile[] photos, String province,
			HttpServletRequest request) throws Exception {
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

		for (MultipartFile photo : photos) {
			String rootPath = "/usr/java/static-web/photos";
//			String rootPath = "f:";
			String path =  loginUser.getId() + File.separator + province;
			String physicalPath =rootPath+File.separator+path ;
			String file = File.separator+photo.getOriginalFilename();

			// 接受文件
			File dir = new File(physicalPath);
			if (!dir.exists())
				dir.mkdirs();

			// 数据库存储链接地址
			photoServiceApi.savePhoto(path+ file, loginUser.getId(), province);
			// 写文件到服务器
			File serverFile = new File(physicalPath+ file);
			photo.transferTo(serverFile);
		}

		return ok;
	}

}
