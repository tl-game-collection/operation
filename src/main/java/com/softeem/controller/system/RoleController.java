package com.softeem.controller.system;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softeem.model.sys.Role;
import com.softeem.model.sys.RolePermission;
import com.softeem.model.sys.User;
import com.softeem.model.tree.DTreeResponse;
import com.softeem.services.RolePermissionService;
import com.softeem.services.RoleService;
import com.softeem.services.UserRoleService;
import com.softeem.util.ResultData;

import io.netty.handler.codec.http.HttpRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;


@Controller
@ApiIgnore
public class RoleController {
	
	  	@Autowired
	    private RoleService roleService;
	  	
	  
	  	@Autowired
	    private RolePermissionService rolePermissionService;
	    @ApiOperation("获取角色名单")
	    @GetMapping("api/getRoles")
	    @ResponseBody
	    public ResultData getListRole(Role w) {
	        HashMap<String, Object> query=new HashMap<>();
	        if(StringUtils.isNotBlank(w.getRole())) {
	            query.put("role",w.getRole());
	        }
	        List<Role> RoleList=(List<Role>) roleService.listByMap(query);
	        ResultData result = ResultData.result("200", "成功",RoleList, RoleList.size());
	        return  result ;
	    } 
	    
	    
	    @ApiOperation("获取角色名单")
	    @GetMapping("api/getRoleSelect")
	    @ResponseBody
	    public List<Role> getRoleSelec (Role w) {
	        HashMap<String, Object> query=new HashMap<>();
	        if(StringUtils.isNotBlank(w.getRole())) {
	            query.put("role",w.getRole());
	        }
	        List<Role> RoleList=(List<Role>) roleService.listByMap(query);
	    
	        return  RoleList;
	    } 
	     
	    @ApiOperation("保存角色")
	    @PostMapping("api/saveRole")
	    @ResponseBody
	    public ResultData saveRole(@RequestBody Role w) {
	    	User user = (User) SecurityUtils.getSubject().getPrincipal();
	    	if(w.getId()!=null && w.getId()==1 && user.getUid()!=1  ) {//超级管理员不能被其他用户修改
	    		return ResultData.fail("200","您不是超级管理员用户，无法为修改超级管理员角色");
	    	}
	    	
	        roleService.saveOrUpdate(w);
	        return ResultData.success();
	    }
	    
	    @ApiOperation("删除角色")
	    @PostMapping("api/deleteRole")
	    @ResponseBody
	    public ResultData deleteRole(Role w) {
	    	
	        if(w.getId()==1) {
	        	return ResultData.fail("200","不能删除超级管理员");
	        }
	        roleService.removeById(w.getId());
	        return ResultData.success();
	    }
	    
	    
	    @ApiOperation("设置角色权限")
	    @PostMapping("api/saveRolePermission")
	    @ResponseBody
	    public ResultData saveRolePermission(@RequestBody List<RolePermission> w) {
	    	//rolePermissionService.saveOrUpdate(w);
	    	Map<String,Object> role=new HashMap<String,Object>();
	    	role.put("role_id",w.get(0).getRole_id());
	    	
	    	User user = (User) SecurityUtils.getSubject().getPrincipal();
	    	if(user.getUid()!=1 && w.get(0).getRole_id()==1) {
	    		return ResultData.fail("200","您不是超级管理员，无法操作超级管理员角色");
	    	}
	    	
	    	if(w.get(0).getPermission_id()==-1) {
	    		rolePermissionService.removeByMap(role);//为空 清除权限
	    	}else {
	    		rolePermissionService.removeByMap(role);//先清空权限，在设置
	    		rolePermissionService.saveOrUpdateBatch(w);//保存权限
	    	}
	    	
	        return ResultData.success();
	    }
	    
	    
	    @ApiOperation("获取角色权限")
	    @RequestMapping("api/getRolePermissions")
	    @ResponseBody
	    public DTreeResponse getRolePermission( Role role,HttpServletRequest req) {
	    	String id=req.getParameter("id");
	    	System.out.println(id);
	        return roleService.getRolePermissionTree(role);
	    }
	    
	    
	    
	    
	    
	
	
	
	
	
}
