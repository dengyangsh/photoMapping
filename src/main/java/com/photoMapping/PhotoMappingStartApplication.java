package com.photoMapping;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.photoMapping.dao")
public class PhotoMappingStartApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotoMappingStartApplication.class, args);
	}

}
