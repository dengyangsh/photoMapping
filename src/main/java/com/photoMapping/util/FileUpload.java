package com.photoMapping.util;

import java.io.InputStream;

import com.aliyun.oss.OSSClient;
import com.aliyuncs.auth.sts.AssumeRoleResponse.Credentials;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;

public class FileUpload {
	
	
	public static void fileUpload(InputStream inputStream ,String storePath) throws ServerException, ClientException {
		//①获取临时凭证
		Credentials credentials = OssUtil.getAdUploadSTSCredentials("photoMapping");
		
		//②使用临时凭证上传文件
		fileUpload(credentials, inputStream, storePath);
	}
	

	@SuppressWarnings("deprecation")
	public static void fileUpload(Credentials credentials ,InputStream inputStream ,String storePath) {
	
		String accessKeyId = credentials.getAccessKeyId() ;
		String accessKeySecret = credentials.getAccessKeySecret();
		String securityToken = credentials.getSecurityToken();
		String bucketName = ConfigComponent.getInstance().getBucketName() ;
		String endpoint = ConfigComponent.getInstance().getOssEndpoint() ;
		// 用户拿到STS临时凭证后，通过其中的安全令牌（SecurityToken）和临时访问密钥（AccessKeyId和AccessKeySecret）生成OSSClient。
		// 创建OSSClient实例。
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret, securityToken);
		
		// OSS操作。
		ossClient.putObject(bucketName, storePath, inputStream);
		// 关闭OSSClient。
		ossClient.shutdown();
	}
	
	
}
