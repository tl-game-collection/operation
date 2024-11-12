package com.softeem.model.sys;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("account")
public class Account{
	@TableId()
	private Long uid;
	@TableField(strategy = FieldStrategy.NOT_EMPTY)
	private String name;
	@TableField(strategy = FieldStrategy.NOT_EMPTY)
	private Integer type;//注册账号类型,0: 游客登陆, 1: 手机号登陆, 2: 快速登陆, 3: 微信登陆
	@TableField(strategy = FieldStrategy.NOT_EMPTY)
	private Integer state;//状态, 0: 正常, 1: 删除, 2: 封号
	@TableField(strategy = FieldStrategy.NOT_EMPTY)
	private String phone;//手机号
	 @TableField(exist = false)
	private String recommend;//推荐信息
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRecommend() {
		return recommend;
	}
	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}
	
	
	
	

	
	
	
	
	
}
