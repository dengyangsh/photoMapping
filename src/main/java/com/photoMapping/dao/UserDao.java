package com.photoMapping.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.photoMapping.model.User;

@Mapper
public interface UserDao {

	@Select("SELECT * FROM user WHERE id = #{id}")
	User findUserById(Integer id);

}
