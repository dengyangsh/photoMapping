package com.photoMapping.service;

import java.util.List;

public interface PhotoServiceApi {

	List<String> provincePhoto(Integer userId, String province);

	void savePhoto(String url, Integer userId, String province);

}
