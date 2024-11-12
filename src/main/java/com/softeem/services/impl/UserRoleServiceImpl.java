package com.softeem.services.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.softeem.mappers.sys.UserMapper;
import com.softeem.mappers.sys.UserRoleMapper;
import com.softeem.model.sys.UserRole;
import com.softeem.services.UserRoleService;
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>  implements UserRoleService{

}
