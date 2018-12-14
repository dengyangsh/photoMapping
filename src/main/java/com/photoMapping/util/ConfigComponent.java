package com.photoMapping.util;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 
* @Title:获取配置的组件 
* @Date: 2018年12月14日
* @Author: 温家祥
 */
@Component
public class ConfigComponent {

	private static ConfigComponent instance;
	
	@PostConstruct
	public void inti() {
		instance = this ;
	}
	
	//申请临时凭证的用户id
	@Value(value="${aliyun.sts.accesskey.id}")
	private String accesskeyId ;
	
	//申请临时凭证的用户密码
	@Value(value="${aliyun.sts.accesskey.secret}")
	private String accesskeySecret ;
	
	//临时凭证扮演的角色
	@Value(value="${aliyun.sts.roleArn}")
	private String roleArn ;
	
	//sts 服务地址
	@Value(value="${aliyun.sts.stsEndpoint}")
	private String stsEndpoint ;
	
	//图片存放在阿里云的bucket
	@Value(value="${aliyun.oss.endpoint}")
	private String ossEndpoint ;
	
	//图片存放在阿里云的bucket
	@Value(value="${aliyun.oss.bucketName}")
	private String bucketName ;
	
	//图片在bucket下的存放路径
	@Value(value="${aliyun.oss.imagePath}")
	private String imagePath ;
	
	//临时凭证的过期时间
	@Value(value="${aliyun.sts.durationSeconds}")
	private String durationSeconds ;
	
	

	public String getAccesskeyId() {
		return accesskeyId;
	}

	public void setAccesskeyId(String accesskeyId) {
		this.accesskeyId = accesskeyId;
	}

	public String getAccesskeySecret() {
		return accesskeySecret;
	}

	public void setAccesskeySecret(String accesskeySecret) {
		this.accesskeySecret = accesskeySecret;
	}

	public String getRoleArn() {
		return roleArn;
	}

	public void setRoleArn(String roleArn) {
		this.roleArn = roleArn;
	}

	public String getStsEndpoint() {
		return stsEndpoint;
	}

	public void setStsEndpoint(String stsEndpoint) {
		this.stsEndpoint = stsEndpoint;
	}

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getDurationSeconds() {
		return durationSeconds;
	}

	public void setDurationSeconds(String durationSeconds) {
		this.durationSeconds = durationSeconds;
	}

	
	public String getOssEndpoint() {
		return ossEndpoint;
	}

	public void setOssEndpoint(String ossEndpoint) {
		this.ossEndpoint = ossEndpoint;
	}

	public static ConfigComponent getInstance() {
		return instance;
	}

//	public static void setInstance(ConfigComponent instance) {
//		ConfigComponent.instance = instance;
//	}

	
	
	
	
	
	
	
}
