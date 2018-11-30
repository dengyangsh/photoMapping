package com.photoMapping.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import com.photoMapping.model.User;

@Mapper
public interface UserDao {
	@Delete({ "delete from user", "where id = #{id,jdbcType=INTEGER}" })
	int deleteByPrimaryKey(Integer id);

	@Insert({ "insert into user (id, phone, ", "createTime, realName, ", "email, password)",
			"values (#{id,jdbcType=INTEGER}, #{phone,jdbcType=VARCHAR}, ",
			"#{createtime,jdbcType=TIMESTAMP}, #{realname,jdbcType=VARCHAR}, ",
			"#{email,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR})" })
	int insert(User record);

	@Select({ "select", "id, phone, createTime, realName, email, password", "from user",
			"where id = #{id,jdbcType=INTEGER}" })
	@Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
			@Result(column = "phone", property = "phone", jdbcType = JdbcType.VARCHAR),
			@Result(column = "createTime", property = "createtime", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "realName", property = "realname", jdbcType = JdbcType.VARCHAR),
			@Result(column = "email", property = "email", jdbcType = JdbcType.VARCHAR),
			@Result(column = "password", property = "password", jdbcType = JdbcType.VARCHAR) })
	User selectByPrimaryKey(Integer id);

	@Select({ "select", "id, phone, createTime, realName, email, password", "from user" })
	@Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
			@Result(column = "phone", property = "phone", jdbcType = JdbcType.VARCHAR),
			@Result(column = "createTime", property = "createtime", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "realName", property = "realname", jdbcType = JdbcType.VARCHAR),
			@Result(column = "email", property = "email", jdbcType = JdbcType.VARCHAR),
			@Result(column = "password", property = "password", jdbcType = JdbcType.VARCHAR) })
	List<User> selectAll();

	@Update({ "update user", "set phone = #{phone,jdbcType=VARCHAR},", "createTime = #{createtime,jdbcType=TIMESTAMP},",
			"realName = #{realname,jdbcType=VARCHAR},", "email = #{email,jdbcType=VARCHAR},",
			"password = #{password,jdbcType=VARCHAR}", "where id = #{id,jdbcType=INTEGER}" })
	int updateByPrimaryKey(User record);

	@Select({ "select count(1) from user where phone = #{phone}" })
	Integer selectByPhone(String phone);
}
