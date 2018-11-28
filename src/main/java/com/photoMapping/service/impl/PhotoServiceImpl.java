package com.photoMapping.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.photoMapping.dao.PhotoDao;
import com.photoMapping.service.PhotoServiceApi;

@Service
public class PhotoServiceImpl implements PhotoServiceApi {
	@Autowired
	private PhotoDao photoDao;

	@Override
	public List<String> provincePhoto(Integer userId, String province) {
		return photoDao.findPhotoUrlByUserIdAndProvince(userId, province);
	}

}
