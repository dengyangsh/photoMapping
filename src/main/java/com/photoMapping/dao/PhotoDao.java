package com.photoMapping.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PhotoDao {

	@Select("SELECT url FROM photo WHERE userId = #{userId} and province = #{province}")
	List<String> findPhotoUrlByUserIdAndProvince(@Param(value = "userId") Integer userId,@Param(value = "province") String province);

	@Insert("INSERT INTO photo(userId,province,url,createTime) VALUES(#{userId},#{province},#{url},NOW())")
	void save(@Param(value = "url") String url, @Param(value = "userId") Integer userId,
			@Param(value = "province") String province);

}
