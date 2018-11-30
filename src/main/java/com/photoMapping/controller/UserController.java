package com.photoMapping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.photoMapping.model.User;
import com.photoMapping.service.UserServiceApi;
import com.photoMapping.util.Response;

@RestController
@RequestMapping(value = "user", method = RequestMethod.GET)
public class UserController {

	@Autowired
	private UserServiceApi userServiceApi;

	@RequestMapping(value = "register")
	public Response register(String phone, String realName,String email,String passWord) {
		userServiceApi.register(phone, realName, passWord, email);
		return Response.ok();
	}
}
