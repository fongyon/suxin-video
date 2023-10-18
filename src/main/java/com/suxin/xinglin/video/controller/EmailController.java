package com.suxin.xinglin.video.controller;

import javax.annotation.Resource;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suxin.xinglin.video.utils.EmailModule;
import com.suxin.xinglin.video.utils.MyUtils;
import com.suxin.xinglin.video.utils.RedisUtils;
import com.suxin.xinglin.video.vo.ResultVO;

@RestController
public class EmailController {
	
	@Resource
	private RedisUtils<String> redisUtils;
	
	@Resource
	private EmailModule emailModule;
	//获取验证码
	@PostMapping("email/code")
	public ResultVO<String> getCode(String email) {
		ResultVO<String> resultVO =null;
		//邮箱的验证
		if(MyUtils.checkEmail(email)) {
			//发送邮件
			String code = emailModule.sendCode(email);
			//判断code
			if(StringUtils.hasLength(code))
			{
				resultVO=ResultVO.success();
				resultVO.setData(code);
				//保存到redis中
				this.redisUtils.set(email, code);
			}else {
				resultVO=ResultVO.failed(200);//邮箱验证码为空，邮箱验证码发送失败
				resultVO.setMessage("邮箱验证码为空，邮箱验证码发送失败");
			}
			return resultVO;
		}else {
			resultVO=ResultVO.failed(201);//邮箱地址验证不合法
			resultVO.setMessage("邮箱地址验证不合法");
		}
		return resultVO;
	}
	/**
	 * 验证邮箱验证码
	 * @return
	 */
	@PostMapping("email/checkCode")
	public ResultVO<String> checkEmailCode(String email,String emailCode){
		ResultVO<String> resultVO =null;
		if(StringUtils.hasText(email)&&StringUtils.hasText(emailCode)) {
			String code= this.redisUtils.get(email);
			//验证前端传入的验证码与后端保存的验证码是否相等
			if(emailCode.equals(code))
			{
				resultVO=ResultVO.success();
				resultVO.setData("true");
				return resultVO;
			}
		}
		resultVO=ResultVO.failed(5002,"邮箱验证码验证失败");
		return resultVO;
	}

}
