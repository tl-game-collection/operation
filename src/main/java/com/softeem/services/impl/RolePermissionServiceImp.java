package com.softeem.services.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.softeem.mappers.sys.RolePermissionMapper;
import com.softeem.model.sys.RolePermission;
import com.softeem.services.RolePermissionService;

@Service
public class RolePermissionServiceImp extends ServiceImpl<RolePermissionMapper,RolePermission> implements RolePermissionService  {

}
