package com.photoMapping.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.photoMapping.dao.UserDao;
import com.photoMapping.model.User;
import com.photoMapping.service.UserServiceApi;

@Service
public class UserServiceApiImpl implements UserServiceApi {
	@Autowired
	private UserDao userDao;

	@Override
	public User findUser(Integer id) {
		return userDao.findUserById(id);
	}

}
