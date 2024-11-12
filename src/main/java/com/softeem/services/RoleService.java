package com.softeem.services;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.IService;
import com.softeem.model.sys.Customer;
import com.softeem.model.sys.Permission;
import com.softeem.model.sys.Role;
import com.softeem.model.sys.User;
import com.softeem.model.tree.DTreeResponse;
@Transactional
public interface RoleService extends IService<Role>  {
	Set<Role> findRolesByUserId( Integer uid);
	
	List<Permission> findRolePermission(Role role);
	
	DTreeResponse getRolePermissionTree(Role role);
	
	
	
	
}
