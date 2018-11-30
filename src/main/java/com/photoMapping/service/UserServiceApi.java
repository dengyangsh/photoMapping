package com.photoMapping.service;

import com.photoMapping.model.User;

public interface UserServiceApi {

	void register(String phone, String realName, String passWord,String email);

}
