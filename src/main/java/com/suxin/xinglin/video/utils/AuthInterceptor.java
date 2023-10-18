package com.suxin.xinglin.video.utils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import com.suxin.xinglin.video.vo.UserVO;

import java.util.ArrayList;
import java.util.List;

/**
 * 自己定义拦截器组件
 * 
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {
	/**
	 * 构造处理redis组件
	 */
	@Resource
	private RedisUtils<UserVO> redisUtils;
	/**
	 * 白名单
	 */
	List<String> whiteList=new ArrayList<String>();
	
	/**
	 * 构造方法
	 */
	public AuthInterceptor() {
		whiteList.add("/login"); //登录
		whiteList.add("/register");//注册
		whiteList.add("/email/code");//发送邮件
		whiteList.add("email/checkCode");//验证邮箱的验证码是否正确
		whiteList.add("/errorCode"); //登录
		whiteList.add("/retrieve");//找回密码
	}
	
	
	/**
	 * 预处理方法
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//判断白名单
		   String contextPah=	request.getContextPath(); // 请求的上下文路径  /video
		   String pathUri=	request.getRequestURI(); //请求的资源路径 /video/login
		   //请求路径名称 /login
		  String pathName=pathUri.replace(contextPah, ""); 
		  //与白名单比较
		  long count= whiteList.stream().filter((str)-> str.equals(pathName)).count();
		  if(count>0)
		  {
			  return HandlerInterceptor.super.preHandle(request, response, handler);
		  }	
		//请求对象request对象中取得口令"
		String token= request.getHeader("Authentication");
		//判断口令
		if(StringUtils.hasText(token)) {//口令符合条件
			//根据口令可以在redis服务器上找到保存的对象
			UserVO userVO=this.redisUtils.get(token);
			if(userVO!=null)
			{
				//将口令认证成功的用户信息保存到用户状态的共享变量
				ComUtils.SESSION_USER=userVO; 
				return HandlerInterceptor.super.preHandle(request, response, handler);
			}
		}
		//跳转到错误请求
		request.getRequestDispatcher("errorCode").forward(request, response);	
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

}
