package com.softeem.mappers.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.softeem.model.sys.Permission;
import com.softeem.model.sys.User;
public interface UserMapper extends BaseMapper<User> {
	 	User findByUserName(String userName);
	    int insert(User user);
	    int del(@Param("username") String username);
	    
	    
	    List<User> listUserRole();

}
