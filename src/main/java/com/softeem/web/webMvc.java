package com.softeem.web;

import com.softeem.util.FileUtils;
import com.softeem.util.GlobalUtil;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class webMvc  extends WebMvcConfigurerAdapter  {

    private static String resourceDir="D:/uploads2/";//window
    private static String resourceDirl="/Users/orcode/";//linux
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
       String str= GlobalUtil.getValue("orcode.filepath");
        String b=str.substring(str.indexOf("/")+1);
        System.out.println(b);
        System.out.println("映射html:"+b);
        registry.addResourceHandler("/orcode/**").addResourceLocations("file:"+str);
       // registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        
	

        super.addResourceHandlers(registry);
    }
    
}