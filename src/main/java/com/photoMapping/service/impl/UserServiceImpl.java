package com.photoMapping.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.photoMapping.dao.UserDao;
import com.photoMapping.model.User;
import com.photoMapping.service.UserServiceApi;

@Service
public class UserServiceImpl implements UserServiceApi {
	@Autowired
	private UserDao userDao;

	@Override
	public void register(String phone, String realName, String passWord, String email) {
		if (StringUtils.isEmpty(phone)) {
			throw new RuntimeException("手机号码为空，无法注册");
		}

		Integer userNum = userDao.selectByPhone(phone);
		if(userNum>0) {
			throw new RuntimeException("用户已存在，不需要再次注册");
		}

		User user = new User();
		user.setCreatetime(LocalDateTime.now());
		user.setRealname(realName);
		user.setPassword(passWord);
		user.setEmail(email);
		user.setPhone(phone);
		userDao.insert(user);
	}

}
