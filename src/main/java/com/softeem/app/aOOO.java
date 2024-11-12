package com.softeem.app;


import com.alibaba.fastjson.JSONObject;
import com.softeem.baseConfig.Switch;
import com.softeem.baseEnum.EMoneyExpendType;
import com.softeem.baseEnum.EPlayerPrivilege;
import com.softeem.baseEnum.EPlayerPrivilegeLevel;
import com.softeem.config.AppConfig;
import com.softeem.model.room.RoomCardReq;
import com.softeem.model.sys.ModifyUserPrivilege;
import com.softeem.util.HttpUtil;
import com.softeem.util.MD5Util;

import net.sf.ehcache.config.ConfigError;

public class aOOO {
    

    public static int getValue(int level, EPlayerPrivilege privilege) {
        if (EPlayerPrivilege.NONE == privilege) {
            return 0;
        }
        if ((Switch.PLAYER_PRIVILEGE_VERIFY & privilege.getValue()) > 0) {
            return 999999;
        }
        int temp = level & (0x0f << (privilege.getIndex() * 4));
        temp >>= (privilege.getIndex() * 4);
        if (0 == temp) {
            temp = 1;
        }
        temp = 9 * privilege.getIndex() + temp;
        return EPlayerPrivilegeLevel.values()[temp].getValue();
    }
    
    
    public static String  getkey(int level, EPlayerPrivilege privilege) {

        int temp = level & (0x0f << (privilege.getIndex() * 4));
        temp >>= (privilege.getIndex() * 4);
        if (0 == temp) {
            temp = 1;
        }
        temp = 9 * privilege.getIndex() + temp;
        return EPlayerPrivilegeLevel.values()[temp].getPrivilege().getDesc();
    }
    
    
    //获取所有权限
    public static long getAllPrivilege(int level) {
        long value = 0;
        String a="";
        int temp = level;
        int index = 0;
        while (0 != temp) {
            int l = temp & 0x000f;
            if (l > 1) {
                value |= EPlayerPrivilegeLevel.values()[index * 9 + 1 - index + l].getPrivilege().getValue();
                
                
                a += EPlayerPrivilegeLevel.values()[index * 9 + 1 - index + l].getPrivilege().getDesc()+":";
                System.out.println(a);
            }

            ++index;
            temp >>= 4;
        }
        return value;
    }
    
    
    public synchronized static int  modifyPrivilege(int level, boolean force) {
        int privilege=0;//权限
        int index = 0;
        int curPrivilege = privilege;
        while (0 != level) {
            int temp = level & 0x000f;
            if (temp > 0) {
                if (force || (temp > (curPrivilege & 0x000f))) {
                    int bit = index * 4;
                    privilege &= (~(0x0f << bit));
                    privilege |= (temp << bit);
                }
            }
            level >>= 4;
            curPrivilege >>= 4;
            ++index;
        }
        
        return privilege;
  
    }
    
    
    
	public static void main(String[] args) {
		long i=EPlayerPrivilegeLevel.getAllPrivilege(63);
		long k=EPlayerPrivilegeLevel.getValue(63,EPlayerPrivilege.GROUP_MEMBER_NUM);
		System.out.println("权限等级1：" +i);
	
		 System.out.println("权限等级2：" +i);
		
		 EPlayerPrivilege[] p=EPlayerPrivilege.values();
	
		 
		 ModifyUserPrivilege req=new ModifyUserPrivilege(3301,63,"ss");
		  String sign=MD5Util.getMD5(req.getUid(),req.getLevel(),AppConfig.APP_KEY);
		  req.sign=sign;
		  
		 
		 String str=JSONObject.toJSONString(req);
		 
		 
		   int aaa=(int)(Math.pow(2,19)+Math.pow(2,17)+Math.pow(2,15)+Math.pow(2,11)+Math.pow(2,10)+Math.pow(2,8));
		   
		 
		   int d2=aOOO.modifyPrivilege(3,true);//设置权限
		   
		   getAllPrivilege(3);
		    
		   System.out.println("设置"+d2);
		   int dj=69904 ;
		 
		 //判断是否有权限
		     boolean a=EPlayerPrivilegeLevel.getValue(11, EPlayerPrivilege.GROUP_NUM) >0;
		     int b=aOOO.getValue(dj, EPlayerPrivilege.GROUP_NUM);//获取权限值
		     String c= aOOO.getkey(dj, EPlayerPrivilege.GROUP_NUM);//获取权限名称
		     
		     int d=aOOO.getValue(dj, EPlayerPrivilege.ARENA_NUM);//获取权限值
             String e= aOOO.getkey(dj, EPlayerPrivilege.ARENA_NUM);//获取权限名称
             
             int b1=aOOO.getValue(dj, EPlayerPrivilege.GROUP_MEMBER_NUM);//获取权限值
             String c1= aOOO.getkey(dj, EPlayerPrivilege.GROUP_MEMBER_NUM);//获取权限名称
             
             int b2=aOOO.getValue(dj, EPlayerPrivilege.SEARCH_PLAYER);//获取权限值
             String c2= aOOO.getkey(dj, EPlayerPrivilege.SEARCH_PLAYER);//获取权限名称
             
             
             
             System.out.println("等级:"+dj+ "----"+ c+":"+b+","  + e+":"+d+","+ c1+":"+b1+","+ c2+":"+b2);



//             req=new ModifyUserPrivilege(7617658,69904,"ss");
//              sign=MD5Util.getMD5(req.getUid(),req.getLevel(),AppConfig.APP_KEY_M);
//             req.sign=sign;
//             
             
             
             //uid + amount + type + key
             
             String sign1="";
             sign1=MD5Util.getMD5(5824763,2000,EMoneyExpendType.RUN_DONATE.getValue(),-1,1,AppConfig.APP_KEY);
             
             
             
             
             //7617658
             RoomCardReq re=new RoomCardReq(5824763, 2000, EMoneyExpendType.RUN_DONATE.getValue(), -1,1 ,sign1);
             
             String str1=JSONObject.toJSONString(re);
		 try {
            
		     
		     //String sDD=HttpUtil.sendPost("http://192.168.0.183:2301/v1/modifyUserPrivilege",str);
            //String g=HttpUtil.sendPost("http://47.114.44.20/user/v1/modifyUserPrivilege",str);
            
		     //String sDD=HttpUtil.sendPost("http://192.168.0.183:2301/v1/addUserMoneyByDaTang",str1);
		     String sDD1=HttpUtil.sendPost("http://api.ubcmeju.cn/user/v1/addUserMoneyByDaTang",str1);
		     
	        //String g=HttpUtil.sendPost("http://47.114.44.20/user/v1/modifyUserPrivilege",str);
               //System.out.println("ff"+sDD);
               System.out.println(sDD1);
                
            
            
            //String kk=HttpUtil.sendPost("http://47.114.44.20/user/v1/addUserMoneyByDaTang",str);
            
            //System.out.println(s);
            //System.out.println(g);
		     
        } catch (Exception gg) {
            // TODO Auto-generated catch block
             gg.printStackTrace();
        }
		 
		 
		
		
	}

}
