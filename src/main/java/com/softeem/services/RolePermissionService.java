package com.softeem.services;

import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.IService;
import com.softeem.model.sys.RolePermission;
@Transactional
public interface RolePermissionService extends IService<RolePermission>  {

}
