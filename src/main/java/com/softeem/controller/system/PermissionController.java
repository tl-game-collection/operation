package com.softeem.controller.system;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.softeem.model.sys.Permission;
import com.softeem.model.tree.DTreeResponse;
import com.softeem.services.PermissionService;
import com.softeem.util.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * 权限
 * 
 * @author MyPC
 * @date 2020/06/13
 */
@Api("权限")
@Controller
public class PermissionController {
    @Autowired
    private PermissionService permissionService;
    @ApiOperation("获取权限名单")
    @GetMapping("api/getPermissions")
    @ResponseBody
    public ResultData getListPermission() {
 
        List<Permission> data=permissionService.findSysPermissions();
        ResultData result = ResultData.result("0", "成功",data, data.size());
        return  result ;
    } 
    
    
    
    
    @ApiOperation("获取所有菜单")
    @GetMapping("api/getMenu")
    @ResponseBody
    public Map<String, Object>   getMenus() {
        return  permissionService.menu();
    } 
    
    
    @ApiOperation("获取用户菜单")
    @GetMapping("api/getUserMenu")
    @ResponseBody
    public Map<String, Object>   getUserMenus() {
        return  permissionService.getUserMenu();
    } 
    
    
    
    
    
    
    @ApiOperation("获取菜单树")
    @RequestMapping("api/getMenuTree")
    @ResponseBody
    public DTreeResponse  MenuTree() {
    	
    	
        return  permissionService.getMenuTree();
    } 
     
    @ApiOperation("保存权限")
    @PostMapping("api/savePermission")
    @ResponseBody
    public ResultData SaveWhite(@RequestBody Permission w) {

        permissionService.saveOrUpdate(w);
        return ResultData.success();
    }
    
    @ApiOperation("删除权限")
    @PostMapping("api/deletePermission")
    @ResponseBody
    public ResultData deletePermission(Integer id) {
    	if(permissionService.ListByParentId(id).isEmpty()) {
    		 permissionService.removeById(id);
    		 return ResultData.success();
    	}else {
    		 return ResultData.fail("200","请先删除子菜单");
    	}
    	
     
        
    }
}
