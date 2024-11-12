package com.softeem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.softeem.mappers.sys.PermissionMapper;
import com.softeem.mappers.sys.UserMapper;
import com.softeem.model.sys.Permission;
import com.softeem.model.sys.User;
import com.softeem.model.system.MenuKey;
import com.softeem.model.system.MenuVo;
import com.softeem.model.system.SysMenu;
import com.softeem.services.PermissionService;
import com.softeem.util.TreeUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OperationApplicationTests {
	
	@Autowired
	private PermissionService p;
	
	
	@Autowired
	private UserMapper user;
	public Map<String, Object> menu() {
        Map<String, Object> map = new HashMap<>(16);
        Map<String,Object> home = new HashMap<>(16);
        Map<String,Object> logo = new HashMap<>(16);
        List<SysMenu> menuList = new ArrayList<SysMenu>();
        List<Permission> permison= p.findSysPermissions();
        
        
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
        
        
    	String a=JSON.toJSONString(TreeUtil.toTree(menuInfo, 0));
    	System.out.println("fafffffffffffffffffffffafaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa:"+a);
       home.put("title","首页");
        home.put("href","/page/welcome-1");//控制器路由,自行定义
        logo.put("title","后台管理系统");
        logo.put("image","/static/images/back.jpg");//静态资源文件路径,可使用默认的logo.png 
        map.put("homeInfo", "{title: '首页',href: '/ruge-web-admin/page/welcome.html'}}");
        map.put("logoInfo", "{title: 'RUGE ADMIN',image: 'images/logo.png'}");
        return map;
    }
	
	
	
	
	@Test
	
	public void gg() {
		
		List<User> s=user.listUserRole();
		
		String ss=JSON.toJSONString(s);
		System.out.println(ss);
		
		
		

		
		
		
	}
	
	
	
	
	
	
	


}
