package com.suxin.xinglin.video.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 656453820123528095L;
	/**
	 * ID唯一
	 */
	private Long id;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 明文密码
	 */
	private String pass;
	
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	private String salt;
	/**
	 * 邮箱验证码
	 */
	private String verifyCode;
	
	/**
	 * 人头像照片的base64编码
	 */
	private String photoBase;
	
	/**
	 * 邮箱地址
	 */
	private String email;
	
	/**
	 * 创建时间
	 */
	private Timestamp addTime;
	/**
	 * 修改时间
	 */
	private Timestamp updTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	public String getPhotoBase() {
		return photoBase;
	}
	public void setPhotoBase(String photoBase) {
		this.photoBase = photoBase;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Timestamp getAddTime() {
		return addTime;
	}
	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
	public Timestamp getUpdTime() {
		return updTime;
	}
	public void setUpdTime(Timestamp updTime) {
		this.updTime = updTime;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	@Override
	public String toString() {
		return "UserVO [id=" + id + ", username=" + username + ", pass=" + pass + ", verifyCode=" + verifyCode
				+ ", photoBase=" + photoBase + ", email=" + email + ", addTime=" + addTime + ", updTime=" + updTime
				+ "]";
	}

	


}
