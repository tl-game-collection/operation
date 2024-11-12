package com.softeem.model.sys;

import java.util.HashSet;
import java.util.Set;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
@TableName("sys_role")
public class Role {
	@TableId(type = IdType.AUTO)
	    private Integer id;
	    private String role;
	    private String description;
	    private String available;
	    @TableField(exist = false)
	    private Set<User> users = new HashSet<>();
	    @TableField(exist = false)
	    private Set<Permission> permissions = new HashSet<>();
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getAvailable() {
			return available;
		}
		public void setAvailable(String available) {
			this.available = available;
		}
		public Set<User> getUsers() {
			return users;
		}
		public void setUsers(Set<User> users) {
			this.users = users;
		}
		public Set<Permission> getPermissions() {
			return permissions;
		}
		public void setPermissions(Set<Permission> permissions) {
			this.permissions = permissions;
		}
	    
	    
}
