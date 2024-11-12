package com.softeem.app;

import com.softeem.config.AppConfig;
import com.softeem.util.MD5Util;

public class TestMd5 {
	public static void main(String[] args) {
			  String sign = MD5Util.getMD5("1","2","3",AppConfig.APP_KEY);
	
		  System.out.println(sign);
		  
		  
	     
	}

}
