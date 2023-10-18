package com.suxin.xinglin.video.utils;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 自己的定义的Redis组件
 * 
 */
@Component //组件注解
public class RedisUtils<V> {
	
	/**
	 * 使用redis模板类
	 */
	@Resource
	private RedisTemplate<String, V> redisTemplate;
	/**
	 * 根据Key获取保存的Vaue
	 * @param key
	 * @return
	 */
	public V get(String key) {
		//通过redis模板类的forVale方法
		return this.redisTemplate.boundValueOps(key).get();
	}
	
	/**
	 * 设置数据保存到redis
	 * @param key
	 * @param v
	 */
	public void set(String key,V v) {
		//通过redis模板保存数据到redis服务器
		this.redisTemplate.boundValueOps(key).set(v);
//		this.redisTemplate.opsForValue().set(key, v);
		//设置在redis服务器上保存的时长
		this.redisTemplate.expire(key, 30, TimeUnit.MINUTES);
	}
	
}
