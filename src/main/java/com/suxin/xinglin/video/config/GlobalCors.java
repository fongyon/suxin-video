package com.suxin.xinglin.video.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 全局的跨越配置
 */
@Configuration //配置注解
public class GlobalCors {
	
	@Bean //配置域控制过滤器对象bean
	CorsFilter corsFilter() {
		//跨越的配置对象
		CorsConfiguration configuration =new CorsConfiguration();
		
		List<String> all=new ArrayList<>();
		all.add("*");
//		configuration.addAllowedOriginPattern("*");
		//配置的来源模式
		configuration.setAllowedOriginPatterns(all);
		//配置头
		configuration.setAllowedHeaders(all);
		//配置匹配的方法
		configuration.setAllowedMethods(all);
		//配置来源
		configuration.setAllowedOrigins(all);
		//配置的扩展头
		configuration.setExposedHeaders(all);
		//配置是否容许缓存Cookies
		configuration.setAllowCredentials(false);
		
		//资源基础跨越配置对象
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource =new UrlBasedCorsConfigurationSource();
		//注册
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", configuration);
		
		//构造对象
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}
}
