package com.photoMapping.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.photoMapping.util.Response;

@RestController
@RequestMapping(value = "user", method = RequestMethod.POST)
public class UserController {

	@RequestMapping(value = "getPhotos")
	public Response getPhotos(Integer userId, String province) {
		return Response.ok();

	}
}
