package com.suxin.xinglin.video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.suxin.xinglin.video.entity.UserEntity;
import com.suxin.xinglin.video.vo.UserVO;

/**
 * 可以使用mybatis-plus的接口
 */
public interface UserService extends IService<UserEntity> {
	/**
	 * 根据用户外部信息保存
	 * @param userVO
	 * @return
	 */
	public UserVO save(UserVO userVO);
	
	/**
	 * 根据email查询用户信息
	 * @param eamil
	 * @return
	 */
	public UserEntity getUserByEmail(String eamil);
}
