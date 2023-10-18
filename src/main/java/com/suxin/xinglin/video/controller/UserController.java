package com.suxin.xinglin.video.controller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.suxin.xinglin.video.entity.UserEntity;
import com.suxin.xinglin.video.service.UserService;
import com.suxin.xinglin.video.utils.ComUtils;
import com.suxin.xinglin.video.utils.EmailModule;
import com.suxin.xinglin.video.utils.MD5Util;
import com.suxin.xinglin.video.vo.ResultVO;
import com.suxin.xinglin.video.vo.UserVO;


@RestController
public class UserController {
	
	@Resource
	private UserService userService;
	
	@Resource
	private EmailModule emailModule;
	
	@GetMapping("user/info")
	public ResultVO<UserVO> getInfo(){
		ResultVO<UserVO> resultVO=null;
		//根据共享数据取得UserVO
		UserVO userVO=ComUtils.SESSION_USER;
		if(userVO!=null) {
			resultVO=ResultVO.success();
			resultVO.setData(userVO);
		}else {
			resultVO=ResultVO.failed(999,"获取数据失败");
		}
		
		return resultVO;
	}
	/**
	 * 找回密码
	 * @return
	 */
	public ResultVO<UserVO> retrieve(String email){
		 ResultVO<UserVO> resultVO =null;
		 //检查邮箱是否存在，根据email查询用户是否存在
		 LambdaQueryWrapper<UserEntity> lambdaQueryWrapper =new LambdaQueryWrapper<UserEntity>();
		 lambdaQueryWrapper.eq(UserEntity::getEmail, email);
		 UserEntity userEntity= userService.getOne(lambdaQueryWrapper);
		 if(userEntity!=null) {//根据邮件地址找到对象
			 //发送随机密码:明文
			 String pass= emailModule.sendPass(email);
			 //给用户修改密码
			 String salt=MD5Util.createSlat(8); //新的盐值
			 String password=MD5Util.MD5Encode(pass, salt); //新的明文密码加密
			 userEntity.setPassword(password); //更新密码
			 userEntity.setSalt(salt); //更新盐值
			 //修改数据库
			if(userService.saveOrUpdate(userEntity)) {
				UserVO userVO=new UserVO();
				//拷贝实体的属性
				BeanUtils.copyProperties(userEntity, userVO);
				resultVO=ResultVO.success();
				resultVO.setData(userVO);
				return resultVO;
			}
		 }
		 resultVO=ResultVO.failed(998,"找回密码失败");
		 return resultVO;
	}
	/**
	 * 重置密码
	 * @param email
	 * @param pass
	 * @return
	 */
	@PostMapping("retrieve")
	public  ResultVO<UserVO> resetPass(String email,String pass){
		 ResultVO<UserVO> resultVO =null;
		 //验证输入
		 if(StringUtils.hasText(email)&& StringUtils.hasText(pass)) {
			 //检查邮箱是否存在，根据email查询用户是否存在
			 LambdaQueryWrapper<UserEntity> lambdaQueryWrapper =new LambdaQueryWrapper<UserEntity>();
			 lambdaQueryWrapper.eq(UserEntity::getEmail, email);
			 UserEntity userEntity= userService.getOne(lambdaQueryWrapper);
			 if(userEntity!=null) {
				 String password=MD5Util.MD5Encode(pass, userEntity.getSalt());
				 userEntity.setPassword(password);
				 LocalDate ld=LocalDate.now();
				 LocalTime lt=LocalTime.now();
				 LocalDateTime ldt= LocalDateTime.of(ld, lt);
				 Timestamp timestamp=Timestamp.valueOf(ldt);
				 userEntity.setUpdTime(timestamp);
				 //修改数据库
					if(userService.saveOrUpdate(userEntity)) {
						UserVO userVO=new UserVO();
						//拷贝实体的属性
						BeanUtils.copyProperties(userEntity, userVO);
						resultVO=ResultVO.success();
						resultVO.setData(userVO);
						return resultVO;
					}
			 }
			 
		 }
		 resultVO=ResultVO.failed(997,"重置密码失败");
		 return resultVO;
	}
}
