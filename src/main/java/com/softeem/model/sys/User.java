package com.softeem.model.sys;

import java.util.HashSet;
import java.util.Set;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
@TableName("user_info")
@ApiModel(value = "用户")
public class User {
	 	@TableId(type = IdType.AUTO)
	    private Integer uid;
	    private String username;
	    private String password;
	    private String name;
	    private String id_card_num;
	    private String state;
	    @TableField(exist = false)
	    private Set<Role> roles = new HashSet<>();
		public Integer getUid() {
			return uid;
		}
		public void setUid(Integer uid) {
			this.uid = uid;
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
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getId_card_num() {
			return id_card_num;
		}
		public void setId_card_num(String id_card_num) {
			this.id_card_num = id_card_num;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public Set<Role> getRoles() {
			return roles;
		}
		public void setRoles(Set<Role> roles) {
			this.roles = roles;
		}
	    
	    
}
