package com.suxin.xinglin.video.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 自己简单验证工具
 * 
 */
public class MyUtils {

	/**
	 * 按要求验证字符串
	 * @param string 被验证的字符串
	 * @param pattern 验证规则 正则表达式字符串
	 * @return
	 */
	public static boolean check(String string,String pattern) {
		//匹配模式
		Pattern p=Pattern.compile(pattern);
		Matcher matcher =p.matcher(string);
		return matcher.find();
	}
	/**
	 * 验证邮件地址是否合法
	 * @param email
	 * @return
	 */
	public static boolean checkEmail(String email) {
		//验证邮箱的正则字符串 ("^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$")
		String regex="^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$"; 
		//调用check方法
		return check(email, regex);
	}
}
