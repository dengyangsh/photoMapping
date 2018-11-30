package com.photoMapping.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import com.photoMapping.model.Photo;

@Mapper
public interface PhotoDao {

	@Select("SELECT url FROM photo WHERE userId = #{userId} and province = #{province}")
	List<String> findPhotoUrlByUserIdAndProvince(@Param(value = "userId") Integer userId,@Param(value = "province") String province);

	@Insert("INSERT INTO photo(userId,province,url,createTime) VALUES(#{userId},#{province},#{url},NOW())")
	void save(@Param(value = "url") String url, @Param(value = "userId") Integer userId,
			@Param(value = "province") String province);

	
	
	  @Delete({
	        "delete from photo",
	        "where id = #{id,jdbcType=INTEGER}"
	    })
	    int deleteByPrimaryKey(Integer id);

	    @Insert({
	        "insert into photo (id, userId, ",
	        "province, city, ",
	        "createTime, url)",
	        "values (#{id,jdbcType=INTEGER}, #{userid,jdbcType=INTEGER}, ",
	        "#{province,jdbcType=VARCHAR}, #{city,jdbcType=VARCHAR}, ",
	        "#{createtime,jdbcType=TIMESTAMP}, #{url,jdbcType=VARCHAR})"
	    })
	    int insert(Photo record);

	    @Select({
	        "select",
	        "id, userId, province, city, createTime, url",
	        "from photo",
	        "where id = #{id,jdbcType=INTEGER}"
	    })
	    @Results({
	        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
	        @Result(column="userId", property="userid", jdbcType=JdbcType.INTEGER),
	        @Result(column="province", property="province", jdbcType=JdbcType.VARCHAR),
	        @Result(column="city", property="city", jdbcType=JdbcType.VARCHAR),
	        @Result(column="createTime", property="createtime", jdbcType=JdbcType.TIMESTAMP),
	        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR)
	    })
	    Photo selectByPrimaryKey(Integer id);

	    @Select({
	        "select",
	        "id, userId, province, city, createTime, url",
	        "from photo"
	    })
	    @Results({
	        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
	        @Result(column="userId", property="userid", jdbcType=JdbcType.INTEGER),
	        @Result(column="province", property="province", jdbcType=JdbcType.VARCHAR),
	        @Result(column="city", property="city", jdbcType=JdbcType.VARCHAR),
	        @Result(column="createTime", property="createtime", jdbcType=JdbcType.TIMESTAMP),
	        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR)
	    })
	    List<Photo> selectAll();

	    @Update({
	        "update photo",
	        "set userId = #{userid,jdbcType=INTEGER},",
	          "province = #{province,jdbcType=VARCHAR},",
	          "city = #{city,jdbcType=VARCHAR},",
	          "createTime = #{createtime,jdbcType=TIMESTAMP},",
	          "url = #{url,jdbcType=VARCHAR}",
	        "where id = #{id,jdbcType=INTEGER}"
	    })
	    int updateByPrimaryKey(Photo record);
}
