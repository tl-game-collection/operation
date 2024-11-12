package com.softeem.util;

import java.net.URL;

import javax.annotation.Resources;

import com.google.common.base.MoreObjects;



public class FileUtils {
    public static  final String orcode="orcode";
    public static URL getResource(String resourceName) {
        ClassLoader loader = MoreObjects.firstNonNull(
         Thread.currentThread().getContextClassLoader(),
         Resources.class.getClassLoader());
         URL url = loader.getResource(resourceName);

         
    
        return url;
       }
    
}