package com.softeem.services;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.IService;
import com.softeem.model.sys.Customer;
import com.softeem.model.sys.Permission;
import com.softeem.model.sys.User;
@Transactional
public interface UserService extends IService<User>  {
	User findByUserName(String userName);
    int insert(User user);
    int del( String username);
    
    List<User> listUserRole();
    
    
	
}
