package com.photoMapping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

}
