package com.suxin.xinglin.video.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

//实体类
@TableName("tb_user")
public class UserEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3982721726928666480L;

	//`id``username``password``verify_code``salt``photo_base``email``add_time``upd_time``del_time`
	/**
	 * 数控主键ID
	 * 1、表的字段名称与java属性（变量）名称相同
	 * 2、表的字段名称与java属性（变量）不同
	 * 3、表的字段名称使用下划线与java直接使用命名规则，可以不用注解
	 */
	@TableId
	private Long id;
	
	/**
	 * 用户名
	 */
	private String username;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 邮箱验证码
	 */
	@TableField("verify_code")
	private String verifyCode;
	/**
	 * 密码加密的盐值
	 */
	private String salt;
	
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
	
	/**
	 * 删除时间
	 */
	private Timestamp delTime;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
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

	public Timestamp getDelTime() {
		return delTime;
	}

	public void setDelTime(Timestamp delTime) {
		this.delTime = delTime;
	}

	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", username=" + username + ", password=" + password + ", verifyCode="
				+ verifyCode + ", salt=" + salt + ", photoBase=" + photoBase + ", email=" + email + ", addTime="
				+ addTime + ", updTime=" + updTime + ", delTime=" + delTime + "]";
	}
	
}
