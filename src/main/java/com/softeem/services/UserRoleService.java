package com.softeem.services;

import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.IService;
import com.softeem.model.sys.UserRole;
@Transactional
public interface UserRoleService extends IService<UserRole> {

}
