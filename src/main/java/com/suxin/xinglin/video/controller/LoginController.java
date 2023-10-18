package com.suxin.xinglin.video.controller;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suxin.xinglin.video.entity.UserEntity;
import com.suxin.xinglin.video.service.UserService;
import com.suxin.xinglin.video.utils.MD5Util;
import com.suxin.xinglin.video.utils.RedisUtils;
import com.suxin.xinglin.video.vo.ResultVO;
import com.suxin.xinglin.video.vo.UserVO;

@RestController
public class LoginController {
	
	/**
	 * 用户的业务
	 */
	@Resource
	private UserService userService;
	
	/**
	 * redis组件
	 * 
	 */
	@Resource
	private RedisUtils<UserVO> redisUtils;
	
	/**
	 * 用户登录
	 * @param email
	 * @param password
	 * @return
	 */
	@PostMapping("login")
	public ResultVO<String> login(String email,String password){
		ResultVO<String> resultVO=null;
		UserVO userVO=new UserVO();
		//根据用户名/电子邮件查询用户
		UserEntity user= this.userService.getUserByEmail(email);
		if(user==null)
		{
			resultVO=ResultVO.failed(10010,"登录用户不存在");
			return resultVO;
		}
		//判断密码是否正确
		if(StringUtils.hasText(password)) {
			//取得加密盐值
			String salt=user.getSalt();
			//给输入的明文密码加密
			String pass=MD5Util.MD5Encode(password,salt);
				
			//比较密码相等
			if(pass.equals(user.getPassword())) {
				//比较成功
				//生成口令
				String token=UUID.randomUUID().toString();
				//将数据库实体复制到前端实体
				BeanUtils.copyProperties(user, userVO);
				//保存数据到redis
				this.redisUtils.set(token, userVO);
				//登录成功
				resultVO=ResultVO.success();
				resultVO.setData(token);
				return resultVO;
			}else
			{
				resultVO=ResultVO.failed(1000,"密码错错误");
				return resultVO;
			}
		} 
		
		resultVO=ResultVO.failed(1000,"登录失败");
		return resultVO;
	}
}
