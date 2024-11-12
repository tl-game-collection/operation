package com.softeem.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class JDBCTest {
	
	    public static final String URL = "jdbc:mysql://192.168.0.188:3306/game_ysy?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&failOverReadOnly=false&useSSL=true&serverTimezone=UTC";
	    public static final String USER = "root";
	    public static final String PASSWORD = "123456";
	    public static void main(String[] args) throws Exception {
	    	Calendar c1 = Calendar.getInstance();
	    	// 获得年份
	    	int year = c1.get(Calendar.YEAR);
	    	// 获得月份
	    	int month = c1.get(Calendar.MONTH) + 1;
	    	// 获得日期
	    	int date = c1.get(Calendar.DATE);
	    	// 获得小时
	    	int hour = c1.get(Calendar.HOUR_OF_DAY);
	    	// 获得分钟
	    	int minute = c1.get(Calendar.MINUTE);
	    	// 获得秒
	    	int second = c1.get(Calendar.SECOND);
	    	// 获得星期几（注意（这个与Date类是不同的）：1代表星期日、2代表星期1、3代表星期二，以此类推）
	    	int day = c1.get(Calendar.DAY_OF_WEEK);
	    	int monthDay=c1.get(Calendar.DAY_OF_MONTH);
	    	System.out.println(year);
	    	System.out.println(month);
	    	System.out.println(date);
	    	System.out.println(monthDay);
	    	List<Byte> a=new ArrayList<>();
	    	
	    	for(byte i=0;i<monthDay;i++) {
	    		a.add(i);
	    	}
	    }
}
