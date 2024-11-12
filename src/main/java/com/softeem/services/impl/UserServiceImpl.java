package com.softeem.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.softeem.mappers.release.CustomerMapper;
import com.softeem.mappers.sys.UserMapper;
import com.softeem.model.sys.Customer;
import com.softeem.model.sys.User;
import com.softeem.services.CustomerService;
import com.softeem.services.UserService;
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public User findByUserName(String userName) {
		
		return  userMapper.findByUserName(userName);
	}

	@Override
	public int insert(User user) {
		
		return  userMapper.insert(user);
	}

	@Override
	public int del(String username) {
		
		return userMapper.del(username);
	}

	@Override
	public List<User> listUserRole() {
		
		return userMapper.listUserRole();
	}
}
