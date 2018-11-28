package com.photoMapping.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PhotoDao {

	@Select("SELECT url FROM photo WHERE userId = #{userId} and province = #{province}")
	List<String> findPhotoUrlByUserIdAndProvince(Integer userId, String province);
}
