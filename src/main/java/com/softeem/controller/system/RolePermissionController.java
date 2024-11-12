package com.softeem.controller.system;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softeem.model.sys.RolePermission;
import com.softeem.services.RolePermissionService;
import com.softeem.util.ResultData;

import io.swagger.annotations.ApiOperation;

@Controller
public class RolePermissionController {
	
//	@Autowired
//    private RolePermissionService RolePermissionService;
//    @ApiOperation("获取角色权限名单")
//    @GetMapping("api/getRolePermissions")
//    @ResponseBody
//    public ResultData getListRolePermission(RolePermission w) {
//        HashMap<String, Object> query=new HashMap<>();
//        
//        List<RolePermission> RolePermissionList=(List<RolePermission>) RolePermissionService.listByMap(query);
//        ResultData result = ResultData.result("200", "成功",RolePermissionList, RolePermissionList.size());
//        return  result ;
//    } 
//     
//    @ApiOperation("保存角色权限")
//    @PostMapping("api/saveRolePermission")
//    @ResponseBody
//    public ResultData SaveRolePermission(@RequestBody RolePermission w) {
//        RolePermissionService.saveOrUpdate(w);
//        return ResultData.success();
//    }
//    
//    @ApiOperation("删除角色权限")
//    @PostMapping("api/deleteRolePermission")
//    @ResponseBody
//    public ResultData deleteRolePermission(RolePermission w) {
//         
//        RolePermissionService.removeById(w.getId());
//        return ResultData.success();
//    }
	

}
