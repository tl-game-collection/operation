package com.softeem.mappers.sys;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.softeem.model.sys.Permission;
import com.softeem.model.sys.Role;

public interface RoleMapper extends BaseMapper<Role> {
	Set<Role> findRolesByUserId(@Param("uid") Integer uid);
	List<Permission> findRolePermission(Role role);
}
