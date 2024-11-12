package com.softeem.model.room;

import java.math.BigInteger;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.softeem.model.base.Page;
@TableName("player")
public class Player extends Page {
	@TableId(type = IdType.AUTO)
	private BigInteger uid;
	private String name ;
	private String sex;//1男，0女
	private String createTimestamp;//创建时间
	private String lastLoginTime;//登录时间
	private String lastLogoutTime;//登出时间
	private String lastLoginIp;//登出ip
	private String money;//货币
	@TableField(exist = false)
	private String opmoney;
	private String clubUids;//群ids
	private BigInteger recharge;//总充值
	private int privilege;
	public BigInteger getUid() {
		return uid;
	}
	public void setUid(BigInteger uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCreateTimestamp() {
		return createTimestamp;
	}
	public void setCreateTimestamp(String createTimestamp) {
		this.createTimestamp = createTimestamp;
	}
	public String getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getLastLogoutTime() {
		return lastLogoutTime;
	}
	public void setLastLogoutTime(String lastLogoutTime) {
		this.lastLogoutTime = lastLogoutTime;
	}
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getClubUids() {
		return clubUids;
	}
	public void setClubUids(String clubUids) {
		this.clubUids = clubUids;
	}
	public BigInteger getRecharge() {
		return recharge;
	}
	public void setRecharge(BigInteger recharge) {
		this.recharge = recharge;
	}
    public String getOpmoney() {
        return opmoney;
    }
    public void setOpmoney(String opmoney) {
        this.opmoney = opmoney;
    }
    public int getPrivilege() {
        return privilege;
    }
    public void setPrivilege(int privilege) {
        this.privilege = privilege;
    }
    
    
	
	
	
	

}
