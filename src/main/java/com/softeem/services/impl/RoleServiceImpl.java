package com.softeem.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.softeem.mappers.release.CustomerMapper;
import com.softeem.mappers.sys.PermissionMapper;
import com.softeem.mappers.sys.RoleMapper;
import com.softeem.mappers.sys.UserMapper;
import com.softeem.model.sys.Customer;
import com.softeem.model.sys.Permission;
import com.softeem.model.sys.Role;
import com.softeem.model.sys.User;
import com.softeem.model.tree.CheckArr;
import com.softeem.model.tree.DTree;
import com.softeem.model.tree.DTreeResponse;
import com.softeem.model.tree.Status;
import com.softeem.services.CustomerService;
import com.softeem.services.RoleService;
import com.softeem.services.UserService;
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper,Role> implements RoleService {
	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private PermissionMapper permissionMapper;

	@Override
	public Set<Role> findRolesByUserId(Integer uid) {
		
		return roleMapper.findRolesByUserId(uid);
	}
	
	
   public List<Permission> findRolePermission(Role role){
	   
	   return roleMapper.findRolePermission(role);
   }
	
	
	

	public DTreeResponse getRolePermissionTree(Role role) {
		 List<Permission> permissio= permissionMapper.findSysPermissions();
		 DTreeResponse resp = new DTreeResponse();
		 List<Permission> rolePermissio =findRolePermission(role);
		
		 List<Integer> ids=new ArrayList<>();
		 
		 for(Permission p:rolePermissio) {
			ids.add(p.getId());
		 }
		  Status s=new Status();
		  s.setCode(200);
		  s.setMessage("成功");
		  resp.setStatus(s);
		  DTree d = null;
		  List<DTree> dtrees = new ArrayList<DTree>();
		  CheckArr unchek=new CheckArr();//不选中
		  unchek.setChecked("0");
		  List<CheckArr> uncheckList=new ArrayList<CheckArr>();
		  uncheckList.add(unchek);
		  
		  
		  CheckArr chek=new CheckArr();//选中
		  chek.setChecked("1");
		  List<CheckArr> checkList=new ArrayList<CheckArr>();
		  checkList.add(chek);
	
		  for(Permission p:permissio) {
			  d=new DTree();
			  d.setId(p.getId()+"");
			  d.setParentId(p.getParent_id()+"");
			  d.setTitle(p.getName()); 
			  if(ids.contains(p.getId())) {
				  d.setCheckArr(checkList);
			  }else {
				  d.setCheckArr(uncheckList);
			  }
			 
			  dtrees.add(d);
		  }
		  resp.setData(dtrees);
		return resp;
	}

	
}
