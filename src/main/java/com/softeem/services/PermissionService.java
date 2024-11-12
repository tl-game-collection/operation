 package com.softeem.services;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.IService;
import com.softeem.model.sys.Customer;
import com.softeem.model.sys.Permission;
import com.softeem.model.sys.Role;
import com.softeem.model.tree.DTreeResponse;

@Transactional
 public interface PermissionService  extends IService<Permission> {
    Set<Permission> findPermissionsByRoleId(@Param("roles") Set<Role> roles);
    List<Permission> findSysPermissions();
    List<Permission> ListByParentId(Integer parent_id);
    Map<String, Object> menu();
    DTreeResponse getMenuTree();
    
    
    List<Permission> findSysPermissionsByUserId();
    
    Map<String, Object> getUserMenu();
    
    
    
   
    
    
    

    
    
    
    
    
    

}
