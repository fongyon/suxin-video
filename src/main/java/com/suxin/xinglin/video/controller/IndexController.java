package com.suxin.xinglin.video.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suxin.xinglin.video.vo.ResultVO;

@RestController  //springmvc的注解
public class IndexController {
	
	@RequestMapping("index")
	public String index() {
		return "INDEX";
	}
	/**
	 * 错误请求
	 * @return
	 */
	@RequestMapping("errorCode")
	public ResultVO<String> error() {
		ResultVO<String> resultVO=ResultVO.failed(7777,"认证授权失败");
		return resultVO;
	}
}
