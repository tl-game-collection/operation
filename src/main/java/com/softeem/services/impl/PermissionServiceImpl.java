 package com.softeem.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.softeem.config.MenuConfig;
import com.softeem.mappers.release.NoticeMapper;
import com.softeem.mappers.sys.PermissionMapper;
import com.softeem.model.sys.Notice;
import com.softeem.model.sys.Permission;
import com.softeem.model.sys.Role;
import com.softeem.model.sys.User;
import com.softeem.model.system.HomeInfo;
import com.softeem.model.system.LogoInfo;
import com.softeem.model.system.MenuKey;
import com.softeem.model.system.MenuVo;
import com.softeem.model.system.SysMenu;
import com.softeem.model.tree.CheckArr;
import com.softeem.model.tree.DTree;
import com.softeem.model.tree.DTreeResponse;
import com.softeem.model.tree.Status;
import com.softeem.services.NoticeService;
import com.softeem.services.PermissionService;
import com.softeem.util.TreeUtil;
@Service
public class PermissionServiceImpl   extends ServiceImpl<PermissionMapper,Permission> implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public Set<Permission> findPermissionsByRoleId(Set<Role> roles) {
       
        
        return permissionMapper.findPermissionsByRoleId(roles);
        
    }

    @Override
    public List<Permission> findSysPermissions() {
       
         return permissionMapper.findSysPermissions();
    }

	@Override
	public List<Permission> ListByParentId(Integer parent_id) {
		
		return permissionMapper.ListByParentId(parent_id);
	}

	@Override
	public Map<String, Object> menu() {
		    Map<String, Object> map = new HashMap<>(16);
	        List<SysMenu> menuList = new ArrayList<SysMenu>();
	        List<Permission> permison= permissionMapper.findSysPermissions();
	      
	        if(!permison.isEmpty()) {
	        	 for(Permission p :permison) {
	 	        	SysMenu m=new SysMenu();
	 	        	MenuKey mk=new MenuKey();
	 	        	mk.setId(p.getId());
	 	        	mk.setHref(p.getUrl());
	 	        	mk.setTitle(p.getName());
	 	        	m.setKey(mk);
	 	        	m.setPid(p.getParent_id());
	 	        	m.setIcon(p.getMenuIcon());
	 	        	m.setSort(p.getOrderNumber());
	 	        	m.setTarget("_self");
	 	        	menuList.add(m);
	 	        }
	 	        List<MenuVo> menuInfo = new ArrayList<>();
	 	        for (SysMenu e : menuList) {
	 	            MenuVo menuVO = new MenuVo();
	 	            menuVO.setId(e.getKey().getId());
	 	            menuVO.setPid(e.getPid());
	 	            menuVO.setHref(e.getKey().getHref());
	 	            menuVO.setTitle(e.getKey().getTitle());
	 	            menuVO.setIcon(e.getIcon());
	 	            menuVO.setTarget(e.getTarget());
	 	            menuInfo.add(menuVO);
	 	        }
	 	       map.put("menuInfo", TreeUtil.toTree(menuInfo, 0));
	        }
	       
	        HomeInfo homeInfo=new HomeInfo();
	        homeInfo.setHref(MenuConfig.HOMEINFO_HREF);
	        homeInfo.setTitle(MenuConfig.HOMEINFO_TITLE);
	        LogoInfo logoInfo=new LogoInfo();
	        logoInfo.setHref(MenuConfig.LOGOINFO_HREF);
	        logoInfo.setImage(MenuConfig.LOGOINFO_IMAGE);
	        logoInfo.setTitle(MenuConfig.LOGOINFO_TITLE);
	        map.put("homeInfo", homeInfo);
	        map.put("logoInfo", logoInfo);
	       
	        return map;
		
		
		
		
	}

	@Override
	public DTreeResponse getMenuTree() {
		 List<Permission> permissio= permissionMapper.findSysPermissions();
		 
		  DTreeResponse resp = new DTreeResponse();
		  Status s=new Status();
		  s.setCode(200);
		  s.setMessage("成功");
		  resp.setStatus(s);
		  DTree d = null;
		  List<DTree> dtrees = new ArrayList<DTree>();
		  CheckArr chek=new CheckArr();
		  chek.setChecked("0");
		  List<CheckArr> checkList=new ArrayList<CheckArr>();
		  checkList.add(chek);
	
		  for(Permission p:permissio) {
			  d=new DTree();
			  d.setId(p.getId()+"");
			  d.setParentId(p.getParent_id()+"");
			  d.setTitle(p.getName()); 
			  d.setCheckArr(checkList);
			  dtrees.add(d);
		  }
		  resp.setData(dtrees);
		return resp;
	}

	@Override
	public List<Permission> findSysPermissionsByUserId() {
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		return permissionMapper.findSysPermissionsByUserId(user.getUid()) ;
	}
	
	
	//用户菜单
	public Map<String, Object> getUserMenu() {
		    Map<String, Object> map = new HashMap<>(16);
	        List<SysMenu> menuList = new ArrayList<SysMenu>();
	        List<Permission> permison= findSysPermissionsByUserId();
	      
	        if(!permison.isEmpty()) {
	        	 for(Permission p :permison) {
	 	        	SysMenu m=new SysMenu();
	 	        	MenuKey mk=new MenuKey();
	 	        	mk.setId(p.getId());
	 	        	mk.setHref(p.getUrl());
	 	        	mk.setTitle(p.getName());
	 	        	m.setKey(mk);
	 	        	m.setPid(p.getParent_id());
	 	        	m.setIcon(p.getMenuIcon());
	 	        	m.setSort(p.getOrderNumber());
	 	        	m.setTarget("_self");
	 	        	menuList.add(m);
	 	        }
	 	        List<MenuVo> menuInfo = new ArrayList<>();
	 	        for (SysMenu e : menuList) {
	 	            MenuVo menuVO = new MenuVo();
	 	            menuVO.setId(e.getKey().getId());
	 	            menuVO.setPid(e.getPid());
	 	            menuVO.setHref(e.getKey().getHref());
	 	            menuVO.setTitle(e.getKey().getTitle());
	 	            menuVO.setIcon(e.getIcon());
	 	            menuVO.setTarget(e.getTarget());
	 	            menuInfo.add(menuVO);
	 	        }
	 	       map.put("menuInfo", TreeUtil.toTree(menuInfo, 0));
	        }
	       
	        HomeInfo homeInfo=new HomeInfo();
	        homeInfo.setHref(MenuConfig.HOMEINFO_HREF);
	        homeInfo.setTitle(MenuConfig.HOMEINFO_TITLE);
	        LogoInfo logoInfo=new LogoInfo();
	        logoInfo.setHref(MenuConfig.LOGOINFO_HREF);
	        logoInfo.setImage(MenuConfig.LOGOINFO_IMAGE);
	        logoInfo.setTitle(MenuConfig.LOGOINFO_TITLE);
	        map.put("homeInfo", homeInfo);
	        map.put("logoInfo", logoInfo);
	       
	        return map;
		
		
		
		
	}

}
