package com.softeem.controller.system;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softeem.model.sys.RolePermission;
import com.softeem.model.sys.User;
import com.softeem.model.sys.UserRole;
import com.softeem.services.UserRoleService;
import com.softeem.services.UserService;
import com.softeem.util.ResultData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;


@Controller
@ApiIgnore
public class UserController {
	
	  @Autowired
	    private UserService userService;
	  
	  @Autowired
	  
	  private UserRoleService userRoleService;
	    @ApiOperation("获取用户名单")
	    @GetMapping("api/getUsers")
	    @ResponseBody
	    public ResultData getListUser(User w) {
	        HashMap<String, Object> query=new HashMap<>();
	        if(w.getUid()!=null) {
	            query.put("id", w.getUid());
	        }
	        
	        if(StringUtils.isNotBlank(w.getUsername())) {
	            query.put("username", w.getUsername());
	        }
	        //List<User> UserList=(List<User>) userService.listByMap(query);
	        List<User> UserList=(List<User>) userService.listUserRole();
	        
	        
	      
	        ResultData result = ResultData.result("200", "成功",UserList, UserList.size());
	        return  result ;
	    } 
	     
	    @ApiOperation("保存用户")
	    @PostMapping("api/saveUser")
	    @ResponseBody
	    public ResultData SaveUser(@RequestBody User w) {
	    	User user = (User) SecurityUtils.getSubject().getPrincipal();
	    	
	    	if(w.getUid()!=null &&w.getUid()==1 && user.getUid()!=1  ) {//超级管理员不能被其他用户修改
	    		return ResultData.fail("200","您不是超级管理员用户，无法为修改超级管理员用户");
	    	}
	    	
	    	
	        userService.saveOrUpdate(w);
	        return ResultData.success();
	    }
	    
	    @ApiOperation("删除用户")
	    @PostMapping("api/deleteUser")
	    @ResponseBody
	    public ResultData deleteUser(User w) {
	        if(w.getUid()==1) {
	        	return ResultData.fail("200","不能删除超级管理员");
	        }
	        userService.removeById(w.getUid());
	        return ResultData.success();
	    }
	    
	    
	    
	    
	    @ApiOperation("设置角色权限")
	    @PostMapping("api/saveUserRole")
	    @ResponseBody
	    public ResultData saveRolePermission(@RequestBody List<UserRole> w) {
	  
	    	Map<String,Object> role=new HashMap<String,Object>();
	    	role.put("uid",w.get(0).getUid());
//	    	
    	User user = (User) SecurityUtils.getSubject().getPrincipal();
//	    	if(user.getUid()!=1 && w.get(0).getRole_id()==1) {
//	    		return ResultData.fail("200","您不是超级管理员，无法操作超级管理员角色");
//	    	}
	    	boolean delFalg=false;
	    	for(UserRole u: w) {
	    		if(u.getRole_id()==1) {
	    			delFalg=true;
	    		}
	    	}

	    	if(user.getUid()!=1 && delFalg) {
	    		return ResultData.fail("200","您不是超级管理员用户，无法为操作超级管理员用户");
	    	}
	    	
	    	
	    	
	    	if(w.get(0).getRole_id()==-1) {
	    		userRoleService.removeByMap(role);//为空 清除权限
	    	}else {
	    		userRoleService.removeByMap(role);//先清空权限，在设置
	    		userRoleService.saveOrUpdateBatch(w);//保存权限
	    	}
	    	
	        return ResultData.success();
	    }
	    
	    
	    
	
	
	
	
	
}
