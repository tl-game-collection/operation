package com.softeem.mappers.sys;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.softeem.model.sys.Customer;
import com.softeem.model.sys.Permission;
import com.softeem.model.sys.Role;
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
	 Set<Permission> findPermissionsByRoleId(@Param("roles") Set<Role> roles);
	 List<Permission> findSysPermissions();
	 List<Permission> ListByParentId(Integer parent_id);
	 List<Permission> findSysPermissionsByUserId(Integer uid);
}
