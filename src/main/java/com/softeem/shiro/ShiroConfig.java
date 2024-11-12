package com.softeem.shiro;
import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.softeem.mappers.sys.PermissionMapper;
import com.softeem.model.sys.Permission;
import com.softeem.services.PermissionService;

import javax.annotation.Resource;
import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

@Configuration
public class ShiroConfig {
    
  
    
    @Autowired
    private PermissionMapper permissionMapper;


    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     * Web应用中,Shiro可控制的Web请求必须经过Shiro主过滤器的拦截
     * @param securityManager
     * @return
     */
    @Bean(name = "shirFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager securityManager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //必须设置 SecurityManager,Shiro的核心安全接口
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //这里的/login是后台的接口名,非页面，如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        //这里的/index是后台的接口名,非页面,登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //未授权界面,该配置无效，并不会进行页面跳转
        //shiroFilterFactoryBean.setUnauthorizedUrl("/401.html");

        //自定义拦截器限制并发人数,参考博客：
        //LinkedHashMap<String, Filter> filtersMap = new LinkedHashMap<>();
        //限制同一帐号同时在线的个数
        //filtersMap.put("kickout", kickoutSessionControlFilter());
        //shiroFilterFactoryBean.setFilters(filtersMap);

        // 配置访问权限 必须是LinkedHashMap，因为它必须保证有序
        // 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 一定要注意顺序,否则就不好使了
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        
        //配置不登录可以访问的资源，anon 表示资源都可以匿名访问
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/tologin", "anon");
        filterChainDefinitionMap.put("/", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/lib/**", "anon");
        filterChainDefinitionMap.put("/page/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/images/**", "anon");
        filterChainDefinitionMap.put("/druid/**", "anon");
        filterChainDefinitionMap.put("/api/getSystemConfigs/**", "anon");//返回接口
        filterChainDefinitionMap.put("/orcode/**", "anon");//返回接口
        
        filterChainDefinitionMap.put("/generateQRCode/**", "anon");//返回接口
        filterChainDefinitionMap.put("/load/**", "anon");//返回接口
        filterChainDefinitionMap.put("/page/load/**", "anon");//返回接口
        filterChainDefinitionMap.put("/api/getCustomers/**", "anon");//返回接口
        
        //logout是shiro提供的过滤器
        filterChainDefinitionMap.put("/logout", "logout");
        
       
        //此时访问/userInfo/del需要del权限,在自定义Realm中为用户授权。
        //filterChainDefinitionMap.put("/userInfo/del", "perms[\"userInfo:del\"]");
        //其他资源都需要认证  authc 表示需要认证才能进行访问
        
        
        if(onPerssion(true)) {
            LinkedHashMap<String, String> sysDefinitionMap =getPermission();//系统权限
            if(!sysDefinitionMap.isEmpty()) {
                for(Map.Entry<String, String> entry : sysDefinitionMap.entrySet()){
                    String url = entry.getKey();
                    String permission = entry.getValue();
                    if(StringUtils.isNotBlank(url) && StringUtils.isNotBlank(permission)) {
                    
                    	
                    	filterChainDefinitionMap.put("/"+url,"perms["+permission+"]");
                    }
                    
                }
            }   
        }
        
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }
    
    

    


    /**
     * 配置核心安全事务管理器
     * @param shiroRealm
     * @return
     */
    @Bean(name="securityManager")
    public SecurityManager securityManager(@Qualifier("shiroRealm") ShiroRealm shiroRealm) {
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        //设置自定义realm.
        securityManager.setRealm(shiroRealm);
        //配置记住我 参考博客：
        //securityManager.setRememberMeManager(rememberMeManager());

        //配置 redis缓存管理器 参考博客：
        //securityManager.setCacheManager(getEhCacheManager());

        //配置自定义session管理，使用redis 参考博客：
        //securityManager.setSessionManager(sessionManager());

        return securityManager;
    }

//    /**
//     * 配置Shiro生命周期处理器
//     * @return
//     */
//    @Bean(name = "lifecycleBeanPostProcessor")
//    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
//        return new LifecycleBeanPostProcessor();
//    }

    /**
     *  身份认证realm; (这个需要自己写，账号密码校验；权限等)
     * @return
     */
    @Bean
    public ShiroRealm shiroRealm(){
        ShiroRealm shiroRealm = new ShiroRealm();
        return shiroRealm;
    }
    
    /**
     * 必须（thymeleaf页面使用shiro标签控制按钮是否显示）
     * 未引入thymeleaf包，Caused by: java.lang.ClassNotFoundException: org.thymeleaf.dialect.AbstractProcessorDialect
     * @return
     */
	/*
	 * @Bean public ShiroDialect shiroDialect() { return new ShiroDialect(); }
	 */
    
    public LinkedHashMap<String, String> getPermission(){
       LinkedHashMap<String, String> allPermission=new LinkedHashMap<>();
       List<Permission> sysPermission= this.permissionMapper.findSysPermissions();
       if(!sysPermission.isEmpty()) {
           for(Permission p:sysPermission) {
               allPermission.put(p.getUrl(),p.getPermission());
           }
       }
       return allPermission;
       
    }
    
    public boolean onPerssion(boolean falg) {
        return falg;
    }
    
    
    
    
}
