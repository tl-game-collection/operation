package com.softeem.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.softeem.model.sys.Permission;
import com.softeem.model.system.MenuKey;
import com.softeem.model.system.MenuVo;
import com.softeem.model.system.SysMenu;
import com.softeem.services.PermissionService;

import jdk.nashorn.internal.ir.annotations.Reference;

/**
 * 运营后台 菜单tree
 * @author ysy
 *
 */

public class TreeUtil {
	@Reference
	private PermissionService permissionService;
	
	
	
	public static List<MenuVo> toTree(List<MenuVo> treeList, Integer pid) {
        List<MenuVo> retList = new ArrayList<MenuVo>();
        for (MenuVo parent : treeList) {
            if (pid.equals(parent.getPid())) {
                retList.add(findChildren(parent, treeList));
            }
        }
        return retList;
    }
    private static MenuVo findChildren(MenuVo parent, List<MenuVo> treeList) {
        for (MenuVo child : treeList) {
            if (parent.getId().equals(child.getPid())) {
                if (parent.getChild() == null) {
                    parent.setChild(new ArrayList<>());
                }
                parent.getChild().add(findChildren(child, treeList));
            }
        }
        return parent;
    }
    
    
    
    
    public Map<String, Object> menu() {
        Map<String, Object> map = new HashMap<>(16);
        Map<String,Object> home = new HashMap<>(16);
        Map<String,Object> logo = new HashMap<>(16);
        List<SysMenu> menuList = new ArrayList<SysMenu>();
        List<Permission> permison=permissionService.findSysPermissions();
        
        
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
       home.put("title","首页");
        home.put("href","/page/welcome-1");//控制器路由,自行定义
        logo.put("title","后台管理系统");
        logo.put("image","/static/images/back.jpg");//静态资源文件路径,可使用默认的logo.png 
        map.put("homeInfo", "{title: '首页',href: '/ruge-web-admin/page/welcome.html'}}");
        map.put("logoInfo", "{title: 'RUGE ADMIN',image: 'images/logo.png'}");
        return map;
    }
    
    
    public static void main(String[] args) {
    	
    	
    	Map<String, Object> menu=new TreeUtil().menu();
    	
    	
    	
    	String menustr=JSONObject.toJSONString(menu);
    	System.out.println(menustr);
		
	}
    
  	
    
    
	
	
	
	
	

}
