package com.suxin.xinglin.video;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//创建springboot启动类
@SpringBootApplication //添加springboot启动类的注解
@MapperScan("com.suxin.xinglin.video.mapper") //添加mybatis持久化的包目录
public class VideoApplication {
	
	/**
	 * 启动入口
	 * @param args
	 */
	public static void main(String[] args) {
		
		//执行springboot启动的方法
		SpringApplication.run(VideoApplication.class, args);
	}
}
