package com.softeem;

import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alibaba.fastjson.JSONObject;
import com.softeem.util.TreeUtil;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@MapperScan("com.softeem.mappers/*") // 扫码mapper包
public class AppMain {
	 public static void main(String[] args) {
	        SpringApplication.run(AppMain.class, args);
	 }
	 
}



