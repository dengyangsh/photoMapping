package com.photoMapping.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.auth.sts.AssumeRoleRequest;
import com.aliyuncs.auth.sts.AssumeRoleResponse;
import com.aliyuncs.auth.sts.AssumeRoleResponse.Credentials;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public class OssUtil {
	
	private static Logger logger = LoggerFactory.getLogger(OssUtil.class);
	
	// 获取 广告上传的  Credentials  
	public static Credentials getAdUploadSTSCredentials(String roleSessionName) throws ServerException, ClientException {
		String bucketName = ConfigComponent.getInstance().getBucketName() ;
		String imagePath = ConfigComponent.getInstance().getImagePath() ;
		//roleArn 需要扮演的角色 ， 该角色上有我们分配的权限 
		String roleArn = ConfigComponent.getInstance().getRoleArn() ;
		String uploadPolicy = getAdUploadPolicy(bucketName, imagePath) ;
		Credentials credentials = getSTSCredentials(roleArn ,roleSessionName, uploadPolicy) ;
		return credentials ;
	}   
	 
	//使用 accessKeyId 和 accessKeySecret 获取   Credentials
	//accessKeyId 这个账号只有调用STS 的权限
	public static Credentials getSTSCredentials(String roleArn ,String roleSessionName ,String policy ) throws ServerException, ClientException {
		String stsEndpoint = ConfigComponent.getInstance().getStsEndpoint() ;
		String accessKeyId = ConfigComponent.getInstance().getAccesskeyId() ;
		String accessKeySecret = ConfigComponent.getInstance().getAccesskeySecret() ;
		long durationSeconds = Long.valueOf(ConfigComponent.getInstance().getDurationSeconds());
		Credentials credentials = getSTSCredentials(stsEndpoint, accessKeyId, accessKeySecret, roleSessionName, policy, roleArn, durationSeconds);
		return credentials ;
	}  
	/**
	 * @throws ClientException 
	 * @throws ServerException 
	 * @Title: 向阿里云sts服务发起请求获取 Credentials
	 * @param
	 *  stsEndpoint : sts服务的请求地址
	 *  accessKeyId ： 子账号的ak
	 *  accessKeySecret ： 子账号的ak
	    roleSessionName ： sts账号发起请求的session名称 ， 方便后期统计，
	    policy ： 授权策略
	    roleArn ： sts账号所扮演的角色
	    durationSeconds ： sts有效的时间
	 * 
	* @Date: 2018年7月23日
	* @Author: 温家祥
	 */
	public static Credentials getSTSCredentials(String stsEndpoint ,String accessKeyId,String accessKeySecret,
			String roleSessionName ,  String policy , String roleArn ,long durationSeconds) throws ServerException, ClientException{
		long beginTime = System.currentTimeMillis();
		logger.info("开始处理[获取阿里云getSTSCredentials]请求，请求参数为[accessKeyId :"+accessKeyId+"accessKeySecret:"+accessKeySecret+"policy:"+policy +"roleArn:"+roleArn+" ]");
		// 添加endpoint（直接使用STS endpoint，前两个参数留空，无需添加region ID） 
		DefaultProfile.addEndpoint("", "", "Sts", stsEndpoint);
		// 构造default profile（参数留空，无需添加region ID）
		IClientProfile profile = DefaultProfile.getProfile("", accessKeyId, accessKeySecret);
		// 用profile构造client
		DefaultAcsClient client = new DefaultAcsClient(profile);
		final AssumeRoleRequest request = new AssumeRoleRequest();
		request.setMethod(MethodType.POST);
		request.setRoleArn(roleArn);
		request.setDurationSeconds(durationSeconds);
		request.setRoleSessionName(roleSessionName);
		request.setPolicy(policy); // Optional
		final AssumeRoleResponse response = client.getAcsResponse(request);
		Credentials credentials = response.getCredentials();
		logger.info("完成[获取阿里云getSTSCredentials]请求,总共耗时[{}]ms", System.currentTimeMillis() - beginTime);
		return credentials ;
	}
	
	
	/**
	 * @Title: 授予 oss的上传权限 oss:PutObject ， 路径限制为 resourcePath
	 	//resource 配置说明 ： acs:<service-name>:<region>:<account-id>:<relative-id>
		// action 配置说明 ： <service-name>:<action-name>   Action": ["oss:ListBuckets", "ecs:Describe*", "rds:Describe*"]
	* @Date: 2018年7月23日
	* @Author: 温家祥
	 */
	private static String getAdUploadPolicy(String bucketName ,String path ) {
		String resourcePath = bucketName + "/" + path + "/*" ;
		String policy = "{\n" +
		        "    \"Version\": \"1\", " +
		        "    \"Statement\": [" +
		        "        {" +
		        "            \"Action\": [" +
		        "                \"oss:PutObject\"" +
		        "            ], " +
		        "            \"Resource\": [" +
		        "                \"acs:oss:*:*:" + resourcePath + "\" " +
		        "            ], " +
		        "            \"Effect\": \"Allow\"" +
		        "        }" +
		        "    ]" +
		        "}";
		
		return policy;
	}

}
