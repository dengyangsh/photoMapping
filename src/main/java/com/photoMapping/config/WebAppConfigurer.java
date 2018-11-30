package com.photoMapping.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.photoMapping.interceptor.LoginInterceptor;

@Configuration
public class WebAppConfigurer extends WebMvcConfigurerAdapter {

	@Bean
	public HandlerInterceptor getLoginInterceptor() {
		return new LoginInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(getLoginInterceptor()).addPathPatterns("/**");

		super.addInterceptors(registry);

	}

}