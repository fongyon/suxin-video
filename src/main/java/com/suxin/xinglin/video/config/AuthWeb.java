package com.suxin.xinglin.video.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.suxin.xinglin.video.utils.AuthInterceptor;

/**
 * 添加自己的web认证配置组件
 */
@Configuration
public class AuthWeb extends WebMvcConfigurationSupport {
	
	/**
	 * 实例化认证组件
	 * 
	 */
	@Resource
	private AuthInterceptor authInterceptor;
	
	
	//拦截器
	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		//添加拦截器对象
		registry.addInterceptor(authInterceptor).addPathPatterns("/**");
		// TODO Auto-generated method stub
		super.addInterceptors(registry);
	}
}
