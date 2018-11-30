package com.photoMapping.service;

import com.photoMapping.model.User;

public interface UserServiceApi {

	/**
	 * 注册
	 * @param phone
	 * @param realName
	 * @param passWord
	 * @param email
	 */
	void register(String phone, String realName, String passWord,String email);

	/**登录
	 * @param phone
	 * @param passWord
	 * @return
	 */
	User login(String phone, String passWord);

}
