package com.suxin.xinglin.video.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suxin.xinglin.video.service.UserService;
import com.suxin.xinglin.video.vo.ResultVO;
import com.suxin.xinglin.video.vo.UserVO;

/**
 * 用户的注册控制器
 */
@RestController
public class RegisterController {
	
	//业务层对象
	@Resource
	private UserService userService;
	
	//提供用户注册请求
	@PostMapping("register")
	public ResultVO<UserVO> register(UserVO userVO) {	
		ResultVO<UserVO> resultVO=null;
		//保存
		UserVO vo= userService.save(userVO);
		if(vo!=null)
		{
			//保存成功，产生返回对象
			resultVO=ResultVO.success();
			resultVO.setData(vo);
		}else {
			resultVO=ResultVO.failed(100);//100代表数据保存失败
			resultVO.setMessage("数据保存失败");
		}
		return resultVO;
	}

}
