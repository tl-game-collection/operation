package com.softeem.app;

import java.io.File;
import java.io.FileNotFoundException;

import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;

import com.softeem.util.GlobalUtil;

public class a {
    public static void main(String[] args) throws FileNotFoundException {
       String a= new File(GlobalUtil.getValue("upload.path"),"appQRcode.png").getPath();
        System.out.println(a);
        
        
        
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();

        String path1 = ResourceUtils.getURL("classpath:").getPath();
        System.out.println(path +"："+path1);

     
    }
    
    
}