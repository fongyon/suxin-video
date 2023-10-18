package com.suxin.xinglin.video.service.impl;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suxin.xinglin.video.entity.UserEntity;
import com.suxin.xinglin.video.mapper.UserMapper;
import com.suxin.xinglin.video.service.UserService;
import com.suxin.xinglin.video.utils.MD5Util;
import com.suxin.xinglin.video.vo.UserVO;

/**
 * 业务层实现类
 */
@Service //spring容器的注解
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

	/**
	 * 根据前端封装的UserVO保存
	 */
	@Override
	public UserVO save(UserVO userVO) {
		// TODO Auto-generated method stub
		UserEntity userEntity =new UserEntity();
		//将UserVO对象的属性复制到UserEntity对象
		BeanUtils.copyProperties(userVO, userEntity);
		//设置密码
		String pass=userVO.getPass();//明文密码
		String salt=MD5Util.createSlat(8);
		userEntity.setPassword(MD5Util.MD5Encode(pass,salt));
		userEntity.setSalt(salt);
		//创建时间
		//日期:当前日期
		LocalDate ld=LocalDate.now();
		//时间对象：当前时间
		LocalTime lt=LocalTime.now();
		//日期，时间复合对象
		LocalDateTime ldt=LocalDateTime.of(ld, lt);
		Timestamp timestamp =Timestamp.valueOf(ldt);
		userEntity.setAddTime(timestamp);
		
		//调用保存方法
		if(this.save(userEntity)) {
			BeanUtils.copyProperties(userEntity,userVO);
			return userVO;
		}
		return null;
	}

	/**
	 * 根据email取得用户信息
	 */
	@Override
	public UserEntity getUserByEmail(String eamil) {
//		UserVO userVO=new UserVO();
		// TODO Auto-generated method stub
		//构造查询对象
		LambdaQueryWrapper<UserEntity> queryWrapper =new LambdaQueryWrapper<UserEntity>();
		queryWrapper.eq(UserEntity::getEmail, eamil);
		
		//查询返回对象
		UserEntity userEntity= this.getOne(queryWrapper);
		//将数据库实体复制到前端实体
//		BeanUtils.copyProperties(userEntity, userVO);
		
		return userEntity;
	}

}
