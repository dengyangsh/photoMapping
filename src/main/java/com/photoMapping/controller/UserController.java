package com.photoMapping.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.photoMapping.model.User;
import com.photoMapping.service.UserServiceApi;
import com.photoMapping.util.Response;

@RestController
@RequestMapping(value = "user", method = RequestMethod.POST)
public class UserController extends BaseController {

	@Autowired
	private UserServiceApi userServiceApi;

	@RequestMapping(value = "register")
	public Response register(String phone, String realName, String email, String passWord, HttpServletRequest request) {
		userServiceApi.register(phone, realName, passWord, email);
		User user = userServiceApi.login(phone, passWord);
		request.getSession().setAttribute("user", user);
		return Response.ok();
	}

	@RequestMapping(value = "login")
	public Response login(String phone, String passWord, HttpServletRequest request) {
		User user = userServiceApi.login(phone, passWord);
		request.getSession().setAttribute("user", user);
		Response ok = Response.ok();
		ok.setBody(user.getRealname());
		return ok;
	}
}
